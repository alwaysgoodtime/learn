package interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 输入abc，
 * 输出a,b,c
 * a,c,b
 * b,a,c
 * b,c,a
 * c,a,b
 * c,b,a
 * @author goodtime
 * @create 2020-03-03 7:09 下午
 */
public class ABCSort {

    public static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Character> a = new ArrayList<>();
        byte s;
        if (sc.hasNext()) {
            String abc = sc.nextLine();
            char[] chars = abc.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                a.add(chars[i]);
            }
        }

        List<String> result = new LinkedList<>();
        List<Integer> d = new ArrayList<>();
        String rt = "";
        digui(a.size(), result, a, d,rt);
        for (String re : result
        ) {
            System.out.println(re);
        }
    }


    private static void digui(int size, List<String> result, List<Character> a, List<Integer> d,String s) {
        for (int i = 0; i < size; i++) {
            if (d.contains(i)) {
                continue;
            }
            s += a.get(i) + ",";
            d.add(i);
            count++;
            if (count != size) {
                digui(size, result, a, d,s);
            } else {
                result.add(s.substring(0, s.length() - 1));
            }
            d.remove(d.size() - 1);
            s = s.substring(0, s.length() - 2);
            count--;
        }

    }
}
