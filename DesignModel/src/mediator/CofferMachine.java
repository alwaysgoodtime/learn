package mediator;

/**
 * @author goodtime
 * @create 2020-03-10 4:51 下午
 */
public class CofferMachine extends Colleague {

    private String name = "咖啡机";
    private Mediator mediator;

    public CofferMachine(Mediator mediator){
        this.mediator = mediator;
        mediator.register(this,name);
    }

    public void startCofferMachine(){
        System.out.println("咖啡机工作，工作结束");
    }

    public void stopCoffeeMachine(){
        System.out.println("咖啡机关了，工作结束");
    }


    @Override
    public void sendMessage(int operation) {
        mediator.getMessage(name,operation);
    }

    @Override
    public Mediator getMediator() {
        return null;
    }
}
