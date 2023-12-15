package leetcode.src.main.java.slidingWindow;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * @author goodtime
 * @create 2023-12-02 04:28
 */
public class Number3 {

    public static void main(String[] args) {
        System.out.println(new Solution3().lengthOfLongestSubstring("abcab"));
    }
}

/**
 * 滑动窗口
 *
 * 用hashmap记录窗口中的字符
 *
 * 窗口从左往右，不重复，右边界往右1格，重复的话，左边界移至i+1格(其中n取决于前一个重复元素的下标i)，右边界往右一格
 */
class Solution3 {
    public int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> map = new HashMap<>(128);

        int leftWindow = 0;
        int rightWindow = 0;
        int lengthOfLongestSubstring = 0;

        while (rightWindow < s.length()) {
            char rightChar = s.charAt(rightWindow);

            if (!map.containsKey(rightChar)) {
                lengthOfLongestSubstring = Math.max(lengthOfLongestSubstring, rightWindow - leftWindow + 1);
            } else {
                //遇到重复，挪动左窗口，同时清空hashmap里存储的字符
                Integer leftIndex = map.get(rightChar);
                for (int i = leftWindow; i <= leftIndex; i++) {
                    map.remove(s.charAt(i));
                }
                //挪动左窗口到重复元素后一个位置
                leftWindow = leftIndex + 1;

            }

            //不论重复与否，右窗口继续向右移动1格，且把当前格放入窗口中
            map.put(rightChar, rightWindow);
            rightWindow++;
        }

        return lengthOfLongestSubstring;
    }
}
