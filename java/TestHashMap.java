///*
//2019年07月17日13:25:18
//目的：测试hashmap容器的一些功能，掌握其知识。
//在郝斌的例子上改写，他讲错了关于Student中hashCode和equals的知识。
//*/
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Set;
////java中，变量全小写，类名用大驼峰命名法（也叫帕斯卡命名法），类中的方法用小驼峰命名法。
//
//class Student {
//    private int id;
//    private String name;
//    private int age;
//
//    public Student() {
//    }
//
//    public Student(int id, String name, int age) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//    }
//
//    // public int hashCode()
//    // {
//    // 	return this.name.hashCode()*id*age;
//    // }值的hashCode方法其实不需要改写，关键的是Key的hashCode方法一定要改写。
//    //一般用string或者数字做Key，就是因为它们的hashCode系统已自动改写。
//    //键也需要考虑改写的问题，一遍最好是final类型的数据。
//
//
//    public boolean equals(Object o) {
//        Student s = (Student) o;
//        return this.name.equals(s.name) && this.id == s.id && this.age == s.age;
//    }
//
//    //这里重写equals方法，是为了方便比较两个键中值是否相等，不改写的话，此方法默认比较的是地址。
//
//    public String toString() {
//        return id + "  " + name + "  " + age;
//    }
//}
//
//public class TestHashMap {
//    public static void main(String[] args) {
//        HashMap hm = new HashMap();
//        hm.put(1001, new Student(1001, "zhangsan", 20));  //1001自动封装成Integer类，put中的两个参数都必须是object。
//        hm.put(1003, new Student(1003, "lisi", 30)); //自动封装
//        hm.put(new Integer(1004), new Student(1004, "wangwu", 10));
//        hm.put(1002, new Student(1002, "baichi", 20)); //自动封装
//
//        //hm.put(1002, new Student(1003, "baichi", 20));这一行会覆盖第44行中Value的值。
//        hm.put(1005, new Student(1002, "baichi", 20));//不同的key中可以存放相同的值。
//
//        //遍历所有的元素
//        System.out.println("hm容器中所有的元素是:");
//        Set s = hm.keySet();  //获取到当前容器键的集合，实际就是Integer对象的集合
//        Iterator it = s.iterator();
//        while (it.hasNext()) {
//            //int Key = (Integer)it.next();   // (Integer) 不能省， 利用了自动拆分技术
//            Integer Key = (Integer) it.next();
//            System.out.println(hm.get(Key));
//        }
//
//        System.out.println("直接查找某一元素");
//        System.out.println(hm.get(1003));
//        System.out.println(hm.get(1006));      //如果找不到 则直接返回null，而不是抛出异常
//        System.out.println("比对不同键中的值");
//        System.out.println(hm.get(1002).equals(hm.get(1005)));
//
//    }
//}
//
///*
//输出结果是：
//--------------------------------
//hm容器中所有的元素是:
//1001  zhangsan  20
//1002  baichi  20
//1003  lisi  30
//1004  wangwu  10
//1002  baichi  20
//直接查找某一元素
//1003  lisi  30
//null
//比对不同键中的值
//true
//--------------------------------
//*/