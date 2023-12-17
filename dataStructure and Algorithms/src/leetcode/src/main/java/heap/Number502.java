package leetcode.src.main.java.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/ipo/
 *
 * @author goodtime
 * @create 2023-12-16 16:15
 */
public class Number502 {
    public static void main(String[] args) {
        int k = 2;
        int w = 0;
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 1};
        System.out.println(new Solution502().findMaximizedCapital(k, w, profits, capital));
    }
}

/**
 * 用贪婪算法即可，无论手里钱有多少，只要选择目前满足启动资金的最赚钱的项目即可
 *
 * 核心思路是把数组按照启动资金排序后，把小于w的所有利润排成一个大顶堆，找到最大的那个profits，加入w，再继续此过程
 *
 */
class Solution502 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        int[][] table = new int[profits.length][2];

        for (int i = 0; i < table.length; i++) {
            table[i] = new int[]{capital[i], profits[i]};
        }

        Arrays.sort(table, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int curr = 0;

        for (int i = 0; i < k; i++) {
            while (curr < profits.length && table[curr][0] <= w) {
                priorityQueue.offer(table[curr][1]);
                curr++;
            }

            if (priorityQueue.size() != 0) {
                w += priorityQueue.poll();
            }else {
                break;
            }
        }

        return w;

    }

}
