package sort;

import java.util.Arrays;

/**
 * 希尔排序，也叫缩小增量排序
 * 有两种实现方法，移动法和交换法
 * 平均时间复杂度o（nlogn）
 *
 * @author goodtime
 * @create 2020-02-05 4:33 下午
 */
public class ShellSort {
    //    移动法，运算比较慢，在每组做直接插入排序时，不停地交换，并非前面所讲的直接插入排序
    public static int[] sortlow(int[] array) {
        int tmp;

        for (int gap = array.length / 2; gap > 0; gap /= 2) {//步长每次除以2向下取整，为0时结束
            for (int i = gap; i < array.length; i++) {//每次步长分组后，每个组最小的下标，这里其实就是在分组做直接插入排序
                //只不过是分组做直接插排
                for (int j = i - gap; j >= 0; j -= gap) {//遍历每个组的值，让大的往前面放
                    //写成j=i-gap，然后判断数组j+gap的下标，可以防止数组越界异常，eg，gap为5，数组长度为10，
                    //如果写成j=i，也就是j=5， 会判断两次，一次是j=5，第二次是j=0，这是array[j-gap]就会报越界异常
                    if (array[j] > array[j + gap]) {
                        tmp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = tmp;
                    }
                }
            }
            System.out.println(Arrays.toString(array));
        }

        return array;
    }

    //运行速度更快，里面的直接插入排序就是我们前面所讲的，先找到位置，再移动，而非让无序表中的元素和有序表里的元素不停交换，移动法
    public static int[] sort(int[] array) {
        int tmp;

        for (int gap = array.length / 2; gap > 0; gap /= 2) {//步长每次除以2向下取整，为0时结束
            for (int i = gap; i < array.length; i++) {//每次步长分组后，每个组最小的下标，这里其实就是在分组做直接插入排序
                //分组做直接插排,移动法,思路和直接插排一模一样的
                tmp = array[i];
                int j = i - gap;
                while (j >= 0 && array[j] > tmp) {
                    array[j + gap] = array[j];
                    j = j - gap;
                }
                array[j+gap] = tmp;
            }
            System.out.println(Arrays.toString(array));
        }

        return array;
    }

}

