package leetcode.src.main.java.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
 *
 * @author goodtime
 * @create 2023-12-08 06:11
 */

public class Number103 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> nodeList = new LinkedList<>();
        nodeList.add(root);
        ArrayList<Integer> rootNode = new ArrayList<>();
        rootNode.add(root.val);
        result.add(rootNode);
        int n = 0;

        while (nodeList.size() != 0) {

            ArrayList<Integer> nodeNumber = new ArrayList<>();
            TreeNode treeNode = nodeList.peekLast();
            Stack<Integer> stack = new Stack<>();

            while (treeNode != nodeList.peekFirst()) {

                TreeNode node = nodeList.pollFirst();
                if (node.left != null) {
                    nodeList.add(node.left);
                    if (n == 1) {
                        nodeNumber.add(node.left.val);
                    } else {
                        stack.push(node.left.val);
                    }
                }
                if (node.right != null) {
                    nodeList.add(node.right);
                    if (n == 1) {
                        nodeNumber.add(node.right.val);
                    } else {
                        stack.push(node.right.val);
                    }
                }
            }

            TreeNode node = nodeList.pollFirst();

            if (node.left != null) {
                nodeList.add(node.left);
                if (n == 1) {
                    nodeNumber.add(node.left.val);
                } else {
                    stack.push(node.left.val);
                }
            }

            if (node.right != null) {
                nodeList.add(node.right);
                if (n == 1) {
                    nodeNumber.add(node.right.val);
                } else {
                    stack.push(node.right.val);
                }
            }

            if (n == 0) {
                while (stack.size() != 0) {
                    nodeNumber.add(stack.pop());
                }
            }


            if (nodeNumber.size() != 0) {
                result.add(nodeNumber);
            }

            n = n == 1 ? 0 : 1;

        }

        return result;
    }
}
