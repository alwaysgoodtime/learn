package datastructure.graph;

import com.sun.org.apache.xml.internal.security.c14n.helper.AttrCompare;

/**
 * 红黑树:注意其性质的要求
 * 节点的color，约定，红色为true，黑色为false
 *
 * @author goodtime
 * @create 2020-02-10 2:32 下午
 */
public class RedBlackTree {

    //简单创建一个红黑树（4，2，5，1，3）
    public RedBlackTreeNode create() {
        RedBlackTreeNode a = new RedBlackTreeNode(4, false, null);
        RedBlackTreeNode b = new RedBlackTreeNode(2, true, a);
        RedBlackTreeNode c = new RedBlackTreeNode(5, false, a);
        RedBlackTreeNode d = new RedBlackTreeNode(1, false, b);
        RedBlackTreeNode e = new RedBlackTreeNode(3, false, b);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        return a;
    }

    //判断节点颜色,null返回false
    public boolean colorOf(RedBlackTreeNode node) {
        if (node == null) {
            return false;//叶子节点（也就是nil节点）默认为黑色
        }
        return node.color;
    }

    //判断节点是否为黑
    public boolean isBlack(RedBlackTreeNode node) {
        return colorOf(node) == false;
    }

    //判断节点是否为红
    public boolean isRed(RedBlackTreeNode node) {
        return colorOf(node) == true;
    }

    //将节点染成红色
    public void red(RedBlackTreeNode node) {
        node.color = true;
    }

    //将节点染成黑色
    public void black(RedBlackTreeNode node) {
        node.color = false;
    }

    //递归添加的方法，按照二叉排序树的方式添加
    public void recadd(RedBlackTreeNode node, RedBlackTreeNode root) {
        if (node.data <= root.data) {
            if (root.left == null) {
                root.left = node;
                node.parent = root;//红黑树涉及到父节点的操作较多，在添加节点的时候，给它初始化一下
            } else {
                recadd(node, root.left);
            }
        } else if (root.right == null) {
            root.right = node;
            node.parent = root;
        } else {
            recadd(node, root.right);
        }
    }

    //中序遍历的方法
    public void inorderTraversal(RedBlackTreeNode root) {//中序遍历
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println(root.data);
            inorderTraversal(root.right);
        }
    }


    //插入节点到红黑树，默认此节点为红，需要进行染色判断和操作
    public void add(RedBlackTreeNode node, RedBlackTreeNode root) {
        if (root == null) {
            throw new RuntimeException("根节点为空，添加失败");
        }
        recadd(node, root);//调用递归添加一个颜色为红的节点，在红黑树的基础上形成新的二叉排序树

        afterAdd(node);//将新的二叉排序树进行染色等操作，让二叉排序树符合红黑树的性质要求
    }

    //afterAdd方法，插入后对树进行修复，满足二叉树的性质
    private void afterAdd(RedBlackTreeNode node) {

        if (node.parent == null) {//说明添加的节点是根节点，直接将father染成黑色(我们虽然不能插入根节点，但在后续
            //插入叶子节点时，可能会上溢，这时候可能会改动整棵红黑树的根节点 )
            black(node);//这个一定要写在前面，否则有可能被第一种情况直接返回，因为根节点的父节点是空指针，
            //而空指针默认是黑色，于是被第一种情况直接就返回了
        }

        //第一种情况，插入的节点父节点为黑色，符合二叉树要求，什么也不用做，直接返回
        if (colorOf(node.parent) == false) {
            return;
        }

        //第二种情况，插入节点的uncle为红色
        if (colorOf(uncle(node)) == true) {
            //染黑父节点和叔叔节点
            black(node.parent);
            black(uncle(node));
            //grand染成red
            red(grand(node));
            afterAdd(grand(node));
            return;
        }


        //第三种情况,插入节点的uncle不是红色,又分四种小情况

        //LL
        if (grand(node).right == node.parent && node.parent.right == node) {
            //染黑父节点
            black(node.parent);
            //染红祖父节点
            red(grand(node));
            leftRotate(grand(node));//左旋
            return;
        }
        //RR
        if (grand(node).left == node.parent && node.parent.right == node) {
            //染黑父节点
            black(node.parent);
            //染红祖父节点
            red(grand(node));
            rightRotate(grand(node));//右旋
            return;
        }
        //LR
        if (grand(node).left == node.parent && node.parent.right == node) {
            //染黑自己
            black(node);
            //染红祖父节点
            red(grand(node));
            leftRotate(node.parent);
            rightRotate(grand(node));
            return;
        }

        //RL
        if (grand(node).right == node.parent && node.parent.left == node) {
            //染黑自己
            black(node);
            //染红祖父节点
            red(grand(node));
            rightRotate(node.parent);
            leftRotate(grand(node));
            return;
        }


    }


    //红黑树算法的左旋转方法，看动态图理解，不要只看步骤,这里赋值也有策略，原来的node还得用，只不过赋值为原来节点的右节点，新创个节点当原来节点的左节点
    //如果新创个节点当根，这个子树也可以平衡，可此时的根不一定是整个树的根，创了个新根，相当于原来树丢了这棵新创的子平衡树
    //逆时针旋转，旋转的是node节点，它是根节点
    //逆时针
    //注意：红黑树，我定义的时候每个节点多了一个父节点指针，所以选择的时候，这个指针也得带上，同时，在定义parent的时候，注意空指针异常的出现
    public void leftRotate(RedBlackTreeNode node) {
        RedBlackTreeNode bstNode = new RedBlackTreeNode(node.data);
        node.data = node.right.data;
        node.color = node.right.color;
        bstNode.left = node.left;
        if (node.left != null) {
            node.left.parent = bstNode;
        }
        bstNode.right = node.right.left;
        if (bstNode.right != null) {
            bstNode.right.parent = bstNode;
        }
        bstNode.parent = node;
        bstNode.color = node.color;
        node.left = bstNode;
        node.right = node.right.right;
        if (node.right != null) {
            node.right.parent = node;
        }
    }


    //红黑树算法的右旋转方法，看动态图理解，不要只看步骤，顺时针
    public void rightRotate(RedBlackTreeNode node) {
        RedBlackTreeNode bstNode = new RedBlackTreeNode(node.data);
        node.data = node.left.data;
        node.color = node.left.color;
        bstNode.left = node.left.right;
        if (node.left.right != null) {
            node.left.right.parent = bstNode;
        }
        bstNode.right = node.right;
        if (node.right != null) {
            node.right.parent = bstNode;
        }
        bstNode.parent = node;
        node.left = node.left.left;
        if (node.left != null) {
            node.left.parent = node;
        }
        node.right = bstNode;
    }

    //找node的叔叔节点，有则返回节点，没有则返回null
    private RedBlackTreeNode uncle(RedBlackTreeNode node) {
        return sibling(node.parent);
    }

    //找node的兄弟节点，如果有则返回节点，如果没有则返回null
    private RedBlackTreeNode sibling(RedBlackTreeNode node) {
        if (node == null || node.parent == null) {
            return null;
        }
        if (node.parent.left == node) {
            return node.parent.right;
        }
        return node.parent.left;
    }

    //找node的爷爷节点，如果有则返回节点，如果没有则返回null
    private RedBlackTreeNode grand(RedBlackTreeNode node) {
        if (node == null || node.parent == null) {
            return null;
        }
        return node.parent.parent;
    }

    //红黑树删除方法入口
    public int delete(int data, RedBlackTreeNode root) {
        //健壮性检查，如果删除失败，则返回-1
        if (root == null) {
            return -1;
        }

        //搜索要删除节点所在的位置
        RedBlackTreeNode deleteMe = search(data, root);

        //如果返回null值，则删除失败
        if (null == deleteMe) {
            return -1;
        }

        //真正开始删除节点
        return deleteMe(deleteMe);
    }


    //返回待删除节点在树中所在的位置（如果有重复节点，就是返回它自树顶向下首次出现的位置）
    private RedBlackTreeNode search(int node, RedBlackTreeNode root) {
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

    private int deleteMe(RedBlackTreeNode deleteMe) {
        //待删除节点就是父节点的情况,分三种情况讨论
        RedBlackTreeNode fatherNode = deleteMe.parent;
        if (deleteMe.parent == null) {
            if (deleteMe.left == null && deleteMe.right == null) {
                return 0;//说明树中只有根节点，而且它就是待删除节点
            }
            if (deleteMe.left == null && deleteMe.right != null) {
                deleteMe.data = deleteMe.right.data;//还不能直接让deleteMe = deleteMe.right,否则根本影响不了原来的树
                deleteMe.right = deleteMe.right.right;
                //这种情况不用处理，因为被删除的节点，一定是红节点，不可能是黑节点，
                //因为如果左边没有节点，右边有黑节点，那在删之前就不是红黑树了
                return 1;
            }
            if (deleteMe.right == null && deleteMe.left != null) {//情况同上，此时待删除节点的右节点为空
                deleteMe.data = deleteMe.left.data;
                deleteMe.left = deleteMe.left.left;
                //这种情况也不用处理，原因同上
                return 1;
            }

            //找到待删除节点右子树的最小节点
            RedBlackTreeNode minNode = deleteMe.right;
            if (minNode.left != null) {
                minNode = minNode.left;
            }
            //找到待删除节点右子树最小节点的父节点
            RedBlackTreeNode minNodeFather = minNode.parent;

            //先删除待删除节点右子树的最小节点
            if (minNodeFather == deleteMe) {
                deleteMe.right = minNode.right;
            } else {
                minNodeFather.left = minNode.right;
            }

            //移花接木，让待删除节点右子树的最小节点放到待删除节点位置上,也就是数据交换
            deleteMe.data = minNode.data;

            afterDelete(minNode, minNode.right);//deleteMe没有被删除，只是被替换了，真正删的是minNode
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
            afterDelete(deleteMe, null);//被删除的deleteMe是叶子节点，相当于没人替换它，或者用null替换了它
            return 1;
        }
        //第二种，待删除节点的左节点为空，右节点不为空
        if (deleteMe.left == null && deleteMe.right != null) {
            if (fatherNode.left == deleteMe) {
                fatherNode.left = deleteMe.right;//直接删除待删除节点的右节点，删除即完成
            } else {
                fatherNode.right = deleteMe.right;
            }
            afterDelete(deleteMe, deleteMe.right);//被删除的就是deleteMe，所以右节点放到了原来节点的位置上，相当于把它替换
            return 1;
        }
        //情况同上，此时待删除节点的右节点为空
        if (deleteMe.right == null && deleteMe.left != null) {
            if (fatherNode.left == deleteMe) {
                fatherNode.left = deleteMe.left;//直接嫁接待删除节点的右节点，删除即完成
            } else {
                fatherNode.right = deleteMe.left;
            }
            afterDelete(deleteMe, deleteMe.left);//它的左节点放到了原来节点的位置上
            return 1;
        }

        //第三种情况，如果待删除节点的左右节点都不为空，取待删除节点右节点的最小节点填到待删除节点的原位置即可
        //这个节点可以保证新树还是二叉排序树

        //找到待删除节点右子树的最小节点
        RedBlackTreeNode minNode = deleteMe.right;
        if (minNode.left != null) {
            minNode = minNode.left;
        }
        //找到待删除节点右子树最小节点的父节点
        RedBlackTreeNode minNodeFather = minNode.parent;

        //移花接木，让待删除节点右子树的最小节点放到待删除节点位置上,也就是数据交换
        deleteMe.data = minNode.data;

        if (deleteMe == minNodeFather) {//说明此时其右子树为只是一个孤点,也是那个最小值
            deleteMe.right = minNode.right;
        } else {
            minNodeFather.left = minNode.right;//这个节点的左子树一定为空，而且一定是其父节点的左节点（二叉排序树的性质）
        }

        afterDelete(minNode, minNode.right);//注意！！！！这种情况下，其实minNode才是真正被删除的节点，minNode.right
        //是替代它的节点，跟deleteMe是没关系的，deleteMe只是换了个值而已，节点其他属性并没有懂

        return 1;
    }

    //删除节点后的处理，保证形成的红黑树能保持原来的性质

    /**
     * 红黑树中，真正被删除的节点在其等价的4阶B树的叶子节点中
     *
     * @param node   要删除的目标节点
     * @param delete 实际被替换删除的节点，如果没有节点"替"node删除，那么值就是为null
     */
    private void afterDelete(RedBlackTreeNode node, RedBlackTreeNode delete) {

        if (node.parent == null) {//如果删除的节点是父节点，直接返回，但这里并不需要作此处理，因为我们在前面处理时，
            //如果删除的是父节点，而且父节点也没有任何子节点，才是这种情况，但这种情况我们已经在deleteMe中返回0
            return;
        }

        if (colorOf(node)) {//node代表的节点，是真正被删除的节点，如果删除的是红色节点，并不会影响原来的红黑树
            return;
            //说明：如果真正被删除的节点是红色的节点，它的左右节点一定为空（或者说nil指针），也就是左右节点为空的情况
            //否则原来的树一定不是红黑树
        }

        //到这里，说明真正删除的节点就是黑色的节点

        //第一种情况：替代的节点是红色节点，说明node节点是个左子树或者右子树为空的节点，这时，直接把代替它的
        //红色节点变黑即可

//        if(colorOf(delete)){
//            black(delete);
//            return;//记得return，不需要往后判断了
//        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //其实delete只有这里用到了，也就是node被一个红色节点给替代的情况，我们可以用node节点左子树或右子树不为空
        //来辨识出这种情况，从而不需要传delete,不过这是利用了搜索二叉树的特性
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        if (node.left != null) {
            black(node.left);
            return;
        }
        if (node.right != null) {
            black(node.right);
            return;
        }


        //第二种情况：删除的就是黑色节点，而且黑色节点还为叶子节点，对于等价的b树来说，此时会产生下溢操作
        //先向兄弟借红节点试试
        //此时判断它的兄弟节点有没有红色节点，注意：它一定有兄弟节点，否则原红黑树不满足第五条性质

        //注意：此时不能调用sibling方法了，因为sibling是判断它父节点的情况，node能找到父节点，但它的父节点已经被重置过了
        //此时可以判断，它的父节点哪个子节点不为空，说明它的兄弟节点就是哪个节点

        //第一步：找到兄弟节点

        RedBlackTreeNode sibling = null;//存放被删除节点的兄弟节点
        if (node.parent.left == null) {
            sibling = node.parent.right;
        } else {
            sibling = node.parent.left;
        }
        //后续的操作，兄弟节点在被删除节点左边和被删除节点右边是不一样的，这里先考虑兄弟节点是左节点
        if (sibling == node.parent.left) {
            //第二步，判断其兄弟节点是否为红色节点，如果是红色节点，把它染成黑色
            if (colorOf(sibling)) {
                black(sibling);//把兄弟节点染成黑色
                red(node.parent);//把它们的父节点染成红色
                rightRotate(sibling);//兄弟在左边，对应右旋，旋转后就成了兄弟节点为黑色节点的情况了
                sibling = node.parent.left;//旋转过后重置一下兄弟节点的值，就变成了兄弟节点是黑色节点的情况
                //这里不要return，继续往后判断
            }
            //第三步，此时其兄弟节点是黑色节点，判断其是否有左右红色节点，并进行第二次旋转处理

            if (colorOf(sibling.right)) {
                sibling.right.color = node.parent.color;//旋转后中心节点颜色为原来父节点颜色
                black(sibling.parent);
                black(sibling);//让旋转后的两个子节点为黑色
                leftRotate(sibling);
                rightRotate(node.parent);
                return;
            }
            //同上
            if (colorOf(sibling.left)) {
                sibling.color = node.parent.color;
                black(sibling.left);
                black(sibling.parent);
                rightRotate(sibling.parent);
                return;
            }

            //第三步:如果兄弟节点没有一个红色子节点，即左右节点都为null的颜色-黑色
            //如果parent是红色，将sibling染成red，将parent染成black
            if (colorOf(node.parent)) {
                red(sibling);
                black(node.parent);
            } else {
                //如果parent是黑色，将sibling染成red，将parent染成black，然后再继续想让借位，也就是递归删除node.parent
                red(sibling);
                black(node.parent);
                afterDelete(node.parent, null);
            }
        } else {//此时兄弟节点是右节点，被删除节点是左节点，开始镜像操作

            //第二步，判断其兄弟节点是否为红色节点，如果是红色节点，把它染成黑色
            if (colorOf(sibling)) {
                black(sibling);//把兄弟节点染成黑色
                red(node.parent);//把它们的父节点染成红色
                leftRotate(sibling);//兄弟在左边，对应右旋，旋转后就成了兄弟节点为黑色节点的情况了
                sibling = node.parent.right;//旋转过后重置一下兄弟节点的值，就变成了兄弟节点是黑色节点的情况
                //这里不要return，继续往后判断
            }
            //第三步，此时其兄弟节点是黑色节点，判断其是否有左右红色节点，并进行第二次旋转处理

            if (colorOf(sibling.left)) {
                sibling.left.color = node.parent.color;//旋转后中心节点颜色为原来父节点颜色
                black(sibling.parent);
                black(sibling);//让旋转后的两个子节点为黑色
                rightRotate(sibling);
                leftRotate(node.parent);
                return;
            }
            //同上
            if (colorOf(sibling.right)) {
                sibling.color = node.parent.color;
                black(sibling.right);
                black(sibling.parent);
                leftRotate(sibling.parent);
                return;
            }

            //第三步:如果兄弟节点没有一个红色子节点，即左右节点都为null的颜色-黑色
            //如果parent是红色，将sibling染成red，将parent染成black
            if (colorOf(node.parent)) {
                red(sibling);
                black(node.parent);
            } else {
                //如果parent是黑色，将sibling染成red，将parent染成black，然后再继续想让借位，也就是递归删除node.parent
                red(sibling);
                black(node.parent);
                afterDelete(node.parent, null);
            }
        }

    }

}
