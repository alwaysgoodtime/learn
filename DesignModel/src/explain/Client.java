package explain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        String expStr = getExpStr();//获得表达式
        HashMap<String,Integer> var = getValue(expStr);//var {a = 10, b = 20}
        Calculator calculator = new Calculator(expStr);
        System.out.println(expStr  + "=" + calculator.run(var));

    }

    private static HashMap<String, Integer> getValue(String expStr) throws Exception {
        HashMap<String,Integer> map = new HashMap<>();

        for (char ch:expStr.toCharArray()
             ) {
            if(ch != '+' && ch != '-'){
                System.out.println("请输入"+ch+"的值:");
                int a  = new Scanner(System.in).nextInt();

                map.put(String.valueOf(ch),a);
            }
        }
        return  map;
    }

    private static String getExpStr() throws Exception {
        System.out.println("请输入表达式:");
        return (new BufferedReader(new InputStreamReader(System.in)).readLine());
    }
}

