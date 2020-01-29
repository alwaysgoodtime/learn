import com.sun.org.apache.xalan.internal.lib.ExsltStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-01-22 3:32 下午
 */
public class Number32 {
    public static void main(String[] args) {
        Solution32 solution32 = new Solution32();
        int[] a = {3,32,321};
        String s = solution32.PrintMinNumber(a);
        System.out.println(s);
    }
}

// 第一种：排列组合成最小的数，最暴力的是，找到所有可能的排列组合，然后找到其中最小的数，并返回
// 第二种：让数字第一位最小的数排最前面，如果最高位数相同，再比较下一个数，排好第一个数后，再找其他的数。
// 思路：让数字转成字符串然后比较大小，可以简单地用快排排完字符串，再输出值。
// 重心不在如何排序上，而是编写字符串比较大小（一个好比较器上）。
class Solution32 {
    public String PrintMinNumber(int[] numbers) {
        String[] strings = new String[numbers.length];
        String rt = "";
        for (int j = 0; j < numbers.length; j++) {
            strings[j] = String.valueOf(numbers[j]);
        }
//        写一个字符串比较大小的方法，用快排排完整个字符串数组。
        QuickSort32(0, strings.length - 1, strings);
        for (String s : strings) {
            rt += s;
        }
        return rt;
    }

    public void QuickSort32(int head, int end, String[] strings) {

        if (end - head == 0 || head < 0 || head == strings.length) {
            return;
        }
        String middle = strings[head];//中间值选定为数组第一个值
        out:
        for (int i = head, j = end; i < j; ) {
            while (compareTo(strings[j],middle) > 0) {
                j--;
            }
            strings[i] = strings[j];
            if (j == i) {
                strings[i] = middle;
                QuickSort32(head, j - 1, strings);
                QuickSort32(j + 1, end, strings);
                break;
            }
            while (compareTo(strings[i],middle) <= 0) {
                i++;
                if (j == i) {
                    strings[i] = middle;
                    QuickSort32(head, j - 1, strings);
                    QuickSort32(j + 1, end, strings);
                    break out;
                }
            }
            strings[j] = strings[i];
        }
    }

    public int compareTo(String a,String b){
        int len1 = a.length();
        int len2 = b.length();
        int lim = len1 > len2 ? len2 : len1;
        char v1[] = a.toCharArray();
        char v2[] = b.toCharArray();

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }

        while((len1 > len2 && k < len1-1 && v1[k] == v1[0]) ||(len2 > len1 && k < len2-1 && v2[k] == v1[0])){
            k++;
        }


        if((len1 > len2  && v1[k] < v1[0]) || (len2 > len1 && v2[k] < v2[0])){
                return len2 - len1;
        }

        return len1 - len2;
    }
}





//需要写个新的compare方法,否则用例:
//[3,32,321]
//
//对应输出应该为:
//
//"321323"
//
//实际输出为:
//
//"332321"
//要让321比32小，32比3小。

//[3,332,334]
//
//要输出"3323334"

