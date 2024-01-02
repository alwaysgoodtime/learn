package leetcode.src.main.java.dictionaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * https://leetcode.cn/problems/implement-trie-prefix-tree/
 *
 * @author goodtime
 * @create 2023-12-17 13:40
 */
public class Number208 {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("applegd");
        trie.delete("applegd");

        System.out.println(trie.search("apple"));
        System.out.println(trie.search("applegd"));
    }
}

/**
 * 参考https://zhuanlan.zhihu.com/p/420663173
 */
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


    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        return search(word, 0, trieNode);
    }

    private boolean search(String word, int index, TrieNode trieNode) {

        char c = word.charAt(index);


        if (trieNode.son.containsKey(c)) {
            if (index == word.length() - 1) {
                return trieNode.son.get(c).isEnd;
            } else {
                return search(word, index + 1, trieNode.son.get(c));
            }
        }


        return false;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }

        return startsWith(prefix, 0, trieNode);
    }

    private boolean startsWith(String prefix, int index, TrieNode trieNode) {

        char c = prefix.charAt(index);


        if (trieNode.son.containsKey(c)) {
            if (index == prefix.length() - 1) {
                return true;
            } else {
                return startsWith(prefix, index + 1, trieNode.son.get(c));
            }
        }

        return false;

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
            }else {
                deleteSonFlag = false;
            }

            lastChar = poll.c;
        }

    }
}
