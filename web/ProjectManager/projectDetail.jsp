<%--
  Created by IntelliJ IDEA.
  User: miku
  Date: 2018/5/31
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragrma","no-cache");
    response.setDateHeader("Expires",0);
%>
<%@ page import="model.Project" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:set var="project" value="${requestScope.project_Detail}"/>
    <title>${project.PNAME}</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/newsList.css" rel="stylesheet">
    <link href="../css/animate.css" rel="stylesheet">
    <link href="../css/changeColor.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-xs-6">
                <img src="../images/投资.png">
                <h3>政府招商网</h3>
            </div>
            <div class="col-lg-6 col-xs-6">
                <ul class="nav nav-pills">
                    <li>
                    <%
                        String username = (String)session.getAttribute("username");
                        String Identify = (String)session.getAttribute("Identify");
                        if (username==null) {
                            out.print("<a href='/UserManager/Login.jsp'>登陆</a>");
                        } else {
                            if (Identify.equals("CompanyUser"))
                                out.print("<a href='/LogoutServlet'>" + username + "</a>");
                            else
                                response.sendRedirect("/newsListServlet");
                        }
                    %>
                    </li>
                    <li><a href='newsListServlet' rel='dropmenu56'>行业新闻</a></li>
                    <li class='active'><a href='projectListServlet'>项目资讯</a></li>
                    <li><a href='#'>联系我们</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>

<div class="other-slide">
    <img src="../images/news-slide.jpg" alt="最新新闻，最快送达">
</div>

<div class="position">
    <div class="container">
        <span class="position-home"></span>
        <a href='newsListServlet'>行业新闻</a>
    </div>
</div>
<div id="content" class="news-view-container">
    <div class="container news-container">
        <div class="row">
            <div class="col-lg-12 col-xs-12">
                <div class="nav-news">
                    <ul>
                        <li><a href="newsListServlet">行业新闻</a>
                            <div class="triangle1"></div>
                            <div class="triangle2"></div>
                        </li>
                        <li  class="active"><a href="projectListServlet">项目资讯</a>
                            <div class="triangle1"></div>
                            <div class="triangle2"></div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-8 col-xs-8">
                <div class="news-box">
                    <div class="view">
                        <h1>${project.PNAME}</h1>
                        <p class="date"> 发布时间： ${project.PTIME}</p>
                        <div class="pagebox">
                            <p style="font-size: 14px;">
                                ${project.PCONTENT} </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-xs-4">

                <div class="right-hot-box">
                    <%
                        Project project = (Project) request.getAttribute("project_Detail");
                        if (project.isProjectBidden())
                                out.print("<h1 class='right-box-title'>该项目已被如下公司中标</h1><ul><li><a href='#'><h3>"+project.getCompanyInProject().get(0)+"</h3></a></li></ul>");
                            else {
                                out.print("<h1 class='right-box-title'>已参与公司：</h1><ul>");
                                for (int i = 0; i < project.getCompanyInProject().size(); i++) {
                                    out.print("<li><a href='#'><span>"+(i+1)+"</span><h3>"+project.getCompanyInProject().get(i)+"</h3></a></li>");
                                }
                                out.print("</ul><br><div class='text-center'><a class='btn btn-my' href='");
                                if (username == null) {
                                    out.print("/UserManager/Login.jsp");
                                } else {
                                    if (Identify.equals("CompanyUser")) {
                                        String pid = request.getParameter("PID");
                                        out.print("/bidProjectServlet?PID="+Integer.valueOf(pid));
                                    }
                                    else
                                        response.sendRedirect("/projectListServlet");
                                }
                            out.print("'>我要申请</a></div>");
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
