package leetcode.src.main.java.slidingWindow;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/minimum-window-substring/
 *
 * @author goodtime
 * @create 2023-12-02 08:56
 */
public class Number76 {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(new Solution76().minWindow(s, t));
    }
}

/**
 * 滑动窗口，维持两个hashmap，查看s中每个字符出现的次数与t中的次数是否对应，这里用了matchLength与tLength的值来维护此对应关系
 *
 * 如果不对应，则右窗口一直往右滑1格
 *
 * 如果对应，则左窗口尝试往右滑至t中拥有的字符一格，再看是否满足t要求，直到无法满足t时，停止滑动左窗口，滑动右窗口1格
 */
class Solution76 {
    public String minWindow(String s, String t) {

        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }

        int leftWindow = 0;
        int rightWindow = 0;
        int sLength = s.length();
        int tLength = t.length();
        int matchLength = 0;
        String minWindow = "";

        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < tLength; i++) {
            tMap.merge(t.charAt(i), 1, Integer::sum);
        }

        while (rightWindow < sLength) {

            Character sChar = s.charAt(rightWindow);

            Integer tCount = tMap.get(sChar);

            rightWindow++;

            //该字符在t中未出现，直接往右滑
            if (tCount == null) {
                continue;
            }

            Integer sCharCount = sMap.get(sChar);
            sMap.merge(sChar, 1, Integer::sum);

            //该字符在t中出现，且滑动窗口中这个字符出现的次数与t中的次数不相等时，才视为一次匹配长度增加
            if (sCharCount == null || sCharCount < tCount) {
                matchLength++;
            }

            //匹配未完成，继续往右滑一格
            if (matchLength != tLength) {
                continue;
            }

            //如果匹配完成，而且leftWindow移动后，还能满足t，那么继续移动，直到无法满足t为止
            while (matchLength == tLength) {

                //匹配完成，如果左窗口所指的字符不在t中，先将其校正到该位置
                if (!tMap.containsKey(s.charAt(leftWindow))) {
                    leftWindow++;
                    continue;
                }

                //得到一种匹配结果
                if (minWindow.length() == 0 || minWindow.length() > rightWindow - leftWindow) {
                    minWindow = s.substring(leftWindow, rightWindow);
                }

                Character leftWindowChar = s.charAt(leftWindow);

                //匹配完成，让leftWindow继续右移1格，先减去sMap中当前字符出现的次数
                leftWindow++;
                Integer leftWindowCharCount = sMap.get(leftWindowChar);
                sMap.put(leftWindowChar, leftWindowCharCount - 1);

                //在这样情况下，如果此时无法满足t，也即滑动窗口中的字符数目不够t中的数目
                if (leftWindowCharCount - 1 < tMap.get(leftWindowChar)) {
                    matchLength--;
                }
            }


        }

        return minWindow;

    }

}