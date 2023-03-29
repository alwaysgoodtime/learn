package datastructure.graph;
import java.util.ArrayList;

/**
 * 无向图（默认没有加权）的基本数据结构：
 * 1.用ArrayList[]数组存放每个图的顶点（eg.A、B、C、D、E）
 * 2.用int[][] edges数组存放图的边（互相直接连通的边置1，不连通的边置0）(也可以用数组+链表来保存这个信息)
 * 3.用edgesNum存放边的数目
 * 4.用isVisited标记图的顶点是否被访问
 *
 * @author goodtime
 * @create 2020-02-06 7:46 下午
 */
public class UnweightedUndirectedConnectedGraph {

    private ArrayList<String> vertices = new ArrayList();
    private int[][] edges;
    private int edgesNum;
    private boolean[] isVisited;
    int count = 0;//方便调试
    int queueCount = 0;//广度优先遍历中队列的计数器

    public UnweightedUndirectedConnectedGraph(int n) {//构造器，n为顶点的个数
        this.edges = new int[n][n];
        isVisited = new boolean[n];
    }

    public UnweightedUndirectedConnectedGraph() {
        this(5);//默认构造一个五个顶点的图
    }

    public void insertEdge(int v1, int v2) {//单个插入边，因为是无向连通图，所以两个方向的领接矩阵都变为1
        //未做输入数据校验
        edges[v1][v2] = 1;
        edges[v2][v1] = 1;
        edgesNum++;
    }


    public void setVertices(ArrayList<String> vertices) {
        this.vertices = vertices;
    }

    public int getEdgesNum() {
        return edgesNum;
    }

    public int getIndexOfVertices(String vertex) {
        return vertices.indexOf(vertex);
    }


    //Depth First Search 递归实现
    public void df(int index) {//实现图的深度遍历，传入值为要遍历的那个节点，返回值为图的所有节点

        //第一步：访问节点，即把记录节点的访问记录的值置为true
        System.out.println(index + "被访问");
        count++;
        isVisited[index] = true;

        //第二步：遍历节点的所有领接节点,如果有且未访问，则访问
        for (int i = findNextVertex(index); i >= 0; i = nextVertex(index, i)) {
            if (i != -1) {
                df(i);
                count++;
            } else {
                break;
            }
        }
    }

    //这才是深度遍历的入口
    public int dfs(int index) {
        df(index);

        //第三步，增加这一步，是为了避免孤顶点的情况，如果有孤顶点，也就是没有其他顶点与它相连，其他顶点深度遍历的时候，是无法遍历到它的
        for (int i = 0; i < isVisited.length; i++) {
            if (isVisited[i] == false) {
                df(i);
            }
        }
        //第四步：说明当前节点所有的相连通节点，以及连通节点的连通节点都已遍历完，返回值
        return count;
    }


    //从领接矩阵中找当前节点剩下的相连节点，接着遍历
    private int nextVertex(int index,int l) {
        for (int i = l+1; i < edges.length; i++) {
            if (edges[index][i] == 1 && !isVisited[i]) {
                return i;
            }
        }
        return -1;
    }


        //从领接矩阵中该顶点可直接连通的顶点，然后看看是否访问过
    //找不到这样的点则返回-1，找到就返回领接顶点的下标。
    private int findNextVertex(int index) {
        for (int i = 0; i < edges.length; i++) {
            if (edges[index][i] == 1 && !isVisited[i]) {
                return i;
            }
        }
        return -1;
    }



    //Broad First Search
    public int bf(int index) {//实现图的广度遍历，传入值为要遍历的那个节点，返回值为图的所有节点

        //第一步：创建动态数组模拟队列，把当前顶点放到一个队列中
        int queueCount = 0;//动态数组的长度
        int currentlevel = 0;//当前层的最后一个顶点在动态数组中的下标，第一个顶点在其中的下标为0
        int currentVertex = 0;//当前顶点在数组中的下标
        ArrayList<Integer> queue = new ArrayList<>();//用动态数组模拟队列
        queue.add(index);
        System.out.println(index + "被访问");
        isVisited[index] = true;
        //第二步：遍历节点的所有领接节点,如果有且未访问，则访问
        while(true) {
            for (int i = findNextVertex(queue.get(currentVertex)); i >= 0; i = nextVertex(queue.get(currentVertex), i)) {
                if (i != -1) {
                    isVisited[i] = true;//在把顶点相连通的顶点加入队列之前，它们已经被访问过了，后续遍历到这些节点的时候
                                        //只是查看它们相连通的顶点，而不管它们本身。
                    System.out.println(i + "被访问");
                    queue.add(i);
                    count++;
                    queueCount++;
                } else {
                    break;
                }
            }
//            if (currentVertex == currentlevel) {//精髓所在，如果当前层的下标被顶点的下标追上，就重置到下一层的顶点下标末尾
//                if(currentlevel < queueCount){
//                    currentlevel = queueCount;
//                }else {//说明这一层已经全部遍历完，没有下一层了，退出循环
//                    break;
//                }
//            }//这个if可以看到广度遍历的深度，但其实没必要这么写。把这个if的代码记为"看层深度"
//            //可以代替"看层深度"的代码

            if(currentVertex == queueCount){
                break;
            }

            currentVertex++;






        }
        return count;//纯为测试用，可以不返回这个值
    }

    //广度遍历入口
    public int bfs(int index) {
        bf(index);
        //第三步，增加这一步，是为了避免孤顶点的情况，如果有孤顶点，也就是没有其他顶点与它相连，其他顶点广度遍历的时候，是无法遍历到它的
        for (int i = 0; i < isVisited.length; i++) {
            if (isVisited[i] == false) {
                df(i);
            }
        }
        //第四步：说明当前节点所有的相连通节点，以及连通节点的连通节点都已遍历完，返回值（方便测试，实际并不需要返回）
        return count;
    }




}
