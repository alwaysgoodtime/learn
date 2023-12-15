package leetcode.src.main.java.section;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/insert-interval/
 * @author goodtime
 * @create 2023-12-02 12:18
 */
public class Number57 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        System.out.println(new Solution57().insert(intervals, newInterval));
    }
}

/**
 * 找到与newInterval有交集的区间，进行处理即可
 *
 * 麻烦在于如何把找到的区间放对位置
 */
class Solution57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        int prePointer = newInterval[0];
        int postPointer = newInterval[1];

        if (intervals.length == 0) {
            int[][] array = new int[1][1];
            array[0] = newInterval;
            return array;
        }

        for (int i = 0; i < intervals.length; i++) {

            if (postPointer < intervals[i][0]) {
                break;
            }

            if (prePointer > intervals[i][1]) {
                continue;
            }

            if (prePointer > intervals[i][0]) {
                prePointer = intervals[i][0];
            }

            if (postPointer < intervals[i][1]) {
                postPointer = intervals[i][1];
            }

        }

        List<Integer> result = new ArrayList<>();

        boolean insert = true;

        for (int i = 0; i < intervals.length; i++) {

            //如果新的区间比第一个区间还小，先让它插入下
            //如果已经插入过，那么忽略即可
            if (postPointer < intervals[i][0]) {

                if (insert) {
                    result.add(prePointer);
                    result.add(postPointer);
                    insert = false;
                }

                result.add(intervals[i][0]);
                result.add(intervals[i][1]);

            }

            //如果新的区间比现有区间大，那么放现有区间即可
            if (intervals[i][1] < prePointer) {
                result.add(intervals[i][0]);
                result.add(intervals[i][1]);
            }

        }

        //如果循环完毕还没有放进去，说明新的重叠区间下限比之前的区间上限都要大
        if (insert) {
            result.add(prePointer);
            result.add(postPointer);
        }

        int length = result.size() / 2;
        int[][] resultArray = new int[length][2];
        for (int j = 0; j < length; j++) {
            resultArray[j][0] = result.get(j * 2);
            resultArray[j][1] = result.get(j * 2 + 1);
        }

        return resultArray;

    }
}

