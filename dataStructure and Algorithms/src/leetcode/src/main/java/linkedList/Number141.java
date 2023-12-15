package leetcode.src.main.java.linkedList;

/**
 * https://leetcode.cn/problems/linked-list-cycle/
 *
 * @author goodtime
 * @create 2023-12-01 00:09
 */
public class Number141 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode2;
        System.out.println(new Solution141().hasCycle(listNode));

    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * 经典快慢指针，只要一个指针每次只走一步，另一个指针一次走两步，如果有循环，两者总会相遇
 */
class Solution141 {

    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }

        ListNode slowPointer = head;
        ListNode fastPointer = head.next;

        while (fastPointer != null) {

            if (fastPointer.next != null && fastPointer.next.next != null) {
                fastPointer = fastPointer.next.next;
            } else {
                return false;
            }

            if (slowPointer.next != null) {
                slowPointer = slowPointer.next;
            }

            if (slowPointer == fastPointer) {
                return true;
            }


        }

        return false;

    }
}
