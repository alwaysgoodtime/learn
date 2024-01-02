package leetcode.src.main.java.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/lexicographical-numbers/
 *
 * @author goodtime
 * @create 2024-01-02 12:01
 */
public class Number386 {
    public static void main(String[] args) {
        System.out.println(new Solution386().lexicalOrderIteration(99));
    }
}

/**
 * 可以视为每个树节点都有10个子节点，从0到9，做深度遍历即可(注意：不是广度遍历！！！)
 * 结束条件为已经收集到n个值
 */
class Solution386 {

    List<Integer> result = new ArrayList<>();

    //迭代写法，核心是：共有 n 个数需要被处理，假设当前处理到的数为 j，
    // 根据字典序规则，在满足条件的前提下，我们优先在 j 的后面添加 0（即 j∗10<n 满足的话），否则我们考虑将上一位回退并进行加一操作。
    //参考
    //作者：宫水三叶
    //链接：https://leetcode.cn/problems/lexicographical-numbers/solutions/1/by-ac_oier-ktn7/
    public List<Integer> lexicalOrderIteration(int n) {

        int j = 1;

        for (int i = 0; i < n; i++) {

            result.add(j);

            if (j * 10 <= n) {
                j = j * 10;
            } else {
                while (j % 10 == 9 || j + 1 > n) {
                    j = j / 10;
                }
                j++;
            }
        }


        return result;
    }


    //递归写法
    public List<Integer> lexicalOrder(int n) {

        for (int i = 1; i <= 9; i++) {

            if (i > n) {
                break;
            }

            dfs(i, n);
        }

        return result;
    }

    private void dfs(int num, int n) {

        result.add(num);

        for (int i = 0; i <= 9; i++) {

            if (num * 10 + i > n) {
                break;
            }

            dfs(num * 10 + i, n);
        }

    }
}
