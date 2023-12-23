package Itertor;

import java.util.Iterator;

/**
 * @author goodtime
 * @create 2020-03-10 1:46 上午
 */
public class InfoCollege implements College{

    public InfoCollege() {
        departments = new Department[5];
        add("大数据","大数据");
        add("java","java");
        add("c++","c++");
    }

    private String name = "信息工程学院";

    private Department[] departments;
    int numOfDepartment = 0;//保存当前数组的对象个数

    @Override
    public String getName() {
        return name;
    }

    @Override
    //还能放两个
    public void add(String name, String desc) {
        Department department = new Department(name,desc);
        departments[numOfDepartment++] = department;
    }

    @Override
    public Iterator getIntertor() {
        return new InfoCollegeIterator(departments);
    }
}
