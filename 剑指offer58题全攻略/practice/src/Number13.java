/**
 * @author goodtime
 * @create 2020-01-19 12:00 下午
 */
public class Number13 {
    public static void main(String[] args) {
        Solution13 solution13 = new Solution13();
        int[] a = {1,4,6,3,7,8,4};
        solution13.reOrderArray(a);
        for (int b:a) {
            System.out.println(b);
        }
    }
}
// 空间换时间，复杂度O(n)
//class Solution13 {
//    public void reOrderArray(int [] array) {
//        int[] odd = new int[array.length];
//        int[] even = new int[array.length];
//        int j = 0;
//        int k = 0;
//        for (int i = 0; i < array.length; i++) {
//            if ((array[i]%2) == 1){
//                odd[j] = array[i];
//                j++;
//            }else {
//                even[k] = array[i];
//                k++;
//            }
//            for (int l = 0; l < j; l++) {
//                array[l] = odd[l];
//            }
//            for (int l = 0; l < k; l++) {
//                array[j+l] = even[l];
//            }
//        }
//
//    }
//}

//前插排序，奇数前插,偶数往后退一次（和前面的偶数交换）
class Solution13{
    public void reOrderArray(int [] array) {
        int k = 0;//记录偶数的个数
        for (int i = 0,temp = 0; i < array.length; i++) {

            if(array[i] % 2 != 0){
                temp = array[i];
                for(int m = 1;m <= k;m++){
                array[i-m+1] = array[i-m];
                }
                array[i-k] = temp;
                continue;
            }
            k++;
        }
        return;
    }
}