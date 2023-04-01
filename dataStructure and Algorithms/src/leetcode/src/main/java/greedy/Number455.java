package leetcode.src.main.java.greedy;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/assign-cookies/
 *
 * @author goodtime
 * @create 2023-03-31 21:25
 */
public class Number455 {
    public static void main(String[] args) {
        int[] g = new int[]{1, 2, 3};
        int[] s = new int[]{1, 2, 3};
        System.out.println(new Solution455().findContentChildren(g, s));

    }
}

/**
 * 因为每个孩子只能拿一块饼干，所以不能尝试用两块饼干来满足一个孩子
 * 解法：总是用最小的饼干尝试去满足胃口最大的孩子，如果不行，再满足胃口次大的孩子，依次这样遍历
 */
class Solution455 {


    public int findContentChildren(int[] g, int[] s) {

        if (s == null || s.length == 0 || g == null || g.length == 0) {
            return 0;
        }

        //满足的人数
        int count = 0;

        //快排
        Arrays.sort(g);
        Arrays.sort(s);


        for (int i = 0; i < s.length; i++) {
            int cookie = s[i];
            for (int j = g.length - 1; j >= 0; j--) {
                if (g[j] <= cookie) {
                    count++;
                    g[j] = Integer.MAX_VALUE;
                    break;//满足孩子后记得退出循环
                }
            }
        }

        return count;
    }

}
