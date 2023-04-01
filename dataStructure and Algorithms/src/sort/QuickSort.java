package sort;

/**
 * 快排，算法复杂度O（nlogn），直接用了数组的第一个值作为中间值,基准可以是第一个值，也可以是最后一个值
 * 这个版本用的是第一个值
 *
 * @author goodtime
 * @create 2020-02-05 7:05 下午
 */
public class QuickSort {

    //多写一个这个类，让用户在用sort时，就不用传需要排序的数组长度，和其他排序方法接口统一起来。
    public static int[] sort(int[] array) {
        return sort2(array, 0, array.length - 1);
    }

    /**
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



    public static int[] sort2(int[] array, int first, int last) {

        if (first >= last) {
            return array;
        }

        //双边指针（交换法）
        int pivotIndex = findPivotIndex(array, first, last);

        sort2(array, first, pivotIndex - 1);
        sort2(array, pivotIndex + 1, last);

        return array;

    }

    private static int findPivotIndex(int[] array, int first, int last) {
        int pivot = array[first];//开始以第一个元素为轴值
        int leftPointer = first;
        int rightPointer = last;

        while (leftPointer < rightPointer) {
            //从右往左找比pivot小或相等的值
            while (leftPointer < rightPointer && pivot < array[rightPointer]) {
                rightPointer--;
            }
            //从左往右找比pivot大的值,记得一定是这里为>=，否则会出问题，eg.[2,3,7,6]
            //因为如果是从右往左<=,会存在轴值已经和array[first]交换过值的情况，最后又交换一遍值,使得原本轴值被覆盖
            while (leftPointer < rightPointer && pivot >= array[leftPointer]) {
                leftPointer++;
            }

            //两边指针未越界
            if(leftPointer < rightPointer){
                int tmp = array[leftPointer];
                array[leftPointer] = array[rightPointer];
                array[rightPointer] = tmp;
            }
        }

        //此时左指针下标等于右指针，这一点就是轴坐标，左边的值都比它小，右边的值都比它大
        //记得把轴值（即目前的pivot所指的值与当前值互换）
        array[first] = array[leftPointer];
        array[leftPointer] = pivot;
        return leftPointer;

    }
}
