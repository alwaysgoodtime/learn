package huawei.src.main.java.inputOutput;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2023-12-21 22:08
 */
public class Test6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            int row = scanner.nextInt();
            int sum = 0;
            for (int i = 0; i < row; i++) {
                int column = scanner.nextInt();
                for (int j = 0; j < column; j++) {
                    sum += scanner.nextInt();
                }
                System.out.println(sum);
                sum = 0;
            }
        }
    }
}
