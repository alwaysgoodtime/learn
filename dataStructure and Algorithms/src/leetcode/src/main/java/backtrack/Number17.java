package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码组合问题
 * 难点在于7和9要对应4个字符
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 *
 * @author goodtime
 * @create 2023-03-30 19:24
 */
public class Number17 {
    public static void main(String[] args) {
        System.out.println(new Solution17().letterCombinations("93"));
    }

}


class Solution17 {

    ArrayList<String> result = new ArrayList<>();
    StringBuilder builder = new StringBuilder();

    //不拆数字版本
    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.equals("")) {
            return result;
        }

        backtracking(digits, 0);
        return result;
    }

    private void backtracking(String digits, int startIndex) {

        if (startIndex == digits.length()) {
            //收集结果
            result.add(builder.toString());
            return;
        }

        //处理节点
        int num = digits.charAt(startIndex) - '0';
        char[] numList = getStringList(num);
        for (int j = 0; j < numList.length; j++) {
            builder.append(numList[j]);
            //递归调用
            backtracking(digits, startIndex + 1);
            //回溯操作
            builder.deleteCharAt(builder.length() - 1);
        }

    }

    private char[]  getStringList(int num) {
        if(num == 2){
            return new char[]{'a','b','c'};
        }else if(num == 3){
            return new char[]{'d','e','f'};
        }else if(num == 4){
            return new char[]{'g','h','i'};
        }else if(num == 5){
            return new char[]{'j','k','l'};
        }else if(num == 6){
            return new char[]{'m','n','o'};
        }else if(num == 7){
            return new char[]{'p','q','r','s'};
        }else if(num == 8){
            return new char[]{'t','u','v'};
        }else {
            return new char[]{'w','x','y','z'};
        }
    }


    /**
     * 先拆数字版本
     * @param digits
     * @return
     */
    public List<String> letterCombinationss(String digits) {

        if (digits == null || digits.equals("")) {
            return result;
        }

        backtrackings(getStringNumber(digits), 0);
        return result;
    }

    private char[] getStringNumber(String digits) {

        int count = 0;
        for (int i = 0; i < digits.length(); i++) {
            char num = digits.charAt(i);
            if (num == '7' || num == '9') {
                count++;
            }
        }


        char[] chars = new char[digits.length() * 3 + count];

        for (int i = 0, index = 0; i < digits.length(); i++) {
            char num = digits.charAt(i);
            if (num < 55) {
                chars[index++] = (char) ((num - 50) * 2 + 47 + num);
                chars[index++] = (char) (chars[index - 2] + 1);
                chars[index++] = (char) (chars[index - 2] + 1);
            } else if (num == '7') {
                chars[index++] = 'p';
                chars[index++] = 'q';
                chars[index++] = 'r';
                chars[index++] = 's';
            } else if (num == '8') {
                chars[index++] = 't';
                chars[index++] = 'u';
                chars[index++] = 'v';
            } else if (num == '9') {
                chars[index++] = 'w';
                chars[index++] = 'x';
                chars[index++] = 'y';
                chars[index++] = 'z';
            }
        }

        return chars;
    }

    private void backtrackings(char[] digits, int startIndex) {

        if (startIndex == digits.length) {
            //收集结果
            result.add(builder.toString());
            return;
        }


        for (int i = startIndex, k = startIndex + 3; i < k; i++) {
            if (digits[i] == 'p' || digits[i] == 'w') {
                k++;
            }
            //处理节点
            builder.append(digits[i]);
            //递归调用
            backtrackings(digits, k);
            //回溯操作
            builder.deleteCharAt(builder.length() - 1);
        }

    }



}
