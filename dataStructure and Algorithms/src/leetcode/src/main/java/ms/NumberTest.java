package leetcode.src.main.java.ms;

/**
 * @author goodtime
 * @create 2023-04-06 18:07
 */
public class NumberTest {

    int sss = 1;

    public static void main(String[] args) {
        NumberTest numberTest = new NumberTest();
        numberTest.test();
        int num = 1;
        numberTest.test(num);
        System.out.println(numberTest.sss);
        System.out.println(num);
    }

    private void test(int num) {
        num = num + 1;
    }

    void test(){
        sss = 2;
    }

}
