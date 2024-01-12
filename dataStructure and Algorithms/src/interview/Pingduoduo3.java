package interview;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-03-20 7:34 下午
 */
public class Pingduoduo3 {
    public static void main(String[] args) {

        Scanner a = new Scanner(System.in);
            int length = a.nextInt();
            int max = 0;

            int[] target = new int[length];
            for (int i = 0; i < length; i++) {
                target[i] = a.nextInt();
                max = target[i] > max ? target[i] : max;
            }


            int[] bucket = new int[max + 1];
            for (int i = 0; i < length; i++) {
                bucket[target[i]]++;
            }
            for (int i = bucket.length-1; i >= 1; i--) {
               bucket[i - 1] = bucket[i] > bucket[i - 1] ? bucket[i] : bucket[i - 1];
            }

            System.out.println(bucket[0]);
        }
}
