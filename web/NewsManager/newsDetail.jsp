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
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:set var="news" value="${requestScope.news_Detail}"/>
    <title>${news.ZXTITLE}</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/newsList.css" rel="stylesheet" type="text/css"/>
    <link href="../css/animate.css" rel="stylesheet">
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
                    <li class='active'><a href='newsListServlet' rel='dropmenu56'>行业新闻</a></li>
                    <li><a href='projectListServlet'>项目资讯</a></li>
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
        <a href='${pageContext.request.contextPath}/newsListServlet'>行业新闻</a>
    </div>
</div>
<div id="content" class="news-view-container">
    <div class="container news-container">
        <div class="row">
            <div class="col-lg-12 col-xs-12">
                <div class="nav-news">
                    <ul>
                        <li class="active"><a href="${pageContext.request.contextPath}/newsListServlet">行业新闻</a>
                            <div class="triangle1"></div>
                            <div class="triangle2"></div>
                        </li>
                        <li><a href="projectListServlet">项目资讯</a>
                            <div class="triangle1"></div>
                            <div class="triangle2"></div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-8 col-xs-8">
                <div class="news-box">
                    <div class="view">
                        <h1>${news.ZXTITLE}</h1>
                        <p class="date"> 发布时间： ${news.ZXTIME}</p>
                        <div class="pagebox">
                            <p style="font-size: 14px;">
                                ${news.ZXMESSAGE} </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-xs-4">
                <div class="right-search-box">
                    <form name="formSearch">
                        <input class="search-text" name="q" type="text" placeholder="“搜索相关关键词”"><input type="hidden"
                                                                                                       name="searchtype"
                                                                                                       value="titlekeyword"/>
                        <input class="search-btn" type="submit" value="">
                    </form>
                </div>
                <div class="right-hot-box">
                    <h1 class="right-box-title">最新文章</h1>

                    <c:set var="newest" value="${requestScope.newest}"/>
                    <ul>
                        <c:forEach var="newest" items="${newest}" varStatus="s">
                            <li>
                                <a href="<c:url value="/newsDetailServlet?ZXID=${newest.ZXID}"/>" title="${newest.ZXTITLE}"><span>${s.count}</span>
                                    <h3>${newest.ZXTITLE}</h3></a>
                            </li>
                        </c:forEach>
                    </ul>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
