<%--如果不加page这一行的utf-8,在html中设置utf-8也是会乱码的--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<>
    <h2>Hello World!</h2>
    <%--请求参数绑定--%>
    <a href="/hello?username=aa&password=bb">aaa</a>

    <a href="/hi?name=aa&password=bb&user.age=18&user.sex=男">测试javabean中有javabean</a>

    <h1>测试requestbody</h1>
<%--    form表单提交，默认为get请求，要显式设置为post--%>

    <form action="/body" method="post">
        姓名<input type="text" name="name" value="请输入姓名"/><br/>
        密码<input type="text" name="password" value="请输入密码"/><br/>
        <input type="submit" value="点我">
    </form>


    <h1>测试bean中的集合和map封装</h1>
    <form action="/hei">
        姓名<input type="text" name="name" value="请输入姓名"/><br/>
        密码<input type="password" name="password" value="请输入密码"/><br/>

        年龄<input type="text" name="userlist[0].age" value="请输入年龄list"/><br/>
        性别<input type="text" name="userlist[0].sex" value="请输入性别list"/><br/>

        年龄<input type="text" name="usermap['sss'].age" value="请输入年龄map"/><br/>
        性别<input type="text" name="usermap['sss'].sex" value="请输入性别map"/><br/>

        <input type="submit">

    </form>

    <h1>测试date等数据的封装，需要我们自定义类型转换器</h1>
    <%-- 日期默认只能写成1994/11/18 12:00:11 的形式 --%>
    <form action="/saveUser">
        年龄<input type="text" name="age" value="请输入年龄"/><br/>
        性别<input type="text" name="sex" value="请输入性别"/><br/>
        日期<input type="text" name="date" value="请输入日期"/><br/>

        <input type="submit" value="点我提交"/>
    </form>

    <a href="/test/Servlet">测试原生servlet</a>

    <a href="/rest/10">测试rest</a>

    <a href="/test/requestheader">测试请求头注解</a><br/>

    <a href="/test/cookieValue">测试cookievalue</a>

    <a href="/test/modelAttribute">测试方法上的modelattribute</a>

    <a href="/test/modelAttribute?age=20">测试方法上的modelattribute,加了age字段</a>

    <a href="/test/sessionattributes">测试sessionattributes</a>

    <a href="/test/getsessionattributes">测试从session中取值</a>

    <a href="/test/deletesessionattributes">测试删除session中的值</a>
</body>
</html>

