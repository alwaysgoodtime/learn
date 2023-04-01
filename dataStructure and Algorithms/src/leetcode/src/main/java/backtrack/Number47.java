package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations-ii/
 *
 * @author goodtime
 * @create 2023-03-31 15:55
 */
public class Number47 {

    public static void main(String[] args) {
        List<List<Integer>> result = new Solution47().permuteUnique(new int[]{1, 2, 3, 1, 1, 1});
        System.out.println(result.size());
    }
}

/**
 * 有重复元素，需要去重
 */
class Solution47 {

    ArrayList<List<Integer>> result = new ArrayList<>();

    ArrayList<Integer> perm = new ArrayList<>();

    //因为一个元素不能用两次，可以用used帮助记录用过的元素
    ArrayList<Integer> used = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {

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

        ArrayList<Integer> curNum = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            //同一元素不能用两次
            if(used.contains(i)){
                continue;
            }

            //同一层没必要递归该层之前已经递归过的相同数字
            if(curNum.contains(nums[i])){
                continue;
            }

            curNum.add(nums[i]);
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