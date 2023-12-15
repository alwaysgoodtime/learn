package leetcode.src.main.java.array;

/**
 * https://leetcode.cn/problems/merge-sorted-array/
 * @author goodtime
 * @create 2023-12-03 00:00
 */
public class Number88 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0}, nums2 = {2, 5, 6};
        int m = 3, n = 3;
        new Solution88().merge(nums1, m, nums2, n);
    }
}


/**
 * 两个数组都是有序非递减，那么用双指针即可
 */
class Solution88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int s1Pointer = 0;
        int s2Pointer = 0;
        int s3Pointer = 0;

        int[] nums3 = new int[nums1.length];

        while (s3Pointer < m + n) {

            if (s1Pointer >= m) {
                nums3[s3Pointer] = nums2[s2Pointer];
                s2Pointer++;
            } else if (s2Pointer >= n) {
                nums3[s3Pointer] = nums1[s1Pointer];
                s1Pointer++;
            } else if (nums1[s1Pointer] <= nums2[s2Pointer]) {
                nums3[s3Pointer] = nums1[s1Pointer];
                s1Pointer++;
            } else {
                nums3[s3Pointer] = nums2[s2Pointer];
                s2Pointer++;
            }

            s3Pointer++;
        }


        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = nums3[i];
        }
    }
}
