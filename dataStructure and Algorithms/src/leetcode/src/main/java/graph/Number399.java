package leetcode.src.main.java.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.cn/problems/evaluate-division/description/
 *
 * @author goodtime
 * @create 2023-12-09 00:58
 */
public class Number399 {
    public static void main(String[] args) {
        List<String> equation = new ArrayList<>();
        equation.add("a");
        equation.add("b");

        List<String> equation1 = new ArrayList<>();
        equation1.add("e");
        equation1.add("f");


        List<String> equation2 = new ArrayList<>();
        equation2.add("b");
        equation2.add("e");

        List<List<String>> equations = new ArrayList<>();
        equations.add(equation);
        equations.add(equation1);
        equations.add(equation2);


        List<String> query = new ArrayList<>();
        query.add("b");
        query.add("a");

        List<String> query1 = new ArrayList<>();
        query1.add("a");
        query1.add("f");


        List<List<String>> queries = new ArrayList<>();
        queries.add(query);
        queries.add(query1);

        double[] values = {3.4, 1.4, 2.3};
        System.out.println(new Solution399().calcEquation(equations, values, queries));
    }
}

/**
 * 先根据equations建立图，并把value，也即两个点的数值关系，转换成对应两个点的值
 * 计算queries时，对该图进行遍历即可
 *
 * 这里涉及到了并查集，参看：https://zhuanlan.zhihu.com/p/93647900
 */
class Solution399 {

    class Union {
        /**
         * 存储的是当前节点对应的父节点下标，初始每个节点的父节点都为自身
         */
        public int[] parent;
        public double[] weight;

        public Union(int size) {
            parent = new int[size];
            weight = new double[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                weight[i] = 1.0;
            }
        }

        /**
         * 联合两个节点所在的图，生成关系
         *
         * @param x     第一个节点的下标
         * @param y     第二个节点的下标
         * @param value x/y的比值
         */
        public void reUnion(int x, int y, double value) {

            int rootx = findRoot(x);
            int rooty = findRoot(y);

            //两者的父亲是同一个，那么他们已有关系，无需重新建立
            if (rootx == rooty) {
                return;
            }

            parent[rootx] = rooty;
            weight[rootx] = value * weight[y] / weight[x];

        }

        /**
         * 在寻找x的根节点同时，对x到根节点中的各个节点进行路径压缩，找到最底部的根节点，并将其作为x的父节点
         *
         * @param x
         * @return
         */
        private int findRoot(int x) {
            if (x != parent[x]) {
                int originParent = parent[x];
                parent[x] = findRoot(parent[x]);
                weight[x] = weight[originParent] * weight[x];
            }
            return parent[x];
        }

        public double calculate(Integer indexS1, Integer indexS2) {

            //在查询时也需要进行路径压缩
            int rootx = findRoot(indexS1);
            int rooty = findRoot(indexS2);

            if (rootx == rooty) {
                return weight[indexS1] / weight[indexS2];
            } else {
                return -1.0;
            }
        }
    }


    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        int size = equations.size();

        //key为节点，value为节点的编号
        HashMap<String, Integer> map = new HashMap<>(size * 2);
        Union union = new Union(size * 2);
        int index = 0;

        //1.建立图
        for (int i = 0; i < equations.size(); i++) {
            double value = values[i];
            List<String> strings = equations.get(i);
            String s1 = strings.get(0);
            String s2 = strings.get(1);

            if (!map.containsKey(s1)) {
                map.put(s1, index);
                index++;
            }

            if (!map.containsKey(s2)) {
                map.put(s2, index);
                index++;
            }

            union.reUnion(map.get(s1), map.get(s2), value);

        }

        //2.找结果
        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            List<String> strings = queries.get(i);
            String s1 = strings.get(0);
            String s2 = strings.get(1);

            Integer indexS1 = map.get(s1);
            Integer indexS2 = map.get(s2);

            if (indexS1 == null || indexS2 == null) {
                result[i] = -1.0;
            } else {
                result[i] = union.calculate(indexS1, indexS2);
            }
        }

        return result;
    }
}