import java.util.ArrayList;

/**
 * JZ52 两个链表的第一个公共结点
 * 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。
 * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 *
 * @author goodtime
 * @create 2020-01-23 11:46 上午
 */
public class Number36 {
    public static void main(String[] args) {
        Solution36s solution36 = new Solution36s();
        ListNode36 a = new ListNode36(1);
        ListNode36 b = new ListNode36(2);
        ListNode36 c = new ListNode36(3);
        ListNode36 d = new ListNode36(4);
        ListNode36 e = new ListNode36(6);
        a.next = b;
        b.next = c;
        d.next = e;
        e.next = a;
        ListNode36 listNode = solution36.FindFirstCommonNode(a, d);
        System.out.println(listNode.val);
    }
}


class ListNode36 {
    int val;
    ListNode36 next = null;

    ListNode36(int val) {
        this.val = val;
    }
}

//找公共节点，不是节点值相等，而是两个链表共用一个节点，之后的链表也一模一样了。
//最简单的思路是，固定一个主链表，一个副链表，从主链表的第一个节点开始，遍历一遍副链表，看看有没有相同的，没有的话，再继续遍历主链表的下一个节点。
//时间复杂度是O（n的平方）
//第二种思路是遍历主链表的节点，先用ArrayList存起来主链表所有节点，然后从前往后遍历副链表，判断副链表中哪个节点在其中，第一个相等的节点就是第一个公共节点（用==即可）

//第三种思路是，开始遍历两遍链表获取两个表的长度，比较长度让长的一个先走差值个步长，
//再两个一起走。（这是快慢指针思想，也是链表问题的一般性思路）

//快慢指针
class Solution36s {
    public ListNode36 FindFirstCommonNode(ListNode36 pHead1, ListNode36 pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }

        int lengthHead1 = 1;
        int lengthHead2 = 1;
        ListNode36 tmp = pHead1;
        ListNode36 tmp2 = pHead2;
        while (tmp.next != null || tmp2.next != null) {
            if (tmp.next != null) {
                lengthHead1++;
                tmp = tmp.next;
            }
            if (tmp2.next != null) {
                lengthHead2++;
                tmp2 = tmp2.next;
            }
        }

        if (lengthHead1 >= lengthHead2) {
            int diff = lengthHead1 - lengthHead2;
            while ((diff--) != 0) {
                pHead1 = pHead1.next;
            }
        } else {
            int diff = lengthHead2 - lengthHead1;
            while ((diff--) != 0) {
                pHead2 = pHead2.next;
            }
        }

        while (pHead1 != null) {
            if (pHead1 != pHead2) {
                pHead1 = pHead1.next;
                pHead2 = pHead2.next;
            } else {
                return pHead1;
            }
        }

        return null;

    }
}


//下面采用第二种思路
class Solution36 {
    public ListNode36 FindFirstCommonNode(ListNode36 pHead1, ListNode36 pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        ArrayList arraylist = new ArrayList();
        while (pHead1 != null) {
            arraylist.add(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            if (arraylist.contains(pHead2)) {
                return pHead2;
            }
            pHead2 = pHead2.next;
        }
        return null;
    }
}