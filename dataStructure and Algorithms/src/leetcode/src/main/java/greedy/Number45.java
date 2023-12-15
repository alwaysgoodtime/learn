package leetcode.src.main.java.greedy;

/**
 * https://leetcode.cn/problems/jump-game-ii/
 *
 * @author goodtime
 * @create 2023-03-31 23:52
 */
public class Number45 {
    public static void main(String[] args) {
        System.out.println(new Solution45().jump(new int[]{1, 2, 3}));
    }
}

/**
 * 找到一步能到达终点的最远的那个点，然后找能到达那个点的最远的点
 *
 * 为什么这样能得到最短步数？
 *
 * 举例[2,3,1,1,4],一步能到达4的最远点是3，虽然2到1，再到1，最后到4
 * 也可以实现这个过程，但肯定没有从3到4步数少。
 *
 * 为什么前面的路一定能到3？ 因为从前面能去1或更后面那个1的格子，一定会经过3，也就一定能到3
 * 从路数上来说，到3是最经济的，也是一定有解的。(当然，如果从第一个格子到不了最后的格子，那就没有
 * 解了。)
 */
class Solution45 {
    public int jump(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int step = 0;
        int target = nums.length - 1;
        int lastStep = target;

        while (lastStep != 0) {
            for (int i = target - 1; i >= 0; i--) {
                if (i + nums[i] >= target) {
                    lastStep = i;
                }
            }
            step++;
            target = lastStep;
        }

        return step;


    }
}

/**
 * 另一种解法，从前往后走，每一步都追求下一步能走最远，也是贪婪算法
 */
class Solution45s {
    public int jump(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int minStepCount = 1;
        int nextMaxStep = 0;
        int nextIndex = 0;

        for (int i = 0; i < nums.length; ) {

            for (int j = i + 1; j <= nums[i] + i; j++) {

                if (i + nums[i] >= nums.length - 1) {
                    return minStepCount;
                }

                if (j + nums[j] > nextMaxStep) {
                    nextMaxStep = j + nums[j];
                    nextIndex = j;
                }


            }

            minStepCount++;
            i = nextIndex;
            nextMaxStep = 0;
        }

        return 0;

    }
}