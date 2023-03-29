package linkedlist;

/**
 * 反转链表 JZ24
 * 给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，反转该链表后，返回新链表的表头。
 * 数据范围： 0≤n≤1000
 * 要求：空间复杂度O(1) ，时间复杂度 O(n)。
 * 如当输入链表{1,2,3}时，
 *
 * @author goodtime
 * @create 2020-01-19 3:16 下午
 */
public class Number15 {
    public static void main(String[] args) {
        ListNode3 listNode1 = new ListNode3(1);
        ListNode3 listNode2 = new ListNode3(2);
        ListNode3 listNode3 = new ListNode3(3);
        ListNode3 listNode4 = new ListNode3(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        Solution15 solution15 = new Solution15();
        ListNode3 listNode31 = solution15.ReverseList(listNode1);
        System.out.println(listNode31.next.next.val);

    }
}

class ListNode3 {
    int val;
    ListNode3 next = null;

    ListNode3(int val) {
        this.val = val;
    }
}

class Solution15 {

    public ListNode3 ReverseList(ListNode3 head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode3 lastNode = head;

        for(ListNode3 nextNode = head.next; nextNode != null;){
            ListNode3 tmp = nextNode.next;
            nextNode.next = lastNode;
            lastNode = nextNode;
            nextNode = tmp;
        }
        //将原链表第一节点的指针指向null
        head.next = null;
        return  lastNode;
    }

}