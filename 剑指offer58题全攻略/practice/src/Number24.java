import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author goodtime
 * @create 2020-01-20 6:14 下午
 */
public class Number24 {
    public static void main(String[] args) {
        TreeNode5 a = new TreeNode5(1);
        TreeNode5 b = new TreeNode5(2);
        TreeNode5 c = new TreeNode5(1);
        TreeNode5 d = new TreeNode5(2);
        TreeNode5 e = new TreeNode5(1);
        TreeNode5 f = new TreeNode5(-1);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        d.left = f;
        Solution24 solution24 = new Solution24();
        ArrayList<ArrayList<Integer>> arrayLists = solution24.FindPath(a, 4);
        System.out.println(arrayLists);

    }
}


class TreeNode5 {
    int val = 0;
    TreeNode5 left = null;
    TreeNode5 right = null;

    public TreeNode5(int val) {
        this.val = val;

    }
}

//思路：前序遍历，如果从根节点到叶节点所加的和和目标值相等，记录路线中所加过的值，然后退回上一步继续进行求和。
class Solution24 {
    private ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> a = new ArrayList<Integer>();
    private int count = 0;

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode5 root, int target) {
        if(root == null){
            return arrayLists;
        }
        FindPaths(root, target);
        //把返回的元素为数组的数组，按照元素的长度，进行冒泡排序

        Comparator<? super ArrayList> comparator = new Comparator<ArrayList>() {
            @Override
            public int compare(ArrayList o1, ArrayList o2) {
                return o2.size() - o1.size();
            }
        };

        arrayLists.sort(comparator);//arrayList自带sort方法，但只能简单地排int等基本数据类型或者String的值（也就是重写compare方法的对象），
        //本例中元素为Arraylist，其没有实现Comparable接口，重写compareTo(obj)方法，所以需要自己重写一个比较器，
        //然后传到sort的形参中去，作为比较的标准。

        return arrayLists;
    }

    private void FindPaths(TreeNode5 root, int target) {
        if (root != null) {
            a.add(root.val);
            count += root.val;
            if (count == target) {
                if (root.left == null && root.right == null) {
                    ArrayList<Integer> c = (ArrayList<Integer>)a.clone();//如果找到数组，必须进行克隆
                    //否则arraylists中存的是a这个指向对象的地址值，如果a指向的对象变化，arraylists中存的值也变化
                    //克隆，相当于新建了个动态数组，和a相同。
                    arrayLists.add(c);
                    //也可以写成arrayLists.add(new ArrayList<Integer>(a));，作用一样，ArrayList有这种构造器

                    a.remove(a.size()-1);
                    count -= root.val;
                    return;
                }
            }
            FindPaths(root.left, target);
            FindPaths(root.right, target);
            a.remove(a.size()-1);
            count -= root.val;
        }
    }
}
