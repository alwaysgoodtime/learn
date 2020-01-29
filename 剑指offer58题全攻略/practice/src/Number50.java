/**
 * @author goodtime
 * @create 2020-01-27 11:30 上午
 */
public class Number50 {
    public static void main(String[] args) {
        Solution50s solution50 = new Solution50s();
        int[] a = {1, 2, 1};
        boolean duplicate = solution50.duplicate(a, 3, a);
        System.out.println(duplicate);

    }
}

//时间复杂度为O（n），空间复杂度为O（n）。用的hash思想，用一个额外的新数组，新数组的下标，就是原来数组的值，下标对应的值
//就是下标对应的值出现的次数。遍历了两次，用了一个新的数组。
class Solution50 {
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null) {
            return false;
        }
        for (int j = 0; j < length; j++) {
            if (numbers[j] < 0 || numbers[j] > length - 1) {
                return false;
            }
        }
        int[] tmp = new int[length];
        for (int i = 0; i < length; i++) {
            tmp[numbers[i]]++;
        }
        for (int j = 0; j < length; j++) {
            if (tmp[j] >= 2) {
                duplication[0] = j;
                return true;
            }
        }
        return false;
    }
}

//高级hash，不耗费额外空间。让数组中原来的元素成为下标，找到这个元素对应的下标，然后和那个下标的值进行交换，
//如果有重复元素，在不断交换的过程中，一定会有交换的值和被交换的值相同的时候。否则就是一个从0~n-1的升序数组
class Solution50s {
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null) {
            return false;
        }
        for (int j = 0; j < length; j++) {
            if (numbers[j] < 0 || numbers[j] > length - 1) {
                return false;
            }
        }
            for (int i = 0; i < length; i++) {
                while (i != numbers[i]) {
                    if (numbers[i] == numbers[numbers[i]]) {
                        duplication[0] = numbers[i];
                        return true;
                    } else {
                        int temp = numbers[numbers[i]];
                        numbers[numbers[i]] = numbers[i];
                        numbers[i] = temp;
                    }
                }
            }
            return false;
    }
}


//用boolean数组存值，boolean只占一位，所以还是比较省的
//public boolean duplicate(int numbers[], int length, int[] duplication) {
//        boolean[] k = new boolean[length];
//        for (int i = 0; i < k.length; i++) {
//        if (k[numbers[i]] == true) {
//        duplication[0] = numbers[i];
//        return true;
//        }
//        k[numbers[i]] = true;
//        }
//        return false;
//        }
