import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-18 12:16 上午
 */
public class Number3 {
    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        ListNode listNode  = new ListNode(1);
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(4);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        ArrayList<Integer> integers = solution3.printListFromTailToHead(listNode);
        System.out.println(integers);
    }
}

//  这个解法额外用了stringBuilder类,同时，只能适用于其中int值只有1位的情况。
//    class Solution3 {
//        public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(listNode.val);
//            for (ListNode next = listNode.next;next != null;) {
//                stringBuilder.append(next.val);
//                next = next.next;
//            }
//            stringBuilder.reverse();
//            ArrayList<Integer> integers = new ArrayList<>();
//            for(int i = 0;i < stringBuilder.length();i++){
//                integers.add(stringBuilder.charAt(i) - '0');//char转int的方法
//            }
//            return  integers;
//        }
//    }
    class Solution3 {
        public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
            ArrayList<Integer> integers = new ArrayList<>();
            if (listNode != null) {
                integers.add(listNode.val);
                for (ListNode next = listNode.next; next != null; ) {
                    integers.add(next.val);
                    next = next.next;
                }
                for (int i = integers.size() - 1,j = 0; i > j ; i--,j++) {
                    {
                        int k = integers.get(i);
                        integers.set(i,integers.get(j));
                        integers.set(j,k);
                    }
                }
            }
            return integers;
        }
    }

    class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

