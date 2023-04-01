package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/
 * @author goodtime
 * @create 2023-03-31 12:46
 */
public class Number78 {

    public static void main(String[] args) {
        List<List<Integer>> result = new Solution78().subsets(new int[]{1, 2, 3});
        System.out.println(result.size());
    }
}

class Solution78 {

    ArrayList<List<Integer>> result = new ArrayList<>();

    ArrayList<Integer> subset = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return result;
        }

        backtracking(nums, 0);
        return result;
    }

    private void backtracking(int[] s, int startIndex) {

        //注意一下这里的终止条件，只有等于才终止，但小于的情况，也属于子集
        if (startIndex <= s.length) {
            //不再是在叶子节点才收获结果，而是在每一层递归里都收获结果
            result.add(new ArrayList<>(subset));
            if(startIndex == s.length){
                return;
            }
        }

        for (int i = startIndex; i < s.length; i++) {
            //处理函数
            subset.add(s[i]);
            //递归函数
            backtracking(s, i + 1);
            //回溯
            subset.remove(subset.size() - 1);
        }
    }
}