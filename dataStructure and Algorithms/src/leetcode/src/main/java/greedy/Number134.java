package leetcode.src.main.java.greedy;

/**
 * @author goodtime
 * @create 2023-04-01 00:46
 */
public class Number134 {

    public static void main(String[] args) {
        System.out.println(
                new Solution134().canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }

}

/**
 * 想要贪心，那么就得花得少，存得多，所有加油站的油都能得到，但走完n个加油站，也会消耗n个路程
 * 的油耗，有一个路程的油耗是不会用的。
 * 有暴力解法，即从每个加油站出发一一尝试，如果有解总能试出来
 * <p>
 * 贪心算法，遍历整个加油站和油耗，如果差值<0,说明走完也不够油耗，可以返回-1
 * 如果差值>=0,则说明有方法走完整个路程，我们要找到后面不会被减成负数的最大子序列，并从它的下标开始走
 * 如果有重复的最大子序列和，也会从后面大的下标那个走（因为中间显然是油耗过大，使得行驶过程中变为了负数，子序列断开了）
 * <p>
 * <p>
 * 2.另一种思路，把第一个加油站视为出发点，如果油变成负数，说明不能从这个油站出发，而是从下个油站出发，同时油归零。
 * 这个也是贪心，假设从某个站出发有余油，后面即使有的站加油，有的站耗油，如果余油为正，说明也是收益大于损耗，
 *
 * 换句话说，如果0号加油站收益为正，到3号加油站收益变负，那么从1号加油站开始，到3号加油站收益一样变负，2号加油站也是如此，
 * 而到2号加油站收益为正，3号加油站收益变负，那么3号加油站肯定是油耗多存油少，也不用看。所以0-3号加油站都可以不用考虑了
 * 直接考虑从4号加油站起步即可。
 *
 */
class Solution134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null) {
            return 0;
        }

        if (gas.length == 1) {
            return gas[0] > cost[0] ? 0 : -1;
        }

        int max = 0;
        int val = 0;
        //(不会被减成负数的)最长子序列尾下标
        int index = 0;

        int sum = 0;

        for (int i = 0; i < gas.length; i++) {
            int remain = gas[i] - cost[i];
            sum += remain;


            //一旦总和小于0，直接可以清0，因为前面留的油，即使再多，也走不完这段路，全被耗完了（负数）
            if (val < 0) {
                max = 0;
                val = remain;
            } else {
                val += remain;
            }

            if (val >= max) {
                index = i;
                max = val;
            }

        }


        //说明油耗比油多，不可能完成环路
        if (sum < 0) {
            return -1;
        }

        int oil = 0;

        for (int i = index; i >= 0; i--) {
            int remain = gas[i] - cost[i];
            oil += remain;
            if (oil == max) {
                return i;
            }
        }

        return -1;

    }


    public int canCompleteCircuit2(int[] gas, int[] cost) {
        if (gas == null) {
            return 0;
        }

        if (gas.length == 1) {
            return gas[0] >= cost[0] ? 0 : -1;
        }

        int index = 0;

        int sum = 0;

        //把第一个加油站视为起点
        int curSum = 0;

        for (int i = 0; i < gas.length; i++) {
            int remain = gas[i] - cost[i];
            sum += remain;
            curSum += remain;

            //说明前面攒的油都被用完了，那么把下一个加油站作为起点，同时把油箱清0
            if (curSum < 0) {
                index = i + 1;
                curSum = 0;
            }

        }


        //说明油耗比油多，不可能完成环路
        if (sum < 0) {
            return -1;
        }


        return index;

    }
}