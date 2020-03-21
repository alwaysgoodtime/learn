package Itertor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-10 2:01 上午
 */
public class Client {
    public static void main(String[] args) {
        List<College> colleges = new ArrayList<>();
        ComputerCollege computerCollege = new ComputerCollege();
        InfoCollege infoCollege = new InfoCollege();
        colleges.add(computerCollege);
        colleges.add(infoCollege);

        OutputImpl output = new OutputImpl(colleges);

        output.printCollege();

    }
}
