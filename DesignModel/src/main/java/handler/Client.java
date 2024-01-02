package handler;

/**
 * @author goodtime
 * @create 2023-12-24 21:56
 */
public class Client {
    public static void main(String[] args) {
        Handler managerHandler = new ManagerHandler();
        Handler bossHandler = new BossHandler();
        Handler ctoHandler = new CTOHandler();
        managerHandler.setNextHandler(bossHandler);
        bossHandler.setNextHandler(ctoHandler);

        managerHandler.handle(500);
        System.out.println("---------------");
        managerHandler.handle(5000);
        System.out.println("---------------");
        managerHandler.handle(50000);

    }
}
