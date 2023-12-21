package huawei.src.main.java.inputOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author goodtime
 * @create 2023-12-21 20:33
 */
public class Test5 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            List<Integer> list = new ArrayList<>();

            String s = "";
            while (s.equals("")) {
                s = scanner.nextLine();
            }

            int space = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    space++;
                    list.add(i);
                }
            }

            int start = 0;

            List<Integer> result = new ArrayList<>();

            int sum = 0;

            for (int i = 0; i < list.size(); i++) {
                sum += Integer.valueOf(s.substring(start, list.get(i)));
                start = list.get(i) + 1;
            }

            sum += Integer.valueOf(s.substring(list.get(list.size() - 1) + 1));
            result.add(sum);
            System.out.println(sum);

            boolean flag = true;

            while (flag) {

                sum = 0;

                for (int i = 0; i < space + 1; i++) {
                    int num = scanner.nextInt();
                    if (num != 0) {
                        sum += num;
                    } else {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    result.add(sum);
                    System.out.println(sum);
                }
            }

        }
    }
}
