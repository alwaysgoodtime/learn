package leetcode.src.main.java.slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/
 *
 * @author goodtime
 * @create 2023-12-02 05:18
 */
public class Number438 {

    public static void main(String[] args) {
        String s = "baa";
        String p = "aa";

        System.out.println(new Solution438().findAnagrams(s, p));
    }

}

/**
 *
 *
 * 先把p放到hashmap中，key为字母，value为出现次数
 *
 * 暴力解法是遍历s中每个字符，看以其开头的字符串是否为p的异位词
 *
 * 可用滑动窗口来解
 *
 * 1.如果s的该字符在p中出现次数未超过p中该字符的出现次数
 *
 * 同时维护一个匹配长度的值，如果匹配长度为p的长度，说明发现一个p的异位词，那么让左右窗口各自向右1格
 *
 * 如果匹配长度小于p，说明该滑动窗口长度不够，让右窗口继续向右1格
 *
 * 2.如果s的该字符在p中出现，但是超过p中该字符的出现次数，那么让右窗口向右1格，左窗口移动到该字符在该滑动窗口第一次出现的右1格
 * *
 * 给它一个机会，说不定还能匹配到
 *
 * 3.如果s的该字符在p中未出现，那么左窗口和右窗口直接移至该字符的下一个字符
 */
class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {

        if (p == null || s == null || p.length() == 0 || s.length() == 0) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();

        HashMap<Character, Integer> pMap = new HashMap<>(128);
        //记录s滑动窗口每个字符出现的次数
        HashMap<Character, Integer> sCountMap = new HashMap<>(128);

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            pMap.merge(c, 1, Integer::sum);
        }

        int leftWindow = 0;
        int rightWindow = 0;
        int matchLength = 0;
        int pLength = p.length();

        while (rightWindow < s.length()) {

            char sChar = s.charAt(rightWindow);
            Integer pCount = pMap.get(sChar);
            Integer sCount = sCountMap.get(sChar);

            rightWindow++;

            if (pCount == null) {

                leftWindow = rightWindow;
                matchLength = 0;
                //清空sCountMap
                sCountMap.clear();

            } else if ((pCount >= 1 && sCount == null) || pCount > sCount) {

                matchLength++;

                if (sCount == null) {
                    sCountMap.put(sChar, 1);
                } else {
                    sCountMap.put(sChar, sCount + 1);
                }

                if (pLength != matchLength) {
                    continue;
                }

                //如果发现p的异位字符串，leftWindow++,同时去掉该滑动窗口leftWindow所指的字符
                result.add(leftWindow);

                char leftWindowChar = s.charAt(leftWindow);
                Integer leftWindowCharCount = sCountMap.get(leftWindowChar);

                if (leftWindowCharCount != 1) {
                    sCountMap.put(leftWindowChar, leftWindowCharCount - 1);
                } else {
                    sCountMap.put(leftWindowChar, null);
                }

                leftWindow++;
                matchLength--;

            } else {

                //清理sCountMap，移动leftWindow，注意：因为新的重复的rightWindow没有加入，当sChar == s.charAt(leftWindow)时，就不清理了
                while (sChar != s.charAt(leftWindow)) {

                    char leftWindowChar = s.charAt(leftWindow);
                    Integer count = sCountMap.get(leftWindowChar);

                    if (count != null && count > 1) {
                        sCountMap.put(leftWindowChar, count - 1);
                    } else {
                        sCountMap.put(leftWindowChar, null);
                    }

                    leftWindow++;
                }

                leftWindow++;
                matchLength = rightWindow - leftWindow;
            }


        }

        return result;

    }
}
