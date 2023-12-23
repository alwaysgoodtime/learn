package command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goodtime
 * @create 2023-12-23 23:27
 */
public class Invoker {

    private List<Command> commands = new ArrayList();

    public void clear(){
        commands.clear();
    }

    public void add(Command command) {
        commands.add(command);
    }

    public void execute() {
        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).execute();
        }
    }

}
