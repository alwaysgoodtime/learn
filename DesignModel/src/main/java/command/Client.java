package command;

/**
 * @author goodtime
 * @create 2023-12-23 23:35
 */
public class Client {

    public static void main(String[] args) {

        Invoker invoker = new Invoker();
        //编辑指令，指定接收者
        DanceCommand danceCommand = new DanceCommand(new WomanRobotReceiver());
        SingSongCommand singSongCommand = new SingSongCommand(new ManRobotReceiver());
        //添加指令
        invoker.add(singSongCommand);
        invoker.add(danceCommand);

        //执行指令
        invoker.execute();

    }

}
