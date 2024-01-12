package interview;
import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-02-28 5:37 下午
 */
public class QiAnXin1 {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        float k = 0;//取出硬币数
        while(sc.hasNext()){
            k = sc.nextInt();
            float l = 1000;//硬币
            float i = 10;//金币

            float rt = lool(k);

            //转化数字
            trans(rt);
        }
    }


    private static float lool(float k) {
        if(Math.abs(k-1) < 0.001){
            float s = 1.0f/100.0f;
            return s;
        }
        float s = lool(k-1) + cheng(k);
        return s;
    }

    private static float cheng(float k) {
        float s = 0.01f;
        for(float i = 1f,h = 0f;k>=2;k--) {
            s = s * (990.0f - h) /(1000.0f - i);
            h++;
            i++;
        }
        return s;
    }


    public static void trans(float s){

        String s1 = String.valueOf(s);
        while(s1.length() < 8){
            s1 = s1+"0";
        }
        if(Integer.valueOf(s1.indexOf(8)) >= 5){

        }
        System.out.println(s1.substring(0,8));
    }
}
