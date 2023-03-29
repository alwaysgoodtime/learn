package linkedlist;

/**
 * 输入一个长度为 n 的链表，设链表中的元素的值为 ai ，返回该链表中倒数第k个节点。
 * 如果该链表长度小于k，请返回一个长度为 0 的链表。
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 * 进阶：空间复杂度 O(1)，时间复杂度 O(n)
 *
 * @author goodtime
 * @create 2020-01-19 2:48 下午
 */
public class Number14 {
    public static void main(String[] args) {
        ListNode2 listNode1 = new ListNode2(1);
        ListNode2 listNode2 = new ListNode2(4);
        ListNode2 listNode3 = new ListNode2(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        Solution14 solution14 = new Solution14();
        ListNode2 listNode21 = solution14.FindKthToTail(listNode1, 2);
        System.out.println(listNode21.val);

    }

}

class ListNode2 {
    int val;
    ListNode2 next = null;

    ListNode2(int val) {
        this.val = val;
    }
}

class Solution14 {
    public ListNode2 FindKthToTail(ListNode2 pHead, int k) {

        if (pHead == null || k == 0) {
            return null;
        }

        //得到链表长度
        int length = 0;
        ListNode2 tmp = pHead;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }

        if (length < k) {
            return null;
        }

        int index = length - k;

        for (int i = index; i > 0; i--) {
            pHead = pHead.next;
        }

        return pHead;

    }
}