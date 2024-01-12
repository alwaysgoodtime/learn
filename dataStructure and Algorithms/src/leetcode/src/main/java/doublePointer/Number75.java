package leetcode.src.main.java.doublePointer;

/**
 * @author goodtime
 * @create 2024-01-11 22:02
 */
public class Number75 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

class Solution75 {

    /**
     * 简单的想法是，遍历一次，把0、1、2的个数存起来，第二次覆盖原数组即可
     *
     * 三指针法，分别为指向0指针的后继，指向1指针的后继，前进指针。
     *
     * 如果前进指针为1，就与1指针交换，1指针前进1格
     *
     * 如果前进指针为0
     * 当0指针与1指针相同时，就与0指针交换，0指针与1指针各前进1格
     * 但0指针与1指针不同时，先与0指针交换，0指针前进1格，当前指针再与1指针交换，1指针前进1格
     *
     */
    public void sortColors(int[] nums) {
        int zeroPointer = 0;
        int onePointer = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 1) {
                nums[i] = nums[onePointer];
                nums[onePointer] = 1;
                onePointer++;
            } else if (nums[i] == 0) {

                if (zeroPointer == onePointer) {
                    int tmp = nums[i];
                    nums[i] = nums[zeroPointer];
                    nums[zeroPointer] = tmp;

                } else {
                    //0指针交换并前进
                    nums[i] = nums[zeroPointer];
                    nums[zeroPointer] = 0;

                    //1指针交换并前进
                    nums[i] = nums[onePointer];
                    nums[onePointer] = 1;
                }

                zeroPointer++;
                onePointer++;

            } else {
            }
        }
    }


}