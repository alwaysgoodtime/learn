package leetcode.src.main.java.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 *
 * @author goodtime
 * @create 2023-12-16 14:59
 */
public class Number215 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 5, 6, 3};
        System.out.println(new Solution215().findKthLargestManualQuickSort(nums, 2));
    }
}

/**
 * 从数组中找到第k大的元素，同时要求O(n)的复杂度
 *
 * 直观方法是：用各种方式给数组从大到小排序，然后找到第k个元素，但比较排序最好也得是O(nlogn)复杂度
 *
 * 这里先用一下大根堆，时间为O(nlogn)
 */
class Solution215 {

    /**
     * 直接快排
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestQuickSort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }


    /**
     * 手写快排，同时只递归一个区间
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestAdvanced(int[] nums, int k) {
        return quickChoice(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickChoice(int[] nums, int leftPointer, int rightPointer, int target) {


        if (leftPointer >= rightPointer) {
            return -1;
        }

        int l = leftPointer;
        int r = rightPointer;


        //选定旋转轴
        int pivot = nums[leftPointer];

        while (leftPointer < rightPointer) {

            //1.先动右指针
            for (int i = rightPointer; i > leftPointer; i--) {
                if (nums[rightPointer] >= pivot) {
                    rightPointer--;
                } else {
                    break;
                }
            }

            if (rightPointer > leftPointer) {
                nums[leftPointer] = nums[rightPointer];
            } else {
                break;
            }

            //2.再动左指针
            for (int i = leftPointer; i < rightPointer; i++) {
                if (nums[leftPointer] <= pivot) {
                    leftPointer++;
                } else {
                    break;
                }
            }

            if (rightPointer > leftPointer) {
                nums[rightPointer] = nums[leftPointer];
            }
        }

        nums[leftPointer] = pivot;

        if (leftPointer == target) {
            return nums[target];
        } else if (leftPointer < target) {
            return quickChoice(nums, leftPointer + 1, r, target);
        } else {
            return quickChoice(nums, l, leftPointer - 1, target);
        }
    }


    /**
     * 手写快排
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestManualQuickSort(int[] nums, int k) {

        quickSort(nums, 0, nums.length - 1);

        return nums[nums.length - k];

    }

    private void quickSort(int[] nums, int leftPointer, int rightPointer) {


        if (leftPointer >= rightPointer) {
            return;
        }

        int l = leftPointer;
        int r = rightPointer;


        //选定旋转轴
        int pivot = nums[leftPointer];

        while (leftPointer < rightPointer) {

            //1.先动右指针
            for (int i = rightPointer; i > leftPointer; i--) {
                if (nums[rightPointer] >= pivot) {
                    rightPointer--;
                } else {
                    break;
                }
            }

            if (rightPointer > leftPointer) {
                nums[leftPointer] = nums[rightPointer];
            } else {
                break;
            }

            //2.再动左指针
            for (int i = leftPointer; i < rightPointer; i++) {
                if (nums[leftPointer] <= pivot) {
                    leftPointer++;
                } else {
                    break;
                }
            }

            if (rightPointer > leftPointer) {
                nums[rightPointer] = nums[leftPointer];
            }
        }

        nums[leftPointer] = pivot;

        quickSort(nums, l, leftPointer - 1);
        quickSort(nums, leftPointer + 1, r);
    }


    /**
     * 优先级队列
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestPriority(int[] nums, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
        }

        int result = 0;
        for (int i = 0; i < k; i++) {
            result = queue.poll();
        }

        return result;

    }


    public int findKthLargestHeap(int[] nums, int k) {

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
