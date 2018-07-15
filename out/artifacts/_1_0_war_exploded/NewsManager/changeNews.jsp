<%--
  Created by IntelliJ IDEA.
  User: pu
  Date: 2018/5/3
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragrma","no-cache");
    response.setDateHeader("Expires",0);
%>
<html>
<head>
    <title>政府招商管理系统</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- bootstrap 3.0.2 -->
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- font Awesome -->
    <link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <!-- Ionicons -->
    <link href="../css/ionicons.min.css" rel="stylesheet" type="text/css"/>
    <!-- Morris chart -->
    <link href="../css/morris/morris.css" rel="stylesheet" type="text/css"/>
    <!-- jvectormap -->
    <link href="../css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css"/>
    <!-- Date Picker -->
    <link href="../css/datepicker/datepicker3.css" rel="stylesheet" type="text/css"/>
    <!-- fullCalendar -->
    <!-- <link href="css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" /> -->
    <!-- Daterange picker -->
    <link href="../css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
    <!-- iCheck for checkboxes and radio inputs -->
    <link href="../css/iCheck/all.css" rel="stylesheet" type="text/css"/>
    <!-- bootstrap wysihtml5 - text editor -->
    <!-- <link href="css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" /> -->
    <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <!-- Theme style -->
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
    <link href="../css/changeColor.css" rel="stylesheet">
</head>
<body class="skin-black">
<!-- header logo: style can be found in header.less -->
<header class="header">
    <a href="#" class="logo">
        政府招商管理系统
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>
        <div class="navbar-right">
            <ul class="nav navbar-nav">
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-user"></i>
                        <span>
                            <%
                                String username = (String)session.getAttribute("username");
                                String Identify = (String)session.getAttribute("Identify");
                                if (Identify.equals("ManagerUser") || Identify.equals("GovernmentUser"))
                                    out.print(username);
                                else
                                    response.sendRedirect("/UserManager/Login.jsp");
                            %>
                            <i class="caret"></i>
                        </span>
                    </a>
                    <ul class="dropdown-menu dropdown-custom dropdown-menu-right">
                        <li class="dropdown-header text-center">Account</li>
                        <li>
                            <a data-toggle="modal" data-target="#myModal">
                                <i class="fa fa-user fa-fw pull-right"></i>
                                Profile
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="${pageContext.request.contextPath}/LogoutServlet"><i class="fa fa-ban fa-fw pull-right"></i> Logout</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><%out.print(username);%></h4>
            </div>
            <form id="form3" name="form3" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="Sex">性别</label>
                        <div class="input-group">
                            <input id="Sex" name="Sex" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Age">年龄</label>
                        <div class="input-group">
                            <input id="Age" name="Age" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Tel">联系方式</label>
                        <div class="input-group">
                            <input id="Tel" name="Tel" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="passwd">密码</label>
                        <div class="input-group">
                            <input id="passwd" name="passwd" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary" onclick="check()">提交更改</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="wrapper row-offcanvas row-offcanvas-left">
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="left-side sidebar-offcanvas">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <div class="user-panel">
                <div class="pull-left image">
                    <%
                        if (Identify.equals("ManagerUser")) out.print("<img src='../images/avatar04.png' class='img-circle' alt='User Image' />");
                        else if (Identify.equals("GovernmentUser")) out.print("<img src='../images/avatar5.png' class='img-circle' alt='User Image' />");
                    %>

                </div>
                <div class="pull-left info">
                    <p><%out.print(Identify);%></p>

                    <a href="#"><i class="fa fa-circle text-success"></i><%out.print(username);%></a>
                </div>
            </div>
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <%
                    if (Identify.equals("ManagerUser")) {
                        out.print("<li><a href='/UserManagerServlet'><i class='fa fa-user'></i> <span>企业用户管理</span></a></li><li><a href='/GovernmentManagerServlet'>" +
                                "                        <i class='fa fa-user'></i> <span>政府人员管理</span></a></li><li><a href='/CompanyManagerServlet'>" +
                                "                        <i class='fa fa-globe'></i> <span>企业管理</span></a> </li><li><a href='/ProjectManagerServlet'><i class='fa fa-envelope'></i> <span>招商信息</span></a></li><li class='active'><a href='/NewsManagerServlet'><i class='fa fa-envelope'></i> <span>新闻信息</span></a></li>"+
                                "                <li><a href='/BiddingManagerServlet'><i class='fa fa-envelope'></i> <span>投标信息</span></a></li>");
                    } else if (Identify.equals("GovernmentUser")){
                        out.print("<li><a href='/ProjectManagerServlet'><i class='fa fa-globe'></i><span>招商信息</span></a></li><li class='active'><a href='/NewsManagerServlet'><i class='fa fa-glass'></i><span>新闻信息</span></a></li><li><a href='/BiddingManagerServlet'><i class='fa fa-glass'></i> <span>投标信息</span></a></li>");
                    }
                %>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <aside class="right-side">
        <!-- Main content -->
        <section class="content">

            <section class="panel general">
                <header class="panel-heading tab-bg-dark-navy-blue">
                    <ul class="nav nav-tabs">

                        <li >
                            <a  href="/NewsManagerServlet">管理</a>
                        </li >
                        <li class="active">
                            <a data-toggle="tab" href="#add" id="add_change">添加</a>
                        </li>
                    </ul>
                </header>
                <div class="panel-body" style="background-color: #f0f3f4">
                    <div class="tab-content">
                        <div id="manager" class="tab-pane active">
                            <div class="row">
                                <section class="panel">
                                    <form action="addNewsServlet" method="post" class="panel-body">
                                        <header class="panel-heading">
                                            <h4>修改新闻
                                                <input  class="btn btn-my pull-right" type="submit" value="提交">
                                            </h4>
                                        </header>
                                        <c:set var="newsDetail" value="${requestScope.news_Detail}"/>
                                        <br>
                                        <br>
                                        <input name="ZXID" type="hidden" value="${newsDetail.ZXID}">
                                        <label class="l1">新闻标题</label>
                                        <input id="newsName"  value="${newsDetail.ZXTITLE}" name="newsTitle" class="form-control">
                                        <label class="l1">新闻内容</label>
                                        <!-- 加载编辑器的容器 -->
                                        <script id="container" name="content" type="text/plain">${newsDetail.ZXMESSAGE}
                                        </script>
                                    </form>

                                </section>
                            </div>
                        </div>
                        <div id="add" class="tab-pane">
                            <div class="row">
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </section>
    </aside><!-- /.right-side -->

</div><!-- ./wrapper -->

<script src="../js/newsJs.js"></script>
<!-- jQuery 2.0.2 -->
<%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>--%>
<script src="../js/jquery.min.js" type="text/javascript"></script>

<!-- jQuery UI 1.10.3 -->
<script src="../js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
<!-- Bootstrap -->
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
<!-- daterangepicker -->
<script src="../js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>

<script src="../js/plugins/chart.js" type="text/javascript"></script>

<!-- datepicker
<script src="js/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>-->
<!-- Bootstrap WYSIHTML5
<script src="js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>-->
<!-- iCheck -->
<script src="../js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
<!-- calendar -->
<script src="../js/plugins/fullcalendar/fullcalendar.js" type="text/javascript"></script>

<!-- Director App -->
<script src="../js/Director/app.js" type="text/javascript"></script>

<!-- Director dashboard demo (This is only for demo purposes) -->
<script src="../js/Director/dashboard.js" type="text/javascript"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="../ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="../ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor('container', {
        initialFrameHeight: 300,
        initialFrameWeight: 100
    });
</script>
<script type="text/javascript">
    $(function () {
        $("change").click(function () {
            $("add_change").trigger("click");
        });
    })
</script>
</body>
</html>