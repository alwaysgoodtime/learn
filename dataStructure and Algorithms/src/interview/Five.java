package interview;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-03-12 7:05 下午
 */
public class Five {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        ArrayList<Integer> target = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            target.add(scanner.nextInt());
        }
    }
}
