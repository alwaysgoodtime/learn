package leetcode.src.main.java.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/average-of-levels-in-binary-tree/
 *
 * @author goodtime
 * @create 2023-12-08 05:49
 */
public class Number637 {
    public static void main(String[] args) {

    }
}

/**
 * 层序遍历
 */
class Solution637 {

    public List<Double> averageOfLevels(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }

        List<Double> result = new ArrayList<>();
        LinkedList<TreeNode> nodeList = new LinkedList<>();
        nodeList.add(root);

        while (nodeList.size() != 0) {

            int n = 0;
            double sum = 0;

            TreeNode treeNode = nodeList.peekLast();

            while (treeNode != nodeList.peekFirst()){

                TreeNode node = nodeList.pollFirst();
                if (node.left != null) {
                    nodeList.add(node.left);
                }
                if (node.right != null) {
                    nodeList.add(node.right);
                }
                sum += node.val;
                n++;
            }

            n++;
            TreeNode node = nodeList.pollFirst();
            if (node.left != null) {
                nodeList.add(node.left);
            }
            if (node.right != null) {
                nodeList.add(node.right);
            }
            sum += node.val;
            result.add(sum/n);
        }

        return result;


    }
}