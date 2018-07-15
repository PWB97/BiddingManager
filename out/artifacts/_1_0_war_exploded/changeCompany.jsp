<%--
  Created by IntelliJ IDEA.
  User: pu
  Date: 2018/4/9
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>跳转</title>
</head>
<body>
    <jsp:useBean id="company" class="model.Company"/>
    <jsp:setProperty name="company" property="cName"/>
    <jsp:getProperty name="company" property="deleteInfo"/>
    <%
        response.sendRedirect("addCompany.jsp");
    %>
</body>
</html>
