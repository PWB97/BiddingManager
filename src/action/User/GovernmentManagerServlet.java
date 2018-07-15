package action.User;

import dao.GovernmentDao;
import model.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GovernmentManagerServlet")
public class GovernmentManagerServlet extends HttpServlet {

    public GovernmentManagerServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tmp = request.getParameter("currentPage");

        GovernmentDao GovernmentDao = new GovernmentDao();
        int currentPage;

        try {
            //当前页数
            currentPage = Integer.valueOf(tmp);
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        PageModel pm=new PageModel();
        pm.setCurrentPage(currentPage);
        pm=GovernmentDao.getGoverByPm(pm);
        request.setAttribute("pm", pm);

        request.getRequestDispatcher("/GovernmentManager/GovernmentManager.jsp").forward(request, response);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
