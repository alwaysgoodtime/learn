
package Itertor;

import java.util.function.Consumer;

/**
 * @author goodtime
 * @create 2020-03-10 1:32 上午
 */
public class InfoCollegeIterator implements java.util.Iterator {

    Department[] department;

    public InfoCollegeIterator(Department[] department) {
        this.department = department;
    }

    private int count = 0;//计数器

    @Override
    public boolean hasNext() {
        return count < department.length && department[count] != null;
    }

    @Override
    public Object next() {
        System.out.println(department[count++]);
        return department[count];
    }

    @Override
    public void remove() {
        //空实现
    }

    @Override
    public void forEachRemaining(Consumer action) {
        //空实现
    }
}