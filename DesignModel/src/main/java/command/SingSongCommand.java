package command;

/**
 * @author goodtime
 * @create 2023-12-23 23:23
 */
public class SingSongCommand implements Command{

    ManRobotReceiver receiver;

    public SingSongCommand(ManRobotReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.singSong();
    }
}
