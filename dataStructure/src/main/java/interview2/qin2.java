package interview2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-02-28 7:07 下午
 */
public class qin2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> k = new ArrayList<>();
        ArrayList<Integer> j = new ArrayList<>();

        while(sc.hasNext()) {
            k.add(sc.nextInt());//k为月数
        }

        for (Integer a: k
             ) {
            if(a <= 0){

                continue;
            }

            j.add(child(a));
        }

        for (Integer b: j
             ) {
            System.out.println(b);
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
