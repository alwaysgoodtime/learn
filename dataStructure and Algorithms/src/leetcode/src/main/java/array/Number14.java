package leetcode.src.main.java.array;

/**
 * https://leetcode.cn/problems/longest-common-prefix/
 *
 * @author goodtime
 * @create 2023-12-04 03:16
 */
public class Number14 {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(new Solution14().longestCommonPrefix(strs));
    }
}

/**
 * 因为是公共前缀，而非公共字符串，那么只需要一个元素一个元素从前往后比较即可
 */
class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        String longestCommonPrefix = strs[0];

        for (int i = 1; i < strs.length; i++) {

            int length = Math.min(longestCommonPrefix.length(), strs[i].length());

            if (length == 0) {
                return "";
            }

            for (int j = 0; j < length; j++) {

                if (longestCommonPrefix.charAt(j) != strs[i].charAt(j)) {
                    longestCommonPrefix = strs[i].substring(0, j);
                    break;
                }
            }

            if (strs[i].length() < longestCommonPrefix.length()) {
                longestCommonPrefix = strs[i];
            }

        }

        return longestCommonPrefix;
    }
}
