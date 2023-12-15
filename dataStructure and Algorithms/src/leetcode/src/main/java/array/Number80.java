package leetcode.src.main.java.array;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/
 *
 * @author goodtime
 * @create 2023-12-03 19:13
 */
public class Number80 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(new Solution80().removeDuplicates(nums));
    }
}

/**
 * @see Number26
 * 双指针，与26不同的是，每个数字可以出现两次
 * 于是，我们可以让慢指针一次前进一个，但是可以放一次重复的元素
 * 快指针还是往前冲，一次前进一格
 */
class Solution80 {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }

        if(nums.length <= 2){
            return nums.length;
        }

        int slowPointer = 0;
        int fastPointer = 1;
        //第一次视作重复
        boolean dupNumberChance = true;

        while (fastPointer < nums.length){

            if(nums[fastPointer] != nums[slowPointer]){
                slowPointer++;
                nums[slowPointer] = nums[fastPointer];
                //遇到新的不等值，重置重复机会
                dupNumberChance = true;
            }else if(dupNumberChance){
                slowPointer++;
                nums[slowPointer] = nums[fastPointer];
                dupNumberChance = false;
            }else {
            }

            fastPointer++;

        }

        return slowPointer + 1;

    }
}