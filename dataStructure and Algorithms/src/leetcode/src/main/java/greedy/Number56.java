package leetcode.src.main.java.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/merge-intervals/
 * @author goodtime
 * @create 2023-04-01 23:08
 */
public class Number56 {
    public static void main(String[] args) {
        System.out.println(new Solution56().merge(new int[][]{
                {1,3},
                {2,6},
                {8,10},
                {15,18}
        }));
    }
}

/**
 * 按照左下标排序，再一个一个叠即可
 */
class Solution56 {
    public int[][] merge(int[][] intervals) {

        ArrayList<Integer> preIndex = new ArrayList<>();
        ArrayList<Integer> endIndex = new ArrayList<>();

        if(intervals == null || intervals.length == 0){
            return intervals;
        }

        if(intervals.length == 1){
            return intervals;
        }

        //排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                //处理一下int，防止加减过程数值溢出
                if(o1[0] != o2[0]){
                    if((long)o1[0] - (long)o2[0] >= 0){
                        return 1;
                    }else {
                        return -1;
                    }
                }

                long result = (long)o1[1] - (long)o2[1];
                if(result > 0){
                    return 1;
                }else if(result == 0) {
                    return 0;
                }
                return -1;
            }
        });



        //从前往后排除区间
        int pre = intervals[0][0];
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int curPre = intervals[i][0];
            int curEnd = intervals[i][1];

            //有重叠
            if (end >= curPre) {
                //留下右下标大的
                end = Math.max(curEnd,end);

                //如果是最后一个元素，且有重叠，直接放进去即可
                if(i == intervals.length - 1){
                    preIndex.add(pre);
                    endIndex.add(end);
                }

            } else {
                //无重叠，把旧的数组放进去
                preIndex.add(pre);
                endIndex.add(end);

                //如果是最后一个元素，且无重叠，直接放进去即可
                if(i == intervals.length - 1){
                    preIndex.add(curPre);
                    endIndex.add(curEnd);
                }else {
                    pre = curPre;
                    end = curEnd;
                }
            }
        }

        int[][] result =  new int[preIndex.size()][2];

        for (int i = 0; i < preIndex.size(); i++) {
            result[i][0] = preIndex.get(i);
            result[i][1] = endIndex.get(i);
        }

        return result;
    }
}