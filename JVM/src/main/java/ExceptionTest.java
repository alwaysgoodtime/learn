/**
 * 测试运行时异常处理后是否会中断程序运行
 * @author goodtime
 * @create 2024-01-20 20:46
 */
public class ExceptionTest {
    public static void main(String[] args) {

        try{
            int a = 1 / 0;
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println("program catch error");
        }

        System.out.println("program is over");

    }
}
