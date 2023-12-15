package leetcode.src.main.java.linkedList;

/**
 * https://leetcode.cn/problems/rotate-list/
 *
 * @author goodtime
 * @create 2023-12-05 02:00
 */
public class Number61 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 旋转链表，当然可以先得出head的长度，然后做计算，还可以用快慢指针
 *
 * 快指针与慢指针都从head出发，距离为k，当快指针走到末尾时，慢指针指的是新链表的末尾，做相应处理即可
 *
 * 但本题k可以大于head的长度，还是先得到长度，再做其他处理，可化简k，减小复杂度
 */
class Solution61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }

        ListNode dummyNode = new ListNode(-1);

        dummyNode.next = head;


        int length = 1;
        while (head.next != null){
            head = head.next;
            length++;
        }

        //化简k
        if(k >= length){
            k = k % length;
        }

        if( k == 0){
            return dummyNode.next;
        }


        head = dummyNode.next;
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        int distance = 0;

        while (distance <= k || fastPointer.next != null) {

            if (distance <= k) {

                if (fastPointer.next == null) {
                    break;
                } else {
                    fastPointer = fastPointer.next;
                    distance++;
                }

            }

            if (distance > k) {
                slowPointer = slowPointer.next;
                distance--;
            }

        }


        //处理原尾节点
        fastPointer.next = head;

        //记录现头节点
        dummyNode.next = slowPointer.next;

        //处理现尾节点
        slowPointer.next = null;

        return dummyNode.next;

    }
}


