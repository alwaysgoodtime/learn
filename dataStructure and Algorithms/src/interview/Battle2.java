package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-16 7:38 下午
 */
public class Battle2 {

    public static void main(String[] args) {
        int[] cards = {7, 10, 12, 0, 0};
        boolean b = check5Link(cards);
        System.out.println(b);
    }


    public static boolean check5Link(int[] cards) {

        int count = 14;
        int countking = 0;//王的个数
        List<Integer> save = new ArrayList();

        for (int i = 0; i < 5; i++) {
            int tmp = cards[i] % 100;
            save.add(tmp);
            if (tmp < count) {
                if (tmp == 0) {
                    countking++;
                } else {
                    count = tmp;
                }
            }
        }

        for (int i = 0; countking >= 0 && i < 4; i++) {
            count++;
            if (save.contains(count)) {
                continue;
            }
            countking--;
        }

        return countking >= 0;
    }
}
