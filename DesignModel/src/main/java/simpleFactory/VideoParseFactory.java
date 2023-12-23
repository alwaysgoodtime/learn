package simpleFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author goodtime
 * @create 2023-12-22 21:50
 */
public class VideoParseFactory {

    //简单工厂的实例化方法
    //一般这里是用switch case实现，所以在新增parser时，这里也需要修改，并不符合开闭原则
    //因为这个方法是静态方法，所以也就静态工厂模式
    public static Parser getParser(String name) {

        try {
            //拼接包名
            name = "simpleFactory." + name;

            //利用反射来得到实例
            Constructor<?> declaredConstructor = Class.forName(name).getDeclaredConstructor(null);
            declaredConstructor.setAccessible(true);
            return  (Parser) declaredConstructor.newInstance(null);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

}
