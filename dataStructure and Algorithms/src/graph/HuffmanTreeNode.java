package graph;

/**赫夫曼树的节点类
 * @author goodtime
 * @create 2020-02-09 10:22 上午
 */

//如果不想强转，最好在Comparable接口后加上泛型

public class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {
    HuffmanTreeNode left;
    HuffmanTreeNode right;
    public int weight;//赫夫曼树排序依赖的权值
    public char c;//每个赫夫曼树节点代表的字符

    @Override
    public int compareTo(HuffmanTreeNode o) {
        return this.weight-o.weight;
    }

    public HuffmanTreeNode() {
    }

    public HuffmanTreeNode(int weight) {
        this.weight = weight;
    }

    public HuffmanTreeNode(HuffmanTreeNode left, HuffmanTreeNode right) {
        this.left = left;
        this.right = right;
    }

    public HuffmanTreeNode(int weight, char c) {
        this.weight = weight;
        this.c = c;
    }

    //    @Override
//    public int compareTo(Object o) {
//        HuffmanTreeNode a = (HuffmanTreeNode) o;
//        return this.weight - a.weight;
//    }


}
