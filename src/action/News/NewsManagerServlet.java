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

@WebServlet(name = "NewsManagerServlet")
public class NewsManagerServlet extends HttpServlet {

    public NewsManagerServlet() {
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

        ArrayList add_active = new ArrayList(2);
        add_active.add("\"\"");
        add_active.add("\"active\"");
        PageModel pm=new PageModel();
        pm.setCurrentPage(currentPage);
        pm=newsDao.getNewsByPm(pm);
        request.setAttribute("pm_news", pm);
        request.setAttribute("news_Detail", null);
        request.setAttribute("add_active", add_active);
        request.getRequestDispatcher("NewsManager/NewsManager.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
