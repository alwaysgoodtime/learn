package leetcode.src.main.java.dictionaryTree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author goodtime
 * @create 2023-12-17 14:59
 */
public class Number211 {
    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("a");
        wordDictionary.addWord("abb");
        System.out.println(wordDictionary.search(".d."));
    }
}

/**
 * @see Number208
 * 不同的是，此次搜索需要支持通配符匹配，用广度遍历即可，即'.'可以匹配所有节点
 */
class WordDictionary {

    class DictionaryNode {
        char c;
        boolean isEnd = false;
        HashMap<Character, DictionaryNode> son = new HashMap<>();

        DictionaryNode() {

        }

        DictionaryNode(char c) {
            this.c = c;
        }
    }

    DictionaryNode rootNode = new DictionaryNode();

    public WordDictionary() {

    }

    public void addWord(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        addWord(word, 0, rootNode);
    }

    private void addWord(String word, int index, DictionaryNode node) {

        char c = word.charAt(index);

        if (node.son.containsKey(c)) {
            if (index != word.length() - 1) {
                addWord(word, index + 1, node.son.get(c));
            } else {
                node.son.get(c).isEnd = true;
            }
            return;
        }

        DictionaryNode son = new DictionaryNode(c);
        node.son.put(c, son);
        if (index != word.length() - 1) {
            addWord(word, index + 1, son);
        } else {
            son.isEnd = true;
        }
    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        return search(word, 0, rootNode);
    }

    private boolean search(String word, int index, DictionaryNode rootNode) {

        char c = word.charAt(index);

        if (rootNode.son.containsKey(c)) {
            if (index == word.length() - 1) {
                return rootNode.son.get(c).isEnd;
            } else {
                return search(word, index + 1, rootNode.son.get(c));
            }
        } else if (c == '.') {

            Iterator<Map.Entry<Character, DictionaryNode>> iterator = rootNode.son.entrySet().iterator();

            boolean isEnd = false;
            boolean canMatch = false;

            while (iterator.hasNext()) {
                Map.Entry<Character, DictionaryNode> next = iterator.next();
                DictionaryNode value = next.getValue();

                if (index == word.length() - 1) {
                    isEnd = isEnd || value.isEnd;
                } else {
                    canMatch = canMatch || search(word, index + 1, value);
                }
            }

            if (index == word.length() - 1) {
                return isEnd;
            } else {
                return canMatch;
            }

        } else {
            return false;
        }

    }
}
