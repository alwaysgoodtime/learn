package leetcode.src.main.java.slidingWindow;

import java.util.*;

/**
 * https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
 *
 * @author goodtime
 * @create 2023-12-02 04:53
 */
public class Number30 {

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        System.out.println(new Solution30().findSubstring(s, words));
    }
}

/**
 * 第一种思路：
 *
 * 先将words所有的串联子串可能性罗列出来
 * 接着遍历每个子串k，问题转换为k是否为s的连续子串，就很简单了，可以使用KMP算法
 *
 * 第二种思路：把words中的每个元素看做字符，那么该问题和Number438其实是一个问题，可用滑动窗口来解决
 * 滑动窗口变为固定为words所有字符串加起来的长度，然后在s中从左往右滑
 *
 * 问题变为：与words所有字符串长度相同的s子串，是否可由words中的字符串元素拼接构成
 * 如果words中每个单词的长度都一致，该问题就非常好解决，用hashmap,key为words中的元素，value为其出现的次数，进行匹配即可
 * 就不用先罗列所有可能子串，再用contain来比较了
 *
 * @see Number438
 */
class Solution30 {
    public List<Integer> findSubstring(String s, String[] words) {

        int wordLength = words[0].length();
        int wordsStringLength = wordLength * words.length;
        int sLength = s.length();
        List<Integer> result = new ArrayList<>();

        if (sLength < wordsStringLength) {
            return new ArrayList<>();
        }

        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, Integer> matchMap = new HashMap<>();

        for (String word : words) {
            map.merge(word, 1, Integer::sum);
        }


        for (int i = 0; i <= sLength - wordsStringLength; i++) {

            boolean flag = true;
            for (int j = i; j < i + wordsStringLength; j = j + wordLength) {
                String needMatchWord = s.substring(j, j + wordLength);
                if (!map.containsKey(needMatchWord)) {
                    flag = false;
                    break;
                }

                matchMap.merge(needMatchWord,1,Integer::sum);
            }

            Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, Integer> next = iterator.next();

                Integer matchCount = matchMap.get(next.getKey());

                if (matchCount == null || !matchCount.equals(next.getValue())) {
                    flag = false;
                }

                //无论如何都重置matchMap
                matchMap.put(next.getKey(), null);
            }

            if (flag) {
                result.add(i);
            }
        }


        return result;
    }

    /**
     * 寻找words中所有可能的子串排列方式，用回溯即可
     */
    private void backtracking(String[] words, HashSet<String> sub, StringBuilder subString) {
        HashSet<String> subStringList = new HashSet<>();

        if (words.length == 1) {
            sub.add(subString.append(words[0]).toString());
            return;
        }

        for (int i = 0; i < words.length; i++) {
            //处理节点
            String word = words[i];
            int end = subString.length();
            subString.append(word);

            String[] subWords = new String[words.length - 1];
            for (int j = 1; j < word.length(); j++) {
                subWords[j - 1] = words[j];
            }
            //回溯函数
            backtracking(subWords, sub, subString);
            //恢复现场
            subString.delete(end, subString.length());
        }

    }
}
