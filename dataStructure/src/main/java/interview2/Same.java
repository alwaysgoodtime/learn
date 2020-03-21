package interview2;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * @author goodtime
 * @create 2020-03-12 7:45 下午
 */
public class Same {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int and = scanner.nextInt();
        int[] a = new int[length];
        HashMap<Integer,Integer> b  = new HashMap<Integer, Integer>(length);
        for (int i = 0; i < length; i++) {
            int i1 = scanner.nextInt();
            a[i] = i1;
            if(b.get(i1) != null){
                b.put(i1,b.get(i1)+1);
            }else {
                b.put(i1,1);
            }
        }
        for (int i = 0; i < length; i++) {
            int tmp = a[i] | and;
            if(tmp != a[i]){
                if(b.get(tmp) != null){
                    b.put(tmp,b.get(tmp)+1);
                }else {
                    b.put(tmp,1);
                }
            }
        }
        int rt = 0;
        Set<Integer> integers = b.keySet();

        for (Integer d:integers
             ) {
            rt = rt < b.get(d) ? b.get(d) : rt;
        }

        System.out.println(rt);

    }
}
