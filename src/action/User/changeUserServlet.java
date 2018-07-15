package action.User;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "changeUserServlet")
public class changeUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String oName = request.getParameter("oName");
        String uName = request.getParameter("uName");
        String uSex = request.getParameter("uSex");
        String uAge = request.getParameter("uAge");
        String uTel = request.getParameter("uTel");

        UserDao userDao = new UserDao();
        if (userDao.changeUser(oName, uName, uSex, uAge, uTel) != -1)
            request.getRequestDispatcher("/Alert/success.jsp").forward(request, response);
        else
            request.getRequestDispatcher("/Alert/fail.jsp").forward(request, response);
    }
}
