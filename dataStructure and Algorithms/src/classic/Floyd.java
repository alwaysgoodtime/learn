package classic;


import graph.WeightedUndirectedConnectedGraph;

import java.util.Arrays;

/**
 * 佛洛依德算法，用来得到带权有向图中，每个顶点到其他所有顶点的所有最短路径。（也叫多源最短路径）
 * 时间复杂度为O（n的三次方）
 *它的逻辑非常简单，就是
 * @author goodtime
 * @create 2020-02-07 11:23 下午
 */
public class Floyd {
    public static int[][] floyd(WeightedUndirectedConnectedGraph graph) {


        //第一步: 一个各顶点距离的表（这个表初始就是邻接矩阵，后面会不断地在遍历中写入数据），最后其中的元素代表一个顶点到
        //另一个顶点的最短距离，例如[3][5]就表示第四个顶点到第六个顶点的最短距离
        int[][] dis = graph.getEdges();//dis为distance

        //第二步：建一个顶点指向前驱顶点的表，行为每个顶点，列也是所有顶点，其中每一行的默认初始前驱顶点都是自己，
        //最后这张前驱顶点表中，例如[3][5]就表示第四个顶点到第六个顶点所需要的前驱顶点（或者说中间顶点）

        int[][] pre = new int[dis.length][dis.length];//新建一个表，如果用graph.getEdges()，对它的修改会改动原来的图
        //当然，对dis的改动会修改原来图的邻接矩阵，可以采用复制的方式来实现传值，而非传引用

        for (int i = 0; i < pre.length; i++) {
            Arrays.fill(pre[i], i);//这个是Arrays自带的方法，让pre中的所有元素用i填充
        }

        //第三步：三层循环，轮流将所有顶点作为中间顶点（第一层），依次遍历整张顶点距离表（需要两层）。
        for (int i = 0; i < pre.length; i++) {
            for (int j = 0; j < pre.length; j++) {
                for (int k = 0; k < pre.length; k++) {
                    //判断用i做中间节点是否会缩短距离，如果是的话，更新距离表和前驱表
                    if (dis[i][j] != Integer.MAX_VALUE && dis[i][k] != Integer.MAX_VALUE &&
                            dis[i][j] + dis[i][k] < dis[j][k]) {//没有直连的节点，距离为Integer.MAX_VALUE
                        //Integer.MAX_VALUE不能做加减法，所以这里做了个判断，避免数字超过int的范围，变为负数
                        dis[j][k] =  dis[i][j] + dis[i][k];
                        pre[j][k] = i;
                    }
                }
            }
        }

        //第四步：返回排好序的距离表，
        //我们也可以输出pre表，根据pre表，就能得到这些最短距离的路径走法，前驱表维护的意义就在于此
        return dis;

    }
}
