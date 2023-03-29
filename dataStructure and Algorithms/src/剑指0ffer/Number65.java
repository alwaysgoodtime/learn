import java.util.ArrayList;

/**
 * JZ78 把二叉树打印成多行
 * 给定一个节点数为 n 二叉树，要求从上到下按层打印二叉树的 val 值，
 * 同一层结点从左至右输出，每一层输出一行，将输出的结果存放到一个二维数组中返回。
 *
 * @author goodtime
 * @create 2023-03-28 00:25
 */
public class Number65 {
    public static void main(String[] args) {
        TreeNode65 a = new TreeNode65(1);
        TreeNode65 b = new TreeNode65(2);
        TreeNode65 c = new TreeNode65(3);
        TreeNode65 d = new TreeNode65(4);
        TreeNode65 e = new TreeNode65(7);
        TreeNode65 f = new TreeNode65(5);
        a.left = b;
        a.right = c;
        b.left = d;
        d.right = e;
        c.left = f;
        Solution65 solution = new Solution65();
        System.out.println(solution.Serialize(a));
    }


}


class TreeNode65 {
    int val;
    TreeNode65 left = null;
    TreeNode65 right = null;

    public TreeNode65(int val) {
        this.val = val;

    }
}

/**
 * 树的序列化和反序列化
 * 使用层序遍历方式,先用空指针填充为满二叉树即可
 */
class Solution65 {

    String Serialize(TreeNode65 root) {

        StringBuilder result = new StringBuilder();

        if (root == null) {
            return result.toString();
        }

        ArrayList<TreeNode65> nodeList = new ArrayList<>();
        nodeList.add(root);

        //如果当前节点的左或右节点没有子节点，就丢一个值为-1的子节点进去。
        TreeNode65 blankNode = new TreeNode65(-1);
        blankNode.left = blankNode;
        blankNode.right = blankNode;

        //计算树的层深
        int depth = count(root, 1);

        int dealCount = (int) Math.pow(2, depth);

        //用-1节点填充原树，使其变为满二叉树
        for (int i = 0; i < dealCount - 1; i++) {
            TreeNode65 dealNode = nodeList.get(i);
            nodeList.add(dealNode.left != null ? dealNode.left : blankNode);
            nodeList.add(dealNode.right != null ? dealNode.right : blankNode);
        }

        for (TreeNode65 node : nodeList) {
            result.append(node.val).append(",");
        }

        result.deleteCharAt(result.lastIndexOf(","));

        return result.toString();

    }

    private int count(TreeNode65 root, int count) {
        if (root.left != null && root.right == null) {
            return count(root.left, ++count);
        } else if (root.left == null && root.right != null) {
            return count(root.right, ++count);
        } else if (root.left != null && root.right != null) {
            int tmp = count;
            int countLeft = count(root.left, ++tmp);
            tmp = count;
            int countRight = count(root.right, ++tmp);
            return countLeft >= countRight ? countLeft : countRight;
        } else {
            return count;
        }
    }


    //层层序列化后的字符串
    TreeNode65 Deserialize(String str) {
        if (str.equals("")) {
            return null;
        }

        String[] nodes = str.split(",");

        TreeNode65 head = new TreeNode65(Integer.parseInt(nodes[0]));

        generate(head, 0, nodes);

        return head;
    }

    private void generate(TreeNode65 node, int index, String[] nodes) {

        int left = 2 * index + 1;
        int right = left + 1;

        int leftValue = Integer.parseInt(nodes[left]);

        if (leftValue != -1) {
            TreeNode65 leftNode = new TreeNode65(leftValue);
            node.left = leftNode;
            generate(leftNode,left,nodes);
        }

        int rightValue = Integer.parseInt(nodes[right]);

        if (rightValue != -1) {
            TreeNode65 rightNode = new TreeNode65(rightValue);
            node.right = rightNode;
            generate(rightNode,right,nodes);
        }
    }
}
