package memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-10 5:53 下午
 */
public class Caretaker {

    private List<Memento> mementos = new ArrayList<>();

    //存储多个备忘录对象,如果有多个Originator，可以做一个hashmap，key为对象，value为一个list，存储对象的各个状态
    public void add(Memento memento){
        mementos.add(memento);
    }

    //获取第index个Originator的备忘录对象
    public Memento get(int index){
        return mementos.get(index);
    }

}
