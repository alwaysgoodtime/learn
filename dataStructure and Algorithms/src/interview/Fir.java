package interview;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-03-12 7:17 下午
 */
public class Fir {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Integer integer = Integer.valueOf(s);
        char[] chars = new char[integer];
        char[] chars2 = new char[integer];
        if(scanner.hasNextLine()) {
            String s1 = scanner.nextLine();
            chars = s1.toCharArray();
        }
        if (scanner.hasNextLine()) {
            String s2 = scanner.nextLine();
            chars2 = s2.toCharArray();
        }

        recur(integer,chars,chars2,1);

        System.out.println(count == 0 ? -1 : count);

    }

    static int count = 0;

    private static void recur(Integer integer, char[] chars, char[] chars2,int i) {
        if(integer == 1){
            if(i == 0){
                count++;
            }
        }else{
        if(i == 1){
            if(chars2[integer-2] =='.'){
                recur(integer-1,chars,chars2,i);
            }
            if(chars[integer - 2] == '.'){
                i = 0;
                recur(integer-1,chars,chars2,i);
            }
        }
        else{
            if(chars[integer - 2] == '.'){
                recur(integer-1,chars,chars2,i);
            }
            if(chars2[integer-2] =='.'){
                i = 1;
                recur(integer-1,chars,chars2,i);
            }

        }
        }

    }
}
