package huawei.src.main.java.recursion;

import java.util.Scanner;

/**
 * 3个空瓶换1汽水，允许向老板借，但必须还，用递归即可
 * @author goodtime
 * @create 2023-12-31 22:44
 */
public class 汽水瓶 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int input = in.nextInt();
            if (input != 0) {
                System.out.println(handle(input));
            }
        }
    }

    private static int handle(int input) {
        if (input < 2) {
            return 0;
        } else if (input == 2) {
            return 1;
        } else {
            return input / 3 + handle(input / 3 + input % 3);
        }

    }
}
