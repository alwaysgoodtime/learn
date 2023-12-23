package Itertor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-10 1:43 上午
 */
public class ComputerCollege implements College {

    private String name = "计算机学院";

    private List<Department>  departments;

    public ComputerCollege() {
        this.departments = new ArrayList<Department>();
        this.add("haha","hehie");
        this.add("haha","hehie");
        this.add("haeeeee","heeeee");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void add(String name,String desc) {
        departments.add(new Department(name,desc));
    }

    @Override
    public Iterator getIntertor() {
        return new ComputerCollegeIterator(departments);
    }
}
