package leetcode.src.main.java.section;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
 * @author goodtime
 * @create 2023-12-02 14:03
 */
public class Number452 {

    public static void main(String[] args) {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println(new Solution452().findMinArrowShots(points));
    }
}

/**
 * 在贪心算法里也有讲解 核心就是尽可能多地一次性射到多个气球
 * 在排序后，只要按顺序扎气球即可，只要前一个区间与后一个区间无重叠，就视为需要一只飞镖
 * 如果有重叠，取其重叠部分和下一个区间比较
 *
 * @see leetcode.src.main.java.greedy.Number452
 */
class Solution452 {
    public int findMinArrowShots(int[][] points) {

        if (points.length == 1) {
            return 1;
        }

        //排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0]) {
                    return -1;
                } else if (o1[0] == o2[0] && o1[1] == o2[1]) {
                    return 0;
                } else if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return 1;
                }
            }
        });

        int n = 1;
        int prePointer = points[0][0];
        int postPointer = points[0][1];

        for (int i = 1; i < points.length; i++) {

            //两者无重叠部分，消耗一直飞镖，更新区间
            if (postPointer < points[i][0]) {
                n++;
                prePointer = points[i][0];
                postPointer = points[i][1];
            } else {
                //取其重叠部分区间
                if (prePointer < points[i][0]) {
                    prePointer = points[i][0];
                }

                if (postPointer > points[i][1]) {
                    postPointer = points[i][1];
                }
            }

        }

        return n;

    }
}
