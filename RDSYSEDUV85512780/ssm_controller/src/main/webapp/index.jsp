<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>所有学生</title>
</head>
<body>
<%-- 添加学生表单 --%>
<form action="/student/add" method="post">
    下次白班的时间是：${map.dayTime}
    下次晚班的时间是：${map.nightTime}
    下次白加晚的时间是：${map.allDayTime}
    下次休息的时间是：${map.restTime}

    <input type="submit" value="提交">
</form>
</body>
</html>