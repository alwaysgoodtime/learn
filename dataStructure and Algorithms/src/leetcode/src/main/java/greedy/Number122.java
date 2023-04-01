package leetcode.src.main.java.greedy;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 * 股票问题一般用动态规划解决，只是这道题正好可以用贪心算法
 * @author goodtime
 * @create 2023-03-31 23:09
 */
public class Number122 {

    public static void main(String[] args) {
        System.out.println(new Solution122().maxProfit(new int[]{7,1,5,3,6,4}));
    }

}

/**
 * 贪心算法解决，第一天就买，第二天如果价格高，就卖，然后再买回来，等后续再卖
 * 第二天如果价格低，就当第一天的没买（或者第一天末尾就原价卖了），然后在第二天买
 *
 * 抽象后就是，每天都要买卖昨天买的股票，赚了就卖出赚钱，再买入
 * 亏了就当昨天没买，今天再买
 *
 * 每天都赚钱（局部最优），最后总得来看，就能赚最多的钱（全局最优）
 */
class Solution122 {
    public int maxProfit(int[] prices) {

        if(prices == null || prices.length == 0){
            return 0;
        }

        int money = 0;
        //第一天没买过，所以需要提前买好
        int buy = prices[0];
        int sale = 0;

        for (int i = 0; i < prices.length; i++) {

            sale = prices[i];

            if (sale > buy) {
                money += (sale - buy);
            }

            buy = prices[i];

        }

        return money;

    }
}