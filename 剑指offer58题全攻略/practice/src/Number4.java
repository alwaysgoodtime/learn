import javax.swing.tree.TreeNode;

/**
 * @author goodtime
 * @create 2020-01-18 12:16 上午
 */
public class Number4 {
    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        SolutionFromNet solutionFromNet = new SolutionFromNet();
        int a[] = {1,2,4,7,3,5,6,8};
        int b[] = {4,7,2,1,5,3,8,6};

//        TreeNod treeNod = solution4.reConstructBinaryTree(a, b);
//        System.out.println(treeNod);
        TreeNod treeNod1 = solutionFromNet.reConstructBinaryTree(a, b);
        System.out.println(treeNod1);
    }
}
//    这里用TreeNode，是因为javax中有TreeNode这个类
class TreeNod{
          int val;
          TreeNod left;
          TreeNod right;
          TreeNod(int x) {
              val = x;
          }

}

//      我的不知道为什么无法通过测试，原因不明，可能是内存占用过多？
    class Solution4 {
    public TreeNod reConstructBinaryTree(int[] pre, int[] in) {
        TreeNod treeNod = new TreeNod(pre[0]);//初始根节点
        if (pre.length == 0) {
            return null;
        }
        int j = 0;
        for (; pre[0] != in[j]; j++) {
        }

        int[] intl = new int[j];
        int[] intl2 = new int[j];
        int[] intr = new int[in.length - j - 1];
        int[] intr2 = new int[in.length - j - 1];

        for (int i = 0; i < j; i++) {
            intl[i] = pre[i + 1];
            intl2[i] = in[i];
        }
        for (int l = ++j; j < in.length; j++) {
            intr[j - l] = pre[j];
            intr2[j - l] = in[j];
        }

        TreeNod recursionl = recursionl(intl, intl2, treeNod.left);
        treeNod.left = recursionl;
        TreeNod recursionr = recursionr(intr, intr2, treeNod.right);
        treeNod.right = recursionr;
        return treeNod;
    }

    //                处理左子树
    public TreeNod recursionl(int[] intl, int[] intl2, TreeNod treeNod) {
        if (intl.length == 1) {
            treeNod = new TreeNod(intl[0]);
            return treeNod;
        } else if (intl.length == 0) {
            return null;
        } else {
            treeNod = new TreeNod(intl[0]);
            int j = 0;
            for (; intl[0] != intl2[j]; j++) {
            }
            int[] intl3 = new int[j];
            int[] intl4 = new int[j];
            int[] intr3 = new int[intl.length - j - 1];
            int[] intr4 = new int[intl.length - j - 1];
            for (int i = 0; i < j; i++) {
                intl3[i] = intl[i + 1];
                intl4[i] = intl2[i];
            }

            for (int l = ++j; j < intl.length; j++) {
                intr3[l - j] = intl[j];
                intr4[l - j] = intl2[j];
            }
            TreeNod recursionl = recursionl(intl3, intl4, treeNod.left);
            treeNod.left = recursionl;
            TreeNod recursion = recursionr(intr3, intr4, treeNod.right);
            treeNod.right = recursion;
            return treeNod;
        }
    }

    public TreeNod recursionr(int[] intr, int[] intr2, TreeNod treeNod) {
        if (intr.length == 1) {
            treeNod = new TreeNod(intr[0]);
            return treeNod;
        } else if (intr.length == 0) {
            return null;
        } else {
            treeNod = new TreeNod(intr[0]);
            int j = 0;
            for (; intr[0] != intr2[j]; j++) {
            }
            int[] intl3 = new int[j];
            int[] intl4 = new int[j];
            int[] intr3 = new int[intr.length - j - 1];
            int[] intr4 = new int[intr.length - j - 1];
            for (int i = 0; i < j; i++) {
                intl3[i] = intr[i + 1];
                intl4[i] = intr2[i];
            }
            for (int l = ++j; j < intr.length; j++) {
                intr3[j - l] = intr[j];
                intr4[j - l] = intr2[j];
            }
            TreeNod recursionl = recursionl(intl3, intl4, treeNod.left);
            treeNod.left = recursionl;
            TreeNod recursion = recursionr(intr3, intr4, treeNod.right);
            treeNod.right = recursion;
            return treeNod;

        }
    }
}
//                网上找的方法
class SolutionFromNet{
        public TreeNod reConstructBinaryTree(int [] pre, int [] in) {
            if(pre.length == 0){
                return null;
            }
            TreeNod root = find(pre,0,pre.length-1,in,0,in.length-1);
            return root;
        }

        public TreeNod find(int[] pre,int start,int end, int[] in,int start2,int end2){
            if(start > end || start2 > end2){
                return null;
            }
            TreeNod root = new TreeNod(pre[start]);
            for(int i = start2;i < end2;i++){
                if(pre[start] == in[i]){
                    root.left = find(pre,start+1,start+i-start2,in,start2,i-1);//srart+i-start2是相对的偏移量
                    root.right= find(pre,i-start2+start+1,end,in,i+1,end2);//srart+1+i-start2是相对的偏移量，且不能简单改成i+1
                    break;
                }
            }

            return root;


        }

    }




