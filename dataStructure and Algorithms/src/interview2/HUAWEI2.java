package interview2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-03-25 7:37 下午
 */
public class HUAWEI2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cpu = 0;
        int task = 0;

        cpu = scanner.nextInt();
        task = scanner.nextInt();
        int[] time = new int[task];
        int[] bucket = new int[cpu];

        for (int i = 0; i < task; i++) {
            time[i] = scanner.nextInt();
        }

        Arrays.sort(time);

        int count = 0;

        for (int i = 0; i < time.length; i++) {
            if (count < cpu) {
                bucket[count++] += time[i];
            } else {
                bucket[0] += time[i];
                count = 1;
            }
        }

        count = bucket[0];

        for (int i = 1; i < cpu; i++) {
            if (count < bucket[i]) {
                count = bucket[i];
            }
        }

        System.out.println(count);

    }
}
