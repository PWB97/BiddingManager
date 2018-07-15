package action.Project;


import dao.ProjectDao;
import model.Project;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CompanyManagerServlet")
public class projectDetailServlet extends HttpServlet {
    public projectDetailServlet(){super();}

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

        request.getRequestDispatcher("ProjectManager/projectDetail.jsp").forward(request, response);
    }

}
