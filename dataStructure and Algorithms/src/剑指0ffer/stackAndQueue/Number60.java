package 剑指0ffer.stackAndQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * JZ59 滑动窗口的最大值
 * <p>
 * 给定一个长度为 n 的数组 num 和滑动窗口的大小 size ，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}，
 * {2,[3,4,2],6,2,5,1}，
 * {2,3,[4,2,6],2,5,1}，
 * {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}，
 * {2,3,4,2,6,[2,5,1]}。
 * 窗口大于数组长度或窗口长度为0的时候，返回空。
 * 要求：空间复杂度为O(n) ，时间复杂度O(n)
 *
 * @author goodtime
 * @create 2023-03-26 20:30
 */
public class Number60 {
    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 3, 3, 6, 2, 5, 2};
        int length = 3;
        Solution60 solution60 = new Solution60();
        ArrayList<Integer> integers = solution60.maxInWindows(a, length);
        integers.forEach(System.out::println);

    }
}

/**
 * 用一个双端队列，队列第一个位置保存当前窗口的最大值，当窗口滑动一次
 * 1.判断当前最大值是否过期
 * 2.新增加的值从队尾开始比较，把所有比他小的值丢掉
 */
class Solution60 {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {


        ArrayList<Integer> result = new ArrayList<>();
        if (size == 0 || num == null || size > num.length) {
            return result;
        }
        int begin;
        ArrayDeque<Integer> q = new ArrayDeque<>();

        //peekFirst,查看q中最早存入的下标
        //pillFirst,去除q中过期的下标
        for (int i = 0; i < num.length; i++) {
            begin = i - size + 1;
            if (q.isEmpty()) {
                q.add(i);
            } else if (begin > q.peekFirst()) {
                q.pollFirst();
            }

            //查看q中最新的值与当前值比较，如果太小，就丢弃，接着循环
            while ((!q.isEmpty()) && num[q.peekLast()] <= num[i]) {
                q.pollLast();
            }

            //需要加上当前值的下标，保证在它前面的都是比它大的
            q.add(i);
            if (begin >= 0) {
                result.add(num[q.peekFirst()]);
            }
        }
        return result;
    }
}


