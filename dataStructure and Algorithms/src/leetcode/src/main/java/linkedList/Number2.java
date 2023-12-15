package leetcode.src.main.java.linkedList;

/**
 * https://leetcode.cn/problems/add-two-numbers/
 *
 * @author goodtime
 * @create 2023-12-04 08:35
 */
public class Number2 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(4);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        System.out.println(new Solution2().addTwoNumbers(listNode, listNode4));

    }

}

/**
 * 主要注意进位的处理，以及和为9遇进位的情形
 */
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null || l2 == null) {
            return null;
        }

        boolean carry = false;
        ListNode listNode = new ListNode(-1);
        ListNode preResult = listNode;

        while (l1 != null || l2 != null) {

            int val1 = 0;
            int val2 = 0;

            if (l1 != null) {
                val1 = l1.val;
            }

            if (l2 != null) {
                val2 = l2.val;
            }

            val2 += val1;

            if (val2 >= 10) {

                if (carry) {
                    listNode.next = new ListNode(val2 % 10 + 1);
                } else {
                    listNode.next = new ListNode(val2 % 10);
                }

                carry = true;
            } else if (val2 == 9 && carry) {

                //这里需要处理两数之和为9的特殊情形
                listNode.next = new ListNode(0);
                carry = true;

            } else {

                if (carry) {
                    listNode.next = new ListNode(val2 + 1);
                } else {
                    listNode.next = new ListNode(val2);
                }

                carry = false;
            }

            listNode = listNode.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        //防止还有进位，但是两个数已经走完的情况
        if (carry) {
            listNode.next = new ListNode(1);
        }

        return preResult.next;
    }
}
