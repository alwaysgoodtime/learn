package leetcode.src.main.java.graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/rotting-oranges
 *
 * @author goodtime
 * @create 2024-01-01 15:27
 */
public class Number994 {
    public static void main(String[] args) {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(new Solution994().orangesRotting(grid));

    }
}


class Pair<T, V> {

    T first;
    V second;

    Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    public T getKey() {
        return first;
    }

    public V getValue() {
        return second;
    }

}


/**
 * 多源层序遍历，把每个烂橘子视为一个遍历源头，从所有腐烂的橘子出发，一层一层遍历下去即可
 */
class Solution994 {
    public int orangesRotting(int[][] grid) {

        int goodOrangesCount = 0;
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque();
        int time = 1;

        //取出所有腐烂的橘子，同时确认未腐烂橘子总数
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    goodOrangesCount++;
                } else if (grid[i][j] == 2) {
                    queue.offer(new Pair<>(i, j));
                }
            }
        }

        //没有好橘子，直接返回
        if (goodOrangesCount == 0) {
            return 0;
        }


        while (!queue.isEmpty()) {
            //每层有size个烂橘子
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> poll = queue.poll();
                goodOrangesCount = handle(grid, poll, goodOrangesCount, queue);
                if (goodOrangesCount == 0) {
                    return time;
                }
            }

            time++;
        }

        return -1;

    }

    private int handle(int[][] grid, Pair<Integer, Integer> pair, int goodOrangesCount, Queue<Pair<Integer, Integer>> queue) {

        int i = pair.getKey();
        int j = pair.getValue();

        return goodOrangesCount - bfs(grid, i - 1, j, queue) - bfs(grid, i + 1, j, queue) - bfs(grid, i, j + 1, queue) - bfs(grid, i, j - 1, queue);
    }


    private int bfs(int[][] grid, int i, int j, Queue<Pair<Integer, Integer>> queue) {

        if (!inArea(i, j, grid) || grid[i][j] != 1) {
            return 0;
        }


        grid[i][j] = 2;
        queue.add(new Pair<>(i, j));
        return 1;
    }

    private boolean inArea(int i, int j, int[][] grid) {
        if (i < 0 || i >= grid.length) {
            return false;
        }

        if (j < 0 || j >= grid[0].length) {
            return false;
        }

        return true;
    }

}


