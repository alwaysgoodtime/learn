import java.util.ArrayList;
import java.util.Stack;

/**
 * @author goodtime
 * @create 2020-01-20 12:51 下午
 */
public class Number21 {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {4, 5, 3, 2, 1};
        System.out.println(new Solution21().IsPopOrder(a, b));
    }
}

class Solution21 {
//    思路，用动态数组模拟栈，第一次，如果出栈的值和入栈的值不相等，就继续入栈，如果相等了，让出栈的值向后移动一位，和
//    动态数组最后一个值比较（也即栈顶元素），如果相同，删除动态数组最后一个值，出栈的值继续后移，和新的栈顶元素比较。
//    结束循环，一个是入栈的值到末尾，说明无法匹配，返回false，如何是出栈的指针走到末尾，说明已经全部出栈。返回true
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA == null) {
            return true;
        }
        int length = popA.length;
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0, j = 0;i < length;) {
            if (pushA[i] != popA[j]) {
                integers.add(pushA[i]);
                i++;
            } else {
                j++;
                if(j == length){
                    return true;
                }//为了避免栈中一开始就只有一个值的情况
                while (popA[j] == integers.get(integers.size() - 1)) {
                    integers.remove(integers.size() - 1);
                    j++;
                    if(j == length){
                        return true;
                    }
                }
                i++;
            }
        }
        return false;
    }
}