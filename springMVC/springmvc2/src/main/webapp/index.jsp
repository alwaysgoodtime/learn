<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!<!doctype html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    你好啊!
    <a href="/user/testString">测试返回值为string，被视图解析器解析成jsp页面返回</a><br/>
    <a href="/user/testVoid">测试返回值为void</a><br/>

    <a href="/user/void1">测试用httpservletresquest进行转发</a>
    <a href="/user/void2">测试用httpservletresponse进行重定向</a>
    <a href="/user/void3">测试用httpservletresponse直接返回值</a>
    <a href="/user/void4">测试用spring的forward转发</a>
    <a href="/user/void4">测试用spring的redirect转发</a>
    <a href="/user/testModelAndView">测试用modelandview处理</a>



</body>
</html>