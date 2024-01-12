package leetcode.src.main.java.linkedList;

/**
 * @author goodtime
 * @create 2024-01-12 15:19
 */
public class Number24 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        System.out.println(new Solution24().swapPairs(listNode));
        System.out.println("test");
    }
}


class Solution24 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode result = head.next;

        ListNode blankNode = new ListNode(-1);
        blankNode.next = head;
        ListNode before = null;

        while (blankNode.next != null && blankNode.next.next != null) {
            ListNode next = swap(blankNode.next, blankNode.next.next, before);
            before = blankNode.next;
            blankNode.next = next;
        }

        //处理链表长度为奇数的情况
        if(blankNode.next != null){
            before.next = blankNode.next;
        }

        return result;

    }

    private ListNode swap(ListNode node, ListNode nodeNext, ListNode before) {

        ListNode tmp = nodeNext.next;

        nodeNext.next = node;

        if (before != null) {
            before.next = nodeNext;
        }

        node.next = null;

        return tmp;

    }
}