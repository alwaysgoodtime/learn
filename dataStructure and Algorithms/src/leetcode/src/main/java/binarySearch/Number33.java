package leetcode.src.main.java.binarySearch;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/
 *
 * @author goodtime
 * @create 2023-12-07 22:40
 */
public class Number33 {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 8, 1, 2, 3};
        System.out.println(new Solution33().search(nums, 8));
    }


}

/**
 * 旋转排序数组，本题不能用红蓝染色法，只能从0到n-1进行二分排序
 *
 * 对于核心处理逻辑，最好的方法是，通过举例来按情况讨论该往左走还是该往右走
 *
 *
 */
class Solution33 {
    public int search(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return -1;
        }


        //下面的二分法情形，没有处理开头结尾这两边与target相等的情形，在循环外面做处理
        if (nums[0] == target) {
            return 0;
        }

        if (nums[nums.length - 1] == target) {
            return nums.length - 1;
        }

        //注意，下面的二分法情形，无法摆脱nums.length为1的情形
        if (nums.length == 1) {
            return -1;
        }


        int l = 0, r = nums.length - 1;

        while (l + 1 != r) {

            int m = (l + r) / 2;

            if (nums[m] == target) {
                return m;
            }

            if (nums[l] == target) {
                return l;
            }

            if (nums[r] == target) {
                return r;
            }

            if (nums[m] > target) {

                //假设nums[m] = 8, target = 5
                //nums[l] 有几种情形，1，6，10， 分别是 < target ,  > target && < nums[m] , > nums[m]
                //如果nums[l] < target, 那么5在其中
                //如果nums[l] > nums[m], 那么5在其中
                //如果nums[l] > target && nums[l] < nums[m]  举例：68x，那么5一定不再[6,8]这个范围里，应该在8后面，在6前面的情况已经被讨论了
                if (nums[l] < target || nums[l] > nums[m]) {
                    r = m;
                } else {
                    l = m;
                }

            } else {
                //假设nums[m] = 5, target = 8
                //nums[l] 有几种情形，1，6，10， 分别是 < nums[m] ,  > nums[m] && < target , > target
                //如果nums[l] < nums[m], 那么8不在其中
                //如果nums[l] > target, 那么8不在其中
                //如果nums[l] > nums[m] && nums[l] < target  举例：75x，那么5一定不再[5,x]这个范围里，应该在7后面，在7前面的情况已经被讨论了
                if (nums[l] < nums[m] || nums[l] > target) {
                    l = m;
                } else {
                    r = m;
                }
            }

        }

        return -1;

    }
}
