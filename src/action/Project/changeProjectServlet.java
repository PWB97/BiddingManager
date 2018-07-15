package action.Project;

import dao.ProjectDao;
import model.Project;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class changeProjectServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tmp = request.getParameter("PID");

        Project project;
        int pid;
        try {
            //当前新闻序号
            pid = Integer.valueOf(tmp);
        } catch (NumberFormatException e) {
            pid = 1;
        }
        ProjectDao projectDao = new ProjectDao();

        String PID=String.valueOf(pid);
        project = projectDao.getProjectDetail(PID);
        request.setAttribute("project_Detail", project);
        request.getRequestDispatcher("ProjectManager/changeProject.jsp").forward(request, response);
    }
}
