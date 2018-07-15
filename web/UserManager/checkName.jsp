<%--
  Created by IntelliJ IDEA.
  User: HiWin10
  Date: 2018/5/6
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title></title>
</head>
<body>
    <jsp:useBean id="user" class="model.User"/>
    <jsp:setProperty name="user" property="uName" />
    <jsp:getProperty name="user" property="nameInfo"/>
</body>
</html>
