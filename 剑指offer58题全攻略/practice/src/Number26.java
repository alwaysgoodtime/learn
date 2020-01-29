import javax.swing.tree.TreeNode;
import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-29 12:28 下午
 */
public class Number26 {
    public static void main(String[] args) {
        TreeNode26 a = new TreeNode26(7);
        TreeNode26 b = new TreeNode26(5);
        TreeNode26 c = new TreeNode26(9);
        TreeNode26 d = new TreeNode26(2);
        TreeNode26 e = new TreeNode26(6);
        TreeNode26 f = new TreeNode26(8);
        a.left = b;
        a.right = c;
        b.left = d;
        d.right = e;
        c.left = f;
        Solution26 solution26 = new Solution26();
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
        if(pRootOfTree == null ){
            return pRootOfTree;
        }
        inorderTraversal(pRootOfTree);
        for (int i = 0; i < a.size(); i++) {
            a.get(i).left = i-1 > -1 ? a.get(i-1) : null;
            a.get(i).right = i+1 < a.size() ? a.get(i+1) : null;
        }
        return a.get(0);
    }

    private void inorderTraversal(TreeNode26 pRootOfTree) {
        if(pRootOfTree != null){
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
        if(pRootOfTree == null){
            return null;
        }
        TreeNode26 head = Convert(pRootOfTree.left);
        if(count == 0 && head == null){
            head = pRootOfTree;
            count++;
        }
        pRootOfTree.left = pLast;
        if(pLast != null){
            pLast.right = pRootOfTree;
        }
        pLast = pRootOfTree;
        Convert(pRootOfTree.right);
        return head;
    }
}

