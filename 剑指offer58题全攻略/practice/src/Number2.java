import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-17 8:20 下午
 */
public class Number2
{
    public static void main(String[] args) {
       StringBuffer str = new StringBuffer();
       str.append("hah haha");
        Solution2 solution2 = new Solution2();
        String s = solution2.replaceSpace(str);
        System.out.println(s);
    }

}

class Solution2 {
    public String replaceSpace(StringBuffer str) {
        for (int i = 0;;i++) {
            int space = str.indexOf(" ");
            if(space != -1){
                str.replace(space,space+1,"%20");
            }else{
                break;
            }
        }
        return str.toString();

    }
}



