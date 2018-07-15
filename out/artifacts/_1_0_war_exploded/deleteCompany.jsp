<%--
  Created by IntelliJ IDEA.
  User: pu
  Date: 2018/4/9
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>

</script>
<body>
    <jsp:useBean id="company" class="model.Company"/>
    <jsp:setProperty name="company" property="*"/>
    <jsp:getProperty name="company" property="deleteInfo"/>
</body>
</html>
