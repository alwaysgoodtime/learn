package sort;

/**
 * 直接插入排序,思路：前面是有序表，后面是无序表，无序表中的第一个元素插入有序表中
 * 形成一个新的有序表
 *
 * @author goodtime
 * @create 2020-02-05 3:44 下午
 */
public class StraightInsertionSort {

    public static int[] sort(int[] array) {
        int currentValue;
        for (int i = 1; i < array.length; i++) {
            currentValue = array[i];
            int j = i-1;
            while(j >= 0 && currentValue < array[j]) {
                    array[j + 1] = array[j];
                    j--;
                }//退出条件要么是j<0,要么是currentValue >= array[j]
                array[j+1] = currentValue;
            }
        return array;
    }
}
