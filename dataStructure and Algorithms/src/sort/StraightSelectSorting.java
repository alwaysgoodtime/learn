package sort;

/**直接选择排序，时间复杂度o（n方），空间复杂度o（1），存放额外的空间
 * 思路：min中放最小的，找到数组0-n最小的，放第一位，然后遍历1-n中次小的，放第二位
 * @author goodtime
 * @create 2020-02-05 3:17 下午
 */
public class StraightSelectSorting {
    public static int[] sort(int[] array){

        int min;//存放最小值
        int minindex = 0;//存放最小值所在的下标，方便交换
        for (int i = 0; i < array.length; i++) {
            min = array[i];
            for (int j = i+1; j < array.length; j++) {
                if(min > array[j]){//改成<就是从大到小
                    min = array[j];//选择那个最小的
                    minindex = j;
                }
            }
            if(min != array[i]){
                array[minindex] = array[i];
                array[i] = min;
            }
        }

        return array;

    }
}
