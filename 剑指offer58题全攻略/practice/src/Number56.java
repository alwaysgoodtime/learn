/**
 * JZ76 删除链表中重复的结点
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表 1->2->3->3->4->4->5  处理后为 1->2->5
 * 链表中的值为0 <= val <= 1000
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 *
 * @author goodtime
 * @create 2020-01-28 6:09 下午
 */

class ListNode56 {
    int val;
    ListNode56 next = null;

    ListNode56(int val) {
        this.val = val;
    }
}

public class Number56 {
    public static void main(String[] args) {
        ListNode56 listNode1 = new ListNode56(1);
        ListNode56 listNode2 = new ListNode56(1);
        ListNode56 listNode3 = new ListNode56(2);
        ListNode56 listNode4 = new ListNode56(3);
        ListNode56 listNode5 = new ListNode56(5);
        ListNode56 listNode6 = new ListNode56(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        Solution56s solution56 = new Solution56s();
        ListNode56 listNode56 = solution56.deleteDuplication(listNode1);
        System.out.println(listNode56.next.val);
    }
}

//思路：快慢指针，快指针先走一步，如果和慢指针一样，快指针走到和慢指针不一样的地方，然后慢指针等于快指针
//如果快指针和慢指针不一样，快指针向前走一步，慢指针向前走一步。（不过对于主链，要保存无重复节点的节点，用count来实现）
//指针first，返回没有重复节点的头指针

class Solution56 {
    public ListNode56 deleteDuplication(ListNode56 pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;//预处理
        }
        byte count = 0;//计数器，看看当前快慢指针不同时，是否前面经历了需要删除的相同节点，
        //如果是，当前的慢指针需要删除,不放入返回主链中
        ListNode56 low = pHead;//慢指针
        ListNode56 fast = pHead.next;//快指针
        ListNode56 main = null;//返回主链的当前节点
        ListNode56 first = null;//返回主链的头节点
        //处理头结点的位置，重置链表头指针
        while (1 == 1) {
            if (low.val == fast.val) {
                count = 1;
                if (fast.next == null) {
                    return first;
                } else {
                    fast = fast.next;
                }
            } else {
                if (count == 1) {
                    count = 0;
                } else {
                    first = low;
                    main = first;
                    break;
                }
                low = fast;
                if (fast.next == null) {
                    return fast;//直接返回fast，它就是链表头，链表中就它一个节点
                } else {
                    fast = fast.next;
                }
            }
        }
        //快慢指针，按部就班删重复数组
        while (1 == 1) {
            if (low.val == fast.val) {
                count = 1;
                if (fast.next == null) {
                    main.next = null;//main存的是low或者first的指向节点的地址，原来的节点其next可能还有重复节点，这里重置
                    //其next值，保证其为链表尾节点
                    return first;
                } else {
                    fast = fast.next;
                }
            } else {
                if (count == 1) {
                    count = 0;
                } else {
                    main.next = low;
                    main = main.next;
                }
                low = fast;
                if (fast.next == null) {
                    main.next = low;//如果fast没有下个节点，让主链包含这最后一个节点，肯定不重复
                    return first;
                } else {
                    fast = fast.next;
                }
            }
        }
    }
}

/**
 * 思路还是快慢指针，为了简化第一个头节点是否会被消去的问题，引入了一个存储值为负数的哨兵，最后再把它消去
 */
class Solution56s {
    public ListNode56 deleteDuplication(ListNode56 pHead) {

        //预处理
        if (pHead == null || pHead.next == null) {
            return pHead;
        }

        //因为默认节点的值为0~1000，可以插入一个为负值的头结点，保证其不会被删去
        ListNode56 preNode = new ListNode56(-1);
        preNode.next = pHead;

        for (ListNode56 lastNode = preNode; lastNode.next != null && lastNode.next.next != null; ) {

            ListNode56 slow = lastNode.next;
            ListNode56 fast = slow.next;

            if (slow.val == fast.val) {
                while (fast != null && slow.val == fast.val) {
                    fast = fast.next;
                }
                lastNode.next = fast;
            } else {
                lastNode = lastNode.next;
            }
        }

        return preNode.next;
    }
}