import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-20 8:52 下午
 */
public class Number29 {
    public static void main(String[] args) {
        Solution29s solution29s = new Solution29s();
        int[] a = {4,5,1,7,2,7,3,8};
        ArrayList<Integer> integers = solution29s.GetLeastNumbers_Solution(a, 8);
        System.out.println(integers);

    }
}

// 堆排序，对这道题来说最适合的解法
// 这里模拟把整个数组都排序了，其实按照堆排序的做法，用最小堆，排除需要的k个最小值即可。
class Solution29s {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> integers = new ArrayList<>();
        if (input == null) {
            return integers;
        }
        if (input.length == 0 || input.length == 1) {
            return integers;
        }
        heapSort(input);
        if (k > input.length) {
            return integers;
        }
        for (int i = 0; i < k; i++) {
            integers.add(input[i]);
        }
        return integers;
    }

    /**
     * 1.让input(长度为n)的无序数组变成大根堆的样子
     * 1.1如果子节点都是大根堆，可以让子节点的根节点进行元素下沉。
     * 1.2找到叶节点，叶节点没有子节点，可以视为一个大根堆，然后不断让它的根节点元素下沉，形成新的大根堆。
     * 1.3迭代到第一个元素，就可以形成大根堆。
     * 2.把第一个元素（最大的数放到最后，然后接着排前面n-1个数）
     * 3.当只剩第一个元素没排时，说明排序已经完成
     * @param input
     */

    public void heapSort(int[] input) {
        int n = input.length-1;
        int j = n % 2 == 0 ? n / 2 - 1 : n / 2;//判断数组形成的完全二叉树中最后一个非子节点的节点下标
        int temp;
        for (int i = j; i >= 0; i--) {
            falling(input, i,input.length- 1);
        }
//        进行第二步，排数
        temp = input[input.length-1];
        input[input.length-1] = input[0];
        input[0] = temp;
        for (int m = 1; m < input.length - 1; m++) {
            falling(input,0,input.length - 1 - m);
            temp = input[input.length-1-m];
            input[input.length-1-m] = input[0];
            input[0] = temp;
        }
    }

    public void falling(int[] input, int i,int end) {// i为要排的数，end为要排的input数组要排的最后一个数的下标
        int temp;
        int j = end % 2 == 0 ? end / 2 - 1 : end / 2 ;
        while (i <= j) {//循环的跳出条件
            if (2 * (i + 1) <= end && input[2 * i + 1] < input[2 * i + 2] && input[i] < input[2 * i + 2]) {
                    temp = input[i];
                    input[i] = input[2 * i + 2];
                    input[2 * i + 2] = temp;
                    i = 2 * i + 2;
                    continue;
            }else if (input[i] < input[2 * i + 1]) {
                temp = input[i];
                input[i] = input[2 * i + 1];
                input[2 * i + 1] = temp;
                i = 2 * i + 1;
                continue;
            } else {
                break;
            }
        }
    }
}

//快排:挖坑+分治理（选中轴的数，可以是数组第一个数，也可以是随机数，可以优化，然后左右轴排序，左边小于中轴右边大于中轴）
//排好以后可以继续排左和右轴，直到轴长度为1或为0
//class Solution29 {
//    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
//        ArrayList<Integer> integers = new ArrayList<>();
//        if (input == null) {
//            return integers;
//        }
//        quickSort(input, 0, input.length - 1);
//        if (k > input.length) {
//            return integers;
//        }
//        for (int i = 0; i < k; i++) {
//            integers.add(input[i]);
//        }
//        return integers;
//    }
//
//    public void quickSort(int[] input, int k, int m) {
//        if ((k - m) == 0 || (k - m) == 1) {
//            return;
//        }
//        int i = input[k];
//        in:
//        for (int j = k, l = m; ; ) {
//            while (input[l] > i && j < l) {
//                l--;//先从后指针开始排序
//            }
//            if (j == l) {
//                input[j] = i;
//                quickSort(input, k, j - 1);
//                quickSort(input, j + 1, m);
//                break;
//            }
//            input[j] = input[l];
//            j++;
//            while (input[j] <= i && j < l) {
//                j++;//这里考虑到了与中轴数相等的情况，兼容排序数组中有重复的数
//            }
//            if (j == l) {
//                input[j] = i;
//                quickSort(input, k, j - 1);
//                quickSort(input, j + 1, m);
//                break;
//            }
//            input[l] = input[j];
//            l--;
//        }

//    }
//}