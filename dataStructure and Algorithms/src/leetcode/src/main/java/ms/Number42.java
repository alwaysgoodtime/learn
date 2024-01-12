package leetcode.src.main.java.ms;

import java.util.Stack;

/**
 * monotone stack 单调栈
 * https://leetcode.cn/problems/trapping-rain-water/
 *
 * @author goodtime
 * @create 2023-04-06 16:49
 */
public class Number42 {
    public static void main(String[] args) {
        System.out.println(new Solution42().trap3(new int[]{4,2,0,3,2,5}));
    }
}

/**
 * 单调栈思路解法
 *
 * 从栈低到栈顶是从大到小排序，把每个数想象为与值等高的柱子
 *
 * 如果发现一个数比栈顶大，那么说明找到比这个柱子高的右柱子，而且因为从栈低到栈顶是从大到小，所以左柱子早已在栈里，取出来算算当前柱子能接多少水即可
 *
 * 不用担心当前柱子左边和右边是不是有更高的柱子，所以当前柱子最后能接更多水，如果有这种可能的话，其他柱子算的时候会把它上面的区间也接上水
 *
 * eg. 5 1 2 4   1这个柱子，第一次只接了1单位的水  算到2这根柱子的时候，它左右柱子最低的是4，但中间宽度是2，所以会装 2 * 2 = 4 格水，相当于给1的柱子又多接了2单位
 */
class Solution42 {

    public int trap2(int[] height) {

        Stack<Integer> stack = new Stack();
        int water = 0;

        for (int i = 0; i < height.length; i++) {

            if (height[i] == 0) {
                continue;
            }

            if (stack.size() == 0) {
                stack.push(i);
                continue;
            }

            int pillarHeight = height[stack.peek()];

            int preHeight = 0;

            //先考虑左小右大的情况
            if (pillarHeight <= height[i]) {

                while (stack.size() != 0 && height[stack.peek()] <= height[i]) {
                    //把其中比新高度小的都弹出去
                    int index = stack.pop();
                    water += (height[index] - preHeight) * (i - index - 1);
                    preHeight = height[index];
                }
            }

            //再考虑左大右小的情况,只需做一次即可，有时候删完左小右大的情况，还需考虑一下左大右小的情况，故引入preHeight考虑
            if (stack.size() != 0 && preHeight < height[i]) {
                int preIndex = stack.peek();
                water += (i - preIndex - 1) * (height[i] - preHeight);
            }

            stack.push(i);
        }

        return water;
    }


    public int trap(int[] height) {

        int rain = 0;

        if (height == null || height.length <= 1) {
            return 0;
        }

        Stack<Integer> ms = new Stack<>();

        ms.push(0);

        for (int i = 1; i < height.length; i++) {

            while (!ms.empty()) {

                Integer peek = ms.peek();

                if (height[i] > height[peek]) {
                    int mid = peek;
                    ms.pop();

                    if (!ms.empty()) {
                        int before = ms.peek();
                        rain += (i - before - 1) * (Math.min(height[before], height[i]) - height[mid]);
                    }
                } else {
                    break;
                }
            }

            ms.push(i);

        }


        return rain;
    }

    public int trap3(int[] height) {

        //stack里存的是柱子下标，而非柱子高度，栈中的柱子高度单调递减
        Stack<Integer> stack = new Stack();
        int water = 0;

        for (int i = 0; i < height.length; i++) {

            int curHigh = height[i];

            //高度为0的柱子忽视，无需进入栈计算
            if (curHigh == 0) {
                continue;
            }

            //已处理的高度，防止重复计算面积
            int handledHeight = 0;

            //新柱子有三种情况，一种是比前一个柱子高或相等，一种是比前一个柱子矮
            //如果比前面柱子高，开始结算，直到栈为空或者比前面柱子矮

            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                int index = stack.pop();
                water += (i - index - 1) * (height[index] - handledHeight);
                handledHeight = height[index];
            }

            //此时在处理前柱子比后面柱子高的情况
            if (!stack.isEmpty()) {
                int preIndex = stack.peek();
                //提前将高低柱子间接到的雨水算到面积中，后续就无需关心
                water += (i - preIndex - 1) * (height[i] - handledHeight);
            }


            //新柱子入栈
            stack.push(i);
        }

        return water;
    }


}