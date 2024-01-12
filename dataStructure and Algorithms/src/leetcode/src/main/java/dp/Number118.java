package leetcode.src.main.java.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goodtime
 * @create 2024-01-12 00:12
 */
public class Number118 {
    public static void main(String[] args) {
        System.out.println("test");
    }
}

/**
 * dp
 */
class Solution118 {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> preRow = new ArrayList<>();
        preRow.add(1);
        result.add(preRow);

        for (int i = 1; i < numRows; i++) {

            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 0; j + 1 < preRow.size(); j++) {
                list.add(preRow.get(j) + preRow.get(j + 1));
            }
            list.add(1);
            result.add(list);
            preRow = list;
        }

        return result;
    }
}