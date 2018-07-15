<%--
  Created by IntelliJ IDEA.
  User: pu
  Date: 2018/4/9
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap-4.0.0.css" rel="stylesheet">
    <style>
        .l1 {
            font-size: large;
        }
    </style>
</head>
<script>
    function checkData(){
        var tmp=document.form1.cName.value;
        if(tmp==null||tmp===""){
            alert("名称不能为空");
            document.form1.cName.focus();
            return;
        }
        tmp=document.form1.cPerson.value;
        if(tmp==null||tmp===""){
            alert("法人不能为空");
            document.form1.cPerson.focus();
            return;
        }
        document.form1.action="do.jsp";
        document.form1.submit();

    }
    function checkName(){
        $.ajax({
            type: "get",
            url: "checkName.jsp",
            data:{ //发送给数据库的数据
                cName:$("#cName").val()
            },
            success: function(data, textStatus){
                $("#nameinfo").html(data);
            }
        });
    }
</script>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="homeTest.jsp">政府招商项目管理系统</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">添加企业 <span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <div class="my-sm-0 navbar-brand">政府工作人员</div>
    </div>
</nav>
<div class="container">
    <h3 class="text-center" style="margin-top: 20px; margin-bottom: 10px">添加或修改企业信息</h3>
    <form id="form1" name="form1" style="margin-left: 50px; margin-right: 50px">
        <div class="form-group">
            <label class="l1">企业名称</label>
            <input id="cName" name="cName" class="form-control" onblur="checkName()">
            <small id="nameinfo" class="form-text text-muted">*</small>
        </div>
        <div class="form-group">
            <label class="l1">企业法人</label>
            <input id="cPerson" name="cPerson" class="form-control">
        </div>
        <div class="form-group">
            <label class="l1">联系方式</label>
            <input id="tel" name="tel" class="form-control">
        </div>
        <div class="form-group">
            <label class="l1">地址</label>
            <input id="address" name="address" class="form-control">
        </div>
        <div class="text-center">
            <button type="submit" class="btn btn-primary" onclick="checkData()">确定</button>
        </div>
    </form>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<%--<script src="js/jquery-3.2.1.min.js"></script>--%>
<script src="jquery-2.0.3.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.0.0.js"></script>
</body>
</html>
