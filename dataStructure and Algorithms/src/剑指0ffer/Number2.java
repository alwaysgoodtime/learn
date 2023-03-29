/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.
 * 则经过替换之后的字符串为We%20Are%20Happy.
 *
 * @author goodtime
 * @create 2020-01-17 8:20 下午
 */
public class Number2 {
    public static void main(String[] args) {
        StringBuffer str = new StringBuffer();
        str.append("hah ha ha");
        Solution2 solution2ss = new Solution2();
        String s = solution2ss.replaceSpace(str);
        System.out.println(s);

    }

}

/**
 * 解题思路，利用stringBuffer的indexOf方法进行替换即可
 */
class Solution2 {
    public String replaceSpace(StringBuffer str) {

        if (str == null || str.length() == 0) {
            return "";
        }

        for (int i = 0; ; i++) {
            int space = str.indexOf(" ");
            if (space != -1) {
                str.replace(space, space + 1, "%20");
            } else {
                break;
            }
        }
        return str.toString();

    }
}

class Solution2s {
    public String replaceSpace(StringBuffer str) {

        // str.capacity()返回的是StringBuffer的容量，它的length（）方法才是返回其中真实数组的长度
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                str.delete(i, i + 1);
                str.insert(i, "%20");
            }
        }
        return str.toString();
    }
}