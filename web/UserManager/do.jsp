<%--
  Created by IntelliJ IDEA.
  User: HiWin10
  Date: 2018/5/5
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title></title>
</head>
<script src="../js/jquery-2.0.3.js"></script><script>
    $(function() {
        var info = $(document.body).html();
        alert(info.replace(/[\r\n ]/g, ""));
        window.location.href = "/UserManagerServlet";
    });
</script>

<body>
    <jsp:useBean id="user" class="model.User"/>
    <jsp:setProperty name="user" property="*"/>
    <jsp:getProperty name="user" property="updateInfo"/>
</body>
</html>
