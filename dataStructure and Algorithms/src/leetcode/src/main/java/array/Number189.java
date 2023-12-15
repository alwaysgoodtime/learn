package leetcode.src.main.java.array;

/**
 * @author goodtime
 * @create 2023-12-03 19:43
 */
public class Number189 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        new Solution189().rotate(nums, 3);
    }
}

/**
 * 轮转，即是把后面的数放前面，用一个额外数组存储
 *
 * 举例：轮转{1, 2, 3, 4, 5, 6, 7}，k为3
 *
 * 指针放5上，先把567搬到另一个数组中
 *
 * 指针放4上，按顺序把4到1往后移动k格(要从后往前放，避免后面的数被前面的数覆盖)
 *
 * 指针放1上，按顺序把567放到新位置
 */
class Solution189 {
    public void rotate(int[] nums, int k) {

        if (k <= 0 || nums == null || nums.length == 0 || k % nums.length == 0) {
            return;
        }

        if (k > nums.length) {
            k = k % nums.length;
        }

        int[] tmp = new int[k];

        for (int i = 0; i < k; i++) {
            tmp[i] = nums[nums.length - k + i];
        }

        for (int i = nums.length - k - 1; i >= 0; i--) {
            nums[k + i] = nums[i];
        }

        for (int i = 0; i < k; i++) {
            nums[i] = tmp[i];
        }

    }

}
