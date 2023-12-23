package Itertor;

import com.sun.org.apache.xml.internal.dtm.ref.CoroutineManager;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author goodtime
 * @create 2020-03-10 1:28 上午
 */
public class ComputerCollegeIterator implements java.util.Iterator {

    //这是我们需要Department 是以怎样的方式存放,假设计算机学院是用List存放
    List<Department> departments;

    int count = 0;

    //注入系元素
    public ComputerCollegeIterator(List<Department> departments) {
        this.departments = departments;
    }



    @Override
    public boolean hasNext() {
        return count < departments.size();
    }

    @Override
    public Object next() {
        System.out.println(departments.get(count));
        return  departments.get(count++);
    }

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer action) {

    }
}
