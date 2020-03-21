package datastructure.graph;

import javafx.scene.chart.AxisBuilder;

import javax.swing.*;

/**
 * 二叉平衡树的一些方法
 *
 * @author goodtime
 * @create 2020-02-09 10:16 下午
 */
public class BinarySelfbalancedTree {

    //简单创建一棵二叉平衡树
    public BSTNode create() {
        BSTNode a = new BSTNode(4);
        BSTNode b = new BSTNode(2);
        BSTNode c = new BSTNode(5);
        BSTNode d = new BSTNode(1);
        BSTNode e = new BSTNode(3);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        return a;
    }

    /**
     * 向二叉平衡树中新增一个节点，新增节点和二叉搜索树一样，但是多了平衡的方法
     * 让二叉树在插入节点后还能保持平衡
     *
     * @param node
     */
    public void add(BSTNode node, BSTNode root) {
        if (root == null) {
            throw new RuntimeException("根节点为空，添加失败");
        }
        recadd(node,root);//调用递归添加

        //添加完成，不断判断被添加节点的父节点是否失衡,添加的节点不可能直接就是根节点，就算值相同，也会继续往下添加
        while((node = searchFather(node,root)) != null){
            if(isBalanced(node)){
            }else {
                Rebalanced(root);
                break;//只要恢复过一次平衡，整个树就平衡了，无需继续平衡
            }
        }
    }

    //递归添加的方法
    public void recadd(BSTNode node,BSTNode root){

        if (node.data <= root.data) {
            if (root.left == null) {
                root.left = node;
            } else {
                recadd(node, root.left);
            }
        } else if (root.right == null) {
            root.right = node;
        } else {
            recadd(node, root.right);
        }

    }

    /**
     * 返回以输入的节点为根的树的高度
     * 非常漂亮的通用的计算树的高度的递归方式，明显的分治算法应用
     */
    public int height(BSTNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(node.left == null ? 0 : height(node.left), node.right == null ? 0 : height(node.right)) + 1;
    }

    //中序遍历二叉平衡树
    public void inorderTraversal(BSTNode root) {//中序遍历
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println(root.data);
            inorderTraversal(root.right);
        }
    }

    //AVL算法的左旋转方法，看动态图理解，不要只看步骤,这里赋值也有策略，原来的node还得用，只不过赋值为原来节点的右节点，新创个节点当原来节点的左节点
    //如果新创个节点当根，这个子树也可以平衡，可此时的根不一定是整个树的根，创了个新根，相当于原来树丢了这棵新创的子平衡树
    public void leftRotate(BSTNode node) {
        BSTNode bstNode = new BSTNode(node.data);
        node.data = node.right.data;
        bstNode.left = node.left;
        bstNode.right = node.right.left;
        node.left = bstNode;
        node.right = node.right.right;
    }


    //AVL算法的右旋转方法，看动态图理解，不要只看步骤
    public void rightRotate(BSTNode node) {
        BSTNode bstNode = new BSTNode(node.data);
        node.data = node.left.data;
        bstNode.left = node.left.right;
        bstNode.right = node.right;
        node.left = node.left.left;
        node.right = bstNode;
    }

    //计算节点平衡因子，判断其是否失衡
    public boolean isBalanced(BSTNode root){
        if(root == null || height(root.left) == height(root.right)) {
            return true;
        }
        return false;
    }

    //add的再平衡方法
    public void Rebalanced(BSTNode root){

        //如果添加完的二叉搜索树中，右子树的高度-左子树的高度>1，开始左旋转
        if (height(root.right) > height(root.left) + 1) {
            if (height(root.right.left) > height(root.right.right)) {
                rightRotate(root.right);
            }//如果需要左旋的树的右节点。其左子树大于右子树的高度，那么先把这个右节点的树右旋一下，再到根节点左旋
            leftRotate(root);
        }

        if (height(root.left) > height(root.right) + 1) {//右旋
            if (height(root.left.right) > height(root.left.left)) {//如果需要右旋的树的左节点。其右子树大于左子树的高度，那么先把这个左节点的树左旋一下，再到根节点右旋
                //如果这里不选，右旋后仍然是一个不平衡的二叉树
                leftRotate(root.left);
            }
            rightRotate(root);
        }
    }


    /**
     * 向二叉平衡树中删除一个节点，思路还是和向二叉排序树中删除一个节点一样，先找到删除的点，确定删除完节点后的二叉排序树的样子
     * 然后将删除完节点后的二叉排序树进行平衡
     * 关键在于删除节点后保持自平衡，同时，二叉排序树删除左右子树均不为空的情况时，直接找到右子树最小节点，然后就直接删除最小节点，把待删除
     * 节点用最小节点代替，现在不行了，因为删除最小节点，在二叉平衡树中会引起平衡操作，所以删最小节点时，就要开始平衡操作了，也就是说，
     * 如果在这种情况下，要进行递归删除。
     *
     *（想只把删除最小节点那里进行平衡，然后平衡待删除目标节点那里的平衡也不行。因为最小节点的父节点平衡了，但父节点所在的树，因为最小节点父节点的重新平衡，
     * 又可能失衡，所以要一直递归平衡，平衡到整棵树的根节点位置）
     *
     * @param root
     */
    public int delete(int data, BSTNode root) {
        //健壮性检查，如果删除失败，则返回-1
        if (root == null) {
            return -1;
        }

        //搜索要删除节点所在的位置
        BSTNode deleteMe = search(data, root);

        //如果返回null值，则删除失败
        if (null == deleteMe) {
            return -1;
        }

        //搜索要删除节点的父节点
        BSTNode deleteFather = searchFather(deleteMe, root);

        //真正开始删除节点
        if(deleteMe(deleteMe, deleteFather) == 0){
            return 0;//删除失败
        }

        //删除成功，现在开始查看删除节点的父节点，看看真正删除的那个节点是否影响到了父节点的平衡
        while(deleteFather != null){
            deleteFather = searchFather(deleteFather,root);
            if(!isBalanced(deleteMe)) {
                Rebalanced(root);
            }
        }

        return 1;
    }

    //因为已经找到node节点肯定存在于树中，可以少判断很多情况
    private BSTNode searchFather(BSTNode node, BSTNode root) {
        if (root == node) {//根节点为删除节点的情况
            return null;
        }
        if (root.left == node || root.right == node) {
            return root;
        }

        if (root.left != null && node.data <= root.data) {//这里可以加等于判断，也就是说node和root值相等，可惜不是同一个节点
            return searchFather(node, root.left);
        } else {
            return searchFather(node, root.right);
        }
    }

    //返回待删除节点在树中所在的位置（如果有重复节点，就是返回它自树顶向下首次出现的位置）
    private BSTNode search(int node, BSTNode root) {
        if (root == null) {//说明找不到此节点
            return null;
        }
        if (node < root.data) {
            return search(node, root.left);
        } else if (node > root.data) {
            return search(node, root.right);
        } else {
            return root;
        }
    }

    private int deleteMe(BSTNode deleteMe, BSTNode fatherNode) {
        //待删除节点就是父节点的情况,分三种情况讨论
        if (fatherNode == null) {
            if (deleteMe.left == null && deleteMe.right == null) {
                return 0;//说明树中只有根节点，而且它就是待删除节点
            }
            if (deleteMe.left == null && deleteMe.right != null) {
                deleteMe.data = deleteMe.right.data;//还不能直接让deleteMe = deleteMe.right,否则根本影响不了原来的树
                deleteMe.right = deleteMe.right.right;
                //这种情况不用再平衡，因为原来是二叉平衡树，现在就剩一个节点了
                return 1;//记得讨论完每种情况要返回
            }
            //情况同上，此时待删除节点的右节点为空
            if (deleteMe.right == null && deleteMe.left != null) {
                deleteMe.data = deleteMe.left.data;
                deleteMe.left = deleteMe.left.left;
                return 1;
            }

            //找到待删除节点右子树的最小节点
            BSTNode minNode = deleteMe.right;
            if (minNode.left != null) {
                minNode = minNode.left;
            }
            //找到待删除节点右子树最小节点的父节点
            BSTNode minNodeFather = searchFather(minNode, deleteMe);

            //这里要递归删除节点右子树最小节点的父节点
            delete(minNode.data,deleteMe);//相当于删除minNode，然后不断递归平衡到deleteMe（这里也就是root）这个节点这里

            //移花接木，让待删除节点右子树的最小节点放到待删除节点位置上,也就是数据交换
            minNode.data = deleteMe.data;
            return 1;
        }


        //删除要分三种情况讨论
        //第一种，如果待删除节点是叶子节点，直接删除
        if (deleteMe.left == null && deleteMe.right == null) {
            if (fatherNode.left == deleteMe) {
                fatherNode.left = null;//把父节点的左节点置空，就是删除
            } else {
                fatherNode.right = null;
            }
            return 1;
        }
        //第二种，待删除节点的左节点为空，右节点不为空
        if (deleteMe.left == null && deleteMe.right != null) {
            if (fatherNode.left == deleteMe) {
                fatherNode.left = deleteMe.right;//直接佳节待删除节点的右节点，删除即完成
            } else {
                fatherNode.right = deleteMe.right;
            }
            return 1;
        }
        //情况同上，此时待删除节点的右节点为空
        if (deleteMe.right == null && deleteMe.left != null) {
            if (fatherNode.left == deleteMe) {
                fatherNode.left = deleteMe.left;//直接佳节待删除节点的右节点，删除即完成
            } else {
                fatherNode.right = deleteMe.left;
            }
            return 1;
        }

        //第三种情况，如果待删除节点的左右节点都不为空，取待删除节点右节点的最小节点填到待删除节点的原位置即可
        //这个节点可以保证新树还是二叉排序树

        //找到待删除节点右子树的最小节点
        BSTNode minNode = deleteMe.right;
        if (minNode.left != null) {
            minNode = minNode.left;
        }

        //找到待删除节点右子树最小节点的父节点
        BSTNode minNodeFather = searchFather(minNode, deleteMe);


        if (deleteMe == minNodeFather) {//说明此时其右子树为只是一个孤点,也是那个最小值
            deleteMe.right = null;
            Rebalanced(deleteMe);//因为deleteMe并没有直接删除，所以平衡一下deleteMe，返回后再继续平衡它的父节点
        } else{
            delete(minNode.data,deleteMe);//递归删除deleteMe这个树中的minNode.data节点，这个删除过程会自平衡deleteMe这棵树
        }
        //移花接木，让待删除节点右子树的最小节点放到待删除节点位置上,也就是数据交换
        deleteMe.data = minNode.data;

        return 1;
    }



}
