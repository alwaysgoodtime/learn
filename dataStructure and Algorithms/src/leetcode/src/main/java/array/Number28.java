package leetcode.src.main.java.array;

/**
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 *
 * @author goodtime
 * @create 2023-12-04 04:43
 */
public class Number28 {

    public static void main(String[] args) {
        String haystack = "sadbutsad", needle = "sad";
        System.out.println(new Solution28().strStr(haystack, needle));
    }

}

/**
 * 普通匹配就可以，这里不写，用下KMP算法
 * 第一步：生成待匹配子串的NEXT数组，即needle
 * 第二步：让haystack与needle进行匹配
 *
 * @see classic.KMP
 */
class Solution28 {
    public int strStr(String haystack, String needle) {

        if (haystack == null || needle == null || haystack.length() == 0 || needle.length() == 0) {
            return 0;
        }

        //needle长度为1时，遍历haystack即可
        if (needle.length() == 1) {
            for (int i = 0; i < haystack.length(); i++) {
                if (haystack.charAt(i) == needle.charAt(0)) {
                    return i;
                }
            }
            return -1;
        }

        //第一步：生成待匹配子串的NEXT数组，即needle
        int[] next = new int[needle.length()];
        int fastPointer = 1;
        int slowPointer = 0;

        while (fastPointer < needle.length()) {

            //快慢指针所指的值不同，需要让慢指针回退，回退过程可以利用前面数值的next值，方便快速定位最大前缀
            while (needle.charAt(fastPointer) != needle.charAt(slowPointer) && slowPointer != 0) {
                slowPointer = next[slowPointer - 1];
            }

            //又找到了重复的值
            if (needle.charAt(fastPointer) == needle.charAt(slowPointer)) {
                //这里注意，其值为慢指针的下标+1
                next[fastPointer] = slowPointer + 1;
                slowPointer++;
            } else {
                //慢指针其实回到了开头
                next[fastPointer] = 0;
            }

            fastPointer++;

        }

        //第二步：开始进行匹配

        for (int j = 0, i = 0; j < needle.length() && i < haystack.length(); ) {

            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {

                if (j != 0) {
                    //这里就用到了next
                    j = next[j - 1];
                } else {
                    i++;
                }
            }

            //匹配成功
            if (j == needle.length()) {
                return i - needle.length();
            }

        }


        return -1;
    }
}
