package leetcode.src.main.java.linkedList;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 *
 * @author goodtime
 * @create 2023-12-04 23:51
 */
public class Number206 {

    public static void main(String[] args) {
        System.out.println("test");
    }

}

/**
 * 反转链表，从前到后一个个处理过去
 */
class Solution206 {

    public ListNode reverseList2(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode preNode = head;
        ListNode nextNode = head.next;
        //头结点需要特殊处理
        head.next = null;

        while (nextNode != null) {
            ListNode tmp = nextNode.next;
            nextNode.next = preNode;
            preNode = nextNode;
            nextNode = tmp;
        }

        return preNode;

    }

    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        //处理第一个节点
        ListNode tmpNext = head.next;
        head.next = null;

        return reverse(head, tmpNext);
    }

    private ListNode reverse(ListNode head, ListNode headNext) {

        //到了最后一个待处理节点,它就是首节点
        if (headNext.next == null) {
            headNext.next = head;
            return headNext;
        }

        //存储下了个待处理节点
        ListNode tmpNext = headNext.next;

        //处理当前节点
        headNext.next = head;

        //处理下一个节点
        return reverse(headNext, tmpNext);

    }

}
