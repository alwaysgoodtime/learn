package interview2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-03-20 7:34 下午
 */
public class Pingduoduo3plus {
    public static void main(String[] args) {

        Scanner a = new Scanner(System.in);
            int length = a.nextInt();
            int count = 1;
            int tmp = 0;
            int[] target = new int[length];
            for (int i = 0; i < length; i++) {
                target[i] = a.nextInt();
            }
            Arrays.sort(target);
        for (int i = 0; i < target.length-1; i++) {
            if(target[i] == target[i+1]){
                tmp++;
            }else {
                tmp = 0;
            }
            count = count > tmp? count : tmp;
        }
        System.out.println(count);

        }
}
