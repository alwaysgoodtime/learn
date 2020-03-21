import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

/**
 * @author goodtime
 * @create 2020-03-14 1:01 上午
 */
public class Test {
    public static void main(String[] args) {
        //获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("beanAnno.xml");
        UserService service = context.getBean("service", UserService.class);
        service.delete(1);

    }
}
