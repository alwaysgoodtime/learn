package com.atguigu.scw.project.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.scw.enums.ProjectStatusEnume;
import com.atguigu.scw.project.bean.TReturn;
import com.atguigu.scw.project.constent.ProjectConstant;
import com.atguigu.scw.project.service.ProjectCreateService;
import com.atguigu.scw.project.vo.req.BaseVo;
import com.atguigu.scw.project.vo.req.ProjectBaseInfoVo;
import com.atguigu.scw.project.vo.req.ProjectRedisStorageVo;
import com.atguigu.scw.project.vo.req.ProjectReturnVo;
import com.atguigu.scw.vo.resp.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Slf4j
@Api("项目发起模块，项目创建的四个步骤")
@RequestMapping("/project/create")
@RestController
public class ProjectCreateController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ProjectCreateService projectCreateService;

    @ApiOperation("项目发起第1步-阅读同意协议")
    @GetMapping("/init")
    //发起协议时，设置隐藏域，将用户的accessToken发过来
    public AppResponse<Object> init(BaseVo vo) {

        try {
            //验证用户是否登录
            String accessToken = vo.getAccessToken();

            if (StringUtils.isEmpty(accessToken)) {
                AppResponse resp = AppResponse.fail(null);
                //有可能用户不是通过提交表单访问，这里能过滤
                resp.setMsg("请求必须提供accessToken值");
                return resp;
            }
            String memberId = redisTemplate.opsForValue().get(accessToken);
            if (StringUtils.isEmpty(memberId)) {
                //有可能存用户的session过了30分钟，或者过期了，在redis中已经清空，所以找不到memberId
                AppResponse resp = AppResponse.fail(null);
                //有可能用户不是通过提交表单访问，这里能过滤
                resp.setMsg("请先登录");
                return resp;
            }
            //上面的代码，其实就是在做权限控制，每一步都得写，我们可以引入SpringSecurity的权限框架
            //这样就能方便地给我们进行权限控制了，重复代码的书写次数也变少，其实这就是面向切面编程。


            //初始化大VO

            ProjectRedisStorageVo bigVo = new ProjectRedisStorageVo();

            //对拷+存入会员id
            BeanUtils.copyProperties(vo, bigVo);

            bigVo.setMemberid(Integer.valueOf(memberId));

            //项目的临时token
            //加入个常量类的前缀，到时候可以看key名知意，否则key名字太多，无法区分
            String porjectToken = UUID.randomUUID().toString().replaceAll("-", "");

            //注意：这里没有把redis中key的前缀放到bigVo中，只存了ProjectToken，后期取得时候需要拼串
            bigVo.setProjectToken(porjectToken);

            //fastjson转换vo,方便存入redis缓存中
            String bigStr = JSON.toJSONString(bigVo);


            //设置单次项目30分钟过期，需要重新申请，避免申请垃圾项目，一直不提交，占缓存的地方
            redisTemplate.opsForValue().set(ProjectConstant.TEMP_PROJECT_PREFIX + porjectToken, bigStr, 30L, TimeUnit.MINUTES);

            log.debug("大VO为{}", bigVo);

            return AppResponse.ok(bigVo);//此时也会把bigVo转换成json，不过用的是springMVC中集成的jackson组件
        } catch (Exception e) {
            e.printStackTrace();
            AppResponse resp = AppResponse.fail(null);
            resp.setMsg("服务器异常");
            return resp;
        }
    }


    @ApiOperation("项目发起第2步-保存项目的基本信息")
    @PostMapping("/savebaseinfo")
    public AppResponse<String> baseInfo(ProjectBaseInfoVo vo) {

        log.debug("项目基本信息为{}", vo);
        try {
            //验证用户是否登录
            String accessToken = vo.getAccessToken();

            if (StringUtils.isEmpty(accessToken)) {
                AppResponse resp = AppResponse.fail(null);
                //有可能用户不是通过提交表单访问，这里能过滤
                resp.setMsg("请求必须提供accessToken值");
                return AppResponse.fail(null);
            }
            String memberId = redisTemplate.opsForValue().get(accessToken);
            if (StringUtils.isEmpty(memberId)) {
                //有可能存用户的session过了30分钟，或者过期了，在redis中已经清空，所以找不到memberId
                AppResponse resp = AppResponse.fail(null);
                //有可能用户不是通过提交表单访问，这里能过滤
                resp.setMsg("请先登录");
                return resp;
            }

            //1、redis中之前存储的项目信息
            String orignal = redisTemplate.opsForValue().get(ProjectConstant.TEMP_PROJECT_PREFIX + vo.getProjectToken());


            //验证存的信息是否为null，如果为null，说明项目已经过期，需要重新提交项目
            if (StringUtils.isEmpty(orignal)) {
                AppResponse resp = AppResponse.fail(null);
                resp.setMsg("项目已过期，请重新开始");
                return resp;
            }

            //2、和redis存储对应的do,用fastjson实现
            ProjectRedisStorageVo bigVo = JSON.parseObject(orignal, ProjectRedisStorageVo.class);


            //验证此次accessToken对应的会员id，和之前redis中存储这个项目发起者的id是否一致
            if (!memberId.equals(bigVo.getMemberid() + "")) {
                log.debug("memberId为{}", memberId);
                log.debug("原来存的MemberId为", bigVo.getMemberid());
                AppResponse resp = AppResponse.fail(null);
                //第二步填写的会员id和第一步不一样，可能更换用户来提交了，这里比第一步要多一次过滤
                resp.setMsg("发起项目失败，请重新开始");
                return resp;
            }

            //3、将页面收集来的数据，转移到和redis映射的do中
            BeanUtils.copyProperties(vo, bigVo);

            //4、将这个再转换为json字符串，更新到redis
            String jsonString = JSON.toJSONString(bigVo);

            log.debug("项目基本信息保存：{}", jsonString);
            //更新同一个key的value值之后，过期时间会失效，所以需要重新设置
            redisTemplate.opsForValue().set(ProjectConstant.TEMP_PROJECT_PREFIX + vo.getProjectToken(), jsonString, 30, TimeUnit.MINUTES);

            return AppResponse.ok(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            //不要直接把报错的错误信息发给用户，如果这里不设置，前台可能就看到我们的错误信息了
            AppResponse resp = AppResponse.fail(null);
            log.debug("服务器异常为{}", e.getMessage());
            resp.setMsg("服务器异常");
            return resp;

        }
    }

    /**
     * SpringMVC绑定复杂对象
     * 1）[v]、List<ProjectReturnVo> vo：
     * 前端：就发ajax，在页面把这个数据转为json；将数据放在请求体中； k=v&k=v
     * [{},{},{}]===转对象
     * <p>
     * 2）[x]、SpringMVC如何将数据封装一个List
     * 方法签名位置的参数类型应该是这样：假设ProjectReturnVo{age,name}
     * Hello{
     * List<ProjectReturnVo> pro;
     * }
     * 页面这样提交
     * <input name="pro[0].name" value="zhangsan"/>
     * <input name="pro[0].age" value="18"/>
     * <input name="pro[1].name" value="lisi"/>
     * <input name="pro[1].age" value="50"/>
     * <input name="..." value=""/>
     * <input name=".." value=""/>
     * <input name="pro[n].properties1" value="value1"/>
     * <input name="pro[n].properties2" value="value2"/>
     *
     * @param
     * @return
     */

    @ApiOperation("项目发起第3步-项目回报信息填写")
    @PostMapping("/return")
    //这里写了requestbody注解，我们需要在前端提交的时候，提交一个json，方便我们队请求体中的数据进行解析
    public AppResponse<Object> saveReturnInfo(@RequestBody List<ProjectReturnVo> pro) {

        log.debug("项目回报信息为{}", pro);
        try {
            //验证用户是否登录,从一系列回报表单的第一个中找accesstoken(这样肯定能找到，因为回报信息要大于1个)
            String accessToken = pro.get(0).getAccessToken();

            if (StringUtils.isEmpty(accessToken)) {
                AppResponse resp = AppResponse.fail(null);
                //有可能用户不是通过提交表单访问，这里能过滤
                resp.setMsg("请求必须提供accessToken值");
                return AppResponse.fail(null);
            }

            String memberId = redisTemplate.opsForValue().get(accessToken);

            if (StringUtils.isEmpty(memberId)) {
                //有可能存用户的session过了30分钟，或者过期了，在redis中已经清空，所以找不到memberId
                AppResponse resp = AppResponse.fail(null);
                //有可能用户不是通过提交表单访问，这里能过滤
                resp.setMsg("请先登录");
                return resp;
            }

            //1、redis中之前存储的项目信息
            String orignal = redisTemplate.opsForValue().get(ProjectConstant.TEMP_PROJECT_PREFIX + pro.get(0).getProjectToken());


            //验证存的信息是否为null，如果为null，说明项目已经过期，需要重新提交项目
            if (StringUtils.isEmpty(orignal)) {
                AppResponse resp = AppResponse.fail(null);
                resp.setMsg("项目已过期，请重新开始");
                return resp;
            }

            //2、和redis存储对应的do,用fastjson实现
            ProjectRedisStorageVo bigVo = JSON.parseObject(orignal, ProjectRedisStorageVo.class);

            //验证此次accessToken对应的会员id，和之前redis中存储这个项目发起者的id是否一致
            if (!memberId.equals(bigVo.getMemberid() + "")) {
                AppResponse resp = AppResponse.fail(null);
                //第二步填写的会员id和第一步不一样，可能更换用户来提交了，这里比第一步要多一次过滤
                resp.setMsg("发起项目失败，请重新开始");
                return resp;
            }


            //3、将页面收集来的回报数据封装重新放入redis，因为传过来的是ProjectReturnVo,需要把其信息封装成TReturn对象。
            List<TReturn> returns = new ArrayList<>();
            for (ProjectReturnVo projectReturnVo2 : pro) {
                TReturn tReturn = new TReturn();
                BeanUtils.copyProperties(projectReturnVo2, tReturn);
                returns.add(tReturn);
            }
            //更新了return集合,如果我们直接先得到bigVo的List<TReturn>,然后往进放参数，就必须先给bigVo的这个参数new一个值，否则调用后为null，不能进行add
            bigVo.setProjectReturns(returns);
            //4、给redis中重新保存这个临时数据
            String jsonString = JSON.toJSONString(bigVo);
            redisTemplate.opsForValue().set(ProjectConstant.TEMP_PROJECT_PREFIX + pro.get(0).getProjectToken(), jsonString, 30, TimeUnit.MINUTES);
            log.debug("封装回报信息后的bigVo为{}", bigVo);
            return AppResponse.ok(bigVo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("服务器保存回报信息失败");
            AppResponse<Object> resp = AppResponse.fail(null);
            resp.setMsg("服务器保存回报信息失败");
            return resp;
        }
    }

    //这里省略了提交易付宝账号和法人身份证的两个参数


    @ApiImplicitParams({
            @ApiImplicitParam(name = "accessToken", value = "用户令牌", required = true),
            @ApiImplicitParam(name = "projectToken", value = "项目标识", required = true),
            @ApiImplicitParam(name = "ops", value = "用户操作类型   0-保存草稿按钮   1-提交审核按钮", required = true)
    })
    @ApiOperation("项目发起第4步-保存项目")
    @PostMapping("/submit")
    public AppResponse<Object> submit(String accessToken,
                                      String projectToken,
                                      String ops) {

        try {
            //1、前置校验
            if (StringUtils.isEmpty(accessToken)) {
                AppResponse resp = AppResponse.fail(null);
                //有可能用户不是通过提交表单访问，这里能过滤
                resp.setMsg("请求必须提供accessToken值");
                return AppResponse.fail(null);
            }

            String memberId = redisTemplate.opsForValue().get(accessToken);

            if (StringUtils.isEmpty(memberId)) {
                //有可能存用户的session过了30分钟，或者过期了，在redis中已经清空，所以找不到memberId
                AppResponse resp = AppResponse.fail(null);
                //有可能用户不是通过提交表单访问，这里能过滤
                resp.setMsg("请先登录");
                return resp;
            }

            //1、redis中之前存储的项目信息
            String orignal = redisTemplate.opsForValue().get(ProjectConstant.TEMP_PROJECT_PREFIX + projectToken);

            //验证存的信息是否为null，如果为null，说明项目已经过期，需要重新提交项目
            if (StringUtils.isEmpty(orignal)) {
                AppResponse resp = AppResponse.fail(null);
                resp.setMsg("项目已过期，请重新开始");
                return resp;
            }

            //2、和redis存储对应的do,用fastjson实现
            ProjectRedisStorageVo bigVo = JSON.parseObject(orignal, ProjectRedisStorageVo.class);

            //验证此次accessToken对应的会员id，和之前redis中存储这个项目发起者的id是否一致
            if (!memberId.equals(bigVo.getMemberid() + "")) {
                AppResponse resp = AppResponse.fail(null);
                //第二步填写的会员id和第一步不一样，可能更换用户来提交了，这里比第一步要多一次过滤
                resp.setMsg("发起项目失败，请重新开始");
                return resp;
            }

            if (!StringUtils.isEmpty(ops)) {
                if ("1".equals(ops)) {
                    //保存到数据库，提交审核
                    ProjectStatusEnume auth = ProjectStatusEnume.SUBMIT_AUTH;
                    projectCreateService.saveProjectInfo(auth, bigVo);
                    log.debug("提交审核{}成功", bigVo);
                    return AppResponse.ok(null);

                } else if ("0".equals(ops)) {
                    //保存到数据库，草稿状态
                    ProjectStatusEnume draft = ProjectStatusEnume.DRAFT;
                    projectCreateService.saveProjectInfo(draft, bigVo);
                    log.debug("保存草稿{}成功", bigVo);
                    return AppResponse.ok(null);
                } else {
                    //既不是0也不是1
                    AppResponse<Object> fail = AppResponse.fail(null);
                    fail.setMsg("不支持此操作");
                    return fail;
                }
            } else {
                AppResponse<Object> fail = AppResponse.fail(null);
                fail.setMsg("您尚未保存草稿或提交");
                return fail;
            }
        } catch (Exception e) {
            e.printStackTrace();
            AppResponse<Object> fail = AppResponse.fail(null);
            fail.setMsg("服务器错误");
            return fail;

        }


    }


}
