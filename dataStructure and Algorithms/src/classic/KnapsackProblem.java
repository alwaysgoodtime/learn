package classic;

import java.util.Arrays;

/**
 * 背包问题，动态规划的经典案例，下一次需要的值依赖上一次分开的结果
 * 这里是01背包，也就是说，每个东西只能放一次
 *
 * @author goodtime
 * @create 2020-02-07 1:39 上午
 */
public class KnapsackProblem {
    /**
     * @param value          需要依次传入不同东西的价格，比如吉他1500，钢笔2000，电脑3000，默认不同商品价格不同
     * @param weight         需要一次传入不同东西的重量，比如吉他1斤，钢笔2斤，电脑4斤
     * @param knapsackWeight 需要传入背包所能容纳的最大重量
     * @return 返回此时背包中能装的东西的最大价值，也可以找到装最大价值东西的组合策略，按需返回即可
     */
    public static int knapsackProblem(int[] value, int[] weight, int knapsackWeight) {


        int m = value.length;//没有相同价格的商品，所以value.length商品个数

        //第一步：建立一会儿要填的表（一行一行填）
        int[][] a = new int[m + 1][knapsackWeight + 1];

        //第二步：初始化第一行和第一列都为0（第一列为0，即背包的容纳重量为0，默认其中容纳商品总价值为0）
        //第一行为0，即不放入任何商品，容纳商品总价值为0；
        //因为二维数组的元素默认值都为0，此步跳过

        int[][] path = new int[m+1][knapsackWeight + 1]; //记录填表的策略。

        //第三步：填表
        for (int i = 1; i < a.length; i++) {//先遍历行的每一列，再遍历下一行，第一行跳过（为0）
            for (int j = 1; j < a[0].length; j++) {//遍历列，第一列跳过（为0),列代表重量
                if (weight[i - 1] > j) {//重量太大
                    a[i][j] = a[i - 1][j];
                } else {
                    if (value[i - 1] + a[i - 1][j - weight[i - 1]] <= a[i - 1][j]) {
                        //本行物品的价值+上一行能装下的物品的价值<没有这个物品时此背包容量下的最大价值
                        a[i][j] = a[i - 1][j];//这种情况下，新一行的商品并没有往背包放，最佳策略是上一行此重量下的策略
                    } else {
                        a[i][j] = value[i - 1] + a[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;//只有这种情况下才是真正放商品进去
                    }
                }
            }
        }
        for (int[] ints: a
             ) {
            System.out.println(Arrays.toString(ints));//查看填之后的表
        }

        //追踪最佳策略，也就是填表后最右下角的背包里塞了什么东西。要么最后一行的东西往里塞了，要么就是延续上一行最后一列的策略
        int l = path.length-1;
        int k = path[0].length-1;
        while (l > 0 && k > 0){
                if(path[l][k] == 1){//说明此物品是最后放进背包去的,在最佳策略的组成部分里面
                    System.out.println("装入了第"+l+"个商品");
                    k = knapsackWeight - weight[l-1];//找到这个物品后，把物品本身的重量减掉。
                }
                l--;//要么最后一个物品放进了背包，最后一个元素直接就是1，要么延续了上一行最后一列的策略，所以直接i--即可。
            //后续查找中，因为重量已经被削走了，基本上直接就能定位到装入背包的商品位置。
        }
        return a[m][knapsackWeight];
    }
}
