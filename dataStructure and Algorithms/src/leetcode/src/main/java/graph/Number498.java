package leetcode.src.main.java.graph;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/diagonal-traverse
 * @author goodtime
 * @create 2024-01-01 22:59
 */
public class Number498 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * 层序遍历即可
 * 注意奇数层遍历完要倒着装入数组
 */
class Solution498 {
    public int[] findDiagonalOrder(int[][] mat) {

        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return null;
        }

        int count = 0;
        int[] result = new int[mat.length * mat[0].length];
        int[] hash = new int[result.length];


        //用queue做层序遍历
        ArrayDeque<Pair<Integer, Integer>> queue = new ArrayDeque();
        queue.offer(new Pair<>(0, 0));
        hash[0] = 1;
        int level = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();

            for (int l = 0; l < size; l++) {

                Pair<Integer, Integer> poll = queue.poll();
                int i = poll.getKey();
                int j = poll.getValue();
                tmp.add(mat[i][j]);

                //先遍历右边元素，再遍历下边的元素
                dfs(queue, i, j + 1, hash, mat);
                dfs(queue, i + 1, j, hash, mat);
            }

            //奇数层倒着装
            if (level % 2 == 1) {
                for (int i = tmp.size() - 1; i >= 0; i--) {
                    result[count++] = tmp.get(i);
                }
            } else {
                for (int i = 0; i < tmp.size(); i++) {
                    result[count++] = tmp.get(i);
                }
            }

            level++;
        }

        return result;
    }

    private void dfs(Queue<Pair<Integer, Integer>> queue, int i, int j, int[] hash, int[][] mat) {

        if (!inMatrix(mat, i, j)) {
            return;
        }

        if (hash[i * mat[0].length + j] == 1) {
            return;
        }

        queue.add(new Pair<>(i, j));
        hash[i * mat[0].length + j] = 1;


    }

    private boolean inMatrix(int[][] mat, int i, int j) {
        return i < mat.length && j < mat[0].length;
    }
}