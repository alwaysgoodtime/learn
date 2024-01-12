package leetcode.src.main.java.doublePointer;

/**
 * @author goodtime
 * @create 2024-01-11 22:26
 */
public class Number287 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 快慢指针，如果有重复数，必定形成环
 * https://leetcode.cn/problems/find-the-duplicate-number/solutions/58841/287xun-zhao-zhong-fu-shu-by-kirsche/?envType=study-plan-v2&envId=top-100-liked
 * 如果数组中没有重复的数，以数组 [1,3,4,2]为例，我们将数组下标 n 和数 nums[n] 建立一个映射关系 f(n)f(n)f(n)，
 * 其映射关系 n->f(n)为：
 * 0->1
 * 1->3
 * 2->4
 * 3->2
 * 我们从下标为 0 出发，根据 f(n)f(n)f(n) 计算出一个值，以这个值为新的下标，再用这个函数计算，以此类推，直到下标超界。这样可以产生一个类似链表一样的序列。
 * 0->1->3->2->4->null
 */
class Solution287 {
    public int findDuplicate(int[] nums) {

        int slow = 0;
        int fast = 0;

        while (slow == 0 || slow != fast) {

            //即不存在重复的整数
            if (fast >= nums.length || nums[fast] >= nums.length) {
                return -1;
            } else {
                fast = nums[nums[fast]];
            }

            slow = nums[slow];
        }

        int number = 0;

        while (slow != number){
            number = nums[number];
            slow = nums[slow];
        }

        return slow;


    }
}
