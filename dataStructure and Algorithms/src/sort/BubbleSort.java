package sort;

import java.util.Arrays;

/**
 * @author goodtime
 * @create 2020-02-05 1:31 下午
 */

//冒泡排序，设置flag提高速度，算法复杂度O(n平方) 最好情况是O(n)，循环过，最差是O(n平方)
//空间复杂度是O(1),本例中我为了不动原来的数组，拷贝了一份新数组，所以是o(n)。后面不再做此操作
//空间复杂度是辅助空间所占的额外空间
public class BubbleSort {
    public static int[] sort(int[] test) {
        //int[] a = test ;//如果用这种方式，对arrayList1的操作，最后会影响
        //原来的arraylist，因为是传地址，可以进行拷贝后再排序
        int count = 0;//记录排序的次数
        int[] ints = Arrays.copyOf(test, test.length);
        for (int i = 0; i < ints.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            //这个可以提高排序的速度，没有这个flag会慢一些
            boolean flag = true;
            for (int j = ints.length - 1; j > 0; j--) {//从后往前冒泡，第一个值最大
                if (ints[j] > ints[j - 1]) {//冒泡排序一定是两两排序，且稳定
                    count++;
                    int temp = ints[j - 1];
                    ints[j - 1] = ints[j];
                    ints[j] = temp;
                    flag = false;
                }
            }

            if (flag) {
                break;
            }
        }

        System.out.println("总排序了" + count + "次");//看看排序了几次


        return ints;
    }
}
