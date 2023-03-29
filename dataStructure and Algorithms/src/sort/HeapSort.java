package sort;

/**
 * 堆排序，借助大顶堆进行升序排序，小顶堆进行降序排序，不同排序方式，所采用的数据结构不一样
 * 不稳定，算法复杂度O（nlogn），线性对数阶
 *
 * @author goodtime
 * @create 2020-02-08 9:16 下午
 */
public class HeapSort {
    public static int[] sort(int[] array) {


        //用到完全二叉树的两个属性：
        //1.父节点序号为n，左子节点为2n+1，右子节点为2n+2
        //2.完全二叉树的叶子节点为m，总节点数为2m或者2m-1
        //大根堆的两个属性：
        //1.大根堆的每个子堆也是大根堆。
        //2.叶子节点默认就是大根堆

        int length = array.length;

        while (length != 1){//推出循环的条件，length为1，即数组中只剩一个元素未排序，它默认就是最小的值了

            //第一步，把数组视作一个顺序完全二叉树，排序之后形成一个大根堆
            int tmp;//交换的临时变量
            if(length == array.length){
                for (int i = length / 2 - 1; i >= 0; i--) {//length/2-1就是从完全二叉树的属性推导出来的
                    heapSort(i, array,length);//这些i是要进入大根堆的父节点，原来的叶子节点默认就是大根堆，这样就能不断形成大根堆
                }
            }else {
                heapSort(0,array,length);//在排好整个数组，成为大根堆之后，只要处理新的堆顶元素即可，其他元素默认都是大根堆
            }

            //第二步，元素下沉，让大根堆（也叫大顶堆）的顶后移到整个堆的最后，也就是数组的末尾，然后让数组剩下元素所对应的顺序完全二叉树重新形成大根堆
            length--;
            tmp = array[0];
            array[0] = array[length];
            array[length] = tmp;

            //第三步，不断循环第一步第二步，让每个元素都变成堆顶，然后下沉，最后就能得到一个升序的数组，
            //当然，后续只用处理最顶上的元素即可，不算下沉的元素，除了新的堆顶元素，其他元素所形成的都是大根堆
        }

        return array;
    }

    /**
     * 形成最大堆的核心方法
     * @param i 处理的父节点（非叶子节点）
     * @param array 处理的数组
     * @param length 处理的数组长度（因为在后来的使用中，数组后面的是根节点沉下去的数，默认是不在大根堆里，我们也不处理它）
     */

    private static void heapSort(int i, int[] array,int length) {
        int tmp;//临时变量
        for (int j = i;j < array.length;) {//第一次遍历，让当前新来的父节点上放着最大值，
            // 后续的遍历，是如果当前父节点值太小，还得重新让原来的子树变成大根堆，也就是让这个值继续下沉
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (right < length && array[left] < array[right] && array[i] < array[right]){
                tmp = array[right];
                 array[right]= array[j];
                 array[j] = tmp;
                 j = left;
            }else if(left < length && array[i] < array[left]) {
                tmp = array[left];
                array[left] = array[i];
                array[i] = tmp;
                j = right;
            }else {
                break;//说明此时这个节点已经是最大，退出循环
            }
        }
    }


}
