<%--
  Created by IntelliJ IDEA.
  User: pu
  Date: 2018/4/9
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登陆</title>
    <link rel="stylesheet" href="../css/assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="../css/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="../css/assets/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="../css/assets/css/Navigation-Clean.css">
    <link rel="stylesheet" href="../css/assets/css/styles.css">
</head>
<body>
    <div>
        <nav class="navbar navbar-light navbar-expand-md navigation-clean">
            <div class="container"><a class="navbar-brand" href="#">登陆</a>
                <div class="collapse navbar-collapse"
                     id="navcol-1">
                    <ul class="nav navbar-nav ml-auto"></ul>
                </div>
            </div>
        </nav>
    </div>
    <div class="login-clean">
        <form id="form1" name="form1">
            <h2 class="sr-only">Login Form</h2>
            <div class="illustration" style="color:rgb(56,66,89);"><i class="icon ion-ios-navigate"></i></div>
            <div class="form-group"><input id="uName" name="uName" class="form-control" placeholder="用户名"></div>
            <div class="form-group"><input id="password" name="password" class="form-control" type="password" placeholder="密码"></div>
            <div class="form-group"><button class="btn btn-primary btn-block" type="submit" style="background-color:rgb(56,66,89);" onclick="LogCheck()">登陆</button></div>
            <div class="form-group text-center"><a class="btn btn-link" href="register.jsp">注册</a></div>
        </form>
    </div>
    <div class="footer-basic">
        <footer>
            <ul class="list-inline">
                <li class="list-inline-item"><a href="${pageContext.request.contextPath}/newsListServlet" style="font-size:26px;">政府招商管理系统</a></li>
            </ul>
        </footer>
    </div>
    <script src="../js/UserManagerJS.js"></script>
    <script src="../css/assets/js/jquery.min.js"></script>
    <script src="../css/assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
