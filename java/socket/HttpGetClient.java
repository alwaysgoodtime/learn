package socket;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * 发送get请求
 * @author goodtime
 * @create 2024-01-24 13:52
 */
public class HttpGetClient {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.sina.com.cn/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setConnectTimeout(5000); // 请求超时5秒
            // 设置HTTP头:
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 11; Windows NT 5.1)");
            // 连接并发送HTTP请求:
            conn.connect();
            // 判断HTTP响应是否200:
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("bad response");
            }

            // 获取所有响应Header:
            Map<String, List<String>> map = conn.getHeaderFields();
            for (
                    String key : map.keySet()) {
                System.out.println(key + ": " + map.get(key));
            }

            // 获取响应内容:
            InputStream in = conn.getInputStream();
            byte[] data = new byte[1024];
            int len = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = in.read(data)) != -1) {
                bos.write(data, 0, len);
            }
            bos.close();
            in.close();
            String res = new String(bos.toByteArray(), StandardCharsets.UTF_8);
            System.out.println(res);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
