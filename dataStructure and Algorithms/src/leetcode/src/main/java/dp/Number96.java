package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/unique-binary-search-trees/
 *
 * @author goodtime
 * @create 2023-03-30 13:29
 */
public class Number96 {

    public static void main(String[] args) {
        int i = 3;
        System.out.println(new Solution96().numTrees(i));
    }

}


/**
 * dp写法（注意，dp写法里是不会使用递归的）
 */
class Solution96 {
    public int numTrees(int n) {

        if(n == 0){
            return 0;
        }

        if(n == 1){
            return 1;
        }

        if(n == 2){
            return 2;
        }

        int[] dp =  new int[n+1];

        //空节点也视为二叉搜索树，所以dp[0]可初始化为1
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }

        return dp[n];
    }

    //另一种写法
    public int numTreess(int n) {

        if(n == 0){
            return 0;
        }

        if(n == 1){
            return 1;
        }

        if(n == 2){
            return 2;
        }

        int[] dp =  new int[n+1];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = 2 * dp[i-1];
            for (int j = 2; j < i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }

        return dp[n];
    }
}
