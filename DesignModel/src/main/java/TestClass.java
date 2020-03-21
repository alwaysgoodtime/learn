import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 测试Class类
 * @author goodtime
 * @create 2020-03-02 9:52 下午
 */
class Phone{
    public String xiaoGao = "666";
    protected String xiaoLi;
    String xiaoBai;
}


class Computer extends  Phone{
    private String a = "good";

    public Computer() {
    }

    public Computer(String a) {
        this.a = a;
    }

    public String getA() {

        return a;
    }

    public void setA(String a,String b) {
        System.out.println("haha");
        this.a = a;
    }

    public void setA(){
        System.out.println("嘿嘿");
    }
}

public class TestClass {

    public static void main(String[] args) throws Exception{
        Class clazz = Computer.class;

        Computer o1 = (Computer) clazz.newInstance();

        Method setA = clazz.getDeclaredMethod("setA");//得到名字为setA的方法，且没有参数
        Method setV = clazz.getDeclaredMethod("setA",String.class,String.class);//得到有两个参数的setA方法

        setA.invoke(o1);

        setV.invoke(o1,"huhu","lla");


       // clazz.getField("a");//private属性无法用getField得到，方法也是如此

        clazz.getField("xiaoGao");//父类的属性也可以得到


        //clazz.getDeclaredField("xiaoGao");//但是得不到父类的属性，只能得到本类中声明的所有属性

        System.out.println("=====================");

        Field[] fields = clazz.getFields();
        for (Field a:fields
             ) {
            System.out.println(a.getName());
        }

        System.out.println("======================");

        Field[] fields1 = clazz.getDeclaredFields();

        for (Field a:fields1
        ) {
            System.out.println(a.getName());//只能得到a，父类的私有属性、受保护属性、默认属性都得不到
        }

        Field a = clazz.getDeclaredField("a");//这样可以得到私有属性

        a.setAccessible(true);//让该方法即使是私有的，也可以设置

        a.set(o1,"奥利给");//虽然可以得到私有属性，但是不能设置


        Constructor constructor = clazz.getConstructor();//得到它的构造函数
        System.out.println(o1.getA());

    }
}
