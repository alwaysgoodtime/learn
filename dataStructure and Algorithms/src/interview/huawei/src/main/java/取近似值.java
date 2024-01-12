package interview.huawei.src.main.java;

import java.util.Scanner;

/**
 * 描述
 * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于 0.5 ,向上取整；小于 0.5 ，则向下取整。
 *
 * 数据范围：保证输入的数字在 32 位浮点数范围内
 * @author goodtime
 * @create 2023-12-21 16:29
 */
public class 取近似值 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextDouble()) { // 注意 while 处理多个 case
            Double a = in.nextDouble();
            System.out.println((int) (a + 0.5));
        }
    }
}