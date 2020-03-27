package com.goodtime.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传前端的处理：
 * 1.method表单提交必须是post（get请求字符串长度有限，放不下）
 * 2.提供一个文件选择域<input type="file"/>
 * 3.form表单的enctype取值必须是：multipart/form-data 以前默认为：application/x-www-form-urlencoded
 *
 * 后台处理：可以依赖common-fileupload和commonss-io这两个jar包
 * @author goodtime
 * @create 2020-03-22 4:14 下午
 */
@Controller
public class FileUploadController {

    @RequestMapping("/file/upload1")
    public String fileUploadtranditional(HttpServletRequest request){
        //通过ServletContext拿到真实路径
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            //创建该文件夹
            file.mkdirs();
        }

        //解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        //开始解析request
        List<FileItem> fileItems = null;
        try {
            fileItems = fileUpload.parseRequest(request);//里面装的都是文件项
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        //遍历
        for (FileItem item: fileItems
             ) {
            //进行判断，它当前item对象是否是上传文件项
            if(item.isFormField()){
                //说明是普通表单项
            }else {
                //说明为上传文件项，取得文件名字
                String name = item.getName();
                //完成文件上传
                try {
                    item.write(new File(path, UUID.randomUUID().toString().replace("-","")+name));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //删除上传文件的临时文件（小于10k就在缓存中，大于10kb就会创建临时文件）
                item.delete();
            }
        }


        System.out.println("传统文件上传方式测试");
        return "success";
    }

    //spring单服务器上传
    //upload，必须和文件上传时，所设置的name值相同
    @RequestMapping("/file/upload2")
    public String fileUpload2(HttpServletRequest request,MultipartFile upload){
        System.out.println("spring上传方式测试");
        //通过ServletContext拿到真实路径
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            //创建该文件夹
            file.mkdirs();
        }
        //获取文件名字
        String originalFilename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-","");
        originalFilename = uuid + "_" + originalFilename;
        try {
            upload.transferTo(new File(path,originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }

    //spring跨服务器上传
    //upload，必须和文件上传时，所设置的name值相同
    @RequestMapping("/file/upload3")
    public String fileUpload3(MultipartFile upload){
        System.out.println("spring跨服务器上传方式测试");

        //定义上传服务器路径
        String path = "http://127.0.0.1:9090/uploads/";//注意，这里末尾有斜杠，没有斜杠的话，+originalFilename
        //时需要拼接

        //获取文件名字
        String originalFilename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-","");
        originalFilename = uuid + "_" + originalFilename;

        //与图片服务器进行连接，首先创建客户端对象
        Client client = Client.create();
        //和图片服务器的路径进行连接
        WebResource resource = client.resource(path + originalFilename);
        //文件上传
        try {
            System.out.println(resource);
            resource.put(upload.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }


}
