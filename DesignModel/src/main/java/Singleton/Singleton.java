package Singleton;

/**
 * @author goodtime
 * @create 2020-03-04 7:13 下午
 */
enum Single {
    SINGLE;
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
class Resource {
    private static volatile Resource resource;
    private Resource(){}
    public static Resource getResource() {
        if (resource == null) {
            synchronized (Resource.class) {//这里不能用this，this已经实例化了，this.class也不能用，能用的只是Resource.class
                if (resource == null) {
                    resource = new Resource();
                }
            }
        }
        return resource;
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
        SingleDouble singleDouble = SingleDouble.getSingleDouble();
        SingleDouble singleDouble2 = SingleDouble.getSingleDouble();
        System.out.println(singleDouble == singleDouble2);
        InnerClassSingle single2 = InnerClassSingle.getSingle();
        InnerClassSingle single3 = InnerClassSingle.getSingle();



        for (int i = 0;  i < 10; i++) {
            new Thread(() -> {
                InnerClassSingle single5 = InnerClassSingle.getSingle();
            }).start();
        }


    }
}

