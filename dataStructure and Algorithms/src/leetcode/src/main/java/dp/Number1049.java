package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/last-stone-weight-ii/
 *
 * @author goodtime
 * @create 2023-04-03 16:04
 */
public class Number1049 {

    public static void main(String[] args) {
        System.out.println(new Solution1048().lastStoneWeightII(new int[]{
                2, 7, 4, 1, 8, 1
        }));
    }

}

/**
 * 关键是要想到，把所有石头分成两堆，让其重量尽量相似
 */
class Solution1048 {
    public int lastStoneWeightII(int[] stones) {
        if (stones == null) {
            return 0;
        }

        if (stones.length == 1) {
            return stones[0];
        }

        int allCount = 0;

        for (int i = 0; i < stones.length; i++) {
            allCount += stones[i];
        }

        int halfCount = allCount / 2;



        int[] dp = new int[halfCount + 1];

        for (int i = 0; i < stones.length; i++) {//物品
            for (int j = halfCount; j >= stones[i]; j--) {//背包
                int noPut = dp[j];
                int put = dp[j - stones[i]] + stones[i];

                dp[j] = Math.max(noPut, put);
            }
        }

        //这个是推导出来的，其实是石头重的一堆减去轻的一堆
        return allCount - 2 * dp[halfCount];
    }
}