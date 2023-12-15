package leetcode.src.main.java.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/description/
 *
 * @author goodtime
 * @create 2023-12-08 05:57
 */
public class Number102 {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode.left = treeNode2;
        treeNode.right = treeNode3;
        System.out.println(new Solution102().levelOrder(treeNode));
    }
}

/**
 * 层序遍历
 */
class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> nodeList = new LinkedList<>();
        nodeList.add(root);
        ArrayList<Integer> rootNode = new ArrayList<>();
        rootNode.add(root.val);
        result.add(rootNode);

        while (nodeList.size() != 0) {

            ArrayList<Integer> nodeNumber = new ArrayList<>();
            TreeNode treeNode = nodeList.peekLast();

            while (treeNode != nodeList.peekFirst()) {

                TreeNode node = nodeList.pollFirst();
                if (node.left != null) {
                    nodeList.add(node.left);
                    nodeNumber.add(node.left.val);
                }
                if (node.right != null) {
                    nodeList.add(node.right);
                    nodeNumber.add(node.right.val);
                }
            }

            TreeNode node = nodeList.pollFirst();
            if (node.left != null) {
                nodeList.add(node.left);
                nodeNumber.add(node.left.val);
            }
            if (node.right != null) {
                nodeList.add(node.right);
                nodeNumber.add(node.right.val);
            }

            if(nodeNumber.size() != 0) {
                result.add(nodeNumber);
            }

        }

        return result;


    }
}
