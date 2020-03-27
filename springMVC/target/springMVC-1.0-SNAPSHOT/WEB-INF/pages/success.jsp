<%--
  Created by IntelliJ IDEA.
  User: goodtime
  Date: 2020/3/21
  Time: 8:05 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>成功跳转</title>
</head>
<body>
    ${sessionScope}
    ${requestScope}
<%--注意：取值不加""，直接写msg即可--%>
    ${msg}
    
</body>
</html>
