package interview.huawei.src.main.java;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2023-12-21 16:22
 */
public class 质数因子 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int input = in.nextInt();
            int sqrt = (int) Math.sqrt(input);
            for (int i = 2; i <= sqrt; i++) {
                while (input % i == 0) {
                    System.out.print(i + " ");
                    input = input / i;
                }
            }

            if (input != 1) {
                System.out.print(input + " ");
            }
        }

    }

}
