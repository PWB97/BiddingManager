package action.News;

import dao.NewsDao;
import model.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "newsListServlet")
public class newsListServlet extends HttpServlet {

    public newsListServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tmp = request.getParameter("currentPage");
        NewsDao newsDao = new NewsDao();
        int currentPage;

        try {
            //当前页数
            currentPage = Integer.valueOf(tmp);
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        PageModel pm=new PageModel();
        pm.setCurrentPage(currentPage);
        pm=newsDao.getNewsListByPm(pm);
        ArrayList newest =newsDao.getNewest();
        request.setAttribute("pm_news", pm);
        request.setAttribute("newest", newest);

        request.getRequestDispatcher("NewsManager/newsList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
