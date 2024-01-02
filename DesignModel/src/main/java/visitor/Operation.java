package visitor;


/**
 * @author goodtime
 * @create 2020-03-10 12:43 上午
 */
public abstract class Operation {

    //得到男性的测评
    public  abstract void  getManResult(Man man);

    //得到女性的测评
    public  abstract void  getWomanResult(Woman man);



}
