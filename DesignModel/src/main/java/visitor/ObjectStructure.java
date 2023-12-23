package visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-10 1:01 上午
 */

//数据结构，管理了很多人
public class ObjectStructure {

    //维护了一个集合
    private List<Person>  person = new LinkedList<>();

    //增加到list，一般不用add，而是attach
    public  void  attach(Person p){
        person.add(p);
    }

    //移除
    public void detach(Person p){
        person.remove(p);
    }

    //显示测评情况
    public void display(Operation operation){
        for (Person p: person
             ) {
            p.accept(operation);
        }
    }

}
