<%--
  Created by IntelliJ IDEA.
  User: HiWin10
  Date: 2018/5/6
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>删除</title>
</head>
<body>
    <jsp:useBean id="user" class="model.User"/>
    <jsp:setProperty name="user" property="*"/>
    <jsp:getProperty name="user" property="deleteInfo"/>
</body>
</html>
