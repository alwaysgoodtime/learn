package classic;


import graph.WeightedUndirectedConnectedGraph;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 迪杰斯特拉算法，用来得到带权（无负权）有向图中，某个顶点到其他所有顶点的所有最短路径。（也叫单源最短路径）
 * 时间复杂度为O（n的平方），或者说o（v的平方），v为顶点数，如果优化一下，可以做到O(nlogn + m)
 * 最简单的思想:先找到与源点直接相连的点中最近的点，再把这个点与源点看做一个整体作为一个新的源点，重复这个步骤即可。
 * （迪杰斯特拉也是按照广度顺序遍历（或者说相对于已访问顶点的集合s的广度遍历，可以把这个集合视为一个点），但是思想和一般的广度遍历是不同的。）
 *
 * @author goodtime
 * @create 2020-02-07 11:25 下午
 */
public class Dijkstra {

    public static void main(String[] args) {
        WeightedUndirectedConnectedGraph graph = new WeightedUndirectedConnectedGraph(5);
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(1, 2, 2);
        graph.insertEdge(2, 3, 3);
        graph.insertEdge(3, 4, 4);
        graph.insertEdge(2, 4, 2);

        int[][] edges = graph.getEdges();

        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                edges[i][j] = edges[i][j] != 0 ? edges[i][j] : Integer.MAX_VALUE;
            }
        }

        int[] dijkstra = dijkstra(graph, 0);
        System.out.println(Arrays.toString(dijkstra));

    }

    public static int[] dijkstra(WeightedUndirectedConnectedGraph graph, int vertexIndex) {

        //第一步：建立三张表

        //所有边
        int[][] edges = graph.getEdges();


        // 一张是存放每个顶点是否被访问的表（visited）,初始为false，顶点没访问为false，访问过为true
        boolean[] visited = new boolean[edges.length];


        // 一张是存放每个顶点的前驱顶点的表（pre），这张表是非必需的，维护它的目的是，如果要找到最短路径对应的路径走法
        //那个走法就维护在这张表里,每个点存的是前驱节点的下标
        int[] pre = new int[edges.length];

        // 一张是存放源顶点到每个顶点的距离的表（distance）
        int[] distance = new int[edges.length];

        for (int i = 0; i < edges.length; i++) {
            distance[i] = Integer.MAX_VALUE;//表的初始值为Integer.MAX_VALUE，代表都为不可通达，无限大。
        }

        int count = 0;//记录访问的顶点个数
        int vertex = vertexIndex;//循环中存放要遍历的顶点下标
        int min = Integer.MAX_VALUE;//记录当前顶点所连通顶点的最小权值
        int tmp = 0; //暂存循环下次要遍历的顶点下标
        distance[vertex] = 0;//让当前顶点到自身的距离为0


        //从源顶点开始遍历所有顶点，每遍历一个顶点，做5件事，
        //遍历它能通达的所有顶点，
        //去distance表中更新距离（所以迪杰斯特拉是动态规划，此次的值需要依赖上一次子问题的结果）
        //去pre前驱表中记录前驱节点，维护路径的走法
        //顶点设为被访问过
        //找到此顶点连通的点中权值最小的顶点，下一次遍历它（所以也是贪婪算法，全局的最优解，依赖于所有局部的最优解）

        //这里用PriorityQueue引入优化，方便下次选点，存储的是遍历的节点与他们的值
        PriorityQueue<Pair<Integer, Integer>> node = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());

        while (count != edges.length) {//edges.length为图的总顶点数

            for (int nextVertex = graph.findNextVertex(vertex); nextVertex != -1;
                 nextVertex = graph.nextVertex(vertex, nextVertex)) {

                if (distance[nextVertex] < 0) {
                    throw new RuntimeException("权值为负，无法使用迪杰斯特拉算法");//健壮性检查
                }

                //这里就是迪杰斯特拉算法的精髓，如果此点已经访问过，就不用再更新了；
                //如果值为Integer.MAX_VALUE,说明当前顶点还不可通达，自然要更新
                //如果源顶点到当前顶点的路径长度（是上次循环的结果，已为最短路径，也就是局部最优解）+当前顶点到它的连通顶点
                //的路径长度<原来存放的路径长度，说明找到了更短的路径，可以更新

                if (!visited[nextVertex] && (distance[nextVertex] == Integer.MAX_VALUE ||
                        distance[vertex] + edges[vertex][nextVertex] < distance[nextVertex])) {
                    distance[nextVertex] = distance[vertex] + edges[vertex][nextVertex];//更新路径的权值
                    pre[nextVertex] = vertex;//更新路径的前驱顶点
                    node.offer(new Pair<>(nextVertex, distance[nextVertex]));
                }
            }

            //设为顶点已访问,这一步相当于把此顶点加到一个最短路径组成部分的集合中，一定要写在确定下次要遍历的顶点前
            visited[vertex] = true;
            count++;//访问过的顶点个数加1

            //优化：用小根堆来实现
            while (!node.isEmpty()) {
                Pair<Integer, Integer> poll = node.poll();
                //有些场景下该点已经被遍历或者存储的值已经过时
                if (!visited[poll.getKey()] && distance[poll.getKey()] == poll.getValue()) {
                    vertex = poll.getKey();//这个是离当前顶点权值最小的顶点，两者之间的路径可以视为最优解
                    min = Integer.MAX_VALUE;//初始化最小权值，方便下次遍历
                    break;
                }
            }

        }

        //返回距离表
        return distance;
    }
}
