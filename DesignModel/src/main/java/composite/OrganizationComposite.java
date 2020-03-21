package composite;

/**
 * @author goodtime
 * @create 2020-03-08 1:13 上午
 */
public class OrganizationComposite {
    public static void main(String[] args) {
        String a = "a";
        String b = new String("b");
        String c = "ab";
        String d = "a" + b;
        System.out.println(c.equals(d));
        System.out.println(c == d);
        String d1 = a + "b";
        System.out.println(d1 == c);
        System.out.println(d1 == d);
        String e = a + b;
        System.out.println(e == d);
        System.out.println(e == d1);
        String f = a + b;//注意：e和f也不相等
        System.out.println(e == f);
        System.out.println(e == d);


        Integer g = 127;
        Integer h = Integer.valueOf(127);
        Integer i = Integer.valueOf(127);
        Integer j = new Integer(127);
        System.out.println(i == h);
        System.out.println(i == g);
        System.out.println(h == j);




    }
}
