/**
 * @author goodtime
 * @create 2020-01-19 2:48 下午
 */
public class Number14 {
    public static void main(String[] args) {
        ListNode2 listNode1 = new ListNode2(1);
        ListNode2 listNode2 = new ListNode2(4);
        ListNode2 listNode3 = new ListNode2(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        Solution14 solution14 = new Solution14();
        ListNode2 listNode21 = solution14.FindKthToTail(listNode1, 2);
        System.out.println(listNode21.val);

    }

}

class ListNode2 {
    int val;
    ListNode2 next = null;
    ListNode2(int val) {
        this.val = val;
    }
}

class Solution14 {
    public ListNode2 FindKthToTail(ListNode2 head,int k) {
        int i = 1;
        if(head == null){
            return null;//程序健壮性
        }
        ListNode2 rt = head;
        for(;head.next != null;head = head.next,i++){}
        if(i < k){
            return null;//程序健壮性
        }
        for(int l = 1;l< i-k+1;rt = rt.next,l++){}
        return rt;
    }
}