package leetcode.src.main.java.greedy;

/**
 * https://leetcode.cn/problems/jump-game/
 *
 * @author goodtime
 * @create 2023-03-31 23:31
 */
public class Number55 {
    public static void main(String[] args) {
        System.out.println(new Solution55().canJump(new int[]{1, 2, 3}));
    }
}

/**
 * 找距离最后一个下标最近的可以到达它的坐标（局部最优），然后从这个坐标继续再往前找，最后看是否能找到第0个下标所在的位置（全局最优）
 *
 * 为什么找离最后下标最近的坐标？因为比最后下标最近的坐标远的坐标，总是可以先到达这个离最后下标最近的坐标，再到达最后一个坐标
 * 也就是说，这个坐标是"必经之路"
 *
 * 比如[5,1,4,3] 我们就先找到4，无论是5想直接到3，还是1先到4后到3，都得老老实实经过4，问题从是否能到3，转换为是否能到4
 */
class Solution55 {
    public boolean canJump(int[] nums) {

        if (nums == null || nums.length == 0) {
            return true;
        }


        int target = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= target) {
                target = i;
            }
        }

        return target == 0;
    }
}
