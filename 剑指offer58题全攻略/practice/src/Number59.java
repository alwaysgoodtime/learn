/**
 * JZ18 删除链表的节点
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。
 * 1.此题对比原题有改动
 * 2.题目保证链表中节点的值互不相同
 * 3.该题只会输出返回的链表和结果做对比，所以若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 *
 * @author goodtime
 * @create 2023-03-26 16:17
 */
public class Number59 {
    public static void main(String[] args) {
        ListNode59 a = new ListNode59(1);
        ListNode59 b = new ListNode59(2);
        ListNode59 c = new ListNode59(5);
        ListNode59 d = new ListNode59(3);
        a.next = b;
        b.next = c;
        c.next = d;
        Solution59 solution59 = new Solution59();
        System.out.println(solution59.deleteNode(a, 5).val);
    }

}

class ListNode59 {
    int val;
    ListNode59 next = null;

    ListNode59(int val) {
        this.val = val;
    }
}


class Solution59 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param head ListNode类
     * @param val  int整型
     * @return ListNode类
     */
    public ListNode59 deleteNode(ListNode59 head, int val) {
        if (head == null) {
            return null;
        }

        //如果头节点就是目标值，返回它的下一个节点
        if(head.val == val){
            return head.next;
        }

        ListNode59 lastNode = head;
        ListNode59 tmp = head.next;

        while (tmp != null) {
            if (tmp.val == val) {
                lastNode.next = tmp.next;
                return head;
            }else {
                lastNode = tmp;
                tmp = tmp.next;
            }
        }

        return head;
    }
}