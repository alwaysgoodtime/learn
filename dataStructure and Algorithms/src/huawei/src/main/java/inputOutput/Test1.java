package huawei.src.main.java.inputOutput;

import java.util.Scanner;

/**
 * https://developer.aliyun.com/article/273406
 * A + B Problem
 * @author goodtime
 * @create 2023-12-21 20:10
 */
public class Test1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(a+b);
        }
    }
}