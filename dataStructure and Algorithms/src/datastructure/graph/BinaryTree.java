package datastructure.graph;


/**二叉树的一些方法测试
 * @author goodtime
 * @create 2020-02-08 5:45 下午
 */
public class BinaryTree {

    /**
     * 非递归创建二叉树
     *
     * @return 返回一个六节点的二叉树的根节点，元素为1-6
     */
    public BinaryTreeNode create() {
        BinaryTreeNode a = new BinaryTreeNode(1);
        BinaryTreeNode b = new BinaryTreeNode(2);
        BinaryTreeNode c = new BinaryTreeNode(3);
        BinaryTreeNode d = new BinaryTreeNode(4);
        BinaryTreeNode e = new BinaryTreeNode(5);
        BinaryTreeNode f = new BinaryTreeNode(6);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        d.right = f;
        return a;
    }

    /**
     * 递归删除：
     * 假设每个根节点存的数据都不同，简单实现树的删除操作
     * 这个方法无法处理的root根节点是需要删除节点的情况，原因见下
     * 删除逻辑如下：
     * 1.如果删除的节点是叶子节点，则删除该节点
     * 2.如果删除的节点是非叶子节点，则删除该子树
     *
     * @param data 需要删除的点存的data数据
     * @param root 需要删除的点所在的树的根节点
     */
    public void delete(int data, BinaryTreeNode root) {

// 1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
// 2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将 this.left = null;并且返回 (结束递归删除)
// 3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将 this.right= null;并且返回 (结束递归删除)
// 4. 如果第 2 和第 3 步没有删除结点，那么我们就需要向左子树进行递归删除，找到删除元素为止
// 5. 如果第 4 步也没有删除结点，则应当向右子树进行递归，找到删除元素为止

//        //这段代码其实仅仅是处理根节点时才有用
//        if (root == null || root.data == data) {
//            root = new BinaryTreeNode(18);
//            return;
//        }
//        这样是无法删除根节点的，因为root这个形参接收过来的是root的地址（传地址），root进行其他操作时，才能真正操作原对象（在堆内存中）
        //比如root.left，但如果让root=null，只是让所传的形参中存的地址变为null，并不能改变原来实际参数所存的地址
        //也无法让原来参数所存的树变成空树。

        if (root == null) {
            return;
        }


        if (root.left != null) {
            if (root.left.data != data) {
                delete(data, root.left);
            } else {
                root.left = null;
                return;
            }
        }

        if (root.right != null) {
            if (root.right.data != data) {
                delete(data, root.left);
            } else {
                root.right = null;
                return;
            }
        }
    }

}


//这里用链表来实现二叉树的数据结构

