package leetcode.src.main.java.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combinations/
 * 组合问题
 *
 * @author goodtime
 * @create 2023-03-30 17:34
 */
public class Number77 {

    public static void main(String[] args) {
        System.out.println(new Solution77().combine(4, 2));
    }

}


class Solution77 {

    List<List<Integer>> results = new ArrayList<>();
    ArrayList<Integer> list = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {

        backtracking(n, 1, k);

        return results;
    }

    private void backtracking(int n, int k, int startIndex) {

        if (list.size() == k) {
            //收集结果
            results.add(new ArrayList<>(list));
            return;
        }

        for (int i = startIndex; i <= n; i++) {

            //[可选的剪枝操作，减小算法复杂度]
            //这一步的意思是，如果当前的长度，加上后续最长的长度，都小于需要的长度k，那么就没必要往下递归，直接剪掉后续的递归和回溯过程即可
            //eg. [1,2,3,4,5] 当i=3, k=4， list.size()=0的时候, 后续最长的可能数组也就是[3,4,5],还是小于4，
            //于是i=3、i=4和i=5的情况就不需要遍历了
            //这个剪支，也可以直接替代循环里的（i <= n），也就是把i的取值范围条件缩小
            if(list.size() + n - i + 1 < k){
                break;
            }

            //处理节点
            list.add(i);
            //递归调用
            backtracking(n, k, i + 1);
            //回溯操作
            list.remove(new Integer(i));

        }

    }
}