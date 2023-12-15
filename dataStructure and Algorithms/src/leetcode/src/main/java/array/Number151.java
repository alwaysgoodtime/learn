package leetcode.src.main.java.array;

import java.util.ArrayList;

/**
 * https://leetcode.cn/problems/reverse-words-in-a-string/
 *
 * @author goodtime
 * @create 2023-12-04 03:35
 */
public class Number151 {
    public static void main(String[] args) {
        System.out.println(new Solution().reverseWords("a good   example"));
    }
}

/**
 * 先切出单词来，再做反转
 */
class Solution {
    public String reverseWords(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }

        ArrayList<String> list = new ArrayList<>();

        StringBuilder word = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && word.length() != 0) {

                list.add(word.toString());
                word.delete(0, word.length());

            } else if (s.charAt(i) != ' ') {
                word.append(s.charAt(i));
            }
        }

        if (word.length() != 0) {
            list.add(word.toString());
        }

        word.delete(0, word.length());

        for (int i = list.size() - 1; i >= 0; i--) {
            word.append(list.get(i));
            word.append(" ");
        }

        word.delete(word.length() - 1, word.length());

        return word.toString();
    }
}
