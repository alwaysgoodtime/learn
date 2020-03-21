package bridge;

/**
 * @author goodtime
 * @create 2020-03-11 1:44 下午
 */
public class Client {
    public static void main(String[] args) {
        //苹果笔记本

        Computer computer = new Laptop(new Apple());

        computer.info();

        //联想台式机


        Computer computer1 = new Desktop(new Lenovo());

        computer1.info();


    }
}
