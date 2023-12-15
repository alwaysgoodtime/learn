package leetcode.src.main.java.hashmap;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/ransom-note/
 *
 * @author goodtime
 * @create 2023-11-30 21:35
 */
public class Number383 {

    public static void main(String[] args) {
        String ransomNote = "aa", magazine = "aab";
        System.out.println(new Solution383().canConstruct(ransomNote, magazine));
    }
}

/**
 * 哈希表 key为magazine中出现的字母，value为出现次数
 */
class Solution383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < magazine.length(); i++) {

            Character c = magazine.charAt(i);

            Integer value = map.get(c);
            if (value == null) {
                map.put(c, 1);
            } else {
                map.put(c, value + 1);
            }

        }

        for (int i = 0; i < ransomNote.length(); i++) {

            Character target = ransomNote.charAt(i);

            Integer value = map.get(target);
            if (value == null || value.equals(0)) {
                return false;
            } else {
                map.put(target, value - 1);
            }

        }

        return true;


    }
}
