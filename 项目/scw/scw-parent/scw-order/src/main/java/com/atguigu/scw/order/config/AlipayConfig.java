package com.atguigu.scw.order.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class AlipayConfig{
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092300574219";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCTdjsB7J8gvuNG0soLSthYual1ebSloSAmV5EmzP9LhHCEKmX8ZEkBmcROlLg7EXwih1Oh+DqRI7depz0tcbqSKTwcLOslxEO6AJX1UfCKQXljssOjrTglKRgrPJWTiOrSJ6tjNcqRuswzt+hwf52dgyhsxBMubk2RMBtrwKsJbUwuS+/J3KWWBlwrbrxWnrtFRbrrrSbyQcObOrdiu9UpDYhZYIS3HcFhUXJL6EKizoIA2fMFC+bDAFSZfweXbrMo6CpHbLGL0Lhzcf/elHKEs1+MF3Gpuhln+DqbgMI1AnMDFH7r/zm+s71JZjEWmoAIM+oX+iwwRxTJ84wEexL7AgMBAAECggEALV4rq1zkXEu+WvTRKoY9Z5a/dyAZnveij4lTZA+f39kAUPlGZU9pUq+/QLnqsGmhunHOHj/jrPHDIcDsRoX986fyEW0Ub4shlyFFAbPTAIZCVZUTMdFCO9SX1f2Ou1Rdgoin5x8OkqVY2/SR88KfeG14y3XLvPY8sRWO6ijdtklMEn4oGh6mEVmsjio/N7bBHNGnKcqe+hAnZ1e3cDmPBAFYmaVcdzW01JJguQING2Huk9mLmf5ya5Xa2WW45+6PgFlnXKrx5NWKLR+qJ3EHQ0HUkLAhFq21VWF5wJ3f0+KmutgtOUu7yfl5Dp8Tfb6uuqDoGlittaQIcAAtwS84cQKBgQDkOzXAZEZ+GGUO3VRqkoCSF0X7/9GQ3zJBg7FXoCp2EjddmSixCZUQpzI2YYGbnCBr8C6wpwp3kzAtY8sXM8CN+6VjzSIMZIU22GX2N1VYOYdLh+r3tjsziM672SwebyCT2/ZuZV6f+TePQOBqxFzq/P69uL11dXsxdZLNPWaMaQKBgQClZ0UHG3RnjrKOyHlzGE8Or/0uaqKh7+B5bTYTMCOOYGiJm09NI2TCOCUiWkXeWh7/M5H2SGU/xwzziB8YBQWRZzI/g0Od6BYNxFAP6U3fXY45fWdt3dNHZIDe9aEa8hKk2BUwVl5PoJ+tzBXduSTHpe6ihcydj6Tr37olAWVHwwKBgDDNsHrJdoF0OffpwClh1nhm3aI7W9a8w4Ra4ss4Bpxk3M34+EiTIfsDpasXQp3BzvUF0RYZQhW7taufEUiO2ngB/aXPsZ4wyIOn1nXF80wAUTwHfmkl4JbuhDaLA/ImFTNI7/M/XfKbWhQZ7WMnRi9Blj9/h5PcYVF8ML4OiSUxAoGAZvOYa4wTMEIJsgXmUlVXH/U6yaFmCRWdyvbHKbpU51Hqv3wEKCdXl+wKcQ9vXa8KjB41Q+5b1w62aP7ZG8JEbPSmuhGiDK9gysDedIjrB5Tb6by8nA3ubAmKjTymQndaU3CL/8JCgZ2gxjS5h4Ct+/a0SkStz4SRSp++5TmxKLECgYAeWZv1INRSyupMARVSskpkIrbNyhgtsyZCQA0kpN0QUA3kyMOCap5ZOoXA2V4RuC3gXPqA+vL5hJP/WMdG2xb/d9DkhOYjwHGLY4UBWITYQwxAD8SP6rty0j5DwVIgNskHN4Vk6jncKe/wbQ41keFNkQtO+56hE873o8JmjXteYQ==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx2oXRv2jYeAJfolU/KtlQUAWFRM/qGngRIVm2RoTCrN+9VMVB0Ct9r7WqWNf5PmIG+BbnLypdNa8cxqZh+cacyi2r3GZ84bJwvF75QwRvmGKWTTnLjmpxXLTOdFFnKNZImYIqCJTGwt8cIO8LZWm/bfUKCaQKeyodZgfDUsCQmOqtj0rOucWhy2OEoSU2v9QYLrQxIOSfEPJp2PxvkU4LaK4fo3FKEnaZs5LHTs/ANHW2w5ef6HF8dmKUQLZEXABOnj95t+C5EhFALhQpAZU6JO/jUZy8nwEfIJOTB/+mc5epzSHyGaZgRBDlJmlL/wgiFqelnkDCZ7yxzD5MMCjOQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://c8ke66vo6w.bjhttp.cn/alipay.trade.page.pay-JAVA-UTF-8-servlet/NotifyUrl";
	
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//支付成功以后跳到哪里 http://tfkv0cljsb.51http.tech/
	public static String return_url = "http://c8ke66vo6w.bjhttp.cn/alipay.trade.page.pay-JAVA-UTF-8-servlet/ReturnUrl";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关  https://openapi.alipaydev.com/gateway.do 这是正式地址
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do"; //沙箱测试地址
	
}

