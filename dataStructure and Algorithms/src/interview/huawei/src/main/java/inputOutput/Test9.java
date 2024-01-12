package interview.huawei.src.main.java.inputOutput;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2023-12-21 22:20
 */
public class Test9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            int row = scanner.nextInt();
            for (int i = 0; i < row; i++) {
                int column = scanner.nextInt();
                int sum = 0;
                for (int j = 0; j < column; j++) {
                   sum += scanner.nextInt();
                }
                System.out.println(sum);
                System.out.println();
            }
        }
    }
}
