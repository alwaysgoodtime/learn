package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

import static sort.QuickSort.sort;

/**
 * https://leetcode.cn/problems/combination-sum-ii/
 *
 * @author goodtime
 * @create 2023-03-30 19:24
 */
public class Number40 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution40().combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        System.out.println(lists.size());
    }

}


class Solution40 {

    ArrayList<List<Integer>> result = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    int sum = 0;
    //保存用过的数组元素下标，为了区分树层重复与树枝重复
    ArrayList<Integer> used = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        //先进行排序，方便去重
        int[] sort = sort(candidates);
        backtracking(sort, target, 0);

        return result;
    }

    private void backtracking(int[] candidates, int target, int startIndex) {

        //终止条件
        if (sum >= target) {
            if (sum == target) {
                //存储结果
                result.add(new ArrayList<>(list));
            }
            return;
        }

        //循环元素
        for (int i = startIndex; i < candidates.length; i++) {

            //树层去重，但不能树枝去重
            if (i >= 1 && candidates[i] == candidates[i - 1] && !used.contains(i - 1)) {
                continue;
            }

            //[剪枝]
            if (candidates[i] + sum > target) {
                continue;
            }

            //处理条件
            sum += candidates[i];
            list.add(candidates[i]);
            used.add(i);
            //正常递归
            backtracking(candidates, target, i + 1);
            //回溯
            sum -= candidates[i];
            list.remove(Integer.valueOf(candidates[i]));
            used.remove(Integer.valueOf(i));
        }
    }

}
