package leetcode.src.main.java.linkedList;

/**
 * https://leetcode.cn/problems/copy-list-with-random-pointer/
 *
 * @author goodtime
 * @create 2023-12-04 09:23
 */
public class Number138 {
    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        node.next = node2;
        node.random = node2;
        node2.random = node2;
        System.out.println(new Solution138().copyRandomList(node));

    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

/**
 * 第一次遍历，在原链表上，先把每个节点的值和它的next拷贝一份
 *
 * 第二次遍历，因为random节点还没有更改，让偶数节点的random节点指向现在指向节点的下一个节点
 *
 * 第三次遍历，把偶数节点全部拆出来
 */
class Solution138 {
    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        Node tmpHead = head;

        //拷贝每个节点
        while (tmpHead != null) {
            Node copyNode = new Node(tmpHead.val);
            copyNode.next = tmpHead.next;
            tmpHead.next = copyNode;
            tmpHead = copyNode.next;
        }

        tmpHead = head;

        //拷贝random
        while (tmpHead != null) {
            if (tmpHead.random != null) {
                tmpHead.next.random = tmpHead.random.next;
            }

            tmpHead = tmpHead.next.next;
        }

        //拆出偶数节点
        tmpHead = head;
        Node resultNode = tmpHead.next;

        while (tmpHead.next != null) {

            if (tmpHead.next.next != null) {
                //偶数的节点copy的next为copy2
                Node copy = tmpHead.next;
                Node copy2 = tmpHead.next.next.next;
                tmpHead.next = tmpHead.next.next;
                copy.next = copy2;
                tmpHead = tmpHead.next;
            } else {
                tmpHead.next = null;
            }
        }


        return resultNode;

    }
}
