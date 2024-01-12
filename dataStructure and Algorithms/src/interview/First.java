package interview;

import java.util.Scanner;

/**
 * 笔试大题第一题
 * @author goodtime
 * @create 2020-02-20 8:43 下午
 */
public class First {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = 0;
        int b = 0;
        int c = 0;
        int len = 0;
        while (in.hasNextInt()) {
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
            if (a > 255 || a < 128) {
                System.out.println("error");
                return;
            }
            while (a != 0) {
                if (a % 2 != 0) {
                    len++;
                    a = a >> 1;
                    continue;
                }
                a = a >> 1;

            }
            int result = b * len + c * (8 - len);
            int ae86 = len * 130 + 90 * (8 - len);
            if (result < ae86) {
                System.out.print("lost");
            } else if (result > ae86) {
                System.out.println("win");
            } else {
                System.out.println("deuce");
            }
        }
    }
}
