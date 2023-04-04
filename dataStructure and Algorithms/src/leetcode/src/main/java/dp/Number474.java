package leetcode.src.main.java.dp;

/**
 * https://leetcode.cn/problems/ones-and-zeroes/
 *
 * @author goodtime
 * @create 2023-04-03 18:36
 */
public class Number474 {

    public static void main(String[] args) {
        System.out.println(new Solution474().findMaxForm(new String[]{
                "10", "0001"
        }, 1, 2));
    }

}

/**
 * dp[i][j][k]含义：随机放入下标为[0,i]之间的数，在容器[j][k]中，能放的数的最大价值
 *
 * 把数字的价值视为1
 * 数字有两个属性，一个是0的数量，一个是1的数量
 * 对应背包也有两个属性，1个是0的数量，1个是1个数量（类似于背包有长和宽两种属性
 * 每个数字也有长和宽两种属性）
 *
 * 本质上还是01背包，每个数字只能使用一次
 *
 * 下面是三维数组的做法，也可以压缩成二维滚动数组，只能遍历需要从后往前遍历了
 */
class Solution474 {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null) {
            return 0;
        }

        if (m < 0 || n < 0) {
            return 0;
        }

        //拍平strs
        int[][] zeroOneArray = new int[strs.length][2];

        for (int i = 0; i < strs.length; i++) {
            int zeroCount = 0;
            int oneCount = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                char c = strs[i].charAt(j);
                if (c == '0') {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }
            zeroOneArray[i][0] = zeroCount;
            zeroOneArray[i][1] = oneCount;
        }


        //dp[i] 前i个元素中0个数不超过m，1个数不超过n的最多元素个数
        int dp[][][] = new int[strs.length][m + 1][n + 1];

        //初始化
        for (int i = zeroOneArray[0][0]; i <= m; i++) {
            for (int j = zeroOneArray[0][1]; j <= n; j++) {
                dp[0][i][j] = 1;
            }
        }

        //递归
        for (int i = 1; i < strs.length; i++) {//物品
            for (int j = 0; j <= m; j++) {//背包
                for (int k = 0; k <= n; k++) {//背包
                    int unPut = dp[i - 1][j][k];
                    int put = 0;
                    if (j >= zeroOneArray[i][0] && k >= zeroOneArray[i][1]) {
                        put = dp[i - 1][j - zeroOneArray[i][0]][k - zeroOneArray[i][0]] + 1;
                    }
                    dp[i][j][k] = Math.max(unPut,put);
                }
            }
        }

        return dp[strs.length - 1][m][n];


    }
}