/**
 * @author goodtime
 * @create 2024-01-20 22:40
 */
public class ConstructorTest {
    public static void main(String[] args) {
        Animal animal = new Cat();
        animal.setName("test");
        System.out.println(animal.name);
    }
}


class Animal {

    String name = "animal";

    public Animal() {
    }

    public void setName(String name) {
        System.out.println("我执行了");
    }
}

class Cat extends Animal {

    String name = "child";

    public Cat() {
    }

    @Override
    public void setName(String name) {
        System.out.println("孩子执行了");
    }
}
