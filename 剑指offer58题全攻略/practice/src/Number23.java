import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-20 4:34 下午
 * 思路分析
 * 在后序遍历得到的序列中，最后一个数字是树的根节点的值。数组中前面的数字可以分为两部分：
 * （1）第一部分是左子树结点的值，它们都比根结点的值小
 * （2）第二部分是右子树结点的值，它们都比根结点的值大
 * <p>
 * 根据上述性质，所以我们可以写一个递归函数：
 * 递归的终止条件是当前树的结点总数为0
 * 判断是否是二叉排序树的方法：首先，找到第一个大于根结点的结点位置，将数组分为两部分，判断右子树中的全部结点是否均大于根结点的值
 * <p>
 * 指出《剑指offer》中的错误：
 * 当长度为0时应该返回true，原因是左、右子树可空
 * ————————————————
 * 版权声明：本文为CSDN博主「低调小一」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/wzy_1988/article/details/8977578
 */

public class Number23 {
    public static void main(String[] args) {
        int[] a = {3, 2, 1};
        Solution23 solution23 = new Solution23();
        System.out.println(solution23.VerifySquenceOfBST(a));
    }
}

class Solution23 {
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0) {
            return false;//牛客网好像认为空树不算二叉搜索（排序）树
        }
        return Judge(sequence);
    }

    public boolean Judge(int[] sequence) {
        if (sequence.length == 0) {
            return true;//子的空树也为二叉排序树
        }

        int i = sequence.length - 1;

        if (i == 0) {
            return true;//如果某子树长度为1，此子树直接返回true；
        }

        int j;

        for (j = 0; sequence[j] < sequence[i]; j++) {
        }

        int[] a = new int[j];//接收左子树的值
        int[] b = new int[sequence.length - j - 1];//接收右子树的值

        for (int k = 0; k < j; k++) {
            a[k] = sequence[k];
        }

        for (int l = j; l < sequence.length - 1; l++) {
            b[l - j] = sequence[l];
            if (b[l - j] < sequence[i]) {
                return false;//判断右子树是否都小于自己的根节点，主要逻辑判断单元
            }
        }
        return Judge(a) && Judge(b);
    }

}

/**
 * 二叉搜索树的子树也是二叉搜索树，最后一个节点一定是二叉树的中点，比它大的是右边二叉搜索树的遍历结果，
 * 比它小的是左边二叉搜索树的遍历结果
 */
class Solution23s {
    public boolean VerifySquenceOfBST(int[] sequence) {
        //空树不算二叉搜索（排序）树
        if (sequence.length == 0) {
            return false;
        }

        ArrayList<Integer> sequenceList = new ArrayList<>();

        for (int j : sequence) {
            sequenceList.add(j);
        }


        return Judge(sequenceList);
    }

    public boolean Judge(ArrayList<Integer> sequence) {

        if (sequence.size() <= 1) {
            return true;
        }

        int middleNode = sequence.get(sequence.size() - 1);

        //按照中值，拆为两颗二叉树
        ArrayList<Integer> leftList = new ArrayList();
        ArrayList<Integer> rightList = new ArrayList();

        for (Integer currentNode : sequence) {

            if (currentNode < middleNode) {
                if (rightList.size() == 0) {
                    leftList.add(currentNode);
                } else {
                    //说明顺序不对，比中点大的值在它的左子树上，比中点小的值在它的右子树上
                    return false;
                }
            }

            if (currentNode > middleNode) {
                rightList.add(currentNode);
            }
        }

        return Judge(leftList) && Judge(rightList);

    }

}
