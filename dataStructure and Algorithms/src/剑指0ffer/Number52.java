/**
 * @author goodtime
 * @create 2020-01-27 4:27 下午
 */
public class Number52 {
    public static void main(String[] args) {
        Solution52 solution52 = new Solution52();
        char[] a = {'a', 'b'};
        char[] b = {'a', '*', 'a', '*', 'b', 'c', '*'};
        boolean match = solution52.match(a, b);
        System.out.println(match);
    }
}

//思路：用需要判断的字符数组去判断通配的字符数组，如果字符数组能配上，就返回true，否则返回false
//分两种情况，一种是匹配后一位为*，一种是不为*，注意判断数组越界，以及i==length的情况。
class Solution52 {
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        return mat(str, 0, pattern, 0);
    }

    private boolean mat(char[] str, int i, char[] pattern, int j) {
        if (i == str.length && j == pattern.length) {
            return true;//结束、匹配成功
        } else if (i != str.length && j == pattern.length) {
            return false;//匹配失败
        }

        if (j <= pattern.length - 2 && pattern[j + 1] == '*') {//第二个是'*'
            //一定要注意i！=str.length这个条件，考虑如下情况：
            //   char[] a = {'a', 'b'};
            //   char[] b = {'a', '*', 'a', '*', 'b', 'c', '*'};
            if ((i != str.length && str[i] == pattern[j]) || (i != str.length && pattern[j] == '.')) {
                //三种匹配方式
                return mat(str, i + 1, pattern, j) || mat(str, i + 1, pattern, j + 2) || mat(str, i, pattern, j + 2);
            } else {
                // 正常往下匹配
                return mat(str, i, pattern, j + 2);
            }
        } else if(i != str.length && str[i] == pattern[j] || i != str.length && '.'== pattern[j]) {//第二个不是'*'
                return mat(str, i + 1, pattern, j + 1);//两个正常后移一位
        }else {
            return false;
        }
    }
}

