<%--
  Created by IntelliJ IDEA.
  User: goodtime
  Date: 2020/3/22
  Time: 2:53 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    
    
    <h1>测试文件上传</h1>
    <form action="/file/upload1" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br/>
        <input type="submit" />
    </form>

    <h1>spring方式上传</h1>

    <form action="/file/upload2" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br/>
        <input type="submit" />
    </form>

    <h1>跨服务器文件上传</h1>

    <form action="/file/upload3" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br/>
        <input type="submit" />
    </form>
    
    
    

    <button id="btn">发送ajax请求</button>
    <h1 id="user"></h1>

    <%--这里必须让springmvc放开对静态资源的拦截--%>
    <script src="js/jquery-2.1.1.min.js"></script>
    <script>
        //页面加载，绑定事件,$(document).ready(function(){...})等价于$(function（）{}),表示页面dom元素加载完再执行
        $(function () {
            $("#btn").click(function () {
                $.ajax({
                    //编写json设置，最原始的方法
                    url: "/testAjax",
                    //注意:内容里是application/json，不要写成text/html
                    contentType: "application/json;charset=utf-8",
                    //注意：data整个数据，要用单引号引起来，（总之是单套双、或者双套单）
                    data: '{"username": "hehe", "password": "李雷", "age": "18"}',
                    dataType: "json",
                    type: "post",
                    //返回成功的回调函数
                    success: function (data) {
                        //响应的数据就是data
                        alert(data.username);
                        alert(data.age);
                    }

                })
            })
        })

    </script>
</body>
</html>
