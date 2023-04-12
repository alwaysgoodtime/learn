package leetcode.src.main.java.ms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/next-greater-element-i/
 *
 * @author goodtime
 * @create 2023-04-06 15:35
 */
public class Number496 {

    public static void main(String[] args) {
        int[] result = new Solution496().nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
        System.out.println(result[0]);
    }

}

/**
 * 还是用单调栈，多了一个hashmap用于映射
 */
class Solution496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return nums1;
        }

        if (nums2 == null || nums2.length == 0) {
            Arrays.fill(nums1, -1);
            return nums1;
        }

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums1.length; i++) {
            hashMap.put(nums1[i], i);
        }

        Stack<Integer> ms = new Stack<>();
        ms.push(0);

        int[] result = new int[nums1.length];

        Arrays.fill(result,-1);


        for (int i = 1; i < nums2.length; i++) {


            while (!ms.empty()) {
                Integer peek = ms.peek();
                if (nums2[i] > nums2[peek]) {
                    if (hashMap.containsKey(nums2[peek])) {
                        result[hashMap.get(nums2[peek])] = nums2[i];
                    }
                    ms.pop();
                } else {
                    break;
                }
            }

            ms.push(i);

        }

        return result;


    }
}