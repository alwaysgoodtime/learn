package 剑指0ffer.stackAndQueue;

import java.util.ArrayList;

/**
 * JZ31 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 *
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
//    思路，用动态数组模拟入栈出栈的情况，第一次，如果出栈的值和入栈的值不相等，就继续入栈，如果相等了，让出栈的值向后移动一位，和
//    动态数组最后一个值比较（也即栈顶元素），如果相同，删除动态数组最后一个值，出栈的值继续后移，和新的栈顶元素比较。
//    结束循环，一个是入栈的值到末尾，说明无法匹配，返回false，如何是出栈的指针走到末尾，说明已经全部出栈。返回true
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA == null) {
            return true;
        }
        int length = popA.length;
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0, j = 0; i < length; ) {
            if (pushA[i] != popA[j]) {
                integers.add(pushA[i]);
                i++;
            } else {
                j++;
                if (j == length) {
                    return true;
                }//为了避免栈中一开始就只有一个值的情况
                while (popA[j] == integers.get(integers.size() - 1)) {
                    integers.remove(integers.size() - 1);
                    j++;
                    if (j == length) {
                        return true;
                    }
                }
                i++;
            }
        }
        return false;
    }
}

//找规律，在列表里，如果出栈的值下标为x，那么下次出栈的值，要么大于等于该下标，要么是x-1
class Solution21s {
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null) {
            return true;
        }

        //转换array为list
        ArrayList<Integer> integers = new ArrayList<>();

        for (int j : pushA) {
            integers.add(j);
        }

        int preIndex = 0;

        for (int i = 0,index = 0; i < popA.length; i++) {
            int value = popA[i];
            index = integers.indexOf(value);
            if (index == -1) {
                return false;
            }

            if(preIndex > index && preIndex -1 != index){
                return false;
            }

            preIndex = index;
            integers.remove(index);
        }

        return true;
    }
}