package action.Project;

import dao.ProjectDao;
import model.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProjectManagerServlet")
public class ProjectManagerServlet extends HttpServlet {

    public ProjectManagerServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tmp = request.getParameter("currentPage");
        ProjectDao projectDao = new ProjectDao();
        int currentPage;

        try {
            //当前页数
            currentPage = Integer.valueOf(tmp);
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        PageModel pm=new PageModel();
        pm.setCurrentPage(currentPage);
        pm=projectDao.getProjectByPm(pm);
        request.setAttribute("pm", pm);

        request.getRequestDispatcher("ProjectManager/ProjectManager.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
