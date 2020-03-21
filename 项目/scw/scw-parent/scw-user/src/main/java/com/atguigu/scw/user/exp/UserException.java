package com.atguigu.scw.user.exp;

import com.atguigu.scw.enums.UserExceptionEnum;

/**
 * @author goodtime
 * @create 2020-02-25 12:09 上午
 */
//这里继承运行时异常，是为了不用在事务切面设置时写回滚的异常类，因为默认就是运行时异常
//    自定义异常，一般提供一个空参构造器，一个有参的构造器

public class UserException extends RuntimeException {

    public UserException() {
    }

    public UserException(UserExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
    }
}
