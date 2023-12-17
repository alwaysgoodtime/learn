package leetcode.src.main.java.divideAndRule;

/**
 * https://leetcode.cn/problems/merge-k-sorted-lists/
 *
 * @author goodtime
 * @create 2023-12-16 11:05
 */
public class Number23 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * @see leetcode.src.main.java.linkedList.Number21
 * 合并k个，可以将k个链表每次拿出两个，来合并，这样就变成合并2个有序链表了
 */
class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        ListNode basicNode = lists[0];

        for (int i = 1; i < lists.length; i++) {
            ListNode node = lists[i];
            basicNode = merge(basicNode, node);
        }

        return basicNode;
    }

    private ListNode merge(ListNode basicNode, ListNode node) {
        if (basicNode == null) {
            return node;
        }
        if (node == null) {
            return basicNode;
        }

        int v1 = basicNode.val;
        int v2 = node.val;

        if (v1 > v2) {
            ListNode tmp = basicNode;
            basicNode = node;
            node = tmp;
        }

        ListNode min = basicNode;

        while (basicNode != null && node != null) {

            if (basicNode.next == null) {
                basicNode.next = node;
                break;
            }

            if (basicNode.next.val <= node.val) {
                basicNode = basicNode.next;
            } else {
                ListNode tmp = basicNode.next;
                while (node != null && node.val < tmp.val) {
                    basicNode.next = node;
                    basicNode = node;
                    node = node.next;
                }

                basicNode.next = tmp;
                basicNode = tmp;
            }
        }

        return min;
    }
}