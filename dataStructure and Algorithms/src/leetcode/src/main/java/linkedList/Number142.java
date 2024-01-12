package leetcode.src.main.java.linkedList;

import java.util.List;

/**
 * https://leetcode.cn/problems/linked-list-cycle-ii/description/
 *
 * @author goodtime
 * @create 2024-01-11 22:56
 */
public class Number142 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * @see Number141
 * 返回链表环的头节点
 */
class Solution142 {
    public ListNode detectCycle(ListNode head) {
        ListNode slowPointer;
        ListNode fastPointer;

        if(head == null) {
            return null;
        }

        if (head.next != null) {
            slowPointer = head.next;
        } else {
            return null;
        }

        if (slowPointer.next != null) {
            fastPointer = slowPointer.next;
        } else {
            return null;
        }

        while (slowPointer != fastPointer) {
            if (slowPointer.next != null) {
                slowPointer = slowPointer.next;
            } else {
                return null;
            }

            if (fastPointer.next != null && fastPointer.next.next != null) {
                fastPointer = fastPointer.next.next;
            } else {
                return null;
            }
        }

        ListNode newPointer = head;

        while (newPointer != slowPointer){
            newPointer = newPointer.next;
            slowPointer = slowPointer.next;
        }

        return slowPointer;

    }
}