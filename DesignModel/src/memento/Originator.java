package memento;

import mediator.Mediator;

/**
 * @author goodtime
 * @create 2020-03-10 5:49 下午
 */
public class Originator {
    private int vit;//攻击力
    private int def;//防御力

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    //编写一个方法，可以保存一个状态对象Memento

    public Memento saveStateMemento(){
        return new Memento(vit,def);
    }

    //恢复角色的状态
    public void getStateFromeMemento(Memento memento){
        vit = memento.getVit();
        def = memento.getDef();
    }

    @Override
    public String toString() {
        return "Originator{" +
                "vit=" + vit +
                ", def=" + def +
                '}';
    }
}
