package mediator;

/**
 * @author goodtime
 * @create 2020-03-10 5:00 下午
 */
public class Client {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Alarm alarm = new Alarm(mediator);
        CofferMachine cofferMachine = new CofferMachine(mediator);
        alarm.sendMessage(0);//彻底封装，alarm只要传一个参数即可
        alarm.sendMessage(1);

    }
}
