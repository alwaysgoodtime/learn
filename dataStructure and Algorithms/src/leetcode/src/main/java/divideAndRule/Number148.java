package leetcode.src.main.java.divideAndRule;

/**
 * https://leetcode.cn/problems/sort-list/
 *
 * @author goodtime
 * @create 2023-12-08 11:38
 */
public class Number148 {

    public static void main(String[] args) {
        System.out.println("test");
    }

}

/**
 * 暴力解法是每次找到剩余链表中最小的节点，然后往前排
 *
 * 既然是排序，考虑用归并排序
 */
class Solution148 {

    class ListNode {
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


    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    /**
     * 其中的元素是左闭右开，也即，排序的是[head,tail)之间的节点
     * 其中节点为0个或1个时，视为排序结束
     *
     * @param head
     * @param tail
     * @return
     */
    private ListNode sortList(ListNode head, ListNode tail) {

        //其实没有什么用，只是防止head一开始就为null的情况
        if (head == null) {
            return head;
        }

        if (head.next == tail) {
            head.next = null;
            return head;
        }

        ListNode slowPointer = head;
        ListNode fastPointer = head;

        //快2慢1，这样快到结尾时，慢正好在中点
        while (fastPointer != tail && fastPointer.next != tail) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }

        ListNode list1 = sortList(head, slowPointer);
        ListNode list2 = sortList(slowPointer, tail);
        return merge(list1, list2);
    }

    /**
     * @see leetcode.src.main.java.linkedList.Number21
     */
    private ListNode merge(ListNode list1, ListNode list2) {

        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        int val = list1.val;
        int val2 = list2.val;

        ListNode min = list1;
        //保持list1的值小于list2
        if (val > val2) {
            ListNode tmp = list1;
            list1 = list2;
            list2 = tmp;
            min = list1;
        }

        while (list1 != null && list2 != null) {

            if (list1.next == null) {
                list1.next = list2;
                break;
            } else if (list1.next.val <= list2.val) {
                list1 = list1.next;
            } else {
                ListNode tmpNext = list1.next;
                while (list2 != null && tmpNext.val > list2.val) {
                    list1.next = list2;
                    list1 = list1.next;
                    list2 = list2.next;
                }
                list1.next = tmpNext;
            }
        }

        return min;
    }

    /**
     * 自底向上归并
     */
    private ListNode sortListO1(ListNode head) {

        if (head == null) {
            return null;
        }

        //求得head链表长度
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        int length = 1;
        while (head.next != null) {
            head = head.next;
            length++;
        }

        for (int subLen = 1; subLen < length; subLen = subLen << 1) {

            ListNode prev = dummyNode;
            ListNode curr = dummyNode.next;

            while (curr != null) {

                //第一个subLen
                ListNode head1 = curr;

                for (int i = 1; i < subLen && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }

                //切断该subLen与后面的链接
                ListNode tmp = curr.next;
                curr.next = null;
                curr = tmp;

                //第二个subLen
                ListNode head2 = curr;

                for (int i = 1; i < subLen && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }

                //切断该subLen与后面的链接
                if (curr != null) {
                    tmp = curr.next;
                    curr.next = null;
                    curr = tmp;
                }

                //对两个subLen进行排序，并返回头节点
                ListNode merge = merge(head1, head2);

                //更换dummyNode的下一个节点，也就是头节点，只需更换一次
                if (dummyNode.next == prev.next) {
                    dummyNode.next = merge;
                }

                //让头节点后继指向新的合并链表头节点
                prev.next = merge;
                //同时prev向后延伸，拉至合并链表的末尾，等待与下一条合并链接继续连接
                while (prev.next != null){
                    prev = prev.next;
                }
            }


        }

        return dummyNode.next;

    }
}
