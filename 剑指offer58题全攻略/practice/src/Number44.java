/**
 * @author goodtime
 * @create 2020-01-26 10:43 上午
 */
public class Number44 {
    public static void main(String[] args) {
        Solution44 solution44 = new Solution44();
        String s = solution44.ReverseSentence(" a    b ");
        System.out.println(s);
    }
}

//本题思路:先把str用" "分割，然后翻转每个单词的位置。
//剑指offer的思路:先把str整体翻转，再依次翻转每个单词。（两次翻转）
class Solution44 {
    public String ReverseSentence(String str) {
        if(str == null || str.trim().equals("")){
            return str;
        }
        String[] s = str.split(" ");
        if(s.length == 0){
            return str;
        }
        String tmp;
        for (int i = s.length-1,j=0; i > j ; i--,j++) {
            tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String a:s
             ) {
            stringBuilder.append(a+" ");

        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(" "));
        return stringBuilder.toString();
    }
}