package com.atguigu.scw.project.component;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author goodtime
 * @create 2020-02-25 2:48 上午
 */
//@Component 如果用Bean注册，千万不能重复注册
@Slf4j
//如果没有data注解，就没有set方法，数据无法注入到OssTemplate中！！！！
@Data
public class OssTemplate {

    String endpoint;
    String accessKeyId;
    String accessKeySecret;
    String bucket;

    //在上传完图片后，我们需要返回图片在网络上的路径，以便后续对图片进行操作
    public String upload(String filename, InputStream inputStream) {
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucket, "pic/" + filename, inputStream);
            //https://alwaysgoodtime.oss-cn-beijing.aliyuncs.com/pic/p1.jpg

            String filepath = "https://" + bucket + "." + endpoint + "/pic/" + filename;

            log.debug("文件{}上传成功，路径为{}", filename, filepath);
            return filepath;

        } catch (Exception e) {
            log.debug("文件{}上传失败,请重新上传", filename);
            return null;
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }
}
