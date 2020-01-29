import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;

import java.util.Stack;

/**
 * @author goodtime
 * @create 2020-01-19 9:32 下午
 */
public class Number17 {
    public static void main(String[] args) {
        TreeNode2 treeNode1 = new TreeNode2(1);
        TreeNode2 treeNode2 = new TreeNode2(2);
        TreeNode2 treeNode3 = new TreeNode2(2);
        TreeNode2 treeNode4 = new TreeNode2(4);
        TreeNode2 treeNode5 = new TreeNode2(3);
        TreeNode2 treeNode6 = new TreeNode2(6);
        TreeNode2 treeNode7 = new TreeNode2(2);
        TreeNode2 treeNode8 = new TreeNode2(3);
        treeNode7.right = treeNode8;
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;
        treeNode3.right = treeNode5;
        treeNode1.right = treeNode6;
        Solution17 solution17 = new Solution17();
        boolean b = solution17.preorderTraversal(treeNode1, treeNode7);
        System.out.println(b);


    }
}

class TreeNode2 {
 int val = 0;
 TreeNode2 left = null;
 TreeNode2 right = null;
 public TreeNode2(int val) {
 this.val = val;
 }

 }
// 思路：首先遍历二叉树root1的每个节点，如果root2和root1的某个节点相同，然后再往下遍历，判定是否为子结构。
//
//循环实现中序遍历
//    public void inorderTraversal(TreeNode2 root){
//         Stack<TreeNode2> s = new Stack<TreeNode2>();
//         TreeNode2 current = root;
//         int[]
//         while(current != null || !s.isEmpty()){
//             if(current != null){
//                 s.push(current);
//                 current = current.left;
//             }else{
//                 TreeNode2 pop = s.pop();
//                 System.out.println(pop.val);
//                 current = pop.right;
//             }
//         }
//    }



class Solution17 {
    public boolean HasSubtree(TreeNode2 root1, TreeNode2 root2) {
        if(root2 == null || root1 == null){//避免后续空指针异常，提前进行处理
            return false;
        }
        return  preorderTraversal(root1,root2);

    }

    public boolean preorderTraversal(TreeNode2 root,TreeNode2 root2) {

        boolean rt = false;

        if(root == null){
            return false;
        }

        if(root.val == root2.val){
            rt = preorderTraversal2(root,root2);
            if(rt){
                return rt;//这里如果不作判断，同时此处值为false; 后续如果有符合的条件，就无法继续迭代，而是直接返回false
            }
        }

        if(preorderTraversal(root.left, root2) || preorderTraversal(root.right, root2)){
            return true;
        }

        return false;
    }

    public boolean preorderTraversal2(TreeNode2 root,TreeNode2 root2){
        if(root2 == null){
            return true;
        }
        else if(root == null){
            return false;
        }
        else if(root.val != root2.val){
                return false;
        }else{
            return preorderTraversal2(root.left,root2.left) && preorderTraversal2(root.right,root2.right);
        }
    }

}