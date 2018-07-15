package action.Project;

import common.DBConnection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class addProjectServlet extends HttpServlet{
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String Title = request.getParameter("projectTitle");
        String message = request.getParameter("contentProject");
        String User = (String) (request.getSession().getAttribute("username"));
        int PID = 0;
        try {
            PID = Integer.parseInt(request.getParameter("PID"));
        } catch (Exception ignored){}


        try {
            boolean add_result = add(Title, message, User, PID);
            if (add_result) {
                response.sendRedirect("/ProjectManagerServlet");
            } else {
                response.sendRedirect("/ProjectManagerServlet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        doPost(request, response);
    }

    public boolean add(String Title, String Message, String User, int PID) throws SQLException {
        DBConnection dbc = new DBConnection();
        Connection conn = dbc.getCon();
        Statement sql = conn.createStatement();
        int m;
        if (PID == 0) {
            m = sql.executeUpdate("insert into PROJECT(APPLICANT, PNAME, PCONTENT, PTIME)" +
                    "values ('" + User + "','" + Title + "','" + Message + "',sysdate)");
        } else {
            m = sql.executeUpdate("update PROJECT set PNAME='" + Title + "',PCONTENT='" +
                    Message + "' where PID=" + PID);
        }
        boolean add_result;
        add_result = (m != 0);

        return add_result;
    }
}
