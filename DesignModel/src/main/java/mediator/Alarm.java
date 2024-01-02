package mediator;

/**
 * @author goodtime
 * @create 2020-03-10 4:41 下午
 */
public class Alarm extends Colleague {

    private String name = "闹钟";
    private Mediator mediator;



    public Alarm(Mediator mediator){
        this.mediator = mediator;
        mediator.register(this,name);
    }

    public void startAlarm(){
        System.out.println("闹钟响了，等咖啡机开");
    }

    public void stopAlarm(){
        System.out.println("闹钟关了，等咖啡机关");
    }

    @Override
    public void sendMessage(int operation) {
        //发消息给中介，我是谁，我的下一步指令是什么
        //这里不要传咖啡机的名字，因为具体指令的业务是由中介者负责，后期进行改动的时候，如果
        //指定对应的行为发生改变，不用动咖啡机和闹钟，只用修改中介者收到的指令对应的行为即可
        mediator.getMessage(name,operation);
    }

    @Override
    public Mediator getMediator() {
        return this.mediator;
    }
}
