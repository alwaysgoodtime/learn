import java.util.HashMap;
import java.util.Map;

/**
 * @author goodtime
 * @create 2024-01-21 01:06
 */
public class FinalMethodTest {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
    }
}

class Dog {
    final void eat(){
        System.out.println("eat food");
    }
}
