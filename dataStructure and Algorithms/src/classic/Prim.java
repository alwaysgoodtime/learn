package classic;

import graph.WeightedUndirectedConnectedGraph;

import java.util.ArrayList;

/**
 * 用普利姆算法解决修路问题，其实就是在一个连通的加权无向图（连通是指没有孤顶点）中找最小生成树，
 * 最小生成树顶点和图顶点相同，共有顶点-1条边，把整个树连起来。
 * 称之为最小生成树，是因为生成的是树结构，也即顶点中没有环。
 * @author goodtime
 * @create 2020-02-07 2:11 下午
 */
public class Prim {
    /**
     * 传入一个连通的加权无向图，返回生成的最小权值生成树的权值总和
     * @param graph 传入的图
     * @return
     */

    //普里姆算法和开始的顶点无关，我们选择第一个顶点即可，无论从哪个顶点开始，得到的最小生成树中的边都是一样的。
    public static int prim(WeightedUndirectedConnectedGraph graph) {


        int minEdgeWeight = Integer.MAX_VALUE;//记录最小的权值
        ArrayList<Integer> verticesIndex = new ArrayList<Integer>();//记录已被选到最小树中的顶点序号
        verticesIndex.add(0);//默认从序号为0的顶点开始，把它放入最小树中，找它能连通的顶点
        int minVertex = -1;//存放当前遍历一次当前最小树后，找到的最小边所连接的顶点的下标，顶点下标最小为0，不可能是-1
        //所以可以设置初始化值
        int allWeight = 0;//记录最小树中所有边的权值，并返回

        //遍历最小树的每个顶点能连通的所有未在最小树中的顶点，找到权值最小的那条边
        while (true) {
            for (int i = 0; i < verticesIndex.size(); i++) {
                int currentVertex = verticesIndex.get(i);//当前要查看的最小树中的节点
                for (int nextVertex = graph.findNextVertex(currentVertex); nextVertex != -1; nextVertex = graph.nextVertex(currentVertex, nextVertex)) {
                    //如果nextVertex不在最小树中，那么就接着判断这条边的权值
                    if (!verticesIndex.contains(nextVertex) && nextVertex != -1 && (graph.getEdgesWeight(currentVertex, nextVertex) < minEdgeWeight)) {//
                        minEdgeWeight = graph.getEdgesWeight(currentVertex,nextVertex);//记录这条边的权值
                        minVertex = nextVertex;
                    }
                }
            }

            if (minVertex == -1) {//说明所有顶点都放在了最小数中，遍历完成
                break;
            } else {
                verticesIndex.add(minVertex);//放入顶点下标
                minVertex = -1;//初始化minVertex
                allWeight += minEdgeWeight;//放入最小权值
                minEdgeWeight = Integer.MAX_VALUE;//初始化边的最小加权值，Integer.MAX_VALUE是图中默认不可通达的边的权值
            }
        }
        return allWeight;
    }
}
