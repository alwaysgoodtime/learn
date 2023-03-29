package classic;


import datastructure.graph.WeightedUndirectedConnectedGraph;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 这个也是在图中找最小树的问题，和prim不同的是，它以边为基础开始找，而prim以点为基础再找，它适合稀疏的图（点多边少）。
 * 对应prim适合边多点少的图。
 *
 * @author goodtime
 * @create 2020-02-07 5:55 下午
 */
public class Kruskal {
    /**
     * @param graph 传入一个图
     * @return 返回图中最短路径的值
     */

    public static int[] noCircle;

    public static int kruskal(WeightedUndirectedConnectedGraph graph) {


        //第一步，给graph所有的顶点排序。因为graph中的顶点我本身就用arraylist存，所以我将其中顶点加入的顺序
        //视为顶点的顺序。而且邻接矩阵也是按此顺序排列的。


        //第二步，遍历邻接矩阵，取到graph所有的边，并把顶点中排序在前的作为起始点，靠后的作为终点。

        int edgesNum = graph.getEdgesNum();//矩阵总边数

        EdgePlugin[] edgePlugin = new EdgePlugin[edgesNum];//自定义一个边的插件类，作为数组的元素
        //分别存边的start/end/weight

        int index = 0;//记录边数组的下标

        int[][] edges = graph.getEdges();//获取图的邻接矩阵

        int[][] removal = new int[edges.length][edges[0].length];//邻接矩阵中不在对角线上的边会出现两次
        //这个数组防止一条边被加入两次

        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[0].length; j++) {
                if (edges[i][j] != Integer.MAX_VALUE && removal[i][j] != 1) {
                    if (i < j) {
                        edgePlugin[index++] = new EdgePlugin(i, j, edges[i][j]);
                    } else {
                        edgePlugin[index++] = new EdgePlugin(j, i, edges[i][j]);
                    }
                    removal[j][i] = 1;
                }
            }
        }

        //第三步，给所有的边按照权值从小到大排序,这里采用插值排序

        insertionSort(edgePlugin);

        //第四步，根据权值，从小到大生成最小子树

        noCircle = new int[edges.length];


        //第五步，按权值从小到大遍历边，能用的边拿进来，边+1，当边的数目+1图顶点数目相同时，说明最小树已经全部找完，结束循环

        index = 0;//edgePlugin数组的下标，依次取出

        int allWeight = 0;

        for (int i = 1, finish = edges.length; i < finish; index++) {//edges.length为图顶点的总数目

            //处理边是否会形成回路的问题
            int endPoint1 = endPoint(edgePlugin[index].start);
            int endPoint2 = endPoint(edgePlugin[index].end);
            if (endPoint1 != endPoint2) {
                noCircle[endPoint1] = endPoint2;
                i++;
                allWeight += edgePlugin[index].weight;
            }
        }

        return allWeight;
    }

    private static int endPoint(int i) {
        while (0 != noCircle[i]) {
            i = noCircle[i];
        }
        return i;
    }

    static class EdgePlugin {
        int start;
        int end;
        int weight;

        public EdgePlugin(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EdgePlugin{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    '}';
        }
    }

    //插值排序
    private static void insertionSort(EdgePlugin[] edge) {
        for (int i = 1; i < edge.length; i++) {
            int j = i - 1;
            EdgePlugin tmp = edge[i];
            while (j >= 0 && edge[j].weight > tmp.weight ) {//j>=0要放在下一个条件的前面，否则会报越界异常
                edge[j + 1] = edge[j];
                j--;
            }
            edge[j + 1] = tmp;
        }
    }

}
