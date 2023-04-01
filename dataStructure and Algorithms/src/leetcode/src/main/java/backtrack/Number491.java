package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/
 *
 * @author goodtime
 * @create 2023-03-31 12:46
 */
public class Number491 {

    public static void main(String[] args) {
        List<List<Integer>> result = new Solution491().findSubsequences(new int[]{1, 2 ,3 , 1, 1,1 });
        System.out.println(result.size());
    }
}

class Solution491 {

    ArrayList<List<Integer>> result = new ArrayList<>();

    ArrayList<Integer> subset = new ArrayList<>();


    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length == 0) {
            return result;
        }

        backtracking(nums, 0);
        return result;
    }

    /**
     * 核心还是去重逻辑，还是树层去重、树枝不去重，和组合里的问题一样（Number 40）
     * 但不能重新排序，比如[3,2,6]排序后变为[2,3,6]
     * 虽然[2,3]是[2,3,6]的递增子序列，但是2之前在3前，所以并不是[3,2,6]的递增子序列
     *
     * 这里的树枝去重，是每一层维护了一个curDepthList，让前面处理过的元素，后面如果在其中出现，就自动剪枝
     */
    private void backtracking(int[] s, int startIndex) {

        //注意一下这里的终止条件，只有等于才终止，但小于的情况，也属于子集
        if (startIndex <= s.length && subset.size() >= 2) {
            //不再是在叶子节点才收获结果，这一题是在第二层及以后的每一层递归里都收获结果
            result.add(new ArrayList<>(subset));
            if (startIndex == s.length) {
                return;
            }
        }

        ArrayList<Integer> curDepthList = new ArrayList<>();

        for (int i = startIndex; i < s.length; i++) {

            //树层去重
            if (curDepthList.contains(s[i])) {
                continue;
            }

            //处理函数
            if (isOrdered(subset, s[i])) {
                subset.add(s[i]);
                curDepthList.add(s[i]);
                //递归函数
                backtracking(s, i + 1);
                //回溯
                subset.remove(subset.size() - 1);
            }
        }
    }

    //该数加入前面的子序列，是否能形成递增子序列
    private boolean isOrdered(ArrayList<Integer> subset, int num) {
        return subset.size() == 0 || subset.get(subset.size() - 1) <= num;
    }
}