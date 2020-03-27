package com.goodtime.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义一个字符串转换成日期的类，加入到springmvc中的类型转换器中，帮助我们进行string到date的类型转换
 * 注意：前端发过来的都是字符串，所以这里进行转换的时候，都是从字符串进行转换的
 * 注意：它会代替springMVC中的默认日期转换器，不会同时生效
 * @author goodtime
 * @create 2020-03-21 9:51 下午
 */
public class StringToDate  implements Converter<String,Date> {

    public Date convert(String s) {
        Date date = null;
        if(StringUtils.isEmpty(s)){
            throw new RuntimeException("请您输入日期");
        }else {
            DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = dataFormat.parse(s);
            } catch (Exception e) {
                throw new RuntimeException("日期格式不正确");
            }
        }
        return date;
    }
}
