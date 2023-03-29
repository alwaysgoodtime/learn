package sort;

import java.util.ArrayList;

/**
 * 桶排序，一般用于数据比较均匀的时候
 *
 * @author goodtime
 * @create 2020-02-06 12:37 上午
 */
public class BucketSort {
    public static int[] sort(int[] array) {
        //第一步：确定要几个桶。
        //让数字分别落在这些桶里，桶不要太多（占空间），也不能太少（桶中要装的元素太多）
        //比如假设排100个数字（0—99），可以用10个桶装，每个桶依次映射10个数字。
        //这里我们用（数组最大值-数组最小值）/数组长度+1，计算桶的数量。


        int min = array[0];//存放数组最小值
        int max = array[0];//存放数组最大值

        for (int i = 0; i < array.length; i++) {
            min = min < array[i] ? min : array[i];
            max = max > array[i] ? max : array[i];
        }

        int bucketTotalNumber = (max - min) / array.length + 1;

        //用ArrayList建桶的桶（也可以用int[]建，因为其中装的桶数量已经确定）
        ArrayList<ArrayList> buckets = new ArrayList<ArrayList>(bucketTotalNumber);

        //把桶装入桶的桶
        for (int l = 0; l < bucketTotalNumber; l++) {
            buckets.add(new ArrayList()); //注意，要初始化buckets中的动态数组，虽然它有容量，但是创建时什么也没装
        }

        //第二步，把原来的数组放入桶

        for (int i = 0; i < array.length; i++) {
            //确定该装哪个桶
            int bucketNumber = (array[i] - min) / array.length;
            buckets.get(bucketNumber).add(array[i]);
        }

        //第三步，如果桶不为空，帮桶中的数字排序，桶排序稳定不稳定，主要看这一步进行时，采用哪种排序算法
        //这里采用插入排序，是稳定的

        for (int i = 0, index = 0; i < bucketTotalNumber; i++) {//index对应原来数组的下标

            ArrayList bucket = buckets.get(i);

            if (bucket.size() != 0) {
                //自己实现的简单插入排序只能排数组，所以转换一下
                int[] tmp = new int[bucket.size()];

                //因为int[]和integer[]不能转化的缘故，不能用java提供的原生方法，只能笨拙地转换了
                for (int j = 0; j < bucket.size(); j++) {
                    tmp[j] = (Integer) bucket.get(j);
                }

                StraightInsertionSort.sort(tmp);
                //第四步：按顺序遍历所有桶，返回数组（这里用了些技巧，给桶排好序后直接输出到原数组里）

                for (int num : tmp
                ) {
                    array[index++] = num;
                }

            }
        }

        return array;
    }
}
