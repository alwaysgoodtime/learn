/**
 * @author goodtime
 * @create 2020-01-28 12:15 下午
 */
public class Number53 {
    public static void main(String[] args) {
        Solution53s solution53 = new Solution53s();
        char[] a = {'-', '1', 'E', '-', '1', '6'};
        boolean numeric = solution53.isNumeric(a);
        System.out.println(numeric);
    }
}

//思路，这种类似胶水程序，从数组从头到尾判断，只有四种情况：+（-）、.、e(E)、数字，
//对于+和-，只能出现在数字e（E）前面和开头；对于.只能出现在e（E）之前，所以也只能出现一次；
//对于e（E），只能出现一次，且前面要有数字
class Solution53 {
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        int count = 0;//e和E计数器
        int period = 0;//小数点计数器
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '+' || str[i] == '-') {
                if (i == 0) {
                    continue;
                } else if (str[i - 1] == 'e' || str[i - 1] == 'E') {
                    continue;
                } else {
                    return false;
                }
            } else if (str[i] >= 48 && str[i] <= 57) {//0-10
                continue;
            } else if (str[i] == 46) {
                if (i == 0) {
                    continue;
                } else if (str[i - 1] >= 48 && str[i - 1] <= 57 || str[i-1] == '+' || str[i-1] == '-') {
                    if (count == 0 && period == 0) {
                        period++;
                    } else {
                        return false;
                    }
                }
            } else if (str[i] == 'e' || str[i] == 'E') {
                if (i == 0 || i == str.length - 1) {
                    return false;
                } else if (str[i - 1] >= 48 && str[i - 1] <= 57 || str[i-1] == 46) {//注意：.后也可以不跟数字，也可以跟e
                    if (count == 0) {
                        count++;
                        continue;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }
}

//java中string类自带的正则表达式匹配方法
class Solution53s {
    public boolean isNumeric(char[] str) {
        String s = String.valueOf(str);
        boolean matches = s.matches("[\\+\\-]?\\d*(\\.\\d*)?([eE][\\+\\-]?\\d+)?");
        return matches;
    }
}


//        以下对正则进行解释:
//        [\\+\\-]?            -> 正或负符号出现与否
//        \\d*                 -> 整数部分是否出现，如-.34 或 +3.34均符合
//        (\\.\\d*)?           -> 如果出现小数点，那么小数点后面可以有数字；否则一起不出现
//        ([eE][\\+\\-]?\\d+)? -> 如果存在指数部分，那么e或E肯定出现，+或-可以不出现，
//        紧接着必须跟着整数；或者整个部分都不出现
//        */
