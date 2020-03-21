package Itertor;

import java.util.Iterator;
import java.util.List;

/**
 * @author goodtime
 * @create 2020-03-10 1:56 上午
 */
public class OutputImpl {

    //学院集合
    List<College> collegeList;

    public OutputImpl(List<College> collegeList){
        this.collegeList = collegeList;
    }

    //遍历所有学院，然后输出各学院的系
    public void printCollege(){

        //List本身已经实现了iterable接口
        Iterator<College> iterator = collegeList.iterator();
        while (iterator.hasNext()){
            College next = iterator.next();
            Iterator intertor = next.getIntertor();
            while (intertor.hasNext()){
                intertor.next();
            }
        }
    }



}
