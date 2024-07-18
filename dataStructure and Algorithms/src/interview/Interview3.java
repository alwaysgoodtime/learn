package interview;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author goodtime
 * @create 2024-01-19 10:55
 */
public class Interview3 {

    private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    private static HashMap<String, Integer> outPutMap = new HashMap<>();

    public static void main(String[] args) throws Exception {

        Thread putNumber = new Thread(() -> {
            while (true) {
                int i = new Random().nextInt(100);
                System.out.println(i);
                map.merge("test", i, Integer::sum);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        putNumber.start();

        while (true) {
            Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Integer> next = iterator.next();
                String key = next.getKey();
                Integer value = next.getValue();
                outPutMap.merge(key, value, Integer::sum);
            }

            Iterator<Map.Entry<String, Integer>> iterator1 = outPutMap.entrySet().iterator();


            while (iterator1.hasNext()) {
                Map.Entry<String, Integer> next = iterator1.next();
                System.out.println(next.getKey() + "_" + next.getValue());
            }

            Thread.sleep(5000);
        }


    }

}
