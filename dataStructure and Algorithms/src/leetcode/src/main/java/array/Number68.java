package leetcode.src.main.java.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/text-justification/
 *
 * @author goodtime
 * @create 2023-12-04 06:28
 */
public class Number68 {
    public static void main(String[] args) {
        String[] words = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        System.out.println(new Solution68().fullJustify(words, 20));
    }
}

/**
 * 1.确定每行的单词
 * 2.确定每行该如何放置空格
 */
class Solution68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }

        ArrayList<String> result = new ArrayList();
        ArrayList<String> row = new ArrayList();
        int length = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() + length <= maxWidth) {
                row.add(words[i]);
                //留一个空格位的长度，不留空格位，单词之间可能放不下空格，题目不允许
                length += words[i].length() + 1;
            } else {
                result.add(handle(row, false, maxWidth));
                row.clear();
                row.add(words[i]);
                length = words[i].length() + 1;
            }
        }

        result.add(handle(row, true, maxWidth));

        return result;
    }

    private String handle(ArrayList<String> row, boolean isFinal, int maxWidth) {

        StringBuilder builder = new StringBuilder();

        //最后一行情形
        if (isFinal) {

            for (int i = 0; i < row.size(); i++) {
                builder.append(row.get(i));
                if (i + 1 != row.size()) {
                    builder.append(" ");
                }
            }

            //补空格
            if (builder.length() < maxWidth) {
                for (int i = builder.length(); i < maxWidth; i++) {
                    builder.append(" ");
                }
            }

            return builder.toString();
        }

        //只有一个单词情形
        if (row.size() == 1) {
            builder.append(row.get(0));
            for (int j = row.get(0).length(); j < maxWidth; j++) {
                builder.append(" ");
            }
            return builder.toString();
        }

        int wordLength = 0;

        for (int i = 0; i < row.size(); i++) {
            wordLength += row.get(i).length();
        }

        int space = maxWidth - wordLength;
        int spaceCount = row.size() - 1;
        int everySpace = space / spaceCount;
        int extra = space % spaceCount;

        //直接放入第一个单词
        builder.append(row.get(0));

        //多单词非最后一行情形，关键在于确定两个单词间空格的长度
        for (int i = 1; i < row.size(); i++) {

            if (extra == 0 || i > extra) {
                for (int j = 0; j < everySpace; j++) {
                    builder.append(" ");
                }
            } else {
                for (int j = 0; j < everySpace + 1; j++) {
                    builder.append(" ");
                }
            }

            builder.append(row.get(i));
        }

        return builder.toString();
    }
}
