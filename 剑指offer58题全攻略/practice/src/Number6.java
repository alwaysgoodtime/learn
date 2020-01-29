import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-18 5:02 下午
 */
public class Number6 {
    public static void main(String[] args) {
        Solution6 solution6 = new Solution6();
        int[] ints = {1,2,3,4,5,6,0};
        System.out.println(solution6.minNumberInRotateArray(ints));


    }
}

//这种方案是暴力查找，估计耗费时间太久，通不过测试
//class Solution6 {
//    public int minNumberInRotateArray(int [] array) {
//        if(array != null && array.length == 0){
//        return 0;}
//        int i = 0;
//        int j = 1;
//        for(;array[i] < array[j];i++,j++){
//        }
//        return array[j];
//    }}


//从后往前找
class Solution6 {
    public int minNumberInRotateArray(int[] array) {
        if(array.length == 0){
        return 0;}
        int i = array.length-1;
        int j = i-1;
        for(;array[i] >= array[j];i--,j--){
        }
        if(array[j] > array[i]){
            return  array[i];
        }
        return array[j];
    }
}

//二分查找（注意可能有重复元素、暂未完成）
//class Solution6 {
//    public int minNumberInRotateArray(int[] array) {
//        if(array.length == 0){
//        return 0;}
//        int j = array.length;
//        int k = 0;
//        int i = array.length/2;
//        for (;;) {
//            if(array[i] > array[0])
//                i = (j + i)/2;
//            else if(array[i] < array[0]) {
//                i = (j - i) / 2;
//            }else if(array[i] == array[0]){
//                return 0;
//            }
//        }
//
//    }

