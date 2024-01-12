package leetcode.src.main.java.linkedList;

/**
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/
 *
 * @author goodtime
 * @create 2023-12-05 00:39
 */
public class Number25 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        System.out.println(new Solution25().reverseKGroup2(listNode, 3));
        System.out.println("test");
    }
}

/**
 * @see Number92
 * k个一组翻转 相当于在Number92的基础上，增加了多个范围
 *
 * eg:[2,3,5,6,7] k = 2
 *
 * 即相当于先反转范围[1,2]的节点,再反转范围[3,4]的节点
 */
class Solution25 {

    public ListNode reverseKGroup2(ListNode head, int k) {


        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        //存储前一链条的头节点，也即是翻转后前一链条的尾节点
        ListNode preNode = null;
        //遍历节点
        ListNode curNode = head;
        //哑结点，用来返回结果
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        while (curNode != null) {

            ListNode tmpHead = curNode;

            for (int i = 1; i < k; i++) {
                if (curNode.next != null) {
                    curNode = curNode.next;
                } else {
                    //说明剩余的长度不够翻转，直接返回新头节点即可
                    return dummyNode.next;
                }
            }

            if (dummyNode.next == head) {
                dummyNode.next = curNode;
            }

            ListNode nextNode = curNode.next;
            reverse2(tmpHead, curNode, preNode);

            curNode = nextNode;
            preNode = tmpHead;

        }

        return dummyNode.next;
    }

    private void reverse2(ListNode head, ListNode last, ListNode preNode) {

        ListNode pre = head;
        ListNode next = head.next;
        ListNode finalNode = last.next;

        while (next != finalNode) {
            ListNode tmp = next;
            next = swapAngGetNext(pre, next);
            pre = tmp;
        }

        head.next = finalNode;
        //整条链前面的preNode.next原本指向head，现在指向curNode
        if (preNode != null) {
            preNode.next = last;
        }

    }

    private ListNode swapAngGetNext(ListNode head, ListNode next) {
        ListNode tmp = next.next;
        next.next = head;
        return tmp;
    }


    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        //计算head节点的个数
        ListNode tmpHead = head;
        int length = 1;
        while (tmpHead.next != null) {
            tmpHead = tmpHead.next;
            length++;
        }

        ListNode result = head;

        //开始循环
        ListNode preNode = null;
        ListNode firstTreatedHead = head;
        ListNode treatedHead = firstTreatedHead;

        while (k <= length) {
            length = length - k;
            //注意，待处理的其实是k-1个，第一个头节点等返回后再处理
            ListNode[] lastNode = reverse(treatedHead, treatedHead.next, k - 1);

            if (preNode != null) {
                preNode.next = lastNode[0];
            }

            firstTreatedHead.next = lastNode[1];
            preNode = firstTreatedHead;
            firstTreatedHead = lastNode[1];
            treatedHead = firstTreatedHead;

            if (result == head) {
                result = lastNode[0];
            }

        }

        return result;

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
