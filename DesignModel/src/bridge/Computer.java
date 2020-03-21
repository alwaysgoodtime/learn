package bridge;

/**
 * 抽象电脑
 * @author goodtime
 * @create 2020-03-11 1:40 下午
 */
public abstract class Computer {

    //聚合品牌
    protected Brand brand;

    public Computer(Brand brand){
        this.brand = brand;
    }

    public void info(){
        brand.info();//自带品牌
    }

}

class Desktop extends Computer{


    public Desktop(Brand brand) {
        super(brand);

}

    @Override
    public void info() {
        super.info();
        System.out.println("台式机");
    }
}

class Laptop extends Computer{

    public Laptop(Brand brand) {
        super(brand);
    }

    @Override
    public void info() {
        super.info();
        System.out.println("笔记本");
    }
}