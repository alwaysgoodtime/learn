package leetcode.src.main.java.math;

/**
 * https://leetcode.cn/problems/add-strings/
 *
 * @author goodtime
 * @create 2023-12-31 15:52
 */
public class Number455 {
    public static void main(String[] args) {
        System.out.println(new Solution455().addStrings("123", "11"));
    }
}

/**
 * 字符串相加，让两个数字从个位加起即可
 */
class Solution455 {
    public String addStrings(String num1, String num2) {
        if (num1.equals("0")) {
            return num2;
        }

        if (num2.equals("0")) {
            return num1;
        }


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

        return stringBuilder.reverse().toString();

    }
}