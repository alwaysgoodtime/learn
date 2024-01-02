package Itertor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-10 2:01 上午
 */
public class Client {
    public static void main(String[] args) {

        //第一个例子
        List<College> colleges = new ArrayList<>();
        ComputerCollege computerCollege = new ComputerCollege();
        InfoCollege infoCollege = new InfoCollege();
        colleges.add(computerCollege);
        colleges.add(infoCollege);

        OutputImpl output = new OutputImpl(colleges);

        output.printCollege();

        //第二个例子
        NewContainer newContainer = new NewContainer();
        newContainer.add("good");
        newContainer.add("dream");
        newContainer.add("time");
        newContainer.remove("dream");
        Iterator iterator = newContainer.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        newContainer.add("bad");

        Iterator iterator2 = newContainer.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }

    }
}
