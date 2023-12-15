package leetcode.src.main.java.binarySearch;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/description/
 *
 * @author goodtime
 * @create 2023-12-08 01:39
 */
public class Number4 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 7};
        int[] nums2 = {2, 3, 4, 5};
        System.out.println(new Solution4().findMedianSortedArrays(nums1, nums2));
    }
}

/**
 * 简单方法是知道两个数组的长度，创建两个分别指向两个数组的指针，根据大小控制两个指针的移动，最后输出中位数，时间复杂度为O(n)
 *
 * 复杂方法是从两个数组中找第k个小的数
 */
class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1 == null || nums2 == null) {
            return 0;
        }

        int length = nums1.length + nums2.length;

        if (length == 0) {
            return 0;
        }

        //如果length是奇数，left和right是一样的，如果length是偶数，right比left大1
        int left = (length + 1) / 2;
        int right = (length + 2) / 2;

        if (length % 2 == 0) {
            return (getMin(left, nums1, nums2, 0, 0) + getMin(right, nums1, nums2, 0, 0)) / 2.0;
        } else {
            return getMin(left, nums1, nums2, 0, 0);
        }


    }

    /**
     * getMin的作用是，从两个数组中找到第k个小的数，并返回
     */
    private double getMin(int k, int[] nums1, int[] nums2, int p1, int p2) {

        //p1和p2是看两个数组已经排除到了哪里
        int length1 = nums1.length;
        int length2 = nums2.length;

        //说明nums1全遍历完了
        if (p1 == length1) {
            return nums2[k + p2 - 1];
        }

        //说明nums2全遍历完了
        if (p2 == length2) {
            return nums1[k + p1 - 1];
        }

        //如果两个数组火拼到了最后，k==1，那么返回两个数组当前指的数中最小的数
        if (k == 1) {
            return Math.min(nums1[p1],nums2[p2]);
        }


        int last1 = nums1.length - p1;
        int last2 = nums2.length - p2;


        //假设k=4，那么先找两个数组中第2个小的数，k=5，也先找2
        int n = k / 2;

        //数组也有长度，n不能超过该数组剩下的最大长度
        int l1 = Math.min(n, last1);
        int l2 = Math.min(n, last2);

        int s1 = nums1[p1 + l1 - 1];
        int s2 = nums2[p2 + l2 - 1];

        //把s1对应长度的数组排除出去
        int cut = n;
        if (s1 <= s2) {
            p1 = p1 + n;

            if (n > last1) {
                cut = last1;
                p1 = length1;
            }
        } else {
            p2 = p2 + n;

            if (n > last2) {
                cut = last2;
                p2 = length2;
            }
        }

        return getMin(k - cut, nums1, nums2, p1, p2);


    }


}
