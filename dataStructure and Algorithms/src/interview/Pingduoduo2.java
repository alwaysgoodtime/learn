package interview;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-03-20 7:17 下午
 */
public class Pingduoduo2 {
    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        int length = a.nextInt();
        int[] target  = new int[length];
        for (int i = 0; i < length; i++) {
            target[i] = a.nextInt();
        }
        for (int i = 0; i < length; i++) {
          target[i] = take(target[i]);
        }
        for (int rt: target
             ) {
            System.out.println(rt);
        }
    }

    private static int take(int i) {
        if(i == 1){
            return 1;
        }
        if(i == 2){
            return 2;
        }
        return take(i/2) + 1;
    }
}
