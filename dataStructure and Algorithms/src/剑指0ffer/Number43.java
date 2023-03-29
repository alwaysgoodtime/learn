/**
 * @author goodtime
 * @create 2020-01-25 1:56 上午
 */
public class Number43 {
    public static void main(String[] args) {
        Solution43plus solution43 = new Solution43plus();
        String abcde = solution43.LeftRotateString("abcde", 2);
        System.out.println(abcde);

    }
}
//简单思路是:把string转为char数组，n为几，就遍历几次数组，每次遍历，都把第一个元素取出，后面元素前移，然后在末尾加入第一个元素。
//优化:
class Solution43 {
    public String LeftRotateString(String str, int n) {
        if(str == null){
            return "";
        }
        if (str.length() == 0){
            return "";
        }
        if (str.length() >= n) {
            for (int i = 0; i < n; i++) {
                char c = str.charAt(i);
                str = str + c;
            }
            return str.substring(n);
        }//主判断逻辑单元

//        if (str.length() >= n) {
//            str += str;
//            return str.substring(n,n+str.length()/2);
//            }
        //另一种判断逻辑，更加简洁，但和我的代码一样，空间复杂度为O(n),且用了内置的函数，
        // 下面的Solution43plus是三次翻转，更加省空间
        //测试用例里面，超过长度的是直接返回空的,如果想要保证n超过length仍然继续反转，可以用n = n% str.length;
        return "";
    }
}


//三次翻转:这道题考的核心是应聘者是不是可以灵活利用字符串翻转。
//假设字符串abcdef，n=3，设X=abc，Y=def，所以字符串可以表示成XY，如题干，问如何求得YX。
//假设X的翻转为XT，XT=cba，同理YT=fed，那么YX=(XTYT)T，三次翻转后可得结果。
//这种方式的好处是，可以多省一个string的空间，不过用java，还是需要用stringbuilder和char[]，并不能减少特别多空间
class Solution43plus {
    public String LeftRotateString(String str, int n) {
        if(str == null){
            return "";
        }
        if (str.length() == 0){
            return "";
        }
        if (str.length() >= n) {
            char[] a = str.toCharArray();
        reverse(0,n-1,a);
        reverse(n,a.length-1,a);
        reverse(0,a.length-1,a);
        StringBuilder b = new StringBuilder();
        for (char s:a){
            b.append(s);
        }
        String rt = b.toString();
        return rt;
        }
        return "";
    }

    private void reverse(int start,int end,char[] str){
        char tmp = 0;
        for (int i = start,j = end; i < j; i++,j--) {
            tmp = str[j];
            str[j] = str[i];
            str[i] = tmp;
        }
    }
}
