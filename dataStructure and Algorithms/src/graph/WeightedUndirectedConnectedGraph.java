package graph;

import java.util.ArrayList;

/**
 * 加权无向连通图，默认没有孤顶点
 *  * 1.用ArrayList[]数组存放每个图的顶点（eg.A、B、C、D、E）
 *  * 2.用int[][] edges数组存放图的边（互相直接连通的边存放相应的权值，不连通的边放整数最大值（表示此路不通））(也可以用数组+链表来保存这个信息)
 *  * 3.用edgesNum存放边的数目
 *  * 4.用isVisited标记图的顶点是否被访问
 * @author goodtime
 * @create 2020-02-07 2:26 下午
 */
public class WeightedUndirectedConnectedGraph {
    private ArrayList<String> vertices = new ArrayList();
    private int[][] edges;
    private int edgesNum;
    private boolean[] isVisited;
    //便于dfs和bfs遍历，控制是否访问

    public WeightedUndirectedConnectedGraph(int n) {//构造器，n为顶点的个数
        this.edges = new int[n][n];
        isVisited = new boolean[n];
    }

    public WeightedUndirectedConnectedGraph() {
        this(5);//默认构造一个五个顶点的图
    }

    public WeightedUndirectedConnectedGraph(int[][] edges) {//提供直接输入邻接矩阵的构造器，生成图
        this.edges = edges;
        for (int i = 0; i < edges.length; i++) {//根据输入的邻接矩阵，生成图中所有边的数目
            for (int j = 0; j < edges[0].length; j++) {
                if(edges[i][j] != Integer.MAX_VALUE){
                    edgesNum++;
                }
            }
        }
        edgesNum = edgesNum/2;
    }

    public void insertEdge(int v1, int v2,int weight) {//单个插入边，因为是无向连通图，所以两个方向的领接矩阵都变为相应权值
        //未做输入数据校验
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgesNum++;
    }


    public void setVertices(ArrayList<String> vertices) {
        this.vertices = vertices;
    }
    //设置每个顶点的名字
    //顶点顺序按照动态数组中的顺序


    public int getEdgesNum() {
        return edgesNum;
    }

    public int getIndexOfVertices(String vertex) {
        return vertices.indexOf(vertex);
    }

    public ArrayList<String> getVertices() {
        return vertices;
    }

    public int[][] getEdges() {
        return edges;
    }

    //从领接矩阵中找当前节点剩下的相连节点，接着遍历
    public int nextVertex(int index,int l) {
        for (int i = l+1; i < edges.length; i++) {
            if (edges[index][i] != Integer.MAX_VALUE) {//最大值表示路不连通，这个是为prim算法定制的方法
                return i;
            }
        }
        return -1;
    }


    //从领接矩阵中该顶点可直接连通的顶点
    //找不到这样的点则返回-1，找到就返回领接顶点的下标。
    public int findNextVertex(int index) {
        for (int i = 0; i < edges.length; i++) {
            if (edges[index][i] != Integer.MAX_VALUE) {
                return i;
            }
        }
        return -1;
    }

    public int getEdgesWeight(int currentVertexIndex, int nextVertexIndex) {
        return edges[currentVertexIndex][nextVertexIndex];
    }
}
