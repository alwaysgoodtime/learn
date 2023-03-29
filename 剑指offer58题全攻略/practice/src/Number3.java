import java.util.ArrayList;
import java.util.HashMap;

/**
 * JZ6 输入一个链表的头节点，按链表从尾到头的顺序返回每个节点的值（用数组返回）。
 *
 * @author goodtime
 * @create 2020-01-18 12:16 上午
 */
public class Number3 {
    public static void main(String[] args) {
        Solution3s solution3 = new Solution3s();
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(5);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        ArrayList<Integer> integers = solution3.printListFromTailToHead(listNode);
        System.out.println(integers);
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

/**
 * 可以用栈，遍历一遍链表，然后将栈中的值一个一个放到数组里，后入先出
 * 不用栈的话，将链表放到数组里，然后反转即可
 */
class Solution3 {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        if (listNode == null) {
            return new ArrayList<Integer>();
        }

        ArrayList<Integer> integers = new ArrayList<>();

        while (listNode != null) {
            integers.add(listNode.val);
            listNode = listNode.next;
        }

        int size = integers.size();

        for (int i = 0; i < size / 2; i++) {
            Integer tmp = integers.get(size - i - 1);
            integers.set(size - i - 1, integers.get(i));
            integers.set(i, tmp);
        }


        return integers;
    }

}

/**
 * 也可以用hashMap,key为节点顺序，value为节点的值。因为链表的顺序是固定的，只要反着取即可
 */
class Solution3s {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        if (listNode == null) {
            return new ArrayList<>();
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; listNode != null; i++) {
            map.put(i, listNode.val);
            listNode = listNode.next;
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = map.size() - 1; i >= 0; i--) {
            result.add(map.get(i));
        }

        return result;

    }
}