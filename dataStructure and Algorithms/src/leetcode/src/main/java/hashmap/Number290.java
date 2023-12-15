package leetcode.src.main.java.hashmap;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/word-pattern/
 * @author goodtime
 * @create 2023-11-30 22:08
 */
public class Number290 {

    public static void main(String[] args) {
        String pattern = "abba", s = "dog cat cat dog";
        System.out.println(new Solution290().wordPattern(pattern,s));
    }
}

/**
 * hashmap key为字母/字符串，value为上一次出现的下标
 *
 */
class Solution290 {
    public boolean wordPattern(String pattern, String s) {

        if (pattern == null || s == null) {
            return false;
        }

        char[] chars = pattern.toCharArray();
        String[] ss = s.split(" ");

        if(chars.length != ss.length){
            return false;
        }


        HashMap<Character, Integer> pMap = new HashMap<>();
        HashMap<String, Integer> sMap = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {

            char pChar = chars[i];
            Integer pValue = pMap.get(pChar);
            String s1 = ss[i];
            Integer sValue = sMap.get(s1);

            if ((pValue == null && sValue != null) || (pValue != null && sValue == null)) {
                return false;
            }

            if(pValue != null && !pValue.equals(sValue)){
                return false;
            }

            pMap.put(pChar, i);
            sMap.put(s1, i);

        }

        return true;
    }
}
