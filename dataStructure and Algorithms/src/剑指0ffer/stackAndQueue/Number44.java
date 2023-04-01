package 剑指0ffer.stackAndQueue;

import java.util.Stack;

/**
 * @author goodtime
 * @create 2020-01-26 10:43 上午
 */
public class Number44 {
    public static void main(String[] args) {
        Solution44 solution44 = new Solution44();
        String s = solution44.ReverseSentence("nowcoder. a am I");
        System.out.println(s);
    }
}

//本题思路:先把str用" "分割，然后翻转每个单词的位置。
//剑指offer的思路:先把str整体翻转，再依次翻转每个单词。（两次翻转）
class Solution44 {
    public String ReverseSentence(String str) {
        if(str == null || str == ""){
            return str;
        }

        String[] words = str.split(" ");
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = words.length - 1 ; i >= 0; i--) {
            stringBuffer.append(words[i] + " ");
        }


        return stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(" ")).toString();
    }
}
