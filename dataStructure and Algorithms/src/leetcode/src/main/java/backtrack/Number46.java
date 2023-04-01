package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations/
 *
 * @author goodtime
 * @create 2023-03-31 15:55
 */
public class Number46 {

    public static void main(String[] args) {
        List<List<Integer>> result = new Solution46().permute(new int[]{1, 2, 3, 1, 1, 1});
        System.out.println(result.size());
    }
}

/**
 * 没有重复元素，不用去重
 */
class Solution46 {

    ArrayList<List<Integer>> result = new ArrayList<>();

    ArrayList<Integer> perm = new ArrayList<>();

    //因为一个元素不能用两次，可以用used帮助记录用过的元素
    //当然，简单题里，如本题，因为没有重复元素，可以用perm一并完成此功能
    ArrayList<Integer> used = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        if (nums == null || nums.length == 0) {
            return result;
        }

        backtracking(nums);

        return result;


    }

    private void backtracking(int[] nums) {

        //终止条件
        if (perm.size() == nums.length) {
            //收集
            result.add(new ArrayList<>(perm));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //处理
            if(used.contains(i)){
                continue;
            }
            perm.add(nums[i]);
            used.add(i);
            //递归
            backtracking(nums);
            //回溯
            perm.remove(perm.size() - 1);
            used.remove(used.size() - 1);
        }


    }
}