package leetcode.src.main.java.array;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/h-index/
 *
 * @author goodtime
 * @create 2023-12-03 20:48
 */
public class Number274 {
    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        new Solution274().hIndex(citations);
    }
}

/**
 * 暴力解法是，先假设该作者的h指数为citations.length,看是否满足
 * 如果不满足，那么就将该值减1，看是否满足，直到发现h值满足为止
 *
 * 另一种贪婪解法：将其数字从小到大排序，排序后从前往后遍历，找到能满足h指数的最大数即可
 */
class Solution274 {

    public int hIndex(int[] citations) {

        if (citations == null || citations.length == 0) {
            return 0;
        }

        if (citations.length == 1) {
            return Math.min(1,citations[0]);
        }

        Arrays.sort(citations);

        int maxHIndex = 0;

        for (int i = 0; i < citations.length; i++) {

            if (citations[i] <= citations.length - i) {
                maxHIndex = Math.max(maxHIndex, citations[i]);
            } else {

                //防止0,1,4,5,6这种情况，此情形下hIndex = 3,而且后面的情况也不用看了
                maxHIndex = Math.max(maxHIndex, citations.length - i);

                break;
            }

        }

        return maxHIndex;

    }
}
