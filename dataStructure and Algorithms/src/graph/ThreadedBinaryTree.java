package graph;

/**
 * 实现线索化功能的二叉树
 *
 * @author goodtime
 * @create 2020-02-08 7:37 下午
 */
public class ThreadedBinaryTree {

    /**
     * 非递归创建中序线索化二叉树
     *
     * @return 返回一个六节点的二叉树的根节点，元素为1-6
     */
    public ThreadedBinaryTreeNode create() {
        ThreadedBinaryTreeNode a = new ThreadedBinaryTreeNode(1);
        ThreadedBinaryTreeNode b = new ThreadedBinaryTreeNode(2);
        ThreadedBinaryTreeNode c = new ThreadedBinaryTreeNode(3);
        ThreadedBinaryTreeNode d = new ThreadedBinaryTreeNode(4);
        ThreadedBinaryTreeNode e = new ThreadedBinaryTreeNode(5);
        ThreadedBinaryTreeNode f = new ThreadedBinaryTreeNode(6);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        d.right = f;
        threaded(a);
        return a;
    }

    /**
     * 中序线索化一个二叉树
     *
     * @param root 传入的一个普通二叉树，按照中序的顺序线索话它
     */

    public ThreadedBinaryTreeNode pre;//指向线索化节点的前驱节点，线索化需要的指针
    //在递归进行线索化时，pre总是保留前驱节点
    //不能定义在递归的里面，否则pre的值每递归一次就要重新初始化，保证递归的方法中共享此变量

    public void threaded(ThreadedBinaryTreeNode root) {

        if (root == null) {
            return;//如果为空，直接返回
        }

        //第一步、线索化左子树
        threaded(root.left);


        //第二步、线索化中节点
        if (root.left == null) {
            root.setLeft(pre);
            root.setLeftType(1);//最左节点的前驱节点为null，这个是所以中序线索化子树的性质
        }

        if (pre != null && pre.right == null) {//在递归的自己的父节点时，才能确定自己的后继节点是谁
            pre.setRight(root);
            pre.setRightType(1);
        }

        pre = root;

        //第三步、线索化右子树
        threaded(root.right);

    }


    //线索化二叉树不能用原来中序的方式遍历了，需要改用新的方式
    //递归写法
    public void inOrderTraversal(ThreadedBinaryTreeNode node) {
        if (node == null) {
            return;//空树，直接返回,如果node为中序遍历的最后一个节点，其右节点也为空，此时也直接返回
        }
        while (node.leftType != 1) {//先找到最左边那个节点，其left指向空,遇到node.right类型不为1时，也回来接着遍历

            node = node.left;
        }
        System.out.println(node.data);//找到打头的左节点后，一直遍历它下个节点的右节点即可

        while (node.getRightType() == 1) {//如果类型是1，说明当前节点的右指针指的是后继节点
            node = node.right;
            System.out.println(node.data);
        }
        node = node.right;//如果右指针类型不为1，说明这是普通的右节点，需要找到以这个右节点为根的子树的最左边的节点
        //于是重新开启循环

        inOrderTraversal(node);
    }

    //线索化二叉树不能用原来中序的方式遍历了，需要改用新的方式
    //非递归写法
    public void inOrderTraversalNoRecursion(ThreadedBinaryTreeNode node) {
        //外层while循环，除了处理node根节点为空的情况外，中序线索化，最右边的节点（也就是遍历的最后一个节点），其右指针为空
        //遍历到那里，说明循环结束
        while(node != null) {

            while (node.leftType != 1) {//先找到最左边那个节点，其left指向空,遇到node.right类型不为1时，也回来接着遍历
                node = node.left;
            }
            System.out.println(node.data);//找到打头的左节点后，一直遍历它下个节点的右节点即可

            while (node.getRightType() == 1) {//如果类型是1，说明当前节点的右指针指的是后继节点
                node = node.right;
                System.out.println(node.data);
            }
            node = node.right;//如果右指针类型不为1，说明这是普通的右节点，需要找到以这个右节点为根的子树的最左边的节点
            //于是重新开启循环
        }
    }




}
