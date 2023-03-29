import java.util.ArrayList;
import java.util.Stack;

/**
 * JZ77 按之字形顺序打印二叉树
 * 给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）
 *
 * @author goodtime
 * @create 2023-03-28 00:25
 */
public class Number61 {
    public static void main(String[] args) {
        TreeNode61 a = new TreeNode61(1);
        TreeNode61 b = new TreeNode61(2);
        TreeNode61 c = new TreeNode61(3);
        TreeNode61 d = new TreeNode61(4);
        TreeNode61 e = new TreeNode61(7);
        TreeNode61 f = new TreeNode61(5);
        a.left = b;
        a.right = c;
        b.left = d;
        d.right = e;
        c.left = f;
        Solution61 solution = new Solution61();
        ArrayList<ArrayList<Integer>> print = solution.Print(a);
        System.out.println(print.toArray().toString());
    }


}


class TreeNode61 {
    int val;
    TreeNode61 left = null;
    TreeNode61 right = null;

    public TreeNode61(int val) {
        this.val = val;

    }
}

/**
 * 使用广度遍历，如果是偶数层，也就是需要逆转输出的层，用栈来实现，其中广度遍历可以看JZ55
 */
class Solution61 {
    public ArrayList<ArrayList<Integer>> Print(TreeNode61 pRoot) {

        if (pRoot == null) {
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        //存放逆序的栈
        Stack<Integer> reverseNodes = new Stack<>();
        //存放顺序的队列
        ArrayList<Integer> nodes = new ArrayList<>();

        ArrayList<TreeNode61> nodeList = new ArrayList<>();
        nodeList.add(pRoot);

        //处理节点的下标
        int dealIndex = 0;
        //当前层的节点
        int currentDepthNode = 1;
        // 当前层深，处理完当前层节点后会+1
        int currentDepth = 0;
        //下一层节点个数
        int nextDepthNode = 0;

        for (TreeNode61 dealNode; nodeList.size() > dealIndex; dealIndex++) {

            dealNode = nodeList.get(dealIndex);

            if (dealNode.left != null) {
                nodeList.add(dealNode.left);
                nextDepthNode++;
            }

            if (dealNode.right != null) {
                nodeList.add(dealNode.right);
                nextDepthNode++;
            }

            if (currentDepth % 2 == 0) {
                nodes.add(dealNode.val);
            } else {
                reverseNodes.push(dealNode.val);
            }

            if (--currentDepthNode != 0) {
                continue;
            }


            if (currentDepth % 2 != 0) {
                while (!reverseNodes.empty()) {
                    nodes.add(reverseNodes.pop());
                }
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
