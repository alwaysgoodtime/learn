package huawei.src.main.java;

import java.util.Scanner;

/**
 * 描述
 * 计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）
 * 输入描述：
 * 输入一行，代表要计算的字符串，非空，长度小于5000。
 *
 * 输出描述：
 * 输出一个整数，表示输入字符串最后一个单词的长度。
 * @author goodtime
 * @create 2023-12-21 14:24
 */
// 注意类名必须为 Main， 不要有任何 package xxx 信息
// 记得用in.nextLine()，按行读取
public class 字符串最后一个单词的长度 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // 注意 hasNext 和 hasNextLine 的区别

        while (in.hasNext()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            System.out.println(getLast(s));
        }
    }

    public static int getLast(String str){
        String[] s = str.split(" ");
        return s[s.length - 1].length();
    }
}