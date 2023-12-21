package huawei.src.main.java;

import java.util.Scanner;

/**
 * 描述
 * 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
 *
 * 数据范围：
 * 1≤n≤1000
 * 输入描述：
 * 第一行输入一个由字母、数字和空格组成的字符串，第二行输入一个字符（保证该字符不为空格）。
 *
 * 输出描述：
 * 输出输入字符串中含有该字符的个数。（不区分大小写字母）
 * @author goodtime
 * @create 2023-12-21 14:33
 */
public class 计算某字符出现次数 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            String s  = in.nextLine();
            String s2 = in.nextLine();
            System.out.println(getCount(s,s2));
        }

    }

    /**
     * 注意不区分大小写
     */
    private static int getCount(String s, String s2){

        char a = s2.charAt(0);
        int result = 0;

        for(int i = 0 ; i < s.length() ; i++){
            char sChar = s.charAt(i);

            if(sChar >= 97 && sChar <= 122 && (sChar == a || sChar - a == 32)){
                //大写情况
                result++;
            }else if(sChar >= 65 && sChar <= 90 && (sChar == a || a - sChar == 32)){
                //小写情况
                result++;
            }else if(sChar >= 48 && sChar <= 57 && sChar == a){
                //数字情况
                result++;
            }

        }

        return result;
    }

}
