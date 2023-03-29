/**
 * JZ53 数字在升序数组中出现的次数
 * 给定一个长度为 n 的非降序数组和一个非负数整数 k ，要求统计 k 在数组中出现的次数
 * 要求：空间复杂度O(1)，时间复杂度O(logn)
 *
 * @author goodtime
 * @create 2020-01-23 2:08 下午
 */
public class Number37 {
    public static void main(String[] args) {
        Solution37s solution37 = new Solution37s();
        int[] a = {1, 2, 3, 3, 3,};
        int i = solution37.GetNumberOfK(a, 3);
        System.out.println(i);
    }
}

//统计一个数字在排序数组中出现的次数，题目是考有序数组的查找，下面采用二分查找。
//题目不难，关键是各种优化二分查找，让运行速度提上去
class Solution37 {
    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;//预处理，保证代码稳定性
        }
        if (array.length == 1) {
            if (k == array[0]) {
                return 1;
            } else {
                return 0;
            }//预处理
        }
        int count = 0;
        int l = -1;
        int m = array.length;
        int start = 0;
        int end = m - 1;
        int middle = (start + end) / 2;
        while (end - start >= 0) {//这里一定要小心，end-start一定是大于等于0才行，如果是大于等于1，end和start相等时，可能正好才取到k值
            //eg. [1,2] k = 1
            if (array[middle] == k) {
                l = middle;
                break;
            } else if (array[middle] > k) {
                end = middle - 1;
                middle = (start + end) / 2;
            } else {
                start = middle + 1;
                middle = (start + end) / 2;
            }
        }
        if (l == -1) {
            return 0;//说明l未赋值，循环正常终止，直接输出，减少代码运行时间
        }
        for (int i = l; i != -1; i--) {
            if (array[i] == k)
                count++;
            else
                break;
        }
        for (int n = l + 1; n != m; n++) {
            if (array[n] == k)
                count++;
            else
                break;
        }
        return count;
    }
}


//有序数组，可以进行二分查找
class Solution37s {
    public int GetNumberOfK(int[] array, int k) {

        //健壮性
        if(array == null || array.length == 0){
            return 0;
        }

        return GetNumberOfKAndIndex(array, k, 0, array.length - 1);
    }

    //二分切分数组
    private int GetNumberOfKAndIndex(int[] array, int k, int subIndex, int index) {

        int middle = (index + subIndex) / 2;

        int middleValue = array[middle];

        if (middleValue < k) {
            //边界处理
            if (middle + 1 <= index) {
                return GetNumberOfKAndIndex(array, k, middle + 1, index);
            } else {
                return 0;
            }
        } else if (middleValue > k) {
            //边界处理
            if (middle == 0) {
                return 0;
            } else {
                return GetNumberOfKAndIndex(array, k, 0, middle - 1);
            }
        }

        return getNumberByIndex(array, k, middle);
    }

    //滑动窗口统计数字
    private int getNumberByIndex(int[] array, int k, int middle) {

        int count = 0;

        for (int i = middle; i < array.length; i++) {
            if (array[i] == k) {
                count++;
            } else {
                break;
            }
        }

        for (int i = middle - 1; i >= 0; i--) {
            if (array[i] == k) {
                count++;
            } else {
                break;
            }
        }

        return count;

    }

}