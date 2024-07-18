package socket;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 发送post请求
 *
 * @author goodtime
 * @create 2024-01-24 13:59
 */
public class HttpPostClient {

    private static final String USER_AGENT = "Mozilla/5.0";


    public static void main(String[] args) {


        try {
            String url = "https://selfsolve.apple.com/wcResults.do";
            URL obj = new URL(url);

            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //添加请求头
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

            //发送Post请求
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("-------------------------------------------------------");
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("-------------------------------------------------------");
            System.out.println("Response Code : " + responseCode);
            System.out.println("-------------------------------------------------------");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            //打印结果
            System.out.println(response.toString());
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
