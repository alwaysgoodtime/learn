package cn.itcast.travel.util;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author goodtime
 * @create 2020-01-07 11:44 上午
 */
//aliyun封了25端口，网上新找了一个轮子
public class MailUtilss {
        private static JavaMailSenderImpl javaMailSender;
        private static String userName = "hjy516619223@163.com";
        static {
            javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost("smtp.163.com");// 链接服务器
            javaMailSender.setUsername("hjy516619223@163.com");// 账号
            javaMailSender.setPassword("xzliu700319");// 密码
            javaMailSender.setDefaultEncoding("UTF-8");
            Properties properties = new Properties();

            // 设置通过ssl协议使用587(qq邮箱)、465（163邮箱）、994(163邮箱)端口发送、使用默认端口（25）时下面几行都不需要
            properties.setProperty("mail.smtp.auth", "true");// 开启授权认证
//            properties.setProperty("mail.debug", "true");//便于调试
            properties.setProperty("mail.smtp.ssl.enable","true");
            properties.setProperty("mail.smtp.socketFactory.port", "465");// 设置ssl端口
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            javaMailSender.setJavaMailProperties(properties);
        }

        public static void sendEmail(final String title, final String content, final String toMail) {

            new Thread(new Runnable() {// 开启线程异步发送 防止发送请求时间过长
                @Override
                public void run() {
                    try {
                        if (!toMail.equals("")) {
                            System.out.println("在发了");//方便测试，后面删除
                            String[] toAddress = toMail.split(",");
                            MimeMessage message = javaMailSender.createMimeMessage();
                            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
                            helper.setFrom(userName);
                            helper.setTo(toAddress);
                            helper.setSubject(title);
                            helper.setText(content, true); // 内容
                            javaMailSender.send(message); // 发送邮件
                            System.out.println("发送成了");//方便测试，看到说明发送成功
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        //测试发送
        public static void main(String[] args) {
            sendEmail("ceshi", "wilaishishi", "hjy516619223@163.com");
        }
    }
