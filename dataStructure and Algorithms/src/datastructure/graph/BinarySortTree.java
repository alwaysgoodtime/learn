package datastructure.graph;

/**
 * 二叉排序树,特性，对于其中的每个子树来说，左边的子孙节点都小于根节点，右边的子孙节点都大于根节点
 *
 * @author goodtime
 * @create 2020-02-09 6:19 下午
 */
public class BinarySortTree {

    //简单创建一棵二叉排序树
    public BinarySortTreeNode create() {
        BinarySortTreeNode a = new BinarySortTreeNode(4);
        BinarySortTreeNode b = new BinarySortTreeNode(2);
        BinarySortTreeNode c = new BinarySortTreeNode(5);
        BinarySortTreeNode d = new BinarySortTreeNode(1);
        BinarySortTreeNode e = new BinarySortTreeNode(3);
        BinarySortTreeNode f = new BinarySortTreeNode(5);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        return a;
    }

    //添加一个新节点，注意要满足二叉排序树的要求

    /**
     * 递归添加的方法
     *
     * @param node 要添加的节点
     * @param s    要添加进去的根节点
     */
    public void add(BinarySortTreeNode node, BinarySortTreeNode s) {
        if (s == null) {//注意，如果s为空指针，是无法向其中添加节点的，因为传的是地址，这里创个新节点，并不会影响原来的节点
            throw new RuntimeException("节点为空");
        }
        if (s.data >= node.data) {//兼容有重复数据的情况，如果有重复数据，放在左边
            if (s.left == null) {
                s.left = node;
            } else {
                add(node, s.left);
            }
        } else {
            if (s.right == null) {
                s.right = node;
            } else {
                add(node, s.right);
            }
        }
    }

    public void inorderTraversal(BinarySortTreeNode root) {//中序遍历
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println(root.data);
            inorderTraversal(root.right);
        }
    }

    //删除方法的接口,传入要删除的值，和需要删除的树的根节点，返回1（成功）或-1(失败),如果返回0，表示待删除的就是树的根
    //而且树就剩这个根了，也没有子树
    public int delete(int data, BinarySortTreeNode root) {
        //健壮性检查，如果删除失败，则返回-1
        if (root == null) {
            return -1;
        }

        //搜索要删除节点所在的位置
        BinarySortTreeNode deleteMe = search(data, root);

        //如果返回null值，则删除失败
        if (null == deleteMe) {
            return -1;
        }

        //搜索要删除节点的父节点
        BinarySortTreeNode deleteFather = searchFather(deleteMe, root);

        //真正开始删除节点
        return deleteMe(deleteMe, deleteFather);
    }

    //因为已经找到node节点肯定存在于树中，可以少判断很多情况
    private BinarySortTreeNode searchFather(BinarySortTreeNode node, BinarySortTreeNode root) {
        if (root == node) {//根节点为删除节点的情况
            return null;
        }
        if (root.left == node || root.right == node) {
            return root;
        }

        if (root.left != null && node.data < root.data) {
            return searchFather(node, root.left);
        } else {
            return searchFather(node, root.right);
        }
    }

    //返回待删除节点在树中所在的位置（如果有重复节点，就是返回它自树顶向下首次出现的位置）
    private BinarySortTreeNode search(int node, BinarySortTreeNode root) {
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

    private int deleteMe(BinarySortTreeNode deleteMe, BinarySortTreeNode fatherNode) {
        //待删除节点就是父节点的情况,分三种情况讨论
        if (fatherNode == null) {
            if (deleteMe.left == null && deleteMe.right == null) {
                return 0;//说明树中只有根节点，而且它就是待删除节点
            }
            if (deleteMe.left == null && deleteMe.right != null) {
                deleteMe.data = deleteMe.right.data;//还不能直接让deleteMe = deleteMe.right,否则根本影响不了原来的树
                deleteMe.right = deleteMe.right.right;
                return 1;//记得讨论完每种情况要返回
            }
            //情况同上，此时待删除节点的右节点为空
            if (deleteMe.right == null && deleteMe.left != null) {
                deleteMe.data = deleteMe.left.data;
                deleteMe.left = deleteMe.left.left;
                return 1;
            }

            //找到待删除节点右子树的最小节点
            BinarySortTreeNode minNode = deleteMe.right;
            if (minNode.left != null) {
                minNode = minNode.left;
            }
            //找到待删除节点右子树最小节点的父节点
            BinarySortTreeNode minNodeFather = searchFather(minNode, deleteMe);

            //先删除待删除节点右子树的最小节点
            if (minNode.right == null) {
                minNodeFather.left = null;//这个节点的左子树一定为空，而且一定是其父节点的左节点（二叉排序树的性质）
            } else {
                minNodeFather.left = minNode.right;
            }

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
        BinarySortTreeNode minNode = deleteMe.right;
        if (minNode.left != null) {
            minNode = minNode.left;
        }
        //找到待删除节点右子树最小节点的父节点
        BinarySortTreeNode minNodeFather = searchFather(minNode, deleteMe);

        //移花接木，让待删除节点右子树的最小节点放到待删除节点位置上,也就是数据交换
        deleteMe.data = minNode.data;

        if (deleteMe == minNodeFather) {//说明此时其右子树为只是一个孤点,也是那个最小值
            deleteMe.right = null;
        } else if (minNode.right == null) {//,如果不是上种情况，再删除待删除节点右子树的最小节点
            minNodeFather.left = null;//这个节点的左子树一定为空，而且一定是其父节点的左节点（二叉排序树的性质）
        } else {
            minNodeFather.left = minNode.right;
        }

        return 1;
    }


}
