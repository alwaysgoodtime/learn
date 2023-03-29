package classic;

/**
 * 如果调用接口不传参，默认就是8皇后，如果传参为n，就是n皇后问题
 * 输出的结果为共有多少种摆法
 *
 * @author goodtime
 * @create 2020-02-06 6:49 下午
 */
public class EightQueen {

    public static int[] rows;//存最后找到的解决方案
    public static int queen;//存皇后的个数
    public static int path = 0;//存最后有多少种解法

    public static int eightQueen() {
        return eightQueen(8);
    }


    //检验当前行的皇后有没有和之前行的皇后在同一列或同一斜线上

    private static boolean checkQueen(int row) {
        for (int i = 0; i < row; i++) {
            if (rows[i] == rows[row] || Math.abs(rows[i] - rows[row]) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }


    public static int eightQueen(int queenCount) {
        rows = new int[queenCount];//几个皇后，就分成几行，下标为行号，存的值为列号
        queen = queenCount;
        countPaths(queenCount);
        return path;
    }

    private static void countPaths(int i) {//典型的回溯算法，就是确定前面几条路，然后看看到最后能不能走通
        //走不通了，就往前退一步，看看有没有岔路，接着走，如果还走不通，再往前退一步，看看有没有岔路。
        //最后就能遍历所有的路径，所以回溯算法也是某种隐式图的深度遍历算法（DFS）
        int row = Math.abs(queen - i);
        for (int j = 0; j < queen; j++) {
            rows[row] = j;
            if (checkQueen(row)) {
                if ((row + 1) == queen) {
                    path++;
                    for (int a : rows
                    ) {
                        System.out.print(a);
                    }
                    System.out.print("\n");
                } else {
                    countPaths(i-1);
                }
            }
        }
    }
}
