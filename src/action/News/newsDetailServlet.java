package action.News;

import dao.NewsDao;
import model.News;
import model.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CompanyManagerServlet")
public class newsDetailServlet extends HttpServlet {
    public newsDetailServlet(){super();}

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tmp = request.getParameter("ZXID");

        int zxid;
        try {
            //当前新闻序号
            zxid = Integer.valueOf(tmp);
        } catch (NumberFormatException e) {
            zxid = 1;
        }
        NewsDao newsDao = new NewsDao();

        String ZXID=String.valueOf(zxid);
        News news = newsDao.getNewsDetail(ZXID);
        ArrayList newest=newsDao.getNewest();

        request.setAttribute("news_Detail", news);
        request.setAttribute("newest", newest);

        request.getRequestDispatcher("NewsManager/newsDetail.jsp").forward(request, response);
    }

}