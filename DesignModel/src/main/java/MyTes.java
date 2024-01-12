/**
 * @author goodtime
 * @create 2023-04-14 19:13
 */
public class MyTes {
    public static void main(String[] args) {
        String a = "aaa";
        String b = "aaa";
        String c = new String("aaa");
        System.out.println(a.equals("aaa"));
        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
        System.out.println(a.equals("aaa"));
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a == "aaa");
        a.hashCode();
        String eeee = new String("\uD834\uDD1E");
        System.out.println(eeee.length());
        System.out.println(eeee.codePointCount(0, eeee.length()));
        System.out.println(eeee);

    }


}

class Cat {
    public Cat(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

