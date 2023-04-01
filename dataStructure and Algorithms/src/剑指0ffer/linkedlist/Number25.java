

/**
 * JZ35 复杂链表的复制
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），
 * 请对此链表进行深拷贝，并返回拷贝后的头结点。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）。
 *
 * @author goodtime
 * @create 2020-01-29 12:33 上午
 */
public class Number25 {
    public static void main(String[] args) {
        Solution25 solution25 = new Solution25();
        RandomListNode a = new RandomListNode(1);
        RandomListNode b = new RandomListNode(2);
        RandomListNode d = new RandomListNode(4);
        a.next = b;
        a.random = b;
        b.next = d;
        RandomListNode clone = solution25.Clone(a);
        System.out.println(clone.random.next.label);

    }
}


class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

//解题思路:题目有个隐藏条件，就是random指针指向的都是主链next链上的节点，而非一个孤立的节点
//那样copy就会出问题，所以这应该是题目隐藏信息
//*1、遍历链表，复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
//*2、重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
//*3、拆分链表，将链表拆分为原链表和复制后的链表
class Solution25 {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }

        RandomListNode tmp = pHead;

        while (tmp != null) {
            RandomListNode nextNode = tmp.next;
            RandomListNode cloneNextNode = new RandomListNode(tmp.label);
            tmp.next = cloneNextNode;
            cloneNextNode.next = nextNode;
            tmp = nextNode;
        }

        tmp = pHead;

        while (tmp != null) {
            RandomListNode cloneNextNode = tmp.next;
            cloneNextNode.random = tmp.random == null ? null : tmp.random.next;
            tmp = cloneNextNode.next;
        }

        tmp = pHead;
        RandomListNode preCloneNode = new RandomListNode(-1);

        for (RandomListNode cloneNode = preCloneNode; tmp != null; ) {
            cloneNode.next = tmp.next;
            tmp.next = tmp.next.next;
            cloneNode = cloneNode.next;
            tmp = tmp.next;
        }

        return preCloneNode.next;

    }

}