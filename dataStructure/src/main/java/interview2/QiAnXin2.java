package interview2;


import java.util.Scanner;

/**兔子繁衍
 * @author goodtime
 * @create 2020-02-28 6:34 下午
 */
public class QiAnXin2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int k = 0;
        while(sc.hasNext()){
            k = sc.nextInt();//k为月数

            if(k <= 0){
                System.out.println(0);
                continue;
            }

            int i = child(k);

            System.out.println(i);
        }

    }

    private static int child(int k) {
        if(k <= 4){
            return 1;
        }else {
           int m =  child(k-4)+child(k-1);
           return m;
        }
    }
}
