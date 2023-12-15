package leetcode.src.main.java.bitOperation;

/**
 * https://leetcode.cn/problems/single-number/description/
 *
 * @author goodtime
 * @create 2023-12-06 07:40
 */
public class Number136 {
    public static void main(String[] args) {

    }
}

/**
 * 1.最容易想的办法是，用一个nums.length长度的hashmap，先遍历一遍nums，然后再遍历一遍hashmap，找到其中出现次数为1的key
 *
 * 2.如果要求常量的额外空间，便不能用hashmap，只能用1或2个值来完成该任务。
 * 本题特殊在，所有的数，要么出现1次，要么出现2次，考虑与一个值按位异或，只要出现两次，那么按位异或就会消减为0。
 * 最后得到的结果为0与出现1次的数得到的结果，正好也就是那个数
 *
 * 异或特性 s1 ^ s2 ^ s2 = s1
 *
 */
class Solution136 {
    public int singleNumber(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }

        return result;


    }
}
