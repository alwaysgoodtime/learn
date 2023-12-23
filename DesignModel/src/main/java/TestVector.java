import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * @author goodtime
 * @create 2023-04-15 08:52
 */
public class TestVector {
    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] args) {
//        while (true) {
//            for (int i = 0; i < 10; i++) {
//                vector.add(i);
//            }

        Integer[] a = new Integer[]{1, 3, 4};

        List<Integer> integers = Arrays.asList(a);

        System.out.println(integers);
        integers.set(1,2);

        System.out.println(integers);


        ArrayList<Integer> integers1 = new ArrayList<>();

//            Thread removeThread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < vector.size(); i++) {
//                        vector.remove(i);
//                    }
//                }
//            });
//            Thread printThread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < vector.size(); i++) {
//                        System.out.println((vector.get(i)));
//                    }
//                }
//            });
//
//            removeThread.start();
//            printThread.start();
//            //不要同时产生过多的线程，否则会导致操作系统假死
//            if (Thread.activeCount() > 20){
//              break;
//            };
    }
}

