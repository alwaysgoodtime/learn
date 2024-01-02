package memento;

/**
 * @author goodtime
 * @create 2020-03-10 5:55 下午
 */
public class Client {
    public static void main(String[] args) {

        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setDef(10);
        originator.setVit(10);

        System.out.println(originator);

        //保存当前状态
        caretaker.add(originator.saveStateMemento());

        //继续更新
        originator.setDef(100);
        originator.setVit(100);

        caretaker.add(originator.saveStateMemento());

        System.out.println(originator);

        //得到状态1，并把Originator恢复状态1
        originator.getStateFromMemento(caretaker.get(0));

        System.out.println(originator);


    }
}
