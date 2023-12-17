package leetcode.src.main.java.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author goodtime
 * @create 2023-12-16 17:43
 */
public class Number373 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 3, 3};
        int[] nums2 = {-3, 22, 35, 56, 70, 100, 123, 200};
        int k = 10;
        System.out.println(new Solution373().kSmallestPairs(nums1, nums2, k));
    }
}

/**
 * 本题不可用双指针，举例：[1,7,11] [2,6,11] 1+11 < 7+2
 */
class Solution373 {


    public List<List<Integer>> kSmallestPairsAdvanced(int[] nums1, int[] nums2, int k) {

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> nums1[o1[0]] + nums2[o1[1]] -
                nums1[o2[0]] - nums2[o2[1]]);

        int m = nums1.length;
        int n = nums2.length;

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < Math.min(m, k); i++) {
            priorityQueue.offer(new int[]{i, 0});
        }

        for (int i = 0; i < k && priorityQueue.size() > 0; i++) {
            int[] poll = priorityQueue.poll();
            ArrayList<Integer> element = new ArrayList<>();
            int l1 = poll[0];
            int l2 = poll[1];
            element.add(nums1[l1]);
            element.add(nums2[l2]);
            result.add(element);
            if (l2 < n - 1) {
                priorityQueue.offer(new int[]{l1, l2 + 1});
            }
        }

        return result;
    }


    /**
     * 这种暴力解法会超时
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> result = new ArrayList<>();

        if (k <= 0 || nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return result;
        }

        int[][] heap = new int[nums1.length * nums2.length][3];
        int index = 0;

        //1.将nums1和nums2中元素的和提前算好，接着依次放入小顶堆
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int s1 = nums1[i];
                int s2 = nums2[j];
                heap[index] = new int[]{s1 + s2, s1, s2};
                index++;
                if (index > 1) {
                    push(heap, index, index / 2 - 1);
                }
            }
        }


        //2.从小顶堆中取数
        while (k != 0 && index > 0) {
            List<Integer> pair = new ArrayList<>();
            pair.add(heap[0][1]);
            pair.add(heap[0][2]);
            result.add(pair);
            swap(heap, 0, index - 1);
            heapify(heap, index - 1, 0);
            index--;
            k--;
        }

        return result;

    }

    /**
     * 上浮操作，新数加入小根堆
     *
     * @param heap
     * @param n
     * @param index
     */
    private void push(int[][] heap, int n, int index) {

        int head = index;
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if (left < n && heap[head][0] > heap[left][0]) {
            head = left;
        }

        if (right < n && heap[head][0] > heap[right][0]) {
            head = right;
        }

        if (head != index) {
            swap(heap, index, head);
            if (index != 0) {
                push(heap, n, (index - 1) / 2);
            }
        }

    }

    /**
     * 下沉操作，小根堆的顶与叶子节点互换、一个数组变化成为小根堆
     *
     * @param heap
     * @param n
     * @param index
     */
    private void heapify(int[][] heap, int n, int index) {
        int head = index;
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if (left < n && heap[head][0] > heap[left][0]) {
            head = left;
        }

        if (right < n && heap[head][0] > heap[right][0]) {
            head = right;
        }

        if (head != index) {
            swap(heap, index, head);
            heapify(heap, n, head);
        }

    }

    private void swap(int[][] heap, int i, int j) {
        int[] tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
