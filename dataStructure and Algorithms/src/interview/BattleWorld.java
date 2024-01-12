package interview;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-03-16 6:47 下午
 */
public class BattleWorld {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        int a = recur(i);
        System.out.println(a);
    }

    private static int recur(int i) {
        if(i == 0 || i == 1){
            return 1;
        }

        return recur(i-1) + recur(i-2);
    }
}
