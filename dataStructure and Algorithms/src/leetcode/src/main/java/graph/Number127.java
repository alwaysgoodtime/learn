package leetcode.src.main.java.graph;

import java.util.*;

/**
 * https://leetcode.cn/problems/word-ladder/
 *
 * @author goodtime
 * @create 2023-12-17 13:21
 */
public class Number127 {
    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};
        new Solution127().ladderLength(beginWord, endWord, Arrays.asList(wordList));
    }
}

/**
 * 最短路径
 *
 * @see Number403
 */
class Solution127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Queue<Pair<String, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(beginWord, 1));

        while (queue.size() != 0) {

            Pair<String, Integer> poll = queue.poll();

            if (diff(poll.getKey(), endWord) == 0) {
                return poll.getValue();
            }

            for (int i = 0; i < wordList.size(); i++) {

                if (diff(poll.getKey(), wordList.get(i)) == 1) {
                    queue.add(new Pair<>(wordList.get(i), poll.getValue() + 1));
                    wordList.remove(i);
                    i--;
                }

            }
        }

        return 0;

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
