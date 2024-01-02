package leetcode.src.main.java.math;


/**
 * https://leetcode.cn/problems/multiply-strings/description/
 *
 * @author goodtime
 * @create 2023-12-31 16:23
 */
public class Number43 {
    public static void main(String[] args) {
        System.out.println(new Solution43().multiply("123", "12"));
    }
}

/**
 * 将num2的每个位上的数字与num1相乘（竖式相乘），结果再相加
 *
 * 相加过程类似
 *
 * @see Number455
 */
class Solution43 {

    public String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        result.append(0);


        for (int i = num2.length() - 1; i >= 0; i--) {

            StringBuilder tmp = new StringBuilder();
            //高位进数
            int add = 0;
            int n2 = num2.charAt(i) - '0';
            for (int j = num1.length() - 1; j >= 0; j--) {
                int n1 = num1.charAt(j) - '0';
                int multiply = n1 * n2 + add;

                add = multiply / 10;
                multiply = multiply % 10;

                tmp.append(multiply);
            }

            //最后进数别忘记
            if (add != 0) {
                tmp.append(add);
            }

            //反转后再补0
            tmp.reverse();
            for (int j = i; j < num2.length() - 1; j++) {
                tmp.append(0);
            }

            result = addStrings(result.toString(), tmp.toString());

        }

        return result.toString();

    }

    public StringBuilder addStrings(String num1, String num2) {


        if (num1.length() > num2.length()) {
            String tmp = num2;
            num2 = num1;
            num1 = tmp;
        }


        int num1Length = num1.length();
        int num2Length = num2.length();

        StringBuilder stringBuilder = new StringBuilder();

        boolean addOne = false;

        int digit = 0;

        while (digit < num2Length) {
            int n2 = num2.charAt(num2Length - digit - 1) - '0';
            int n1 = num1Length - digit - 1 >= 0 ? num1.charAt(num1Length - digit - 1) - '0' : 0;
            int sum = n1 + n2;

            if (addOne) {
                sum++;
                addOne = false;
            }

            if (sum > 9) {
                addOne = true;
                stringBuilder.append(sum - 10);
            } else {
                stringBuilder.append(sum);
            }

            digit++;
        }

        //最后的进位别忘记
        if (addOne) {
            stringBuilder.append(1);
        }

        return stringBuilder.reverse();

    }

}
