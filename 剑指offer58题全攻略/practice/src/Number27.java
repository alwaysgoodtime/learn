
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * JZ38 字符串的排列
 *
 * @author goodtime
 * @create 2020-01-20 7:26 下午
 */
public class Number27 {
    public static void main(String[] args) {
        Solution27s solution27 = new Solution27s();
        ArrayList<String> abc = solution27.Permutation("abc");
        System.out.println(abc);

    }
}

//思路：对于一个字符串，先定第一个字符，然后定第二个字符，不断递归，递归终止后把string放到动态数组中，然后修改
//string，继续进行递归。递归完成后再进行排序与去重。
//关键的语句是：string = string.substring(0,length-str.length());这一句可以保证string这个字符串对象的
//重复使用与修改
class Solution27 {
    ArrayList<String> strings = new ArrayList<>();
    String string = "";
    int length = 0;

    public ArrayList<String> Permutation(String str) {
        if (str == null || str.length() == 0) {
            return strings;
        }
        length = str.length();
        permutation(str);//分治，得到所有的可能的字符串
        //排序模块（这种是分治后排序）
        Comparator<? super String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        strings.sort(comparator);
        //查重模块
        for (int i = 0; i < strings.size() - 1; i++) {
            if (strings.get(i).equals(strings.get(i + 1))) {
                strings.remove(i + 1);
                i--;
            }
        }
        return strings;
    }

    private void permutation(String str) {
        if (str == null || str.length() == 0) {
            strings.add(string);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            string += String.valueOf(str.charAt(i));
            permutation(str.substring(0, i) + str.substring(i + 1, str.length()));
            string = string.substring(0, length - str.length());
        }
    }
}

class Solution27s {
    Set<String> result = new HashSet<>();

    public ArrayList<String> Permutation(String str) {

        if (str == null || str.length() == 0) {
            return new ArrayList<>(result);
        }

        int length = str.length();

        if (length == 1) {
            result.add(str);
            return new ArrayList<>(result);
        }

        permuation("",new StringBuilder(str));

        return new ArrayList<>(result);
    }

    private void permuation(String pre, StringBuilder str) {

        //如果str长度为2，那么就让pre+str与pre+str的反转
        if (str.length() == 2) {
            result.add(str + pre);
            result.add(str.reverse() + pre);
            return;
        }

        //把str转成字符数组,分别让其中的每一个字符作为第一个字母，其他字母组成剩下的字符串
        char[] chars = str.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {

            char aChar = chars[i];

            StringBuilder tmp = new StringBuilder(str);

            tmp.deleteCharAt(i);

            permuation(pre + aChar, tmp);
        }
    }


}