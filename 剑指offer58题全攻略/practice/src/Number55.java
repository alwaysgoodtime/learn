import java.util.ArrayList;

/**
 * JZ23 链表中环的入口结点
 * 给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 *
 * @author goodtime
 * @create 2020-01-28 5:14 下午
 */
public class Number55 {
    public static void main(String[] args) {
        Solution55s solution55 = new Solution55s();
        ListNode55 listNode1 = new ListNode55(1);
        ListNode55 listNode2 = new ListNode55(2);
        ListNode55 listNode3 = new ListNode55(3);
        ListNode55 listNode4 = new ListNode55(4);
        ListNode55 listNode5 = new ListNode55(5);
        ListNode55 listNode6 = new ListNode55(6);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode3;
        ListNode55 listNode55 = solution55.EntryNodeOfLoop(listNode1);
        System.out.println(listNode55.val);
        ;

    }
}

class ListNode55 {
    int val;
    ListNode55 next = null;

    ListNode55(int val) {
        this.val = val;
    }
}

//思路：找一个存ListNode55的动态数组，如果出现重复的节点，说明这个节点就是环的入口。时间复杂度O（n的平方）,空间复杂度O（n）。
class Solution55 {
    public ListNode55 EntryNodeOfLoop(ListNode55 pHead) {
        ArrayList<ListNode55> listNode55s = new ArrayList<>();
        while (pHead.next != null) {
            if (listNode55s.contains(pHead)) {
                return pHead;
            }
            listNode55s.add(pHead);
            pHead = pHead.next;
        }
        return null;
    }
}

//思路：快慢指针方法，时间复杂度O（n）,空间复杂度可以O(1)，多用了两个指针的空间

/**
 * 1）选择快慢指针，让快指针每次走两步，慢指针每次走一步，若是单链表中有环的话，那么两个指针会相遇，即指向的相同的节点的值相等来判断。
 * <p>
 * 2）当相遇的时候，慢指针在环中走了k步，设环之外的部分长为x,环的长度为n,则快指针一共走了 x+m*n+k步，（m为快指针在环中走的圈数），慢指针一共走了x+k步，因为快指针的速度是慢指针的两倍。那么可以得到2(x+k)=x+m*n+k;得到x为m*n-k ,慢指针在圈中还剩下的步数n-k;
 * <p>
 * <p>
 * 让快指针从头开始，两个指针每次都走一步，当快指针走了想x(m*n-k)步的时候，到达环的入口，慢指针在圈中走m-1圈加k步的时候，也到达环入口那个节点，两个指针再次相遇，此刻的节点就是环的入口的节点。
 * <p>
 * ————————————————
 * 版权声明：本文为CSDN博主「snow_7」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/snow_7/article/details/52181049
 */

/**
 * 快慢指针
 */
class Solution55s {
    public ListNode55 EntryNodeOfLoop(ListNode55 pHead) {
        if (pHead == null) {
            return null;
        }

        ListNode55 slow = pHead;
        ListNode55 fast = pHead;

        while (true) {
            if (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    //把快指针放到初始位置，他们第二次相遇的位置就是入口
                    fast = pHead;
                    while (fast != slow) {
                        fast = fast.next;
                        slow = slow.next;
                    }
                    return fast;
                }
            } else {
                return null;
            }
        }


    }
}