package huawei.src.main.java.inputOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * https://ac.nowcoder.com/acm/contest/5652/H
 * @author goodtime
 * @create 2023-12-21 22:42
 */
public class Test10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){

            int number = scanner.nextInt();

            List<String> result = new ArrayList<>();

            for (int i = 0; i < number; i++) {
                result.add(scanner.next());
            }

            result.sort((o1,o2) -> o1.charAt(0) - o2.charAt(0));

            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i));
                if(i < result.size() - 1){
                    System.out.print(" ");
                }
            }

        }
    }
}
