package leetcode.src.main.java.ms;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/
 *
 * @author goodtime
 * @create 2023-04-06 19:45
 */
public class Number84 {
    public static void main(String[] args) {
        System.out.println(new Solution84().largestRectangleArea(new int[]{2, 1, 0, 2}));
    }
}

/**
 * 单调栈
 */
class Solution84 {
    public int largestRectangleArea(int[] heights) {

        if (heights == null || heights.length == 0) {
            return 0;
        }

        if (heights.length == 1) {
            return heights[0];
        }

        int max = 0;

        Stack<Integer> ms = new Stack<>();

        ms.push(0);

        for (int i = 1; i < heights.length; i++) {

            while (!ms.empty()) {

                Integer peek = ms.peek();

                if (heights[peek] > heights[i]) {

                    ms.pop();

                    int high = heights[peek];
                    //为了避免这种left=-1的情况，可以提前在heights的头部加个0
                    int left = -1;
                    if (!ms.empty()) {
                        left = ms.peek();
                    }
                    max = Math.max(max, high * (i - left - 1));
                } else if (heights[peek] == heights[i]) {
                    //等于的情况下，包含后面柱子的最大矩形一定包含前面柱子，所以无需计算前面那个柱子
                    ms.pop();
                } else {
                    break;
                }
            }

            ms.push(i);

        }

        //某些柱子还未弹出栈，说明在其右边的柱子都比它们高，为了避免这种情况，可以提前在heights的尾部加个0
        while (!ms.empty()) {
            Integer pop = ms.pop();
            if(!ms.empty()) {
                int left = ms.peek();
                max = Math.max(max, (heights.length - left - 1) * heights[pop]);
            }else {
                //最后一个元素是整个柱状图最矮的柱子
                max = Math.max(max, heights.length * heights[pop]);
            }
        }


        return max;

    }
}