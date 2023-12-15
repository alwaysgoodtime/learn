package leetcode.src.main.java.hashmap;

import java.util.HashMap;

/**
 * @author goodtime
 * @create 2023-11-30 22:15
 */
public class Number242 {
    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        System.out.println(new Solution242().isAnagram(s, t));
    }
}

/**
 * hashmap key为字母，value为出现次数
 */
class Solution242 {
    public boolean isAnagram(String s, String t) {

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


            if (s1Value == null) {
                sMap.put(s1, 1);
            } else {
                sMap.put(s1, s1Value + 1);
            }

            if (t1Value == null) {
                tMap.put(t1, 1);
            } else {
                tMap.put(t1, t1Value + 1);
            }

        }

        for (int i = 0; i < s.length(); i++) {

            Character s1 = s.charAt(i);
            Integer s1Value = sMap.get(s1);
            Integer t1Value = tMap.get(s1);


            if (!s1Value.equals(t1Value)) {
                return false;
            }

        }

        return true;

    }
}
