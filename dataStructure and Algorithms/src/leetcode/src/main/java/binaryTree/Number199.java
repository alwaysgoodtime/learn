package leetcode.src.main.java.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-right-side-view/
 *
 * @author goodtime
 * @create 2023-12-08 05:36
 */
public class Number199 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}


/**
 * 层序遍历
 */
class Solution199 {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        LinkedList<TreeNode> nodeList = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        nodeList.add(root);

        while (nodeList.size() != 0) {

            TreeNode last = nodeList.getLast();

            while (nodeList.peekFirst() != last) {
                TreeNode node = nodeList.pollFirst();
                if (node.left != null) {
                    nodeList.add(node.left);
                }
                if (node.right != null) {
                    nodeList.add(node.right);
                }
            }

            TreeNode treeNode = nodeList.pollFirst();
            if (treeNode.left != null) {
                nodeList.add(treeNode.left);
            }
            if (treeNode.right != null) {
                nodeList.add(treeNode.right);
            }
            result.add(treeNode.val);

        }

        return result;

    }
}
