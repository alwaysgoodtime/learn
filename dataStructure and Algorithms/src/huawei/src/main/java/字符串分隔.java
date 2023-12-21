package huawei.src.main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 描述
 *
 * •输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
 *
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * 输入描述：
 *
 * 连续输入字符串(每个字符串长度小于等于100)
 *
 * 输出描述：
 *
 * 依次输出所有分割后的长度为8的新字符串
 *
 * @author goodtime
 * @create 2023-12-21 15:34
 */
public class 字符串分隔 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            List<String> list = new ArrayList<>();
            int n = s.length();
            for (int i = 0; i < n; i += 8) {
                if (i + 8 < n) {
                    list.add(s.substring(i, i + 8));
                } else {
                    String s2 = s.substring(i, n);
                    while (s2.length() < 8) {
                        s2 += '0';
                    }
                    list.add(s2);
                }
            }

            for (int j = 0; j < list.size(); j++) {
                System.out.println(list.get(j));
            }
        }
    }


}
