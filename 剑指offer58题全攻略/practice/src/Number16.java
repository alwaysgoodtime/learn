/**
 * @author goodtime
 * @create 2020-01-19 4:37 下午
 */
public class Number16 {
    public static void main(String[] args) {
        ListNode4 listNode4 = new ListNode4(1);
        ListNode4 listNode5 = new ListNode4(16);
        ListNode4 listNode9 = new ListNode4(17);
        ListNode4 listNode6 = new ListNode4(17);
        ListNode4 listNode7 = new ListNode4(17);
        ListNode4 listNode8 = new ListNode4(17);
        listNode4.next = listNode5;
        listNode5.next = listNode9;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        Solution16s solution16 = new Solution16s();
        ListNode4 merge = solution16.Merge(listNode4, listNode6);
        System.out.println(merge.next.next.val);
    }
}

class ListNode4 {
    int val;
    ListNode4 next = null;

    ListNode4(int val) {
        this.val = val;
    }
}

//以list1为主干，把list2融到list1当中，形成新的ListNode,如果list1的头比list2的头值大，则直接互换list1与list2
class Solution16 {
    public ListNode4 Merge(ListNode4 list1, ListNode4 list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        // 如果list1的值小，链表头就是它，否则是list2
        ListNode4 rt = list1;
        if(list1.val > list2.val){
            rt = list2;
            list2 = list1;
            list1 = rt;
        }
        ListNode4 beforeNode = null;//功能是定位把list2纳入list1时，list1之前的那个节点，方便list2融入
        ListNode4 temp;
        ListNode4 temp2;
        ListNode4 temp6;
        ListNode4 temp7;//修改链表时的备用链节点

        for (;list1 != null && list2 != null;) {

            if (list1.val <= list2.val) {
                if (list1.next == null) {
                    list1.next = list2;
                    break;//链表结束，终止循环
                } else if (list1.next.val >= list2.val) {
                    beforeNode = list2;
                    temp = list1.next;
                    temp2 = list2.next;
                    list1.next = list2;
                    list2.next = temp;
                    list1 = temp;
                    list2 = temp2;
                } else {
                    list1 = list1.next;//说明链表list1小值多，再往下个节点走
                }
            } else if (list1.val == list2.val) {
                if(list1.next != null) {
                    temp = list1.next;
                    temp2 = list2.next;
                    list1.next = list2;
                    list2.next = temp;
                    list1 = temp;
                    list2 = temp2;
                }else {
                    list1.next = list2;
                }
            } else {
                temp6 = beforeNode.next;
                temp7 = list2.next;
                beforeNode.next = list2;
                list2.next = temp6;
                beforeNode = list2;
                list2 = temp7;
            }
            }
        return rt;
    }
}

//上个版本显示复杂度高，优化了一下
class Solution16s {
    public ListNode4 Merge(ListNode4 list1, ListNode4 list2) {
        if (list1 == null) {
            return list2;//一个表为空，直接返回
        }
        if (list2 == null) {
            return list1;//同上
        }

        ListNode4 rt = list1;// 如果list1的值小，链表头就是它，否则是让list2和list1的表头互换，保证我们用list1的链表头充当返回的主链
        if(list1.val > list2.val){
            rt = list2;
            list2 = list1;
            list1 = rt;
        }

        ListNode4 beforeNode = null;//功能是定位把list2纳入list1时，list1之前的那个节点，方便list2的节点小于list1的节点时插入
        ListNode4 temp;
        ListNode4 temp2;//修改链表时的备用链节点

        while(list1 != null && list2 != null) {//结束条件是两个链表有一个为已到达末尾
            if (list1.val <= list2.val) {
                if (list1.next == null) {
                    list1.next = list2;
                    break;//链表结束，直接把list1目前节点连到list2目前节点上，然后终止循环
                } else if (list1.next.val >= list2.val) {
                    beforeNode = list2;//定位结点，当出现list2的当前节点<list1的当前节点时，插值可以插在这个值后面
                    temp = list1.next;
                    temp2 = list2.next;
                    list1.next = list2;
                    list2.next = temp;
                    list1 = temp;//list1当前节点下移
                    list2 = temp2;//list2当前节点下移
                } else {
                    list1 = list1.next;//说明链表list1的下个节点也比list2小，让list1当前节点下移
                }
            }else {//list2的当前节点大于list1的当前节点情况
                temp = beforeNode.next;
                temp2 = list2.next;
                beforeNode.next = list2;
                list2.next = temp;//list1插入list1当前节点的前一个节点，并充当新的标记节点
                beforeNode = list2;
                list2 = temp2;//list2当前节点下移
            }
        }
        return rt;
    }
}
