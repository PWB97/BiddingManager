<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>删除</title>
</head>
<script>

</script>
<body>
<jsp:useBean id="news" class="model.News"/>
<jsp:setProperty name="news" property="*"/>
<jsp:getProperty name="news" property="newsDeleteInfo"/>
</body>
</html>