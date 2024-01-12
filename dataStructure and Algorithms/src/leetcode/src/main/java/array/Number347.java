package leetcode.src.main.java.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author goodtime
 * @create 2024-01-12 12:52
 */
public class Number347 {
    public static void main(String[] args) {
        int[] array = {6,0,1,4,9,7,-3,1,-4,-8,4,-7,-3,3,2,-3,9,5,-4,0};
        System.out.println(Arrays.toString(new Solution347().topKFrequent(array, 6)));
    }
}

class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.merge(nums[i], 1, Integer::sum);
        }

        //对hashMap的value，也即出现频率从高到底排序，这里用小顶堆排序，Java可以用优先队列实现
        //如果堆的元素个数小于 k，就可以直接插入堆中。
        //如果堆的元素个数等于 k，则检查堆顶与当前出现次数的大小。
        //如果堆顶更大，说明至少有 k 个数字的出现次数比当前值大，故舍弃当前值；否则，就弹出堆顶，并将当前值插入堆中。

        Iterator<Map.Entry<Integer, Integer>> iterator = hashMap.entrySet().iterator();

        int[] heap = new int[k];
        int[] numArray = new int[k];
        int index = 0;

        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            Integer num = next.getKey();
            Integer count = next.getValue();

            //说明前k的元素还未装满
            if (index < heap.length) {
                heap[index] = count;
                numArray[index] = num;
                //处理的是当前节点的父节点，从树节点的下往上处理
                heapify(heap, numArray, (index - 1) / 2, index+1);
                index++;
            } else {
                if (heap[0] >= count) {
                    continue;
                }

                heap[0] = count;
                numArray[0] = num;
                //处理当前节点的子节点，从树节点的上往下处理
                heapifyUpToDown(heap, numArray, 0, heap.length);
            }

        }

        return numArray;
    }

    private void heapifyUpToDown(int[] heap, int[] numArray, int parentIndex, int length) {

        int leftIndex = parentIndex * 2 + 1;
        int rightIndex = parentIndex * 2 + 2;
        int parent = parentIndex;

        if (leftIndex < length && heap[leftIndex] < heap[parentIndex]) {
            parentIndex = leftIndex;
        }

        if (rightIndex < length && heap[rightIndex] < heap[parentIndex]) {
            parentIndex = rightIndex;
        }

        if (parentIndex != parent) {
            swap(heap, numArray, parentIndex, parent);
            heapifyUpToDown(heap, numArray, parentIndex, length);
        }


    }

    private void heapify(int[] heap, int[] numArray, int parentIndex, int length) {

        int leftIndex = parentIndex * 2 + 1;
        int rightIndex = parentIndex * 2 + 2;
        int parent = parentIndex;

        if (leftIndex < length && heap[leftIndex] < heap[parentIndex]) {
            parentIndex = leftIndex;
        }

        if (rightIndex < length && heap[rightIndex] < heap[parentIndex]) {
            parentIndex = rightIndex;
        }

        if (parentIndex != parent) {
            swap(heap, numArray, parentIndex, parent);
            heapify(heap, numArray, (parent - 1) / 2, length);
        }
    }

    private void swap(int[] heap, int[] numArray, int parentIndex, int parent) {

        int tmpHeap = heap[parentIndex];
        int tmpArray = numArray[parentIndex];

        heap[parentIndex] = heap[parent];
        heap[parent] = tmpHeap;

        numArray[parentIndex] = numArray[parent];
        numArray[parent] = tmpArray;

    }


}