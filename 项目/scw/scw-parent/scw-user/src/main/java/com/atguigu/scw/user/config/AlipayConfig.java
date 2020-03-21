package com.atguigu.scw.user.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101800717229";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDNM54eBvvM1U0+" +
            "L7HHzyVyaE2SXbYbfI7x28F8n83ZknkAEnn4KSOaqV270e6u29numN2LY31sI3y+" +
            "uU8TeweigXerx8ddFNhNV0Mabln04wWJoBnl/qNf++9nmmrbqy83LCHGl6wtEIgk" +
            "Yir5aTV7tJOSCVGwrQOju7goY2zUxLc7esek3+dM9HtGuRUfs2AX9roADLhr9w5R" +
            "S4sxhlowMbChpLHt4HZA8jLvXYky8cQIJZfqB+Fpf+Qti6FbhFHhqQXBgx4q3X0K" +
            "KRZxYJcKSi3L3n1wBGHbNvELdVp12s/U6WZ9kK/d4XifqT4xYp5Xw2b3R603iRFM" +
            "nD5fHyEvAgMBAAECggEAdTviQAF5ZkMOyFJzXVZJbJ2Og+qqGfYFrMQCPPMHph/6" +
            "pUHpERBf2QKeHKoJlTjhv2DyYapLg7aalFRGizik2gDhn/CZQ+Ke2UDbg4Q8PJ5f" +
            "EYu4gCAEste6pRQhrLKWXQ0HlvAcW/qv7Slwp+GlvrEiBjLwyuFtzON4iQqUqNFc" +
            "YRXLweJ1LJLfdUe+hhEEETgcjAzOIxawGbqZSG6pxw3vPScUEtwokRejecIucCYP" +
            "dlGNscANv7q3M7umZFKtwHVJhP2cNKwMNFvXftTOo6qJpZ2rF49YfTRs+BZ754Qi" +
            "WX5/EFcGF5VELB75pmCS+KM9O5z76BxdBmIMtf7woQKBgQDseopXWhDF/xnDVxiJ" +
            "UBlSfSIo/NTVodIqF8ChVpsUBmbmNnXdI+TWSRy9/DKetgmRV8pe7o7pJh9cgD1p" +
            "48UjMusrIZWkqY3lpQwIJ06ruYsGO9yOMLyayaFn5np5oW+oMCWX5FkAHMuhIrcf" +
            "5sHTViKt9KlnYcK9zg1U2/LDOQKBgQDeJBr5ZRAJHt5Xjqc0+rF2g4qdz49/PXgU" +
            "NKfzgJX1IVRhn6nWpi54dDcruiW7ZAecZ1rhcWqrcQFx53UQpWYJsvI9uMe2EUYt" +
            "ZroNtd0nKlc4MIz4zBjjMX5+Nww3sKjXY/8qBTfOgQZWKLP/KL2/D2yHMDGeXq6J" +
            "2GfrMaz/pwKBgQDjEIbleUXB2EEUn/yfXJnz6tNyJ9L1jnwOjuAYCuC9SahkRMbH" +
            "oD+Kwokmo5K3cnOda36VyNrQZBqY50oQ8vPH6IyGzaiyg1wMtX3oFlYj7fTf93q/" +
            "zWxL9G9XJKdVQv3DsPdn8YrD4OJlxr+vkhNqqbqC7fn7mpMzTTbf1JI1gQKBgB8g" +
            "UgNGDPQqNh9wpq8+vWUM03x/W+0JUO0L38i36Ms7S1aOh77C9Fn+dsiSwby7LHHW" +
            "7Z4PRVIWeIxcUUPu2Rhc0aKiZKF3VC0CaumG5SEaxrPh4a7u8kHLap0G25hmEZJ0" +
            "uYlKx+IQpDGobGhBbiz6XUuoDhvRyoUYJJ+JC2ObAoGAaKariVoefN3k718ymLCL" +
            "K5i4WaOgh7KnHcSuVFoao65PcgymVueGIrArji6IyUxYjinzhIKW3x2tf/A8xDjl" +
            "YdRBCQJhAGh9QE8EM8Lc4arOePkcgdpHEGXsjAq2lusRPOUiTqtR/mhweldBf2j9" +
            "vNdBpngy06VaF5iEYhtvDus=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmaFjNwtmHej3o8HPP97RSTRjFb56lybkT6A04wiInix7kKC8s/Yd/otH1K6kEDNZZYhyx6WNcsGHDmPl5uj5LBdfgdyPyc0W0PSi2EA7AfoEuAYuu2aLnzy9NC3Q1IcNtAIw/FNhxh4TtqPiYkrfj22T2Zmeu4ruvhjn6LkyuAW4qRvVP/pv8K3uImIHTDKEHNoYgKq7+Vtvr5VR62IT795502TESsT161rXbHEhLN/rc7AWDqpMsCnlLxO/sHdnGynUYPkrgs0UlpVbZBDFB08tp3D6hBWrPkixKvzupJ+ikdlk12gna+dVaNIsVFR5nOKkXw6MObQrHRPHeSSKGwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://47.95.147.76:7080/alipay_trade_page_pay_JAVA_UTF_8_war_exploded/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://47.95.147.76:7080/alipay_trade_page_pay_JAVA_UTF_8_war_exploded/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

