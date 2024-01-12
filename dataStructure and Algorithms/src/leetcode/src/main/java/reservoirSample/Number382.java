package leetcode.src.main.java.reservoirSample;

import java.util.Random;

/**
 * https://leetcode.cn/problems/linked-list-random-node/
 * @author goodtime
 * @create 2024-01-08 21:31
 */
public class Number382 {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(2));
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * 核心是蓄水池抽样算法
 */
class Solution382 {

    ListNode head;

    public Solution382(ListNode head) {
        this.head = head;
    }

    public int getRandom() {

        ListNode result = null;
        int count = 1;

        Random random = new Random();

        ListNode tmp = head;

        while (tmp != null) {

            int i = random.nextInt(count);
            if (i == 0) {
                result = tmp;
            }

            tmp = tmp.next;
            count++;
        }

        return result == null ? -1 : result.val;

    }
}

