package linkedlist;

/**
 * JZ25 合并两个排序的链表
 * 输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 数据范围： 0≤n≤1000，
 * 节点值：−1000≤节点值≤1000，
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 *
 * @author goodtime
 * @create 2020-01-19 4:37 下午
 */
public class Number16 {
    public static void main(String[] args) {
        ListNode4 listNode4 = new ListNode4(1);
        ListNode4 listNode5 = new ListNode4(16);
        ListNode4 listNode9 = new ListNode4(17);
        ListNode4 listNode6 = new ListNode4(14);
        ListNode4 listNode7 = new ListNode4(17);
        ListNode4 listNode8 = new ListNode4(17);
        listNode4.next = listNode5;
        listNode5.next = listNode9;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        Solution16ss solution16 = new Solution16ss();
        ListNode4 merge = solution16.Merge(listNode4, listNode6);
        System.out.println(merge.next.next.val);
    }
}

/**
 * 如果list1的头比list2的头值大，则直接互换list1与list2; 以list1为主干，把list2视作一个一个节点，融到list1当中，形成新的ListNode。
 * 2023.3.26新方法
 */
class Solution16ss {
    public ListNode4 Merge(ListNode4 list1, ListNode4 list2) {

        //防止空指针，同时快速处理结果
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        int headList1 = list1.val;
        int headList2 = list2.val;
        if (headList1 > headList2) {
            ListNode4 tmp = list2;
            list2 = list1;
            list1 = tmp;
        }

        ListNode4 head = list1;

        //注意list1.next为空的情况，这种情况下直接把list2连在后面即可
        while (list2 != null) {

            if (list1.next == null || list2.val <= list1.next.val) {
                ListNode4 originalNode = list1.next;
                ListNode4 originalNode2 = list2.next;
                list1.next = list2;
                list2.next = originalNode;
                list2 = originalNode2;
            }

            list1 = list1.next;
        }

        return head;
    }
}


class ListNode4 {
    int val;
    ListNode4 next = null;

    ListNode4(int val) {
        this.val = val;
    }
}