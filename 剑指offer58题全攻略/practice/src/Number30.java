/**
 * @author goodtime
 * @create 2020-01-21 10:31 下午
 */
public class Number30 {
    public static void main(String[] args) {
        Solution30 solution30 = new Solution30();
        int[] a = {2,3,8,-10000,10000,-3};
        int i = solution30.FindGreatestSumOfSubArray(a);
        System.out.println(i);
    }
}

//下面的方式虽然通过牛客网，但有bug，已废弃

//class Solution30 {
//    public int FindGreatestSumOfSubArray(int[] array) {
//        if(array == null || array.length == 0){
//            throw new RuntimeException("输入值不合法");
//        }//array不能为空指针或为空字符串
//        int max = 0;
//        int count = 0;//如果数组都为负值，count就为0，从而触发此计数器
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] >= 0) {
//                max += array[i];
//                count++;
//            } else {
//                int j = i + 1;
//                for (int l = array[i], k = max + array[i]; j < array.length; j++) {
//                    if (array[j] >= 0 && k <= 0 && array[j] > max) {
//                        max = array[j];
//                        break;
//                    }//如果此条件进入，会触发移动子向量的起始位置，因为默认是从下标0开始的。
//                    // 判断也很简单，就是如果array[j]不小于0，并且k<0(就算之后k可以再大于0，也不如从array[j]这里开始取子向量),
//                    //其中的array[j] > max,是为了防止[1000,-1001,2,3]这种情况出现，但无法阻止[3,-4,2,3]的情况，
//                    //能通过牛客网测试，说明它的测试用例还是不够完美
//                    l += array[j];
//                    k += array[j];
//                    if (l >= 0) {
//                        max += l;
//                        break;
//                    }//这个是起始脚标不变，看看后面的负数打头的数组有没有必要包含，如果为非负值，就包含进来。
//                }
//                i = j;//下次直接跳到j+1遍历
//            }
//        }
//        if (count == 0) {
//            max = array[0];
//            for (int i = 1; i < array.length; i++) {//防止数组每个数都为负数的情况出现
//                if (array[i] > max) {
//                    max = array[i];
//                }
//            }
//        }
//        return max;
//    }
//}


//动态规划

/*
算法时间复杂度O（n）
用total记录累计值，maxSum记录和最大
基于思想：对于一个数A，若是A的左边累计数非负，那么加上A能使得值不小于A，认为累计值对
        整体和是有贡献的。如果前几项累计值负数，则认为有害于总和，total记录当前值。
        此时,若和大于maxSum,则用maxSum记录下来
改编自牛客网
*/

class Solution30 {

    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0)
            return 0;//排除array为null或array为空数组的情况，暂时返回一个1
        else {
            int total = array[0], maxSum = array[0];
            for (int i = 1; i < array.length; i++) {
                if (total >= 0)
                    total += array[i];
                else
                    total = array[i];
                if (total > maxSum)
                    maxSum = total;
            }
            return maxSum;
        }

    }
}