package leetcode.src.main.java.hashmap;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/isomorphic-strings/
 *
 * @author goodtime
 * @create 2023-11-30 21:50
 */

public class Number205 {

    public static void main(String[] args) {
        String s = "egg", t = "add";
        System.out.println(new Solution205().isIsomorphic(s, t));
    }

}

/**
 * hashmap key为字母，value为上一次出现的下标
 *
 * 注意：如果value记录的是出现次数，那么就无法区分"bbbaaaba"与"aaabbbba"
 */
class Solution205 {
    public boolean isIsomorphic(String s, String t) {

        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            Character s1 = s.charAt(i);
            Integer s1Value = sMap.get(s1);
            Character t1 = t.charAt(i);
            Integer t1Value = tMap.get(t1);

            if ((s1Value == null && t1Value != null) || (s1Value != null && t1Value == null)) {
                return false;
            }

            if(s1Value != null && !s1Value.equals(t1Value)){
                return false;
            }

            sMap.put(s1, i);
            tMap.put(t1, i);


        }

        return true;
    }
}
