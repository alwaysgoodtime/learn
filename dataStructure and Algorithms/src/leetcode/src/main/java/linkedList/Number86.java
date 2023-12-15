package leetcode.src.main.java.linkedList;

/**
 * https://leetcode.cn/problems/partition-list/
 *
 * @author goodtime
 * @create 2023-12-05 10:30
 */
public class Number86 {
    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(0);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        System.out.println(new Solution86().partition(node1, 3));
    }
}

/**
 * 按照顺序移动比x大或相等的节点
 *
 * 快慢指针，慢指针指的是小于x的节点，遇到大于等于x的节点，等快指针滑到下一个小于x的节点，做节点移动，再往前移动
 * 快指针则一路前移
 *
 * 为了方便本题的处理，再引入一个指针，它指向的是快指针滑过的最后一个大于等于x的节点
 *
 *
 * 第二种方案是维护两个链表，一个是<x的链表，一个是>=x的链表，最后两者一结合即可
 *
 *
 */
class Solution86 {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode slowPointer = dummyNode;
        ListNode fastPointer = head;
        //再引入一个指针，它指向的是快指针滑过的最后一个大于等于x的节点
        ListNode lastFastPointer = dummyNode;

        while (fastPointer != null) {

            ListNode nextFastPointer = fastPointer.next;

            if (fastPointer.val < x) {

                ListNode tmp = slowPointer.next;
                slowPointer.next = fastPointer;
                slowPointer = fastPointer;

                //中间有>=x的结点
                if (lastFastPointer != dummyNode) {
                    slowPointer.next = tmp;
                    lastFastPointer.next = nextFastPointer;
                }

            } else {
                lastFastPointer = fastPointer;
            }

            if (nextFastPointer == null) {
                return dummyNode.next;
            }

            fastPointer = nextFastPointer;

        }

        return dummyNode.next;
    }
}
