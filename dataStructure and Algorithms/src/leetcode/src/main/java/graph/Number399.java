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
        System.out.println(new Solution399().calcEquation(null, null, null));
    }
}

/**
 * 先根据equations建立图，并把value，也即两个点的数值关系，转换成对应两个点的值
 * 计算queries时，对该图进行遍历即可
 *
 * TODO
 */
class Solution399 {

    class Node {
        public double val;
        public String word;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(double val, String word) {
            this.val = val;
            this.word = word;
            neighbors = new ArrayList<Node>();
        }

        public Node(double val, ArrayList<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }


    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        HashMap<String, Node> map = new HashMap<>();

        //1.建立图
        for (int i = 0; i < equations.size(); i++) {
            List<String> strings = equations.get(i);
            String s1 = strings.get(0);
            String s2 = strings.get(1);
            double value = values[i];
            Node nodeS1 = null;
            Node nodeS2 = null;

            if (map.containsKey(s1)) {
                nodeS1 = map.get(s1);
            }

            if (map.containsKey(s2)) {
                nodeS2 = map.get(s2);
            }

            if (nodeS1 != null && nodeS2 != null) {
            } else if (nodeS1 != null) {
                nodeS2 = new Node(nodeS1.val / value, s2);
            } else if (nodeS2 != null) {
                nodeS1 = new Node(value * nodeS2.val, s1);
            } else {
                nodeS1 = new Node(value, s1);
                nodeS2 = new Node(1, s2);
            }

            nodeS1.neighbors.add(nodeS2);
            nodeS2.neighbors.add(nodeS1);

        }

        //2.遍历图，得出答案
        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {

            List<String> strings = queries.get(i);
            String s1 = strings.get(0);
            String s2 = strings.get(1);

            //广度遍历图，试图得到答案

        }

        return result;



    }


}