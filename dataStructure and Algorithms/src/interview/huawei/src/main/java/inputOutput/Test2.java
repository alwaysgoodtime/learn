package interview.huawei.src.main.java.inputOutput;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2023-12-21 20:13
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int res = 0;
            for (int i = 1; i <= a; i++) {
                res += i;
            }

            System.out.println(res + " ");
        }

    }
}

