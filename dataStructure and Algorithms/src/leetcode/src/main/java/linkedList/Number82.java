package leetcode.src.main.java.linkedList;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
 *
 * @author goodtime
 * @create 2023-12-05 01:22
 */
public class Number82 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 类似快慢指针，添加哑节点，开始时，慢指针都指向哑结点，快指针指向head
 *
 * 当快指针发现自己的值与前驱和后继都不同时，慢指针指向该节点
 */
class Solution82 {
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        //按照题目要求，不会与第一个节点的值重复
        ListNode dummyNode = new ListNode(Integer.MAX_VALUE);

        ListNode slowPointer = dummyNode;
        int preValue = Integer.MAX_VALUE;
        ListNode fastPointer = head;
        int currentValue = 0;
        int nextValue = 0;

        while (fastPointer != null) {

            currentValue = fastPointer.val;
            nextValue = Integer.MAX_VALUE;

            if (fastPointer.next != null) {
                nextValue = fastPointer.next.val;
            }

            if (currentValue != nextValue && currentValue != preValue) {
                slowPointer.next = fastPointer;
                slowPointer = fastPointer;
            }

            fastPointer = fastPointer.next;
            preValue = currentValue;
        }

        //记得最后处理下尾节点，防止尾节点后面跟着重复节点，被带到结果里
        slowPointer.next = null;

        return dummyNode.next;

    }
}
