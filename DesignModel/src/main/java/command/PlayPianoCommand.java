package command;

/**
 * @author goodtime
 * @create 2023-12-23 23:24
 */
public class PlayPianoCommand implements Command {

    ManRobotReceiver receiver;

    public PlayPianoCommand(ManRobotReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.playPiano();
    }
}
