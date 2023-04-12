package leetcode.src.main.java.ms;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/trapping-rain-water/
 *
 * @author goodtime
 * @create 2023-04-06 16:49
 */
public class Number42 {
    public static void main(String[] args) {
        System.out.println(new Solution42().trap(new int[]{0, 1, 0, 2, 1, 0, 4}));
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
 *
 *
 */
class Solution42 {


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
                        rain += (i - before - 1) * ( Math.min(height[before], height[i]) - height[mid]);
                    }
                } else {
                    break;
                }
            }

            ms.push(i);

        }


        return rain;
    }
}