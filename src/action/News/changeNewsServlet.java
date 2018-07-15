package action.News;

import dao.NewsDao;
import model.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "changeNewsServlet")
public class changeNewsServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tmp = request.getParameter("ZXID");
        News news;
        int zxid;
        try {
            //当前新闻序号
            zxid = Integer.valueOf(tmp);
        } catch (NumberFormatException e) {
            zxid = 1;
        }
        NewsDao newsDao = new NewsDao();

        String ZXID=String.valueOf(zxid);
        news = newsDao.getNewsDetail(ZXID);
        request.setAttribute("news_Detail", news);
        request.getRequestDispatcher("/NewsManager/changeNews.jsp").forward(request, response);
    }
}
