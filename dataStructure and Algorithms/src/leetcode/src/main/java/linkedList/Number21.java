package leetcode.src.main.java.linkedList;

/**
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 *
 * @author goodtime
 * @create 2023-12-04 08:57
 */
public class Number21 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 以一条链为最终链返回即可
 */
class Solution21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        int val1 = list1.val;
        int val2 = list2.val;

        //以list1为主链，如果val1>val2，交换
        ListNode result = list1;
        if (val1 > val2) {
            result = list2;
            list2 = list1;
            list1 = result;
        }

        while (list2 != null) {

            //永远保持list1.val <= list2.val，所以可以直接接
            //同时，list1永不为null，在后续操作中保证
            if (list1.next == null) {
                list1.next = list2;
                break;
            }

            if (list1.next.val <= list2.val) {
                list1 = list1.next;
            } else {
                ListNode tmp = list2;
                list2 = list2.next;
                tmp.next = list1.next;
                list1.next = tmp;
                list1 = list1.next;
            }

        }

        return result;
    }

}
