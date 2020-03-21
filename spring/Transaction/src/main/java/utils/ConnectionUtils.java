package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接的工具类，用于从连接池中获取一个连接，并将该连接和线程绑定，保证连接的事物一致性
 * @author goodtime
 * @create 2020-03-13 4:11 下午
 */
@Component
@Scope("singleton")
//它可以是单例的，不同的Transaction，调用它时，用的是同一个ThreadLocal<Connection>，但是ThreadLocal的get方法,是通过
//当前线程来找实例的，所以避免了线程不安全的问题，解决原理和在springmvc中，用threadlocal修饰成员变量来解决线程不安全的问题
//是一样的
//而且关键是，它不能变成多例的，如果是多例的，那么在mapper和在service中获取的ThreadLocal，不是同一个ThreadLocal
//这样的话，就算是同一个线程，不同的ThreadLocal取到的值是不同的。
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    @Autowired
    //我感觉可以自动注入，因为连接池也是单例的
    private DataSource dataSource;

    /**
     * 获取当前线程的连接
     */
    public Connection getThreadConnection(){
        //先从当前线程的ThreadLocalMap中获取连接，看看是否已经有连接
        Connection connection = tl.get();
        //判断是否有连接
        if(connection == null){
            try {
                //没有则从数据连接池中获取连接
                connection = dataSource.getConnection();
                tl.set(connection);
                System.out.println("从数据库获取连接"+connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            return connection;

    }

    /**
     * 该方法的作用，就是把与线程绑定的连接（其实是存在线程的ThreadLocalMap里面的）移除
     */
    public void removeConnection(){
        tl.remove();
    }
}
