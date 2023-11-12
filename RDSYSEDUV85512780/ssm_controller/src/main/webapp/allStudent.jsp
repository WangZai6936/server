<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>所有学生</title>
</head>
<body>
<%-- 添加学生表单 --%>
<form action="/student/add" method="post">
    姓名：<input name="name">
    性别：<input name="sex">
    地址：<input name="address">
    <input type="submit" value="提交">
</form>
<%-- 展示学生表格 --%>
<table width="500" cellpadding="0" cellspacing="0" border="1" align="center">
    <tr>
        <th>id</th>
        <th>姓名</th>
        <th>性别</th>
        <th>地址</th>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.sex}</td>
            <td>${student.address}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
