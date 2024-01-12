package interview;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-03-12 7:11 下午
 */
public class Xiaomei {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] target = new int[4];
        for (int i = 0; i < 4; i++) {
            target[i] = scanner.nextInt();//n,k,l,r
        }
        int k = target[1];
        int l = target[2];
        int r = target[3];
        int n = target[0];
        int count = 0;
        for (int i = k*l; i <= k*r;i = i+k ) {
            digui(n,l,r);
        }

        double i = 10000000007d;
        System.out.println((int)(count2 % i));


    }

    private static double count2;

    private static void digui(int n,int l,int r) {
        if(n == 0){
           count2++;
        }
        for (int i = l; i <= r; i++) {
            digui(n-i,l,r);
        }
    }


}
