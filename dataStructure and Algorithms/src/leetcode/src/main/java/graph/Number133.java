package leetcode.src.main.java.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.cn/problems/clone-graph/
 *
 * @author goodtime
 * @create 2023-12-08 23:12
 */
public class Number133 {
    public static void main(String[] args) {
        System.out.println("test");
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * 无向图的深拷贝，首先要遍历一遍图，接着深拷贝
     */
    class Solution133 {

        /**
         * 深度遍历
         */
        public Node cloneGraphDFS(Node node) {

            if (node == null) {
                return null;
            }

            HashMap<Node, Node> map = new HashMap<>();
            Node head = new Node(node.val);
            dfsNode(node, head, map);

            return head;


        }

        /**
         * 用hashmap存储该节点是否被遍历过，key为旧节点，value为新节点
         */
        private void dfsNode(Node node, Node head, HashMap<Node, Node> map) {

            map.put(node, head);
            for (int i = 0; i < node.neighbors.size(); i++) {

                Node neighbor = node.neighbors.get(i);
                if (!map.containsKey(neighbor)) {
                    Node clone = new Node(neighbor.val);
                    dfsNode(neighbor, clone, map);
                    head.neighbors.add(clone);
                } else {
                    head.neighbors.add(map.get(neighbor));
                }
            }
        }

        /**
         * 广度遍历
         */
        public Node cloneGraphBFS(Node node) {

            if (node == null) {
                return null;
            }

            HashMap<Node, Node> map = new HashMap<>();
            Node head = new Node(node.val);
            List<Node> unHandleNode = new ArrayList<>();
            bfsNode(node, head, map, unHandleNode);
            return head;


        }

        /**
         * 用hashmap存储该节点是否被遍历过，key为旧节点，value为新节点
         */
        private void bfsNode(Node node, Node head, HashMap<Node, Node> map, List<Node> unHandleNode) {

            map.put(node, head);

            for (int i = 0; i < node.neighbors.size(); i++) {
                Node neighbor = node.neighbors.get(i);
                if (!map.containsKey(neighbor)) {
                    Node clone = new Node(neighbor.val);
                    head.neighbors.add(clone);
                    map.put(neighbor,clone);
                    unHandleNode.add(neighbor);
                    unHandleNode.add(clone);
                } else {
                    head.neighbors.add(map.get(neighbor));
                }
            }

            if (unHandleNode.size() != 0) {
                Node oldNode = unHandleNode.get(0);
                Node cloneNode = unHandleNode.get(1);
                unHandleNode.remove(0);
                unHandleNode.remove(0);
                bfsNode(oldNode, cloneNode, map, unHandleNode);
            }

        }


        public Node cloneGraph(Node node) {
            if (node == null) {
                return null;
            }

            Node head = new Node(node.val);
            ArrayList<Node> tmp = new ArrayList<>(node.neighbors);

            while (tmp.size() > 0) {

                Node neighbor = tmp.get(0);
                Node cloneNeighbor = new Node(neighbor.val);
                HashMap<Integer, Node> preNodeMap = new HashMap<>();
                preNodeMap.put(head.val, head);

                List<Node> lastNode = dps(neighbor, cloneNeighbor, preNodeMap, head);

                if (lastNode.size() != 0) {
                    head.neighbors.addAll(lastNode);
                }

                //因为不是全连通图，head的有些邻居节点在一次遍历后还得不到结果，需要多次遍历
                for (int i = 0; i < lastNode.size(); i++) {
                    Node last = lastNode.get(i);
                    for (int j = 0; j < tmp.size(); j++) {

                        if (last.val == tmp.get(j).val) {
                            tmp.remove(j);
                            break;
                        }
                    }
                }

            }

            return head;
        }

        /**
         * 转圈拷贝，因为是简单图，所以不会有自环、断点等问题，最后也一定会回到第一个点
         * 返回的值为从一边开始遍历到另一边的最后一个点  举例 1-> 2 -> 3 -> 4 -> 1 ,假设从1开始，而且先遍历2，那么返回4
         */
        private List<Node> dps(Node oldNeighbor, Node cloneNeighbor, HashMap<Integer, Node> preNodeMap, Node firstNode) {

            List<Node> lastNode = new ArrayList<>();
            List<Node> neighbors = oldNeighbor.neighbors;
            preNodeMap.put(cloneNeighbor.val, cloneNeighbor);

            for (int i = 0; i < neighbors.size(); i++) {

                Node node = neighbors.get(i);
                Node preNode = preNodeMap.get(node.val);

                if (preNode == null) {

                    Node nextNode = new Node(node.val);
                    cloneNeighbor.neighbors.add(nextNode);
                    lastNode.addAll(dps(node, nextNode, preNodeMap, firstNode));

                } else if (node.val != firstNode.val) {

                    cloneNeighbor.neighbors.add(preNode);

                } else {

                    cloneNeighbor.neighbors.add(firstNode);
                    lastNode.add(cloneNeighbor);

                }
            }

            return lastNode;
        }
    }


}
