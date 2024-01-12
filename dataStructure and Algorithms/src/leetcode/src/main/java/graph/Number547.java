package leetcode.src.main.java.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * @author goodtime
 * @create 2024-01-09 02:17
 */
public class Number547 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 并查集
 */
class Solution547 {

    int[] parent;

    public int findCircleNum(int[][] isConnected) {

        parent = new int[isConnected.length];

        for (int i = 0; i < isConnected.length; i++) {
            parent[i] = i;
        }

        //1.建立并查集
        //如果isConnected[i][j] = 1, 那么如果isConnected[j][i]一定也为1，所以只看一半即可
        //同时isContested[i][j] = 1, 无需考察
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected.length; j++) {
                if (isConnected[i][j] != 1) {
                    continue;
                }
                union(i, j);
            }
        }

        //2.遍历省份，确认不交集的数目
        Map<Integer, Boolean> rootMap = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            int root = find(i);
            if (rootMap.containsKey(root)) {
                continue;
            }
            rootMap.put(root, true);
        }

        return rootMap.size();

    }

    private int find(int i) {

        if (i == parent[i]) {
            return i;
        }

        parent[i] = find(parent[i]);
        return parent[i];

    }

    private void union(int i, int j) {

        int parentI = find(i);
        int parentJ = find(j);

        if (parentI != parentJ) {
            parent[parentI] = parentJ;
        }

    }
}
