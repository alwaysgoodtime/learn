package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets-ii/
 *
 * @author goodtime
 * @create 2023-03-31 12:46
 */
public class Number90 {

    public static void main(String[] args) {
        List<List<Integer>> result = new Solution90().subsetsWithDup(new int[]{1, 2, 3});
        System.out.println(result.size());
    }
}

class Solution90 {

    ArrayList<List<Integer>> result = new ArrayList<>();

    ArrayList<Integer> subset = new ArrayList<>();

    ArrayList<Integer> used = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return result;
        }
        sort.QuickSort.sort2(nums, 0, nums.length - 1);
        backtracking(nums, 0);
        return result;
    }

    /**
     * 核心是去重逻辑，还是树层去重、树枝不去重，和组合里的问题一样（Number 40）
     *
     * @param s
     * @param startIndex
     */
    private void backtracking(int[] s, int startIndex) {

        //注意一下这里的终止条件，只有等于才终止，但小于的情况，也属于子集
        if (startIndex <= s.length) {
            //不再是在叶子节点才收获结果，而是在每一层递归里都收获结果
            result.add(new ArrayList<>(subset));
            if (startIndex == s.length) {
                return;
            }
        }

        for (int i = startIndex; i < s.length; i++) {

            //树层去重
            if (i >= 1 && s[i] == s[i - 1] && !used.contains(i - 1)) {
                continue;
            }

            //处理函数
            subset.add(s[i]);
            used.add(i);
            //递归函数
            backtracking(s, i + 1);
            //回溯
            subset.remove(subset.size() - 1);
            used.remove(used.size() - 1);
        }
    }
}