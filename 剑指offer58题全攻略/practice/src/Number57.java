/**
 *JZ8 二叉树的下一个结点
 *给定一个二叉树其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 *注意，树中的结点不仅包含左右子结点，同时包含指向父结点的next指针。
 * @author goodtime
 * @create 2020-01-28 8:24 下午
 */
public class Number57 {
    public static void main(String[] args) {
        TreeLinkNode57 a = new TreeLinkNode57(1);
        TreeLinkNode57 b = new TreeLinkNode57(2);
        TreeLinkNode57 c = new TreeLinkNode57(3);
        TreeLinkNode57 d = new TreeLinkNode57(4);
        TreeLinkNode57 e = new TreeLinkNode57(5);
        TreeLinkNode57 f = new TreeLinkNode57(6);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        b.next = a;
        c.next = a;
        d.next = b;
        e.next = b;
        f.next = c;
        Solution57 solution57 = new Solution57();
        TreeLinkNode57 treeLinkNode57 = solution57.GetNext(a);
        System.out.println(treeLinkNode57.val);
    }
}

class TreeLinkNode57 {
    int val;
    TreeLinkNode57 left = null;
    TreeLinkNode57 right = null;
    TreeLinkNode57 next = null;

    TreeLinkNode57(int val) {
        this.val = val;
    }
}

//思路：视当前节点为根节点，中序遍历，下一步要中序遍历其右边的子节点，如果没有右子树，查看其是否有父节点，没有则返回null
//如果有父节点，且为左子树（如果为父节点的右子树，返回null），返回父节点。
//
class Solution57 {
    public TreeLinkNode57 GetNext(TreeLinkNode57 pNode) {
        if (pNode == null) {
            return null;//程序健壮性
        }
        if (pNode.right != null) {
            TreeLinkNode57 node = pNode.right;
            while (node != null && node.left != null) {
                node = node.left;
            }
            return node;//右子树不为空，返回其中中序遍历的第一个节点
        }
        while (pNode.next != null) {
            if (pNode.next.right == pNode) {
                pNode = pNode.next;
            } else {
                return pNode.next;//找到它的父节点中，父节点是祖先节点左节点的节点，并返回它
            }
        }
        return null;//没有下一个节点
    }
}

//找规律题 中序遍历顺序 左 —> 根 -> 右
class Solution57s {
    public TreeLinkNode57 GetNext(TreeLinkNode57 pNode) {

        //程序健壮性
        if (pNode == null) {
            return null;
        }

        //如果该节点有右子节点，中序遍历右子节点即可
        if(pNode.right != null){
            return middleOrder(pNode.right);
        }

        //如果没有右子节点，开始寻父之旅
        return findRightFather(pNode);
    }

    private TreeLinkNode57 findRightFather(TreeLinkNode57 pNode) {
        //看自己是否有父节点
        if(pNode.next == null){
            return null;
        }

        //如果父节点的左节点是自己，那么下一个节点就是父节点
        if(pNode.next.left == pNode){
            return pNode.next;
        }

        //如果父节点的右节点是自己，那么需要找父节点的左父节点
        return findRightFather(pNode.next);
    }

    //中序遍历时，如果有左节点，左节点优先，否则就是这个节点自身
    private TreeLinkNode57 middleOrder(TreeLinkNode57 node) {

        if(node.left != null){
            return middleOrder(node.left);
        }

        return node;
    }


}