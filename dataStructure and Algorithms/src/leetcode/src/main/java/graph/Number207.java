package leetcode.src.main.java.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //入度数组，index:课程编号，value:该课程的入度，即有多少课程在其前面
        int[] inDegree = new int[numCourses];
        //邻接表 key:课程编号 value:该课程的后继课程
        HashMap<Integer, List<Integer>> sub = new HashMap<>();

        //1.遍历原数组，构成图
        for (int[] relation : prerequisites) {
            int course = relation[0];
            int preCourse = relation[1];

            //该课程入度+1
            inDegree[course]++;

            //邻接表为前驱课程增加后继
            ArrayList<Integer> postCourse = new ArrayList<>();
            postCourse.add(course);
            sub.merge(preCourse, postCourse, (o1, o2) -> {
                o1.addAll(o2);
                return o1;
            });
        }


        //2.找到入度数组中入度为0的课程编号
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                stack.push(i);
            }
        }

        int finish = 0;

        //3.先将入度为0的课程编号一一处理，看最后是否可以全部处理完成
        while (!stack.isEmpty()) {
            Integer course = stack.pop();
            finish++;
            List<Integer> postCourses = sub.get(course);
            if (postCourses == null) {
                continue;
            }
            for (Integer c : postCourses) {
                inDegree[c]--;
                if (inDegree[c] == 0) {
                    stack.push(c);
                }
            }
        }

        return finish == numCourses;

    }


}
