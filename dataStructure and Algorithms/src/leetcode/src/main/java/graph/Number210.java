package leetcode.src.main.java.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/course-schedule-ii/description/
 *
 * @author goodtime
 * @create 2023-12-15 21:21
 */
public class Number210 {
    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}};
        System.out.println(new Solution210().findOrder(2, prerequisites));
    }
}

/**
 * @see Number207
 * 这次题目要求按照返回1个拓扑排序
 */
class Solution210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> table = new ArrayList<>();//邻接表
        int[] course = new int[numCourses];//代表对应课程的入度
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            table.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            table.get(prerequisites[i][1]).add(prerequisites[i][0]);
            course[prerequisites[i][0]]++;
        }

        Queue<List<Integer>> queue = new ArrayDeque<>();

        int index = 0;

        for (int i = 0; i < numCourses; i++) {
            if (course[i] == 0) {
                queue.add(table.get(i));
                result[index] = i;
                index++;
            }
        }

        while (queue.size() != 0) {
            List<Integer> poll = queue.poll();
            for (int i = 0; i < poll.size(); i++) {
                Integer courseNumber = poll.get(i);
                course[courseNumber]--;
                if (course[courseNumber] == 0) {
                    queue.add(table.get(courseNumber));
                    result[index] = courseNumber;
                    index++;
                }
            }
        }

        if (index + 1 < numCourses) {
            return new int[0];
        } else {
            return result;
        }

    }
}