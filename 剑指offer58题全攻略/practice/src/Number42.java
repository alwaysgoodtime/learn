import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-24 6:24 下午
 */
public class Number42 {
    public static void main(String[] args) {
        Solution42 solution42 = new Solution42();
        int[] a = {1,2,3,4,5,6};
        ArrayList<Integer> integers = solution42.FindNumbersWithSum(a, 7);
        System.out.println(integers);
    }
}

//思路：首先找到数字S的坐标（保证前面都比S小，后面都比S大、这里用二分查找实现），然后借鉴tcp的滑动窗口思路，在S前面的坐标中设定头指针指向
//第一个元素，尾指针指向S前面的那个元素，如果两者之和比S小，头指针后移，如果两者之和比S大，头指针前移。如果相等，存于两个
//数，然后头指针后移
class Solution42 {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> rt = new ArrayList<>();
        if(array == null || array.length == 0){
            return rt;//程序健壮性，防止后续的空指针异常
        }
        int first = 0;//数组的下标
        int end = array.length-1;//数组的下标
        int middle = (first+end)/2;//存的就是S的指针，如果有S，则返回S的下标，如果数组里没有S，则返回的是S前面那个元素的下标
        while (array[first] < array[end]){
            if(array[middle] == sum){
                break;
            }else if(array[middle] < sum){
                first = middle+1;
                middle = (first+end)/2;
            }else {
                end = middle-1;
                middle = (first+end)/2 ;
            }
        }//middle就是找到的下标

        int i = 0;
        first = array[0];//数组第一个元素
        end = array[middle];//数组的第middle个元素
        while (first < end){
            if(first + end  < sum ){
                i++;
            }else if( first + end > sum){
                middle--;
            }else {
                rt.add(first);
                rt.add(end);
                i++;
                middle--;
            }
            first = array[i];
            end = array[middle];
        }
        if(rt.size() <= 2){
            return rt;
        }else {
            while (rt.size() > 2) {
                if(rt.get(0)*rt.get(1) < rt.get(2)*rt.get(3)){
                    rt.remove(2);
                    rt.remove(2);
                }else {
                    rt.remove(0);
                    rt.remove(0);
                }
            }
            return rt;
        }
    }
}
