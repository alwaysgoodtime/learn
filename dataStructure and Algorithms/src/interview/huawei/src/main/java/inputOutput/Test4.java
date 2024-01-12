package interview.huawei.src.main.java.inputOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author goodtime
 * @create 2023-12-21 20:30
 */
public class Test4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            List<Integer> list = new ArrayList<>();

            while (true) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                if (a != 0 && b != 0) {
                    list.add(a + b);
                } else {
                    break;
                }
            }

            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }
}
