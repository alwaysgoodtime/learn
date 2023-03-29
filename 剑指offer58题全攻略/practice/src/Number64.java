import java.util.ArrayList;
import java.util.Stack;

/**
 * JZ78 把二叉树打印成多行
 * 给定一个节点数为 n 二叉树，要求从上到下按层打印二叉树的 val 值，
 * 同一层结点从左至右输出，每一层输出一行，将输出的结果存放到一个二维数组中返回。
 *
 * @author goodtime
 * @create 2023-03-28 00:25
 */
public class Number64 {
    public static void main(String[] args) {
        TreeNode64 a = new TreeNode64(1);
        TreeNode64 b = new TreeNode64(2);
        TreeNode64 c = new TreeNode64(3);
        TreeNode64 d = new TreeNode64(4);
        TreeNode64 e = new TreeNode64(7);
        TreeNode64 f = new TreeNode64(5);
        a.left = b;
        a.right = c;
        b.left = d;
        d.right = e;
        c.left = f;
        Solution64 solution = new Solution64();
        ArrayList<ArrayList<Integer>> print = solution.Print(a);
        System.out.println(print.toArray().toString());
    }


}


class TreeNode64 {
    int val;
    TreeNode64 left = null;
    TreeNode64 right = null;

    public TreeNode64(int val) {
        this.val = val;

    }
}

/**
 * 使用广度遍历即可
 */
class Solution64 {
    public ArrayList<ArrayList<Integer>> Print(TreeNode64 pRoot) {

        if (pRoot == null) {
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        //存放顺序的队列
        ArrayList<Integer> nodes = new ArrayList<>();

        ArrayList<TreeNode64> nodeList = new ArrayList<>();
        nodeList.add(pRoot);

        //处理节点的下标
        int dealIndex = 0;
        //当前层的节点
        int currentDepthNode = 1;
        // 当前层深，处理完当前层节点后会+1
        int currentDepth = 0;
        //下一层节点个数
        int nextDepthNode = 0;

        for (TreeNode64 dealNode; nodeList.size() > dealIndex; dealIndex++) {

            dealNode = nodeList.get(dealIndex);

            if (dealNode.left != null) {
                nodeList.add(dealNode.left);
                nextDepthNode++;
            }

            if (dealNode.right != null) {
                nodeList.add(dealNode.right);
                nextDepthNode++;
            }


            nodes.add(dealNode.val);


            if (--currentDepthNode != 0) {
                continue;
            }

            result.add(new ArrayList<>(nodes));
            nodes.clear();

            currentDepth++;
            currentDepthNode = nextDepthNode;
            nextDepthNode = 0;
        }

        return result;
    }
}
