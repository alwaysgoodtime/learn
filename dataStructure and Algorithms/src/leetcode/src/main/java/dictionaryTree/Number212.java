package leetcode.src.main.java.dictionaryTree;

import java.util.*;

/**
 * https://leetcode.cn/problems/word-search-ii/
 *
 * @author goodtime
 * @create 2023-12-17 15:39
 */
public class Number212 {
    public static void main(String[] args) {
        char[][] broad = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        System.out.println(new Solution212().findWords(broad, words));
    }
}

/**
 * 1.建立词典树，将words中的词语放入词典树
 *
 * @see Number208
 *
 * 2.将board视为一副图，每个节点可以与上下左右节点连通，广度遍历每个节点，看是否可以组成词典树中的词
 */
class Solution212 {

    class Trie {

        class TrieNode {

            //并非表示该节点为叶子节点，而表示该节点为一个单词的结束，方便search函数
            boolean isEnd = false;
            //其实用List也行，但是因为需要经常遍历son，于是用hashmap装，方便快速查找定位，用空间换时间
            HashMap<Character, TrieNode> son = new HashMap<>();
            char c = 0;

            TrieNode() {
            }

            TrieNode(char c) {
                this.c = c;
            }
        }

        TrieNode trieNode;

        public Trie() {
            trieNode = new TrieNode();
        }


        public void insert(String word) {
            if (word == null || word.length() == 0) {
                return;
            }

            //从根节点开始
            insert(word, 0, trieNode);
        }


        //默认word已经在trie上，且只有一个该word
        public void delete(String word) {
            if (word == null || word.length() == 0) {
                return;
            }

            //从根节点开始
            delete(word, 0, trieNode);

        }

        private void delete(String word, int index, TrieNode trieNode) {

            Deque<TrieNode> queue = new ArrayDeque<>();

            TrieNode tmp = trieNode;

            while (index < word.length()) {
                TrieNode son = tmp.son.get(word.charAt(index));
                queue.offer(son);
                tmp = son;
                index++;
            }

            //对最后一个节点做特殊处理
            TrieNode lastNode = queue.pollLast();
            lastNode.isEnd = false;
            char lastChar = lastNode.c;
            boolean deleteSonFlag = lastNode.son.size() == 0;

            while (!queue.isEmpty()) {

                TrieNode poll = queue.pollLast();

                if (deleteSonFlag) {
                    poll.son.remove(lastChar);
                }

                if (poll.son.size() == 0 && !poll.isEnd) {
                    deleteSonFlag = true;
                } else {
                    deleteSonFlag = false;
                }

                lastChar = poll.c;
            }

        }

        /**
         * 递归插入word的每个字符
         */
        private void insert(String word, int index, TrieNode trieNode) {

            char c = word.charAt(index);

            if (trieNode.son.containsKey(c)) {
                if (index != word.length() - 1) {
                    insert(word, index + 1, trieNode.son.get(c));
                } else {
                    trieNode.son.get(c).isEnd = true;
                }

                return;
            }

            //到达这里，说明word该字符并非当前trieNode的孩子
            TrieNode son = new TrieNode(c);
            trieNode.son.put(c, son);
            if (index != word.length() - 1) {
                insert(word, index + 1, son);
            } else {
                son.isEnd = true;
            }

        }
    }

    StringBuilder stringBuilder = new StringBuilder();
    //已经通过字典数的delete操作完成去重
    List<String> result = new ArrayList<>();
    Trie trie = new Trie();
    //用数组去重，保证遍历过的节点不会再遍历一次
    boolean[] array;

    public List<String> findWords(char[][] board, String[] words) {

        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return new ArrayList<>();
        }

        for (String word : words) {
            trie.insert(word);
        }

        array = new boolean[board.length * board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                search(board, i, j, trie.trieNode);
            }
        }

        return result;
    }

    private void search(char[][] board, int row, int column, Trie.TrieNode node) {

        if (row < 0 || row >= board.length) {
            return;
        }

        if (column < 0 || column >= board[0].length) {
            return;
        }

        int num = row * board[0].length + column;

        if (array[num]) {
            return;
        }

        char c = board[row][column];

        if (!node.son.containsKey(c)) {
            return;
        }

        Trie.TrieNode trieNode = node.son.get(c);
        array[num] = true;
        stringBuilder.append(c);

        if (trieNode.isEnd) {
            result.add(stringBuilder.toString());
            trie.delete(stringBuilder.toString());
        }

        if (!trieNode.son.isEmpty()) {
            search(board, row - 1, column, trieNode);
            search(board, row + 1, column, trieNode);
            search(board, row, column - 1, trieNode);
            search(board, row, column + 1, trieNode);
        }

        //回溯时还原stringBuilder、map
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        array[num] = false;
    }

}
