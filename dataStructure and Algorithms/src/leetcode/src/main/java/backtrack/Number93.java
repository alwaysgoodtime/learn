package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/restore-ip-addresses/
 *
 * @author goodtime
 * @create 2023-03-31 12:25
 */
public class Number93 {

    public static void main(String[] args) {
        List<String> result = new Solution93().restoreIpAddresses("101112");
        System.out.println(result.size());
    }
}

class Solution93 {

    ArrayList<String> result = new ArrayList<>();

    StringBuilder tmp = new StringBuilder();

    public List<String> restoreIpAddresses(String s) {

        if (s == null || s.equals("")) {
            return result;
        }

        backtracking(s, 0,0);
        return result;
    }

    private void backtracking(String s, int startIndex, int depth) {

        //终止条件
        if(depth == 4) {
            if (tmp.length() == s.length() + 4) {
                result.add(tmp.deleteCharAt(tmp.length() - 1).toString());
            }
            return;
        }

        //i - startIndex <= 3 剪枝
        for (int i = startIndex + 1; i <= s.length() && i - startIndex <= 3; i++) {
            String substring = s.substring(startIndex, i);
            if (isValid(substring)) {
                //处理函数
                int tmpLength = tmp.length();
                tmp.append(substring).append(".");
                //递归函数
                backtracking(s, i, depth + 1);
                //回溯
                tmp.delete(tmpLength, tmp.length());
            }
        }
    }

    /**
     * 是否小于等于255，且是否为0开头的1位数字
     *
     * @param substring
     * @return
     */
    private boolean isValid(String substring) {
        int num = Integer.parseInt(substring);
        return num <= 255 && (substring.charAt(0) != '0' || substring.length() == 1);
    }
}
