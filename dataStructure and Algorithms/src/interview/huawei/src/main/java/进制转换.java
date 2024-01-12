package interview.huawei.src.main.java;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 *
 * 输入描述：
 * 输入一个十六进制的数值字符串。
 *
 * 输出描述：
 * 输出该数值的十进制字符串。不同组的测试用例用\n隔开。
 * @author goodtime
 * @create 2023-12-21 15:44
 */
public class 进制转换 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String input = in.nextLine();

            int digit = input.length() - 3;

            int output = 0;

            for(int i = 2; i < input.length(); i++){

                char c = input.charAt(i);

                if(c >= 48 && c <= 57){
                    output += Math.pow(2,digit*4)*(c - 48);
                }else{
                    output += Math.pow(2,digit*4)*(c - 65 + 10);
                }

                digit--;
            }

            System.out.println(output);

        }
    }
}
