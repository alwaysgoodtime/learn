package search;

/**插值查找，二分查找的改进版，也叫自适应查找，
 * 关键是取中值的方式发生了变化。
 * 注意和二分查找退出循环条件的区别
 * 用low>high退出二分查找，可能会发生死循环。
 * @author goodtime
 * @create 2020-02-06 1:12 下午
 */
public class InterpolationSearch {
    public static int search(int[] array,int target){
        //循环实现
        int low = 0;
        int high = array.length - 1;
        int middle = low + (target-array[low])/(array[high]-array[low])*(high-low);
        while (low != high && array[low] >= 0 && array[high] < array.length) {
            if (array[middle] < target) {
                low = middle + 1;
                middle = low + (target-array[low])/(array[high]-array[low])*(high-low);
            } else if (array[middle] > target) {
                high = middle - 1;
                middle = low + (target-array[low])/(array[high]-array[low])*(high-low);
            } else {
                while (--middle >= 0 && array[middle] == target) {//用循环，返回有序数组中目标值的第一次出现
                    //注意middle<0的边界情况
                }
                return middle + 1;
            }
        }

        //这里还有low==high的情况为判断，不能在循环里判断的原因是，万一low == high，还要计算middle，可能会除0异常
        if(low == high && array[low] == middle){
            while (--middle >= 0 && array[middle] == target) {
            }
            return middle + 1;
        }
        return -1;
    }
}
