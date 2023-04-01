package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum/submissions/
 *
 * @author goodtime
 * @create 2023-03-30 19:24
 */
public class Number39 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution39().combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(lists.size());
    }

}


class Solution39 {

    ArrayList<List<Integer>> result = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        backtracking(candidates, target, 0);

        return result;
    }

    private void backtracking(int[] candidates, int target, int index) {

        //终止条件
        if (sum >= target) {
            if (sum == target) {
                //存储结果
                result.add(new ArrayList<>(list));
            }
            return;
        }

        //循环元素
        for (int i = index; i < candidates.length; i++) {
            //[剪枝]
            if (candidates[i] + sum > target) {
                continue;
            }
            //处理条件
            sum += candidates[i];
            list.add(candidates[i]);
            //正常递归，只是因为值可以重复使用，所以传i
            backtracking(candidates, target, i);
            //回溯
            sum -= candidates[i];
            list.remove(Integer.valueOf(candidates[i]));
        }
    }

}
