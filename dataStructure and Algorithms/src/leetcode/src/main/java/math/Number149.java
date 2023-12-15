package leetcode.src.main.java.math;

/**
 * https://leetcode.cn/problems/max-points-on-a-line/
 *
 * @author goodtime
 * @create 2023-12-06 12:43
 */
public class Number149 {

    public static void main(String[] args) {
        System.out.println("test");
    }

}

/**
 * 暴力的解法是从第一个点开始遍历到最后一个点，每一个点都尝试与其他点组成直线，看最多有几个点落在同一直线上
 */
class Solution149 {
    public int maxPoints(int[][] points) {

        if (points == null) {
            return 0;
        }

        if (points.length == 1) {
            return 1;
        }

        int maxPoints = 2;

        //maxPoints为所经过的最多的点，假设有5个点，前面几轮后maxPoints已经为3，那么从第3个点开始后面的情况已经可以不用考察了
        //以第3个点第一个经过的点的直线，所经过的最多的点也不过是3个，不会更多了，所以可优化成points.length - maxPoints
        for (int i = 0; i < points.length - maxPoints; i++) {

            int[] point1 = points[i];
            int x1 = point1[0];
            int y1 = point1[1];

            for (int j = i + 1; j < points.length - 1; j++) {

                int[] point2 = points[j];
                int x2 = point2[0];
                int y2 = point2[1];
                int linePoints = 2;

                for (int l = j + 1; l < points.length; l++) {

                    int[] point3 = points[l];
                    int x3 = point3[0];
                    int y3 = point3[1];

                    /**
                     * 核心就是计算斜率，如果point1、point2组成的直线斜率与point2、point3组成的直线斜率相同，那么两种或平行或就是1条直线
                     * 因为两条直线都经过point2，显然是一条直线
                     */
                    if ((y2 - y1) * (x3 - x2) == (y3 - y2) * (x2 - x1)) {
                        linePoints++;
                    }

                }

                if (linePoints > maxPoints) {
                    maxPoints = linePoints;
                }

            }

        }

        return maxPoints;

    }
}