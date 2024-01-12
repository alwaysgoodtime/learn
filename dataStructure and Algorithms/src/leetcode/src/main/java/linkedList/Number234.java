package leetcode.src.main.java.linkedList;

/**
 * @author goodtime
 * @create 2024-01-12 17:42
 */
public class Number234 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(2);
        ListNode listNode5 = new ListNode(1);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        System.out.println(new Solution234().isPalindrome(listNode));
    }
}

class Solution234 {

    /**
     * 快慢指针 + 翻转链表
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode slowNode = dummyNode;
        ListNode fastNode = dummyNode;

        while (fastNode.next != null && fastNode.next.next != null) {

            slowNode = slowNode.next;
            fastNode = fastNode.next.next;

        }


        //翻转slowNode后面的链表（不包括slowNode）,并把头节点接到slowNode后面
        slowNode.next = reverse(slowNode.next);

        fastNode = slowNode.next;

        boolean result = true;

        while (head != slowNode.next) {
            if (head.val != fastNode.val) {
                result = false;
                break;
            }

            head = head.next;
            fastNode = fastNode.next;
        }

        //再次翻转链表，也就是恢复链表
        slowNode.next  = reverse(slowNode.next);

        //返回结果
        return result;
    }

    private ListNode reverse(ListNode head) {

        ListNode pre = head;
        ListNode next = head.next;
        pre.next = null;

        while (next != null) {
            ListNode tmp = next.next;
            next.next = pre;
            pre = next;
            next = tmp;
        }

        return pre;
    }


    private ListNode frontPointer;

    /**
     * 用栈递归
     */
    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }
}
