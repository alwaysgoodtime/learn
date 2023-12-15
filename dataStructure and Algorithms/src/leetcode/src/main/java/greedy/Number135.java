package leetcode.src.main.java.greedy;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/candy/
 *
 * @author goodtime
 * @create 2023-04-01 10:53
 */
public class Number135 {
    public static void main(String[] args) {
        System.out.println(new Solution135().candy(new int[]{1,2,87,87,87,2,1}));
    }
}


/**
 * 思路：每个孩子都给发一个，然后从前往后发，能少发就少发
 * 如果是后面孩子比前面孩子大，比如 [2,3]的情况，给3这个孩子多发一个
 *
 * 如果是后面孩子比前面孩子小，如果是[3,2]的情况，看是否需要给3这个孩子补偿一个，同时看看3前面的孩子是否需要补偿
 *
 * 比如[4,3,2]，显然需要给3补偿1个，同时连着4，所以给4也补一个
 *
 * 比如[2,3,2]，3这个孩子有两个，不用多补
 *
 * 比如[3,3,2],第二个3只有1个，那么就需要补，而前面第一个3不需要补发
 *
 * 如果是后面孩子和前面孩子相当，不需要处理
 *
 *
 *
 *
 * 思路2：
 *
 * 我们可以将「相邻的孩子中，评分高的孩子必须获得更多的糖果」这句话拆分为两个规则，分别处理。
 *
 * 左规则：当 ratings[i−1]<ratings[i]\textit{ratings}[i - 1] < \textit{ratings}[i]ratings[i−1]<ratings[i] 时，iii 号学生的糖果数量将比 i−1i - 1i−1 号孩子的糖果数量多。
 *
 * 右规则：当 ratings[i]>ratings[i+1]\textit{ratings}[i] > \textit{ratings}[i + 1]ratings[i]>ratings[i+1] 时，iii 号学生的糖果数量将比 i+1i + 1i+1 号孩子的糖果数量多。
 *
 * 作者：力扣官方题解
 * 链接：https://leetcode.cn/problems/candy/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * 这样，用两个数组，让学生分别满足这两个规则，学生们满足这两个规则的两个值的最大值即可。
 *
 *
 *
 *
 *
 *
 *
 */
class Solution135 {

    public int candy(int[] ratings) {

        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        if (ratings.length == 1) {
            return 1;
        }

        int candy = 0;
        int[] candyArray = new int[ratings.length];
        Arrays.fill(candyArray, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candyArray[i] = candyArray[i - 1] + 1;
            }

            if (ratings[i] < ratings[i - 1]) {
                compensation(ratings, candyArray, i - 1);
            }

        }

        for (int j : candyArray) {
            candy += j;
        }

        return candy;
    }

    private void compensation(int[] ratings, int[] candyArray, int endIndex) {

        for (int i = endIndex; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candyArray[i] == candyArray[i + 1]) {
                candyArray[i]++;
            } else {
                break;
            }
        }
    }
}
