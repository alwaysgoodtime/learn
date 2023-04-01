package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/palindrome-partitioning/
 *
 * @author goodtime
 * @create 2023-03-31 11:56
 */
public class Number131 {

    public static void main(String[] args) {

        List<List<String>> avc = new Solution131().partition("aab");
        System.out.println(avc.size());
    }

}

class Solution131 {

    ArrayList<List<String>> result = new ArrayList<>();

    ArrayList<String> strList = new ArrayList<>();

    StringBuilder tmp = new StringBuilder();

    public List<List<String>> partition(String s) {

        if (s == null || s.equals("")) {
            return result;
        }

        backtracking(s,0);
        return result;


    }

    private void backtracking(String s, int startIndex) {

        //终止条件
        if(tmp.length() == s.length()){
            result.add(new ArrayList<>(strList));
            return;
        }

        for (int i = startIndex + 1; i <= s.length(); i++) {
            String substring = s.substring(startIndex, i);
            if(isPalindrome(substring)){
                //处理函数
                int tmpLength = tmp.length();
                tmp.append(substring);
                strList.add(substring);
                //递归函数
                backtracking(s,i);
                //回溯
                tmp.delete(tmpLength,tmp.length());
                strList.remove(strList.size() - 1);
            }
        }



    }

    /**
     * 是否为回文数
     * @param substring
     * @return
     */
    private boolean isPalindrome(String substring) {
        return substring.equals(new StringBuilder().append(substring).reverse().toString());
    }


}
