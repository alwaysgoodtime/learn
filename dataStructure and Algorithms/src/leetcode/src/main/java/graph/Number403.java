package leetcode.src.main.java.graph;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

/**
 * @author goodtime
 * @create 2023-12-17 12:40
 */
public class Number403 {
    public static void main(String[] args) {
        String startGene = "AAAAAAAA";
        String endGene = "AAAAACGG";
        String[] bank = new String[]{"AAAAAAGA","AAAAAGGA","AAAAACGA","AAAAACGG","AAAAAAGG","AAAAAAGC"};
        System.out.println(new Solution403().minMutation(startGene, endGene, bank));
    }
}

/**
 * 先遍历endGene是否在bank
 *
 * 因为要求对endGene每一次更改后的中间结果都在bank里，可以用广度遍历，看是否可以变为startGene
 *
 * 也即startGene为起始起点，而bank为中间节点，endGene为最后节点，看startGene是否经过中间节点，抵达最后的endGene
 */
class Solution403 {
    public int minMutation(String startGene, String endGene, String[] bank) {

        //用hashmap记录每个中间节点是否已经经过，如果已遍历过，那么就不处理
        HashMap<String, Boolean> map = new HashMap<>(bank.length);

        for (int i = 0; i < bank.length; i++) {
            map.put(bank[i], false);
        }

        Queue<Pair<String, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(startGene, 0));

        while (queue.size() != 0) {
            Pair<String, Integer> poll = queue.poll();

            for (int i = 0; i < bank.length; i++) {

                if (diff(poll.getKey(), endGene) == 0 && map.get(bank[i])) {
                    return poll.getValue();
                }

                if (diff(poll.getKey(), bank[i]) == 1 && !map.get(bank[i])) {
                    map.put(bank[i], true);
                    queue.offer(new Pair<>(bank[i], poll.getValue() + 1));
                }

            }

        }

        return -1;

    }

    private int diff(String s1, String s2) {

        int diff = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }

        return diff;
    }
}
