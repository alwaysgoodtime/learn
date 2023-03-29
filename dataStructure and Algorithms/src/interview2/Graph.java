package interview2;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-03-12 8:32 下午
 */
public class Graph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] target = new int[3];
        for (int i = 0; i < 3; i++) {
            target[i] = scanner.nextInt();
        }
        int d = target[0];
        int[] a = new int[target[0]];
        int[][] line = new int[target[0]][target[0]];
        for (int i = 0; i < target[0] ; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i < target[1]; i++) {
            line[scanner.nextInt()-1][scanner.nextInt()-1] = 1;
        }

        for (int i = 0; i < d; i++) {


        }

    }
}
