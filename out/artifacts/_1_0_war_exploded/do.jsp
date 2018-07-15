<%--
  Created by IntelliJ IDEA.
  User: pu
  Date: 2018/4/9
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="jquery-2.0.3.js"></script>
<script>
    $(function() {
        var info = $(document.body).html();
        alert(info.replace(/[\r\n ]/g, ""));
        window.location.href = "CompanyManagerServlet";
    });
</script>
<body>
    <jsp:useBean id="company" class="model.Company"/>
    <jsp:setProperty name="company" property="*"/>
    <jsp:getProperty name="company" property="updateInfo"/>
</body>
</html>
