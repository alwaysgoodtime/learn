<%--
  Created by IntelliJ IDEA.
  User: goodtime
  Date: 2020/2/1
  Time: 3:14 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<body>

    <a href="account/findAll">哈哈点我</a>

    <h3>测试保存</h3>

    <form action="account/save" method="post">
        姓名：<input type="text" name="name" />
        金额：<input type="text" name="money"/>
        <input type="submit" value="保存" />
    </form>
</body>
</html>
