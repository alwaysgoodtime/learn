package leetcode.src.main.java.array;

/**
 * https://leetcode.cn/problems/length-of-last-word/description/
 * @author goodtime
 * @create 2023-12-04 03:05
 */
public class Number58 {

    public static void main(String[] args) {
        System.out.println(new Solution58().lengthOfLastWord("aaa aaabc"));
    }
    
}

class Solution58 {
    public int lengthOfLastWord(String s) {

        int lastWordEndIndex = -1;
        int lastWordStartIndex = 0;

        for (int i = s.length() - 1 ; i >= 0; i--) {
            if(lastWordEndIndex == -1) {
                if (s.charAt(i) != ' ') {
                    lastWordEndIndex = i;
                }
            }else {
                if(s.charAt(i) == ' '){
                    lastWordStartIndex = i+1;
                    break;
                }
            }
        }

        return lastWordEndIndex == -1 ? 0 : lastWordEndIndex - lastWordStartIndex + 1;
    }
}
