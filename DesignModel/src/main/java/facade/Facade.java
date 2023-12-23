package facade;

/**
 * @author goodtime
 * @create 2023-12-23 18:34
 */
public class Facade {

    SystemA systemA = new SystemA();
    SystemB systemB = new SystemB();

    public Facade() {
    }

    public void wrap() {
        systemA.work();
        systemB.work();
        System.out.println("抄送leader");
    }

}
