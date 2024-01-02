package leetcode.src.main.java.slidingWindow;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 *
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


        /**
         1、首先，判断当前字符是否包含在map中，如果不包含，将该字符添加到map（字符，字符在数组下标）,
         此时没有出现重复的字符，左指针不需要变化。此时不重复子串的长度为：i-left+1，与原来的maxLen比较，取最大值；

         2、如果当前字符 ch 包含在 map中，此时有2类情况：
         1）当前字符包含在当前有效的子段中，如：abca，当我们遍历到第二个a，当前有效最长子段是 abc，我们又遍历到a，
         那么此时更新 left 为 map.get(a)+1=1，当前有效子段更新为 bca；
         2）当前字符不包含在当前最长有效子段中，如：abba，我们先添加a,b进map，此时left=0，我们再添加b，发现map中包含b，
         而且b包含在最长有效子段中，就是1）的情况，我们更新 left=map.get(b)+1=2，此时子段更新为 b，而且map中仍然包含a，map.get(a)=0；
         随后，我们遍历到a，发现a包含在map中，且map.get(a)=0，如果我们像1）一样处理，就会发现 left=map.get(a)+1=1，实际上，left此时
         应该不变，left始终为2，子段变成 ba才对。

         为了处理以上2类情况，我们每次更新left，left=Math.max(left , map.get(ch)+1).
         另外，更新left后，不管原来的 s.charAt(i) 是否在最长子段中，我们都要将 s.charAt(i) 的位置更新为当前的i，
         因此此时新的 s.charAt(i) 已经进入到 当前最长的子段中！
         */


        while (rightWindow < s.length()) {
            char rightChar = s.charAt(rightWindow);

            if (map.get(rightChar) != null) {
                leftWindow = Math.max(map.get(rightChar) + 1, leftWindow);
            }

            //不论重复与否，右窗口继续向右移动1格，且把当前格放入窗口中
            lengthOfLongestSubstring = Math.max(lengthOfLongestSubstring, rightWindow - leftWindow + 1);
            map.put(rightChar, rightWindow);
            rightWindow++;
        }

        return lengthOfLongestSubstring;
    }
}
