import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-19 3:16 下午
 */
public class Number15 {
    public static void main(String[] args) {
        ListNode3 listNode1 = new ListNode3(1);
        ListNode3 listNode2 = new ListNode3(2);
        ListNode3 listNode3 = new ListNode3(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        Solution15 solution15 = new Solution15();
        ListNode3 listNode31 = solution15.ReverseList(listNode1);
        System.out.println(listNode31.val);


    }
}

class ListNode3 {
    int val;
    ListNode3 next = null;

    ListNode3(int val) {
        this.val = val;
    }
}
//  算法复杂度O(n),但遍历了三次，时间复杂度过高
// class Solution15 {
//    public ListNode3 ReverseList(ListNode3 head) {
//        if(head == null || head.next == null){
//            return head;//程序健壮性
//        }
//        int i = 1;
//
//        ListNode3 firstListNode = head;
//
//        for(;head.next != null;head = head.next,i++){}
//
//        ListNode3 lastListNode = head;
//
//        ListNode3[] a = new ListNode3[i];
//
//        for(int l = 0;l<i;firstListNode = firstListNode.next,l++){
//            a[l] = firstListNode;
//        }
//
//        for (int j = 0; j < i-1; j++) {
//                head = a[i-j-1];
//                head.next = a[i - j - 2];
//            }
//
//        return lastListNode ;
//
//    }
//}


 class Solution15 {
    public ListNode3 ReverseList(ListNode3 head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode3 lastNode = head;
        for(ListNode3 nextNode = head.next;nextNode != null;){
            ListNode3 temp = nextNode.next;
            nextNode.next = lastNode;
            lastNode = nextNode;
            nextNode = temp;
        }
        head.next = null;
        return  lastNode;
    }
}