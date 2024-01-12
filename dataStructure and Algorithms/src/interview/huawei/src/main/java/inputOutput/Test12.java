package interview.huawei.src.main.java.inputOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://ac.nowcoder.com/acm/contest/5652/J
 * @author goodtime
 * @create 2023-12-21 22:51
 */
public class Test12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){


            String line = scanner.nextLine();

            List<String> result = new ArrayList<>();

            String[] s = line.split(",");

            for (int i = 0; i < s.length; i++) {
                result.add(s[i]);
            }

            result.sort((o1,o2) -> o1.charAt(0) - o2.charAt(0));

            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i));
                if(i < result.size() - 1){
                    System.out.print(",");
                }
            }

            System.out.println();

        }
    }
}
