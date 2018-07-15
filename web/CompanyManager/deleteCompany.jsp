<%--
  Created by IntelliJ IDEA.
  User: pu
  Date: 2018/4/9
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>删除</title>
</head>
<body>
    <jsp:useBean id="company" class="model.Company"/>
    <jsp:setProperty name="company" property="*"/>
    <jsp:getProperty name="company" property="deleteInfo"/>
</body>
</html>
