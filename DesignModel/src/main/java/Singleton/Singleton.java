package Singleton;

/**
 * @author goodtime
 * @create 2020-03-04 7:13 下午
 */
enum Single {
    SINGLE;
//    SINGLE2("2");测试枚举类用法
    private String name = "haha";
    private Single(){
        }
    public static Single getSingle(){
        return SINGLE;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
//    private Singleton.Single(String string){
//        this.name = string;
//    }

}


class InnerClassSingle{
    private InnerClassSingle(){
        System.out.println("创建了一次");
    };
    public static InnerClassSingle getSingle(){
        return Inner.instance;
    }

    private static class Inner{
        public static final InnerClassSingle instance = new InnerClassSingle();//一定是个全局常量，public static final 线程安全
    }

}

class SingleDouble{
    private static volatile SingleDouble singleDouble;
    private SingleDouble(){};
    public static SingleDouble getSingleDouble(){
        if(singleDouble == null){
            synchronized (SingleDouble.class){
                if(singleDouble == null){
                    singleDouble = new SingleDouble();
                }
            }
        }
        return singleDouble;
    }
}

public class Singleton{
    public static void main(String[] args) {
        Single single = Single.getSingle();
        single.setName("hehiehi");
//        String string = Singleton.Single.SINGLE2.getName();
//        System.out.println(string);
        Single single1 = Single.getSingle();
        System.out.println(single1.getName());
        System.out.println(single.getName());
        InnerClassSingle single2 = InnerClassSingle.getSingle();
        InnerClassSingle single3 = InnerClassSingle.getSingle();

        for (int i = 0;  i < 10; i++) {
            new Thread(() -> {
                InnerClassSingle single5 = InnerClassSingle.getSingle();
            }).start();
        }


    }
}

