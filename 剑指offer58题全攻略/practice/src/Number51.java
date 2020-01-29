import java.util.ArrayList;
/**
 * @author goodtime
 * @create 2020-01-27 3:27 下午
 */
public class Number51 {
    public static void main(String[] args) {
        Solution51plus solution51 = new Solution51plus();
        int[] a = {1,2,3,4,5};
        int[] multiply = solution51.multiply(a);
        for (int c:multiply
             ) {
            System.out.println(c);
        }
    }
}

//这种是最直观的解法，时间复杂度为O（n的平方）
class Solution51 {
    public int[] multiply(int[] A) {
        if(A == null || A.length == 0){
            int[] rt = new int[0];
            return rt;
        }
        int[] ints = new int[A.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = 1;
        }
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < i; j++) {
                ints[i] = ints[i]*A[j];
            }
            for (int l = i+1; l < ints.length; l++) {
                ints[i] = ints[i]*A[l];
            }
        }
        return  ints;
    }
}

//时间复杂度还是O（n的平方）

class Solution51s {
    public int[] multiply(int[] A) {
        if(A == null || A.length == 0){
            int[] rt = new int[0];//程序健壮性
            return rt;
        }
        int[] ints = new int[A.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = 1;//初始化
        }
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                if(j == i){
                    continue;
                }
                ints[i] = ints[i]*A[j];
            }
        }
        return  ints;
    }
}

//剑指offer上的答案，把返回值数组的值分两次（上下三角）计算，这样时间复杂度是O（n），大大节省时间

class Solution51plus {
    public int[] multiply(int[] A) {
        if(A == null || A.length == 0){
            int[] rt = new int[0];
            return rt;
        }
        int[] ints = new int[A.length];
        ints[0] = 1;
        for (int i = 1; i < ints.length; i++) {
            ints[i] = ints[i-1]*A[i-1];//上三角
        }
        int temp = 1;
        for (int j = ints.length-1 ; j >= 1; j--) {
            temp *= A[j];
            ints[j-1] *= temp;
        }

        return  ints;
    }
}

