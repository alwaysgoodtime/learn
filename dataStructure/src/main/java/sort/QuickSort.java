package sort;

import com.sun.scenario.effect.Brightpass;
import sun.util.resources.sr.CalendarData_sr;

/**
 * 快排，算法复杂度O（nlogn），直接用了数组的第一个值作为中间值,基准可以是第一个值，也可以是最后一个值
 * 这个版本用的是第一个值
 *
 * @author goodtime
 * @create 2020-02-05 7:05 下午
 */
public class QuickSort {

    //多写一个这个类，让用户在用sort时，就不用传需要排序的数组长度，和其他排序方法接口统一起来。
    public static int[] sort(int[] array){
        return  sort(array, 0, array.length - 1);
    }
       /**
     *
     * @param array 要处理的数组
     * @param first 分治开数组的头索引
     * @param last  分治开数组的尾索引
     * @return
     */


    public static int[] sort(int[] array, int first, int last) {
        if (last < 0 || last - first < 1) {
            return array;//递归终止条件
        }
        // pivot 中轴,把第一个值视为中轴的值
        int pivot = array[first];
        //      开始左右分治
        int j = first;
        int l = last;
        while (true) {
            while (array[l] >= pivot && l > j) {
                l--;
            }
            if (l <= j) {
                array[j] = pivot;
                sort(array, first, j - 1);
                sort(array, j + 1, last);
                break;
            }
            if (array[l] < pivot) {
                array[j] = array[l];
                j++;
            }
            while (array[j] < pivot && l > j) {
                j++;
            }
            if (l <= j) {
                array[l] = pivot;
                sort(array, first, l - 1);
                sort(array, l + 1, last);
                break;
            }
            if (array[j] >= pivot) {
                array[l] = array[j];
                l--;
            }
        }
        return array;
    }
}
