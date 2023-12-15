package leetcode.src.main.java.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.cn/problems/group-anagrams/
 *
 * @author goodtime
 * @create 2023-11-30 22:28
 */
public class Number49 {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new Solution49().groupAnagrams(strs));
    }
}

/**
 * hashmap key为单词按照a-z排序后的顺序，value为单词组成的列表
 */
class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {



        if (strs == null || strs.length == 0) {
            return null;
        }


        List<List<String>> result = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();  //key为aet,value为单词列表
        int length = strs.length;

        for (int i = 0; i < length; i++) {

            String str = strs[i];
            int[] words = new int[26];

            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                words[c - 97]++;
            }

            String sortedWords = Arrays.toString(words);

            ArrayList<String> elements = map.get(sortedWords);

            if (elements != null) {
                elements.add(str);
            } else {
                ArrayList<String> ele = new ArrayList<>();
                ele.add(str);
                map.put(sortedWords, ele);
                result.add(ele);
            }

        }

        return result;

    }
}