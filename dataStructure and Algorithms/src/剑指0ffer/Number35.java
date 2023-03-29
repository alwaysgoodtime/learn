/**
 * @author goodtime
 * @create 2020-01-22 9:32 下午
 */
public class Number35 {
    public static void main(String[] args) {
        Solution35 solution35 = new Solution35();
        int[] a = {1, 2, 3, 0, 1,2};
        int i = solution35.InversePairs(a);
        System.out.println(i);
    }

}

//复杂度为O（n的平方），最直观的解法
//class Solution35 {
//    public int InversePairs(int [] array) {
//        int count = 0;
//        for (int i = 0; i < array.length-1; i++) {
//            for (int j = i+1; j < array.length; j++) {
//                if(array[i] > array[j]){
//                    count++;
//                }
//            }
//        }
//        return count%1000000007;
//    }
//}

//归并排序，在归并的时候顺便统计逆序对

class Solution35 {
    int count = 0;//用于计数，这里用类的属性来实现，保证每次迭代都是同一个值

    public int InversePairs(int[] array) {
        int[] temp = new int[array.length];
        if(array != null && array.length != 0){
            mergeSort(array, 0, array.length - 1, temp);
        }
        return count;
    }

    //分
    private void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int middle = (right + left) / 2;
            mergeSort(array, left, middle, temp);
            mergeSort(array, middle + 1, right, temp);
            merge(array, left, middle, right, temp);
        }
    }

    //治,下面这种是一般治法，即从左边的第一个开始，和右边的第一个比较，为了本题的特殊性（要计算count的值，需要采取
    // 从左边和右边分别从后往前治的思路）
//    private void merge(int[] array, int first, int middle, int end, int[] temp) {
//        int l = 0;
//        int left = first;//左指针
//        int right= middle+1;//右指针
//        while(left <= middle && right <= end){
//            if (array[left] <= array[right]) {
//                temp[l++] = array[left++];
//            } else {
//                count += 1;
//                temp[l++] = array[right++];
//            }
//        }
//        while (left <= middle) {
//            temp[l++] = array[left++];
//
//        }
//        while (right <= end) {
//            temp[l++] = array[right++];
//        }
//        l = 0;
//        while( first <= end) {
//            array[first++] = temp[l++];
//        }
//    }

    private void merge(int[] array, int first, int middle, int end, int[] temp) {
        int all = end-first;
        int l = all;
        int left = middle;//左指针
        int right= end;//右指针
        while(left >= first && right > middle){
            if (array[left] <= array[right]) {
                temp[l--] = array[right--];
            } else {
                if(count>=1000000007)//数值过大求余,一定要求余，否则空间不够，会通不过测试
                {
                    count%=1000000007;
                }
                temp[l--] = array[left--];
            }
        }
        while (left >= first) {
            temp[l--] = array[left--];

        }
        while (right > middle) {
            temp[l--] = array[right--];
        }

        for (int i = 0;i <= all;) {
            array[first++] = temp[i++];
        }
    }
}