package huawei.src.main.java.inputOutput;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author goodtime
 * @create 2023-12-21 20:22
 */
public class Test3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            int row = scanner.nextInt();

            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < row; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                list.add(a+b);
            }

            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }

    }
}
