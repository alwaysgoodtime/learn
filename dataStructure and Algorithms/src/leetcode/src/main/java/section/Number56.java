package leetcode.src.main.java.section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/merge-intervals/
 * @author goodtime
 * @create 2023-12-02 11:06
 */
public class Number56 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {0, 4}};
        System.out.println(new Solution56().merge(intervals));
    }

}

/**
 * 两个指针，一个指向已有区间的最小值，一个指向已有区间的最大值
 * 必须先排序
 */
class Solution56 {
    public int[][] merge(int[][] intervals) {

        if (intervals == null || intervals.length == 1) {
            return intervals;
        }

        //排序
        Arrays.sort(intervals, (o1, o2) -> {
            if(o1[0] < o2[0]) {
                return -1;
            } else if(o1[0] == o2[0]){
                return o1[1] - o2[1];
            }else {
                return 1;
            }
        });

        int prePointer = intervals[0][0];
        int postPointer = intervals[0][1];

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 1; i < intervals.length; i++) {

            //表示两个区间没有交集
            if (postPointer < intervals[i][0]) {
                result.add(prePointer);
                result.add(postPointer);
                prePointer = intervals[i][0];
                postPointer = intervals[i][1];
                continue;
            }

            if (postPointer < intervals[i][1]) {
                postPointer = intervals[i][1];
            }
        }

        //将最后的结果放入结果集
        result.add(prePointer);
        result.add(postPointer);

        int length = result.size() / 2;
        int[][] resultArray = new int[length][2];
        for (int j = 0; j < length; j++) {
            resultArray[j][0] = result.get(j * 2);
            resultArray[j][1] = result.get(j * 2 + 1);
        }

        return resultArray;
    }

}
