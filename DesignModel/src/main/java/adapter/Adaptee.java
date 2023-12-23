package adapter;

/**
 * @author goodtime
 * @create 2023-12-23 04:20
 */
public class Adaptee {

    public String print(String errorName, String error, String time){
        System.out.println(errorName);
        System.out.println(error);
        System.out.println(time);
        return "error";
    }

}
