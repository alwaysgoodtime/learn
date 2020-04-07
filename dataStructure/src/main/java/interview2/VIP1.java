package interview2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author goodtime
 * @create 2020-04-03 8:29 下午
 */
public class VIP1 {
    public static void mai(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char[] chars = s.toCharArray();

        Stack stack = new Stack<Character>();//装括号

        byte flag = 0;


        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '}'){
                char peek = (Character) stack.peek();
                if(peek == '{'){
                    stack.pop();
                    continue;
                }else {
                    flag = 1;
                    break;
                }
            }

            if(chars[i] == ']'){
                char peek = (Character) stack.peek();
                if(peek == '['){
                    stack.pop();
                    continue;
                }else {
                    flag = 1;
                    break;
                }
            }

            if(chars[i] == '}'){
                char peek = (Character) stack.peek();
                if(peek == '{'){
                    stack.pop();
                    continue;
                }else {
                    flag = 1;
                    break;
                }
            }

            if(chars[i] == '{' || chars[i] == '[' || chars[i] == '{' ){
                stack.push(chars[i]);
            }

        }

        if(flag == 1 || stack.size() != 0){
            System.out.println(false);
        }else {
            System.out.println(true);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();

        char[] chars = s.toCharArray();

        ArrayList<Character> characters = new ArrayList<>();

        byte flag = 0;

        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '}'){
                int tmp = characters.indexOf('{');
                if(tmp != -1){
                    characters.remove(tmp);
                }else {
                    flag = 1;
                    break;
                }
            }

            if(chars[i] == ']'){
                int tmp = characters.indexOf('[');
                if(tmp != -1){
                    characters.remove(tmp);
                }else {
                    flag = 1;
                    break;
                }
            }

            if(chars[i] == ')'){
                int tmp = characters.indexOf('(');
                if(tmp != -1){
                    characters.remove(tmp);
                }else {
                    flag = 1;
                    break;
                }
            }

            if(chars[i] == '{' || chars[i] == '[' || chars[i] == '(' ){
                characters.add(chars[i]);
            }

        }

        if(flag == 1 || characters.size() != 0){
            System.out.println(false);
        }else {
            System.out.println(true);
        }

    }
}
