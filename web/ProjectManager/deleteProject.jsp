<%--
  Created by IntelliJ IDEA.
  User: miku
  Date: 2018/6/4
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>删除</title>
</head>
<script>

</script>
<body>
<jsp:useBean id="project" class="model.Project"/>
<jsp:setProperty name="project" property="*"/>
<jsp:getProperty name="project" property="projectDeleteInfo"/>
</body>
</html>
