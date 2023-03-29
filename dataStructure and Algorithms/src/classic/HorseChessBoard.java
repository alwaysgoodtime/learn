package classic;

import sort.BubbleSort;

import java.io.BufferedWriter;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 马踏棋盘问题，也叫骑士周游问题，是回溯法的经典案例
 * 递归+循环
 * 可以用贪婪算法进行优化，方法是:选取下一步的下一步选择最少的那步棋去下
 *
 * @author goodtime
 * @create 2020-02-08 12:15 下午
 */
public class HorseChessBoard {
    /**
     * @param length 棋盘的长度和宽度（默认为正方形棋盘）
     * @return 马儿踏棋盘的走法
     */

    private static boolean finished;//作为返回的标记，如果棋子把64个格子都走了一遍，说明已经完成遍历，把它置为true

    private static int leng;

    public static int[][] horseChessBoard(int length) {

        leng = length;

        int count = 0;//计数器,每走一步，计数器就加1

        //用二维数组模拟棋盘
        int[][] chessBoard = new int[length][length];//记录每步棋下的位置

        //记录棋盘每个位置是否访问过，可以用横坐标*（每一行的列数）+纵坐标的方式，把二维数组转成一位数组存储。

        int[] visited = new int[length * length];

        //第一步，确定马的起点，可以是棋盘任何一个位置，马的走法也有很多，这里以（0，0）为例
        chessBoard[0][0] = ++count;//count此时为1
        visited[0] = 1;

        //第二步，遍历马可以走的8个位置，存入ArrayList中，如果坐标存在于棋盘上
        //而且未访问，进行贪心算法优化后返回一个可以访问的坐标。

        ArrayList<Point> check = check(0, 0);


        //贪心算法优化环节，没有这个环节亦可以遍历，但是会多耗一些时间，优化的思想是：选取下一步的下一步选择最少的那步棋去下，这样减少返回次数

        //比较Point这种自定义类的的两种方法：1.传入一个比较器
//        Comparator<Point> comparator = new Comparator<Point>() {
//            @Override
//            public int compare(Point o1, Point o2) {
//                ArrayList check1 = check(o1.x, o1.y);
//                ArrayList check2 = check(o2.x, o2.y);
//                return check1.size()-check2.size();
//            }
//        } ;
//        check.sort(comparator);
        Collections.sort(check);

        //当存放下一步能走位置的集合不为空
        while (!check.isEmpty()) {
            Point point = (Point) check.remove(0);
            if(visited[point.x*length+point.y] == 0) {
                traverse(chessBoard, point.x, point.y, visited, length, count + 1);
            }
        }

        if(finished){
            System.out.println("搜索成功");
            return chessBoard;
        }else {
            System.out.println("搜索失败");
            chessBoard[0][0] = 0;//给初始地址步数置为0
            return chessBoard;
        }
    }

    //贪心算法，用空间换取时间，占用了额外空间，但是算法时间会减少
    private static void sortByNext(ArrayList check) {

        int[] nextNum = new int[check.size()];
        int i = 0;

        Iterator iterator = check.iterator();
        while (iterator.hasNext()){
            Point a = (Point)iterator.next();
            nextNum[i++] = check(a.x,a.y).size();
        }


    }

    private static void traverse(int[][] chessBoard, int x, int y, int[] visited, int length,int count) {

        chessBoard[x][y] = count;//棋子下的第几步
        visited[x * length + y] = 1;//标记其所在的位置为已访问
        ArrayList check = check(x, y);
        while (!check.isEmpty()) {
            Point point = (Point) check.remove(0);
            if(visited[point.x*length+point.y] == 0){//在这里判断下一节点是否访问，而非在check（）函数中判断的原因是，一旦找到第64个
                //棋子可以放的位置，遍历就应该停止，虽然在第64次循环里，把finished置为true，而且这个点的访问情况不回退为0，但check判断时，
                //并没有把遍历为此节点后，访问情况是否为1的情况考虑到，所以还会继续按照这个点未访问的情况进行下次考虑。
                //所以，虽然finished置为true的话，确实会让遍历结束，但是返回的数组中，因为前面几次还会继续遍历，遍历的情况会把我们得到的棋子走的路径覆盖掉。
                traverse(chessBoard, point.x, point.y, visited, length,count+1);
            }
        }

        //判断马儿是否完成了任务，使用count和应该走的步数比较 ，
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置 0
        //说明: count< length * length 成立的情况有两种
        //1. 棋盘到目前位置,仍然没有走完
        //2. 棋盘处于一个回溯过程
        if (count < length*length && !finished) {
            chessBoard[x][y] = 0;
            visited[x*length + y] = 0;
        } else {
            finished = true;
        }

    }

    //比较Point这种自定义类的的两种方法：2.让容器中的元素实现Comparable接口，并重写方法
    static class Point implements Comparable{//存放棋子下的位置的类
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object o) {
            Point a = (Point)o;
            ArrayList check1 = check(this.x, this.y);
            ArrayList check2 = check(a.x, a.y);
            return check1.size()-check2.size();
        }
    }

    private static ArrayList<Point> check(int i, int j) {

        ArrayList<Point> points = new ArrayList<Point>();

        //把8个位置是否可以走进行判断

        if (i - 2 >= 0 && j - 1 >= 0 ) {

            points.add(new Point(i - 2, j - 1));//这个位置可用，加入存放下一步可以走的位置的动态集合中
        }
        if (i - 2 >= 0 && j + 1 < leng ) {

            points.add(new Point(i - 2, j + 1));

        }
        if (i - 1 >= 0 && j - 2 >= 0 ) {

            points.add(new Point(i - 1, j - 2));

        }
        if (i - 1 >= 0 && j + 2 < leng ) {

            points.add(new Point(i - 1, j + 2));

        }
        if (i + 1 < leng && j - 2 >= 0 ) {

            points.add(new Point(i + 1, j - 2));

        }
        if (i + 1 < leng && j + 2 < leng ) {

            points.add(new Point(i + 1, j + 2));

        }
        if (i + 2 < leng && j - 1 >= 0 ) {

            points.add(new Point(i + 2, j - 1));

        }
        if (i + 2 < leng && j + 1 < leng) {

            points.add(new Point(i + 2, j + 1));

        }

        return points;
    }
}
