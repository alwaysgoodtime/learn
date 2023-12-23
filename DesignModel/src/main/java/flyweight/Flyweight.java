package flyweight;

/**
 * @author goodtime
 * @create 2023-12-23 19:42
 */
public class Flyweight {

    //内部状态，共享
    private final String name;

    public Flyweight(String name) {
        this.name = name;
    }

    //可以这样理解，Flyweight本来有name和value两个属性，其中name只有固定名字，
    public void doSomething(int value) {
        System.out.println(value);
    }

}

