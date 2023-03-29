/**
 * @author goodtime
 * @create 2020-01-20 7:58 下午
 */
public class Number28 {
    public static void main(String[] args) {
        Solution28 solution28 = new Solution28();
        int[] a = {1,2,3,5,1,5,6};
        int i = solution28.MoreThanHalfNum_Solution(a);
        System.out.println(i);
    }
}
//也可以先用排序算法，如果有个数在数组中超过一半，它一定是数组中间的那个数。对于java来说，有Array.sort方法，可以方便的对数组进行排序。

class Solution28 {
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array == null){
            return 0;
        }
        int j = array.length%2 == 0 ? array.length/2 : array.length/2 +1;

//多添加一个数组，存储已经搜索过的值，后续遍历的时候先遍历这个数组，如果在其中就直接pass掉，用动态数组存更好，它自带方法。
//可惜牛客网不让导包

        int[] store = new int[j];

        for (int i = 0,count = 0; i < j; i++) {
            out:for(int l = i;l < array.length;l++){
                if (array[i] == array[l]){
                    count ++;
                    store[i] = array[i];
                    for (int k = 0; k < i; k++) {
                        if(store[k] == store[i]){
                            break out;
                        }
                    }
                }
                if(count > array.length/2){
                    return array[i];
                }
            }
            count = 0;
        }

        return 0;
    }
}

