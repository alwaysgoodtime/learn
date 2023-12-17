package leetcode.src.main.java.heap;

/**
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 *
 * @author goodtime
 * @create 2023-12-16 14:59
 */
public class Number215 {
    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(new Solution215().findKthLargest(nums, 1));
    }
}

/**
 * 从数组中找到第k大的元素，同时要求O(n)的复杂度
 *
 * 直观方法是：用各种方式给数组从大到小排序，然后找到第k个元素，但比较排序最好也得是O(nlogn)复杂度
 *
 * 这里先用一下大根堆，时间为O(nlogn)
 *
 */
class Solution215 {

    public int findKthLargest(int[] nums, int k) {

        int n = nums.length;

        //1.对nums进行堆的构建，从最后一个叶子节点的父节点开始
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }

        //2.找到第k个元素，也即对大顶堆做处理，处理k-1次即可
        int lastIndex = n - 1;
        while (k != 1) {
            swap(nums, 0, lastIndex);
            heapify(nums, lastIndex, 0);
            lastIndex--;
            k--;
        }

        return nums[0];

    }

    private void heapify(int[] nums, int n, int i) {
        int head = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && nums[head] < nums[left]) {
            head = left;
        }

        if (right < n && nums[head] < nums[right]) {
            head = right;
        }

        if (head != i) {
            swap(nums, i, head);
            //同时对被交换的节点进行维护
            heapify(nums, n, head);
        }


    }

    private void swap(int[] nums, int i, int head) {

        int tmp = nums[i];
        nums[i] = nums[head];
        nums[head] = tmp;

    }
}
