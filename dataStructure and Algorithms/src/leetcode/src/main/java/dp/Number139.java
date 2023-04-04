package leetcode.src.main.java.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/word-break/
 *
 * @author goodtime
 * @create 2023-04-04 15:40
 */
public class Number139 {
    public static void main(String[] args) {
        ArrayList<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        System.out.println("codel".substring(1));
        System.out.println(new Solution139().wordBreak("leetcode", wordDict));
    }
}

/**
 * 完全背包
 *
 * dp[i]含义：长度为i的字符串，是否可以由wordDict中的字符串表示出来
 *
 * 初始化：dp[0]=true(空字符串可以由任何字符串表示)，dp[i]=false(i != 0 其他长度字符串初始值都视为false)
 *
 * 递推公式：dp[i] = dp[i - word.length()] (当dp[i]为false，且i >= word.length()，且i的最后word.length()长度的字符串可以被word表示)
 *
 * 遍历顺序：先遍历背包，后遍历物品，必须如此遍历，因为本题里求的是排列而非组合，wordDict的排列顺序对dp[i]的值有影响
 *
 */

class Solution139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        if (wordDict == null || wordDict.size() == 0) {
            return false;
        }

        boolean[] dp  = new boolean[s.length()+1];

        //初始化
        dp[0] = true;

        //递推
        for (int i = 1; i <= s.length(); i++) {//背包
            for (int j = 0; j < wordDict.size(); j++) {//物品
                String target = s.substring(0, i);
                String word = wordDict.get(j);
                if (!dp[i] && word.length() <= i && target.substring(i - word.length()).equals(word)) {
                    dp[i] = dp[i - word.length()];
                }

            }
        }

        return dp[s.length()];
    }
}