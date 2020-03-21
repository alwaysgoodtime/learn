package utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类
 * @author goodtime
 * @create 2020-03-14 12:42 上午
 */
@Component
//@Aspect必须写，表示当前类是一个切面类，不加就无法进行AOP
@Aspect
public class LoggerAnno {

    //切入的地方
    @Pointcut("execution(* service.impl.*.*(..))")
    private void pt(){}


    //前置通知
    @Before("pt()")
    public void printlog(){

        System.out.println("Logger类中的printlog方法开始记录日志了前");
    }

    //后置通知
    @AfterReturning("pt()")
    public void printlog1(){
        System.out.println("Logger类中的printlog方法开始记录日志了后");
    }

    //最终通知
    @After("pt()")
    public void printlog2(){
        System.out.println("Logger类中的printlog方法开始记录日志了最终");
    }

    //异常通知
    @AfterThrowing("pt()")
    public void printlog3(){
        System.out.println("Logger类中的printlog方法开始记录日志了异常");
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
