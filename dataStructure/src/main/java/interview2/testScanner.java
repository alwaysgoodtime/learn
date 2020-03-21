package interview2;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-02-20 5:45 下午
 */
public class testScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();//回车和空格，都能结束一个字符串，开始下一个字符串
        String next1 = scanner.next();
        System.out.println(next);
        System.out.println(next1);
    }
}
