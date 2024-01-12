package interview.huawei.src.main.java.inputOutput;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2023-12-21 22:15
 */
public class Test7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int sum = 0;
            int column = scanner.nextInt();
            for (int j = 0; j < column; j++) {
                sum += scanner.nextInt();
            }
            System.out.println(sum);
        }
    }
}
