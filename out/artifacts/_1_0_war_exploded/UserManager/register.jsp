<%--
  Created by IntelliJ IDEA.
  User: HiWin10
  Date: 2018/4/23
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/assets/css/Contact-Form-Clean.css">
    <link rel="stylesheet" href="../css/assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="../css/assets/css/Navigation-Clean.css">
    <link rel="stylesheet" href="../css/assets/css/styles.css">
    <meta charset="utf-8">
    <title>注册</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/re.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="contact-clean">
    <form id="form1" name="form1">

        <div class="form-group has-feedback">
            <label for="uName">用户名</label>
            <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                <input id="uName" name="uName" class="form-control" placeholder="请输入用户名" maxlength="20" type="text" onblur="checkName()">
            </div>
            <small id="nameInfo" class="form-text text-muted">*</small>
        </div>
        <div class="form-group">
            <label for="identify">请选择注册身份</label>
            <select class="form-control" name="identify" id="identify">
                <option value="CompanyUser">企业法人</option>
                <option value="GovernmentUser">政府人员</option>
            </select>
        </div>
        <div class="form-group">
            <label for="uSex">性别</label>
            <select class="form-control" id="uSex" name="uSex">
                <option>男</option>
                <option>女</option>
            </select>
        </div>
        <div class="form-group">
            <label for="uAge">年龄：</label>
            <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                <input id="uAge" name="uAge" class="form-control">
            </div>
        </div>
        <div class="form-group has-feedback">
            <label for="password">密码</label>
            <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                <input id="password" name="password" class="form-control" placeholder="请输入密码" maxlength="20" type="password">
            </div>
        </div>
        <div class="form-group has-feedback">
            <label for="uTel">手机号码</label>
            <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>
                <input id="uTel" name="uTel" class="form-control" placeholder="请输入手机号码" maxlength="11" type="text">
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary" onclick="checkData()">注册</button>
        </div>
    </form>

</div>
<script src="../js/jquery.min.js"></script>
<script src="../js/UserManagerJS.js"></script>
</body>
</html>
