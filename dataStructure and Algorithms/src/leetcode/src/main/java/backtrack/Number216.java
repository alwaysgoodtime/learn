package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum-iii/
 * @author goodtime
 * @create 2023-03-30 18:46
 */
public class Number216
{
    public static void main(String[] args) {
        System.out.println(new Solution216().combinationSum3(3, 9));
    }

}


class Solution216 {

    List<List<Integer>> results = new ArrayList<>();
    ArrayList<Integer> list = new ArrayList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum3(int k, int n) {

        backtracking(n, k, 1);

        return results;
    }

    private void backtracking(int n, int k, int startIndex) {

        if (list.size() == k && sum == n) {
            //收集结果
            results.add(new ArrayList<>(list));
            return;
        } else if (list.size() == k) {
            return;
        }

        //可以做两次剪枝，一个是sum + i <= n, 一个是 i <=  9 - k + list.size() + 1
        for (int i = startIndex; sum + i <= n && i <= 9 - k + list.size() + 1 ; i++) {
            //处理节点
            list.add(i);
            sum += i;
            //递归调用
            backtracking(n, k, i + 1);
            //回溯操作
            sum -= i;
            list.remove(new Integer(i));

        }

    }
}
