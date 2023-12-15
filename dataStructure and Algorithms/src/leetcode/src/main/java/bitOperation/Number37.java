package leetcode.src.main.java.bitOperation;

/**
 * https://leetcode.cn/problems/single-number-ii/
 *
 * @author goodtime
 * @create 2023-12-06 07:59
 */
public class Number37 {

    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 考虑出现1次与出现3次如何区分出来？
 *
 * 用一个32元素的数组，把每个数的32位2进制数对应的数加起来，最后取其除以3的余数。
 *
 * 因为一位2进制数只有0或1，3个相同的数加起来为0或3， 那个不同的数要么是0要么是1，加起来要么是0要么是4，所以取其除以3的余数就是它本身。
 */
class Solution37 {
    public int singleNumber(int[] nums) {

        int[] result = new int[32];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < 32; j++) {
                result[j] += (nums[i] >>> j) & 1;
            }
        }

        int number = 0;

        for (int i = 0; i < 32; i++) {
            result[i] = result[i] % 3;
            number = number | result[i] << i;
        }

        return number;




    }
}
