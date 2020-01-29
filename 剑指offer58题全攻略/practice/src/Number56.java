/**
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
        ListNode56 listNode5 = new ListNode56(3);
        ListNode56 listNode6 = new ListNode56(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        Solution56 solution56 = new Solution56();
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