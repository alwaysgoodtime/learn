import java.util.ArrayList;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * @author goodtime
 * @create 2020-01-18 5:02 下午
 */
public class Number6 {
    public static void main(String[] args) {
        Solution6s solution6 = new Solution6s();
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



class Solution6s {
    public int minNumberInRotateArray(int [] array) {
        if(array == null || array.length == 0){
            return 0;
        }
        if(array[0] < array[array.length-1]){
            return array[0];//特殊情况，旋转所有的元素，所以第一个为最小
        }

        //二分查找，注意可能有重复元素的情况，需要特殊处理
        int i =  find(array,0,array.length-1);
        return i;
    }

    private int find(int[] array, int start, int end) {
        if(start < end){
            int middle = (start+end)/2;
            if(array[middle] < array[0]){
               return find(array,start,middle);
            }else if(array[middle] == array[0]){//可能有重复元素，而且不知道是前面的数小还是后面的数小,无法二分
                int afterDiff = 0;
                int beforeDiff = 0;
                for (int i = middle; i < array.length; i++) {
                    if(array[middle] != array[i] || i == array.length-1){//防止后面和middle的值都一样
                        afterDiff = array[i];
                        break;
                    }
                }
                for (int i = middle; i >= 0; i--) {
                    if(array[middle] != array[i] || i == 0){//防止前面和middle的值都一样
                        beforeDiff = array[i];
                        break;
                    }
                }
                return beforeDiff < afterDiff ? beforeDiff : afterDiff;
            }
            else {
               return find(array,middle+1,end);
            }
        }else{
            return array[end];
        }
    }
}

