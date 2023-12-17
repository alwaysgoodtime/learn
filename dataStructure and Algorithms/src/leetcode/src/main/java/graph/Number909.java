package leetcode.src.main.java.graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/snakes-and-ladders/
 *
 * @author goodtime
 * @create 2023-12-15 21:45
 */
public class Number909 {
    public static void main(String[] args) {
        int[][] board = {{-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 35, -1, -1, 13, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 15, -1, -1, -1, -1}};
        System.out.println(new Solution909().snakesAndLadders(board));
    }
}

/**
 * 广度优先搜索,走每一步时，要么走到可以爬梯子和蛇的地方，要么走到最远（即6步，贪心）
 * 这样可能错过一些机会，那么可以把1-6步的情况都遍历，同时，但如果某个点已经到过，下次就不再对其重复搜索
 * 因为已经到过一次，那么那次到的时候，耗的步数肯定更少些，第二次到达这个点就不用再计算了，也就是用vis数组来进行此工作
 */
class Solution909 {

    public int snakesAndLadders(int[][] board) {

        int n = board.length;
        boolean[] vis = new boolean[n * n];
        vis[0] = true;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1, 0});

        while (queue.size() != 0) {

            int[] poll = queue.poll();

            for (int i = 1; i <= 6; i++) {

                //超出边界
                if (poll[0] + i > n * n) {
                    continue;
                }

                //找到下一个点的坐标，算好蛇与梯
                int next = toNext(poll[0] + i, board);

                if (next == n * n) {
                    return poll[1] + 1;
                }

                if (!vis[next]) {
                    vis[next] = true;
                    queue.add(new int[]{next, poll[1] + 1});
                }

            }

        }

        return -1;

    }

    private int toNext(int index, int[][] board) {

        int n = board.length;
        int row = (index - 1) / n;
        int column = (index - 1) % n;

        if (row % 2 == 1) {
            column = n - 1 - column;
        }

        row = n - 1 - row;

        return board[row][column] != -1 ? board[row][column] : index;

    }


}