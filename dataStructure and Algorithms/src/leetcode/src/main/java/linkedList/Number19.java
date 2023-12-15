package leetcode.src.main.java.linkedList;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 *
 * @author goodtime
 * @create 2023-12-05 01:10
 */
public class Number19 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 删除倒数第n个节点，那么先从前往后遍历，确定整个链表的长度l，删掉第l-n+1个节点即可（注意，节点从1开始编号）
 *
 * 第二种方法是：用快慢指针，快指针比慢指针快n个节点，这样，当快指针到结尾时，慢指针指的就是第n个节点
 * 为了方便处理，最好指向倒数第n+1个节点，可以在开头增加哑结点，这样即使是删除第一个节点，也不用做特殊处理
 *
 * 在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），它的 next 指针指向链表的头节点。
 * 这样一来，我们就不需要对头节点进行特殊的判断了，可省去许多麻烦。
 *
 *
 */
class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }

        //求长度
        int length = 1;
        ListNode tmpHead = head;
        while (tmpHead.next != null) {
            tmpHead = tmpHead.next;
            length++;
        }

        int nodeIndex = length - n + 1;

        if (nodeIndex < 1) {
            return head;
        }

        //如果删除的恰好是头结点，特殊处理下
        if (nodeIndex == 1) {
            return head.next;
        }

        tmpHead = head;

        //链表中跳过节点
        for (int i = 2; i <= length; i++) {
            if (nodeIndex == i) {
                tmpHead.next = tmpHead.next.next;
                return head;
            }else {
                tmpHead = tmpHead.next;
            }
        }

        return head;


    }
}
