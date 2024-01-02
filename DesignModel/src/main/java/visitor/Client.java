package visitor;

/**
 * @author goodtime
 * @create 2020-03-10 1:05 上午
 */
public class Client {
    public static void main(String[] args) {

        //创建评委
        ObjectStructure objectStructure = new ObjectStructure();

        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());


        //成功
        Success success =new Success();
        objectStructure.display(success);

        //失败
        Fail fail = new Fail();
        objectStructure.display(fail);

        //待定
        Wait wait = new Wait();
        objectStructure.display(wait);


        //第二个例子，老板和 PM 作为 visitor 巡视 程序员 与 设计师
        CompanyStructure companyStructure = new CompanyStructure();
        companyStructure.accept(new BossVisitor());
        companyStructure.accept(new PMVisitor());

    }
}
