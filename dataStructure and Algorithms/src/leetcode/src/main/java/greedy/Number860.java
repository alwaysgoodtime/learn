package leetcode.src.main.java.greedy;

/**
 * https://leetcode.cn/problems/lemonade-change/
 *
 * @author goodtime
 * @create 2023-04-01 13:28
 */
public class Number860 {

    public static void main(String[] args) {
        System.out.println(new Solution860().lemonadeChange(new int[]{5, 5, 5, 10, 20}));

    }

}

/**
 * 常识题，因为面额小的值可以组成面额大的值，所以尽量用面额大的去找零
 * 5元 -> 获得一张零钱
 * 10元 -> 增加一张十元零钱，减少一张5元零钱
 * 20元 -> 20元是不能拿来找零15元的。如果有10元，优先找给顾客1张10元1张5元
 * 如果没有10元，给顾客3张5元
 */
class Solution860 {
    public boolean lemonadeChange(int[] bills) {

        int fiveCount = 0;
        int tenCount = 0;

        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                fiveCount++;
            } else if (bills[i] == 10) {
                tenCount++;
                fiveCount--;
            } else {
                if (tenCount > 0) {
                    tenCount--;
                    fiveCount--;
                } else {
                    fiveCount = fiveCount - 3;
                }
            }

            if (fiveCount < 0 || tenCount < 0) {
                return false;
            }
        }

        return true;
    }
}