package Itertor;

import java.util.Iterator;

/**
 * @author goodtime
 * @create 2020-03-10 1:34 上午
 */
public interface College {

    String getName();

    //增加系的方法
    void add(String name,String desc);


    //返回一个迭代器，遍历
    Iterator getIntertor();
}
