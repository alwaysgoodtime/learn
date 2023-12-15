package leetcode.src.main.java.linkedList;

/**
 * https://leetcode.cn/problems/reverse-linked-list-ii/
 *
 * @author goodtime
 * @create 2023-12-04 23:31
 */
public class Number92 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(3);
        ListNode listNode5 = new ListNode(5);
        listNode.next = listNode5;

        System.out.println(new Solution92().reverseBetween(listNode, 1, 2));

    }
}

/**
 * @see Number206
 * 反转链表,但是有了限定，那么直到满足限定之处再开始
 */
class Solution92 {

    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || head.next == null || left >= right) {
            return head;
        }

        ListNode result = null;
        ListNode lastUntreatedNode = null;

        //注意left<=1的特殊情况
        if (left > 1) {

            //123456,假设要反转3到5，它就是2
            lastUntreatedNode = head;
            result = head;
            int index = 1;

            while (index < left) {
                if (head.next == null) {
                    return result;
                }
                lastUntreatedNode = head;
                head = head.next;
                index++;
            }
        }


        //123456,假设要反转3到5，它就是3
        ListNode tmpNext = head;

        //123456,假设要反转3到5，它就是5-3=2
        int count = right - left;

        //123456，假设要反转3到5，lastNode[0]为5，lastNode[1]为6
        ListNode[] lastNode = reverse(head, head.next, count);

        //处理第一个节点
        tmpNext.next = lastNode[1];

        if (left > 1) {
            //处理最后一个需处理节点
            lastUntreatedNode.next = lastNode[0];
            return result;
        } else {
            return lastNode[0];
        }

    }

    private ListNode[] reverse(ListNode head, ListNode headNext, int count) {

        //存储下了个待处理节点
        ListNode tmpNext = headNext.next;
        //处理当前节点
        headNext.next = head;

        //到了最后一个待处理节点，终止迭代
        if (tmpNext == null || count == 1) {
            ListNode[] lastNode = new ListNode[2];
            lastNode[0] = headNext;
            lastNode[1] = tmpNext;
            return lastNode;
        }

        //待处理节点数减1
        count--;

        //处理下一个节点
        return reverse(headNext, tmpNext, count);

    }
}