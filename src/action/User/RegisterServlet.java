package action.User;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {

    public RegisterServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String identify = request.getParameter("identify");
        String uName = request.getParameter("uName");
        String uSex = request.getParameter("uSex");
        String uAge = request.getParameter("uAge");
        String uTel = request.getParameter("uTel");
        String password = request.getParameter("password");
        User user = new User();
        user.setIdentify(identify);
        user.setuName(uName);
        user.setuSex(uSex);
        user.setuAge(uAge);
        user.setuTel(uTel);
        user.setPassword(password);
        String updateInfo = user.getUpdateInfo();
        if (updateInfo.equals("成功"))
        {
            request.getSession().setAttribute("Message","注册成功");
            response.sendRedirect("/UserManager/ShowMessage.jsp");
        }
        else
        {
            request.getSession().setAttribute("Message","注册失败");
            response.sendRedirect("/UserManager/ShowMessage.jsp");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
