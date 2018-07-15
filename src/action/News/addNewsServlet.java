package action.News;

import common.DBConnection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class addNewsServlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String Title = request.getParameter("newsTitle");
        String message = request.getParameter("content");
        String User = (String) (request.getSession().getAttribute("username"));
        int ZXID = 0;
        try {
            ZXID = Integer.parseInt(request.getParameter("ZXID"));
        } catch (Exception ignored) {}


        try {
            boolean add_result = add(Title, message, User, ZXID);
            if (add_result) {
                response.sendRedirect("/NewsManagerServlet");
            } else {
                response.sendRedirect("/NewsManagerServlet#add");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        doPost(request, response);
    }

    public boolean add(String Title, String Message, String User, int ZXID) throws SQLException {
        DBConnection dbc = new DBConnection();
        Connection conn = dbc.getCon();
        Statement sql = conn.createStatement();
        int m;
        if(ZXID == 0){
         m = sql.executeUpdate("insert into MESSAGE(AUTH, ZXTITLE, ZXMESSAGE, ZXTIME)" +
                "values ('" + User + "','" + Title + "','" + Message + "',sysdate)");
        }
        else {
            m=sql.executeUpdate("update MESSAGE set ZXTITLE='"+Title+"',ZXMESSAGE='"+
            Message+"' where ZXID = "+ZXID);
        }

        boolean add_result;
        add_result = (m != 0);

        return add_result;
    }
}
