package leetcode.src.main.java.binaryTree;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/
 *
 * @author goodtime
 * @create 2023-12-06 20:09
 */
public class Number117 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 深度遍历即可,考虑用linkedList,很方便
 */
class Solution117 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            val = val;
            left = left;
            right = right;
            next = next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        LinkedList<Node> list = new LinkedList<>();

        if (root.left != null) {
            list.add(root.left);
        }
        if (root.right != null) {
            list.add(root.right);
        }

        while (list.size() != 0) {

            Node last = list.getLast();

            while (list.getFirst() != last) {
                Node node = list.poll();
                if (list.size() != 0) {
                    Node nodeNext = list.getFirst();
                    node.next = nodeNext;
                    if (node.left != null) {
                        list.add(node.left);
                    }
                    if (node.right != null) {
                        list.add(node.right);
                    }
                }
            }

            if (last == list.getFirst()) {
                last = list.pollFirst();
                if (last.left != null) {
                    list.add(last.left);
                }
                if (last.right != null) {
                    list.add(last.right);
                }
            }

        }

        return root;
    }
}
