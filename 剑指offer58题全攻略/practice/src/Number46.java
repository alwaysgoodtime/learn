/**
 * @author goodtime
 * @create 2020-01-26 3:33 下午
 */
public class Number46 {
    public static void main(String[] args) {
        Solution46 solution46 = new Solution46();
        int i = solution46.LastRemaining_Solution(5, 3);//2
        System.out.println(i);
    }
}
//思路：让ints数组存每个人是否被择出去，如果择出去，则数组中它对应的值变为1，循环n-1次后（n个小朋友），剩下值为0的就是
//最后一个小朋友
class Solution46 {
    public int LastRemaining_Solution(int n, int m) {//数组编号是0~n-1，m是报数的值。
        if (n <= 0) {
            return -1;//没有数，直接返回-1
        }
        short[] ints = new short[n];
        for (int i = 0,l = 0,count = 0; l < n - 1; l++) {//循环n-1次，留一个ints数组中的元素值为0;
            for (;count <= m-1;count++,i++) {//count就是计数器，找个一个小朋友就加1
                if (i == n) {//如果小朋友编号为n，意思是循环完一圈，从第1个小朋友（编号为0）重新数起
                    i = 0;
                }
                if(ints[i] == 1){
                    count--;//意思是该小朋友已经被择出去，count--,结束本次循环后count++，意味着没算这个小朋友
                }

            }
            count = 0;//重置计数器
            ints[i - 1] = 1;//返回的i值是选中小朋友后边的那个人，所以要减1，如果i变成n，说明选中的是最后一个小朋友，
            //不用做特殊处理，而且i最小为1，不可能为0
        }
        for (int j = 0; j < ints.length; j++) {
            if (ints[j] == 0) {
                return j;
            }
        }
        return -1;//无实际意义，不会执行此语句
    }
}
