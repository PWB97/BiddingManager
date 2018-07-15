<%--
  Created by IntelliJ IDEA.
  User: HiWin10
  Date: 2018/6/5
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title></title>
</head>
<%
    String Message = (String)session.getAttribute("Message");
%>
<body>
<script src="../js/jquery-2.0.3.js"></script><script>
     $(function() {
        alert("<%=Message%>");
        window.location.href = "Login.jsp";
     });
</script>
</body>
</html>
