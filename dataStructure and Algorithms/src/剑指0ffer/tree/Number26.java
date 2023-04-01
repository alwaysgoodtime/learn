package 剑指0ffer.tree;

import java.util.ArrayList;

/**
 * JZ36 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。如下图所示
 *
 * @author goodtime
 * @create 2020-01-29 12:28 下午
 */
public class Number26 {
    public static void main(String[] args) {
        TreeNode26 a = new TreeNode26(5);
        TreeNode26 b = new TreeNode26(4);
        TreeNode26 c = new TreeNode26(3);
        TreeNode26 d = new TreeNode26(2);
        TreeNode26 e = new TreeNode26(1);
        TreeNode26 f = new TreeNode26(8);
        a.left = b;
        b.left = c;
        c.left = d;
        d.left = e;
        Solution26s solution26 = new Solution26s();
        TreeNode26 convert = solution26.Convert(a);
        System.out.println(convert.val);

    }
}

class TreeNode26 {
    int val = 0;
    TreeNode26 left = null;
    TreeNode26 right = null;

    public TreeNode26(int val) {
        this.val = val;
    }

}

//思路：根据二叉搜索树的特性，进行中序遍历得到的节点顺序，就是一个从小到大排序的链表数组。不过这样思路简单，空间复杂度是O（n）
class Solution26 {
    ArrayList<TreeNode26> a = new ArrayList<TreeNode26>();

    public TreeNode26 Convert(TreeNode26 pRootOfTree) {
        if (pRootOfTree == null) {
            return pRootOfTree;
        }
        inorderTraversal(pRootOfTree);
        for (int i = 0; i < a.size(); i++) {
            a.get(i).left = i - 1 > -1 ? a.get(i - 1) : null;
            a.get(i).right = i + 1 < a.size() ? a.get(i + 1) : null;
        }
        return a.get(0);
    }

    private void inorderTraversal(TreeNode26 pRootOfTree) {
        if (pRootOfTree != null) {
            inorderTraversal(pRootOfTree.left);
            a.add(pRootOfTree);
            inorderTraversal(pRootOfTree.right);
        }
    }
}

//思路：中序遍历的顺序就是节点应该返回的顺序，主要是整理节点的左右指针指向，我们可以造一个新的链表，然后把这些节点一个一个
//加到新链表上面，最后返回最左边的节点即可（中序遍历的头节点）。 不过这样创建了两个新节点，一个是头节点，一个是指向返回
//链表当前尾节点的节点

class Solution26plus {
    private TreeNode26 pLast = null;
    private int count = 0;

    public TreeNode26 Convert(TreeNode26 pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        TreeNode26 head = Convert(pRootOfTree.left);
        if (count == 0 && head == null) {
            head = pRootOfTree;
            count++;
        }
        pRootOfTree.left = pLast;
        if (pLast != null) {
            pLast.right = pRootOfTree;
        }
        pLast = pRootOfTree;
        Convert(pRootOfTree.right);
        return head;
    }
}

//需要中序遍历，然后设置一个last节点，存储当前节点的前一个节点
//head存储最开头的那个节点。

class Solution26s {

    private TreeNode26 last;
    private TreeNode26 head;

    public TreeNode26 Convert(TreeNode26 pRootOfTree) {

        if (pRootOfTree == null) {
            return null;
        }

        if (pRootOfTree.left != null) {
            Convert(pRootOfTree.left);
        }

        //如果last为空，那么它就是头节点，如果last不为空，那么它接在last后面即可
        if (last == null) {
            head = pRootOfTree;
            last = pRootOfTree;
        } else {
            pRootOfTree.left = last;
            last.right = pRootOfTree;
            last = pRootOfTree;
        }

        if (pRootOfTree.right != null) {
            Convert(pRootOfTree.right);
        }

        return head;
    }
}

