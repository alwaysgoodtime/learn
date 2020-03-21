package search;


import java.util.ArrayList;

/**
 * 斐波那契数列查找算法，利用的是斐波那契数列的特性，先把原来数组扩容成一个斐波那契数列为（f（k）-1）的长度，然后不断找类似黄金分割点的中间点,
 * 也即f（k-1）-1 ,大于黄金分割点，则找右边的数列（正好长度是f（k-2）-1），小于，则找左边的数列（长度为f（k-1）-1）。不断循环即可
 *
 * @author goodtime
 * @create 2020-02-06 1:26 下午
 */
public class FibonacciSearch {
    public static int search(int[] array, int target) {
        //这里用循环实现，也可以用递归

        if(array.length == 0){
            return -1;
        }
        int[] fibonacci = fibonacci(array.length);

        int end = array.length;
        int k = 0;

        //第一步：确定要扩容的数组总长度(其实这一步也可以省略，因为我们是动态找了一个斐波那契数列，数列最后一个值的下标就是要找
        // 的k值)
        while (fibonacci[k] - 1 < end) {//确保新的数组的总长度为f（k）-1，而且一定不必原来的数组短
            k++;
        }


        //第二步：把待排序的数组扩容成f（k）-1长的数组,数组本身不能扩容，所以创个新数组来装
        int[] tmp = new int[fibonacci[k] - 1];//装扩容后的数组
        for (int l = 0; l < fibonacci[k] - 1; l++) {
            if (l < array.length) {
                tmp[l] = array[l];
            } else {
                tmp[l] = array[end - 1];
            }
        }
        int low = 0;//存扩容后的数组第一个下标
        int high =  tmp.length-1;//存扩容后数组的最后一个下标

        //第三步：执行循环
        while (low <= high) {
            int middle = fibonacci[k - 1] - 1 + low;
            if (tmp[middle] < target) {
                low = middle + 1;
                k -= 2;
            } else if (tmp[middle] > target) {
                high = middle - 1;
                k--;
            } else {
                while (--middle >= 0 && tmp[middle] == target) {//返回第一个找到的数
                    //这里还多了一个功能，因为斐波拉契数列扩容的时候，最后扩的数组里全是array[array.length-1]的值
                    //这样如果落到扩容的数组下标当中，正好进行回退。
                }
                return middle + 1;
            }
        }
        return -1;
    }

    //第0步：
    //用于动态的产生一个fibonacci（网上一般都是生成一个下标最大为20的斐波那契数列，那样数据大的话就无法调用此方法了）
    public static int[] fibonacci(int length) {
        ArrayList<Integer> arrayList = new <Integer>ArrayList();
        arrayList.add(0);
        arrayList.add(1);//斐波那契数列的前两位是0和1
        int i = 0;
        while (arrayList.get(i) + arrayList.get(i + 1) - 1 < length) {
            arrayList.add(arrayList.get(i) + arrayList.get(i + 1));//通过遍历，给动态数组中塞斐波那契数列
            i++;
        }
        arrayList.add(arrayList.get(i) + arrayList.get(i + 1));//最后这一次没有遍历，需要额外加进去
        int[] a = new int[arrayList.size()];//找到最小满足需求的斐波那契数组
        for (int j = 0; j < arrayList.size(); j++) {
            a[j] = arrayList.get(j);
        }
        return a;
    }

}