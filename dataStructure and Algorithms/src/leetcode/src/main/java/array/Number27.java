package leetcode.src.main.java.array;

/**
 * https://leetcode.cn/problems/remove-element/description/
 *
 * @author goodtime
 * @create 2023-12-03 00:49
 */
public class Number27 {
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        new Solution27().removeElement(nums, val);
    }
}

/**
 * 双指针一前一后，前面的指针负责探测val的下标，如果不是val则丢给后面的指针负责搬运数字填补空白
 */
class Solution27 {
    public int removeElement(int[] nums, int val) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int preIndex = 0;
        int postIndex = 0;
        int length = nums.length;

        //初始化preIndex和postIndex
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                preIndex = i + 1;
                postIndex = i;
                length--;
                break;
            }
        }

        if (length == nums.length) {
            return 0;
        }

        //原地处理原数组
        while (preIndex < nums.length) {

            if (nums[preIndex] != val) {
                nums[postIndex] = nums[preIndex];
                postIndex++;
            } else {
                length--;
            }

            preIndex++;

        }

        return length;


}
}


