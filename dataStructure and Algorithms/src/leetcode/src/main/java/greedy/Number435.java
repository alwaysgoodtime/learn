package leetcode.src.main.java.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/non-overlapping-intervals/
 * @author goodtime
 * @create 2023-04-01 22:04
 */
public class Number435 {
    public static void main(String[] args) {
        System.out.println(new Solution435().eraseOverlapIntervals(new int[][]{
                {1,2},
                {1,2}
        }));
    }
}

/**
 * 擦除重叠区间，边界重叠不算重叠
 * 先按区间前下标进行排序（如果前下标相同，后下标大的在后面）
 * 从头开始和下一步比较长度，如果有重叠部分，代表两个区间一定得删一个，后下标越大，越容易和别的
 * 区间相重叠，就删后下标更大的那个。
 *
 */
class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {

        if (intervals == null) {
            return 0;
        }

        if(intervals.length <= 1){
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                //处理一下int，防止加减过程数值溢出
                if(o1[0] != o2[0]){
                    if((long)o1[0] - (long)o2[0] > 0){
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
        int count = 0;

        for (int i = 1; i < intervals.length; i++) {
            int curPre = intervals[i][0];
            int curEnd = intervals[i][1];

            //有重叠，删区间
            if (end > curPre) {
                count++;
                if(end > curEnd){
                    //留下右下标小的
                    end = curEnd;
                }

            } else {
                //更新新的范围
                pre = curPre;
                end = curEnd;
            }
        }

        return count;
    }
}