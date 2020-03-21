package utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类
 * @author goodtime
 * @create 2020-03-14 12:42 上午
 */
public class Logger {

    /**
     * 用于打印日志：计划在切入点（UserServiceImpl中的方法）之前实行，我们要进行织入
     */
    public void printlog(){
        System.out.println("Logger类中的printlog方法开始记录日志了");
    }


    /**
     * 环绕通知
     */
    public Object printlogaround(ProceedingJoinPoint pdj){
        Object rtValue = null;
        try {
            System.out.println("Logger类中的printlog方法开始记录日志了前");
            Object[] args = pdj.getArgs();
            rtValue = pdj.proceed(args);//明确调用业务层方法
        }catch (Throwable e){
            System.out.println("Logger类中的printlog方法开始记录日志了异常");
            e.getCause();
        }
        finally {
            System.out.println("Logger类中的printlog方法开始记录日志了最终");
        }
        System.out.println("Logger类中的printlog方法开始记录日志了后");
        return rtValue;

    }


}
