/**
 * @author goodtime
 * @create 2020-01-29 12:33 上午
 */
public class Number25 {
    public static void main(String[] args) {
        Solution25 solution25 = new Solution25();
        RandomListNode a = new RandomListNode(1);
        RandomListNode b = new RandomListNode(2);
        RandomListNode c = new RandomListNode(3);
        RandomListNode d = new RandomListNode(4);
        a.next  = b;
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

//需求：完成复杂链表的克隆功能
//不知道为什么，这种解决方案无法通过测试，也没有任何提示，不过这种方式有缺陷，比如1的next为第2个节点，1的random为第2个节点
//按我这种方式处理，会有两个第2个节点。
//class Solution25 {
//    RandomListNode rt = new RandomListNode(0);
//    public RandomListNode Clone(RandomListNode pHead) {
//        if (pHead == null) {
//            return null;
//        }
//        rt.label = pHead.label;
//        clone(pHead, rt);
//        return rt;
//    }
//
//    public void clone(RandomListNode pHead, RandomListNode current) {
//        if (pHead != null) {
//            current.label = pHead.label;
//            if (pHead.next != null) {
//                current.next = new RandomListNode(0);
//            }
//            if (pHead.random != null) {
//                current.random = new RandomListNode(0);
//            }
//            clone(pHead.next, current.next);
//            clone(pHead.random, current.random);
//        }
//    }
//}


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

        RandomListNode origin = pHead;

        //   复制每个节点
        while (origin != null) {
            RandomListNode A1 = new RandomListNode(origin.label);//复制的节点
            RandomListNode tmp = origin.next;
            origin.next = A1;
            A1.next = tmp;
            origin = tmp;
        }
        //  重新遍历链表，
        origin = pHead;

        while (origin != null) {
            origin.next.random = origin.random == null ? null : origin.random.next;
            origin = origin.next.next;
        }

        //  拆分链表
        origin = pHead;
        RandomListNode rt = pHead.next;//这一步必须写，不能直接返回pHead，否则返回空
        while (origin != null) {
            RandomListNode cloneNode = origin.next;
            origin.next = cloneNode.next;//这里不能直接写origin = cloneNode.next，否则也返回空
            if(cloneNode.next != null){
                cloneNode.next = cloneNode.next.next;
            }
            origin = origin.next;
        }
        return rt;
    }
}