package interview.huawei.src.main.java.inputOutput;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2023-12-21 22:17
 */
public class Test8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {

            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(a + b);
            System.out.println();

        }
    }
}
