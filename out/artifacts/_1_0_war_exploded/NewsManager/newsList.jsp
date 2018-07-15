<%--
  Created by IntelliJ IDEA.
  User: miku
  Date: 2018/5/7
  Time: 17:02
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
    <title>行业新闻</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/newsList.css" rel="stylesheet" type="text/css"/>
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
                        <li class="active"><a href="newsListServlet">行业新闻</a>
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
                <div class="list-news">
                    <c:set var="totalPages" value="${requestScope.pm_news.totalPages}"/>
                    <c:set var="currentPage" value="${requestScope.pm_news.currentPage}"/>
                    <c:set var="newest" value="${requestScope.newest}"/>
                    <c:set var="newsList" value="${requestScope.pm_news.newsList}"/>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <c:forEach var="news" items="${newsList}" varStatus="s">
                            <div class="news_info">
                                <h4>
                                    <a href="<c:url value="/newsDetailServlet?ZXID=${news.ZXID}"/>">${news.ZXTITLE}</a>
                                </h4>
                                <p>${news.ZXTIME}</p>
                                <p>${news.ZXMESSAGE}</p>
                                <hr>
                            </div>
                            </c:forEach>
                            <div class="text-center">
                                <nav>
                                    <ul class="pagination myPagination">
                                        <li class="page-item">
                                            <a class="page-link"
                                               href="<c:url value="/newsListServlet?currentPage=1"/>">首页</a>
                                        </li>
                                        <li class="page-item">
                                            <a class="page-link"
                                               href="<c:url value="/newsListServlet?currentPage=${currentPage-1>1?currentPage-1:1}"/>"
                                               aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </li>
                                        <c:forEach begin="1" end="${totalPages}" varStatus="s">
                                            <c:set var="active" value="${s.index == currentPage?'active':''}"/>
                                            <li class="page-item ${active}">
                                                <a class="page-link"
                                                   href="<c:url value="/newsListServlet?currentPage=${s.index}"/>">${s.index}</a>
                                            </li>
                                        </c:forEach>
                                        <li class="page-item">
                                            <a class="page-link"
                                               href="<c:url value="/newsListServlet?currentPage=${currentPage+1<totalPages?currentPage+1:totalPages}"/> "
                                               aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </li>
                                        <li class="page-item">
                                            <a class="page-link"
                                               href="<c:url value="/newsListServlet?currentPage=${totalPages}"/> ">尾页</a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
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
