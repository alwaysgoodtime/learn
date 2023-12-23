package command;

/**
 * @author goodtime
 * @create 2023-12-23 23:24
 */
public class DanceCommand implements Command {

    WomanRobotReceiver receiver;

    public DanceCommand(WomanRobotReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.dance();
    }
}
