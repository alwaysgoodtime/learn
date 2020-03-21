package principle;

/**
 * @author goodtime
 * @create 2020-03-07 1:46 下午
 */
public class segregation {
    public static void main(String[] args) {

        C c = new C();
        c.apera1(new Impl1());//这种就是依赖，通过接口Interface1来依赖Impl1
    }
}

interface Interface1{
    void operation1();
    void operation2();
    void operation4();
    void operation3();
}

//A实现了4个方法
class  Impl1 implements Interface1{

    public void operation1() {
        System.out.println("A实现");
    }

    public void operation2() {
        System.out.println("A实现");


    }

    public void operation3() {
        System.out.println("A实现");


    }

    public void operation4() {
        System.out.println("A实现");


    }
}

//B实现了4个方法
class  Impl2 implements Interface1{

    public void operation1() {
        System.out.println("B实现");
    }

    public void operation2() {
        System.out.println("B实现");

    }

    public void operation3() {
        System.out.println("B实现");

    }

    public void operation4() {
        System.out.println("B实现");

    }
}

//C通过接口依赖A的三个方法

class C{
    public void apera1(Interface1 interface1){
        Impl1 a = (Impl1)interface1;
        interface1.operation1();
    }
    public void opera2(Interface1 interface1){
        Impl1 a = (Impl1)interface1;
        interface1.operation1();
    }
    public void opera3(Interface1 interface1){
        Impl1 a = (Impl1)interface1;
        interface1.operation1();
    }
}


//C通过接口依赖A的三个方法

class D{
    public void apera1(Interface1 interface1){
        Impl2 a = (Impl2) interface1;
        interface1.operation1();
    }
    public void opera2(Interface1 interface1){
        Impl2 a = (Impl2)interface1;
        interface1.operation1();
    }
    public void opera4(Interface1 interface1){
        Impl2 a = (Impl2)interface1;
        interface1.operation4();
    }
}


