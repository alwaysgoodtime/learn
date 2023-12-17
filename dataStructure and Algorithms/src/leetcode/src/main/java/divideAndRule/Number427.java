package leetcode.src.main.java.divideAndRule;

/**
 * https://leetcode.cn/problems/construct-quad-tree/
 *
 * @author goodtime
 * @create 2023-12-16 10:23
 */
public class Number427 {
    public static void main(String[] args) {
        int[][] grid = {{0,1},{1,0}};
        Solution427.Node construct = new Solution427().construct(grid);
        System.out.println(construct);
    }
}

/**
 * 四叉树，关键是划分区域，看该区域是否有不同的值，我们可以先划分四个区域，确定根节点，把区域界划成4份后，再分别对每个子区域做同样的工作即可
 */
class Solution427 {

    // Definition for a QuadTree node.
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    ;


    public Node construct(int[][] grid) {
        if (grid == null) {
            return null;
        }
        return construct(grid, 0, grid.length - 1, 0, grid.length - 1);
    }

    private Node construct(int[][] grid, int rowBegin, int rowEnd, int columnBegin, int columnEnd) {

        int zeroCount = 0;
        int oneCount = 0;

        for (int i = rowBegin; i <= rowEnd; i++) {
            for (int j = columnBegin; j <= columnEnd; j++) {
                int value = grid[i][j];
                if (value == 1) {
                    oneCount++;
                } else {
                    zeroCount++;
                }
            }
        }

        //说明正方形四个区域的值都为0或都为1，该区域只需要一个叶子节点即可
        if (zeroCount == 0 || oneCount == 0) {
            return new Node(zeroCount == 0, true);
        }

        //创建根节点
        Node root = new Node(true, false);
        root.topLeft = construct(grid, rowBegin, (rowEnd - rowBegin) / 2 + rowBegin, columnBegin, (columnEnd - columnBegin) / 2 + columnBegin);
        root.topRight = construct(grid, rowBegin, (rowEnd - rowBegin) / 2 + rowBegin, (columnEnd - columnBegin) / 2 + columnBegin + 1, columnEnd);
        root.bottomLeft = construct(grid, (rowEnd - rowBegin) / 2 + rowBegin + 1, rowEnd, columnBegin, (columnEnd - columnBegin) / 2 + columnBegin );
        root.bottomRight = construct(grid, (rowEnd - rowBegin) / 2 + rowBegin + 1, rowEnd, (columnEnd - columnBegin) / 2 + columnBegin + 1, columnEnd);
        return root;

    }
}
