package leetcode.src.main.java.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * @author goodtime
 * @create 2023-04-01 20:43
 */
public class Number452 {

    public static void main(String[] args) {
        System.out.println(new Solution452().findMinArrowShots(new int[][]{
                {-2147483646,-2147483645},{2147483646,2147483647}
                }
        ));
    }
}

/**
 * 先给所有气球按前下标排序，如果前下标相同，再按后下标从小到大
 * 然后开射，如果第二个在第一范围里，取其重叠的部分，如果不在，把两个范围都统计，当射两镖
 * 然后再拿最新的范围（如果第二个气球不在第一个气球范围里，那么一定是在更后面，直接取这个新
 * 气球范围即可）和第三个气球半径
 *
 * 排好序后就能贪，想象一只飞镖从左往右射按照前下标排好序的气球即可
 */
class Solution452 {
    public int findMinArrowShots(int[][] points) {

        if (points == null || points.length == 0) {
            return 0;
        }

        if(points.length == 1){
            return 1;
        }

        Arrays.sort(points, new Comparator<int[]>() {
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

        //从前往后射气球
        int pre = points[0][0];
        int end = points[0][1];
        int count = 1;

        for (int i = 1; i < points.length; i++) {

            int curPre = points[i][0];
            int curEnd = points[i][1];

            //能插爆，更新重叠部分
            if (end >= curPre) {
                pre = curPre;
                if (end > curEnd) {
                    end = curEnd;
                }
            } else {
                count++;
                //更新新气球半径作为新镖插的范围
                pre = curPre;
                end = curEnd;
            }
        }

        return count;


    }
}