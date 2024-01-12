package interview;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-03-25 7:01 下午
 */
public class HUAWEI1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] a = new String[3];

        for (int i = 0; i < 3; i++) {
            a[i] = scanner.next().trim();
        }

        int[] one = new int[4];
        int[] two = new int[4];
        int[] three = new int[4];


        String[] split = a[0].split("\\.");
        for (int j = 0; j < 4; j++) {
            one[j] = Integer.parseInt(split[j]);
        }

        String[] split2 = a[1].split("\\.");
        for (int j = 0; j < 4; j++) {
            two[j] = Integer.parseInt(split2[j]);
        }

        String[] split3 = a[2].split("\\.");
        for (int j = 0; j < 4; j++) {
            three[j] = Integer.parseInt(split3[j]);
        }

        byte tag = 1;

        for (int i = 0; i < 4; i++) {
           one[i] = one[i] & three[i];
           two[i] = two[i] & three[i];
           if(one[i] != two[i]){
               tag = 0;
           }
        }

        StringBuilder stringBuilder = new StringBuilder(tag+" ");
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(one[i]+".");
        }

        System.out.println(stringBuilder.toString().substring(0,stringBuilder.length()-1));

    }
}
