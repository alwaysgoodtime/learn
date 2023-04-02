package leetcode.src.main.java.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/queue-reconstruction-by-height/
 *
 * @author goodtime
 * @create 2023-04-01 13:40
 */
public class Number406 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution406().reconstructQueue(new int[][]{
                {7, 0},
                {4, 4},
                {7, 1},
                {5, 0},
                {6, 1},
                {5, 2}
        })));
    }

}

/**
 * 先根据身高排序（身高相同的按照前面有几个人排序），再按照身体后面具体的要求（即前面有几个人），来做
 * 一一插入
 *
 */
class Solution406 {
    public int[][] reconstructQueue(int[][] people) {

        if (people == null || people.length <= 1) {
            return people;
        }

        //身高高的在前，低的在后
        //如果身高相同，那么说自己前面人多的往后排
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o2[0] - o1[0];
                }
                return o1[1] - o2[1];
            }
        });


        //把身高低但说比自己高的人比现在所处位置少的往前放（只会少不会多）
        //每次遍历时，到i-1都是按照要求排列的（局部最优），只需要把i放对位置即可（全局最优）
        for (int i = 0; i < people.length; i++) {
            int order = people[i][1];
            int high = people[i][0];

            if (order < i) {

                for (int j = i; j > order; j--) {
                    people[j][0] = people[j - 1][0];
                    people[j][1] = people[j - 1][1];
                }
                people[order][0] = high;
                people[order][1] = order;
            }

        }

        return people;
    }
}