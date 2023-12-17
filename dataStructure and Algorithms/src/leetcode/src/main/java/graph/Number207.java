package leetcode.src.main.java.graph;

import java.util.*;

/**
 * https://leetcode.cn/problems/course-schedule/description/
 *
 * @author goodtime
 * @create 2023-12-15 18:56
 */
public class Number207 {
    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}};
        System.out.println(new Solution207().canFinish(2, prerequisites));
    }
}

/**
 * 使用图来表示，整个图中不能用互相连通的点，也即，如果有两个点a和b，要么是a->b,要么是b->a，
 * 或者说，整个节点组成的图中不能有环存在 eg: a->b->c->a
 * 可用拓扑排序来判断，如果拓扑排序后可以穷尽图中所有点，说明整个图是无环的
 *
 * numCourses 表示有n个点
 */
class Solution207 {

    class Node {
        int val;
        List<Node> post;
        int visited = 0;

        Node(int val) {
            this.val = val;
            this.post = new ArrayList<>();
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        HashMap<Integer, Node> map = new HashMap<>();
        HashMap<Node, Integer> startPoint = new HashMap<>();

        //1.构成图
        for (int i = 0; i < prerequisites.length; i++) {
            int[] prerequisite = prerequisites[i];
            int course = prerequisite[0];
            int preCourse = prerequisite[1];

            Node courseNode = null;
            Node preCourseNode = null;

            if (!map.containsKey(course)) {
                courseNode = new Node(course);
                map.put(course, courseNode);
            } else {
                courseNode = map.get(course);
            }

            if (!map.containsKey(preCourse)) {
                preCourseNode = new Node(preCourse);
                map.put(preCourse, preCourseNode);
            } else {
                preCourseNode = map.get(preCourse);
            }

            preCourseNode.post.add(courseNode);
            startPoint.merge(courseNode, 1, Integer::sum);

            if (preCourseNode != courseNode) {
                startPoint.merge(preCourseNode, 0, Integer::sum);
            }
        }

        //2.找到图中的每个入度为0的节点，放入nodes中遍历
        Queue<Node> nodes = new ArrayDeque<>();
        Iterator<Map.Entry<Node, Integer>> iterator = startPoint.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Node, Integer> next = iterator.next();
            Integer value = next.getValue();
            if (value.equals(0)) {
                nodes.add(next.getKey());
            }
        }

        int count = 0;
        int num = startPoint.size();

        while (nodes.size() != 0) {
            Node poll = nodes.poll();
            bfs(poll, startPoint, nodes);
            count++;
        }

        return count == num;
    }

    /**
     * 广度遍历
     *
     * @param key
     * @param startPoint
     * @param nodes
     */
    private void bfs(Node key, Map<Node, Integer> startPoint, Queue nodes) {

        List<Node> post = key.post;
        for (int i = 0; i < post.size(); i++) {
            Node node = post.get(i);
            Integer integer = startPoint.get(node);
            if (integer == 1) {
                nodes.add(node);
            }
            startPoint.put(node, integer - 1);
        }
    }


    /**
     * 深度遍历，把每个点设置为3个状态：未搜索、搜索中、已搜索
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {

        HashMap<Integer, Node> map = new HashMap<>();

        //1.构成图
        for (int i = 0; i < prerequisites.length; i++) {
            int[] prerequisite = prerequisites[i];
            int course = prerequisite[0];
            int preCourse = prerequisite[1];

            Node courseNode = null;
            Node preCourseNode = null;

            if (!map.containsKey(course)) {
                courseNode = new Node(course);
                map.put(course, courseNode);
            } else {
                courseNode = map.get(course);
            }

            if (!map.containsKey(preCourse)) {
                preCourseNode = new Node(preCourse);
                map.put(preCourse, preCourseNode);
            } else {
                preCourseNode = map.get(preCourse);
            }

            preCourseNode.post.add(courseNode);
        }

        //2.深度遍历所有点
        Iterator<Map.Entry<Integer, Node>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, Node> next = iterator.next();
            Node value = next.getValue();
            if (value.visited == 0 && !dfs(value)) {
                return false;
            }
        }

        return true;

    }

    /**
     * 深度遍历，返回false的条件是，深度搜索时发现搜索中的后继节点，说明有环
     */
    private boolean dfs(Node key) {

        key.visited = 1;
        List<Node> post = key.post;
        for (int i = 0; i < post.size(); i++) {
            Node node = post.get(i);
            if (node.visited == 0) {
                if (!dfs(node)) {
                    return false;
                }
            } else if (node.visited == 1) {
                return false;
            }
        }
        key.visited = 2;
        return true;
    }


}
