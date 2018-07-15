<%--
  Created by IntelliJ IDEA.
  User: pu
  Date: 2018/4/9
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>
<body>
    <jsp:useBean id="company" class="model.Company"/>
    <jsp:setProperty property="cName" name="company"/>
    <jsp:getProperty property="nameInfo" name="company"/>
</body>
</html>
