package action.User;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangeServlet")
public class ChangeServlet extends HttpServlet {
    public ChangeServlet() {
        super();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String Identify = (String)request.getSession().getAttribute("Identify");
        String uName = (String)request.getSession().getAttribute("username");
        String uSex = request.getParameter("Sex");
        String uAge = request.getParameter("Age");
        String uTel = request.getParameter("Tel");
        String password = request.getParameter("passwd");
        User user = new User();
        user.setuName(uName);
        user.setuSex(uSex);
        user.setuAge(uAge);
        user.setuTel(uTel);
        user.setPassword(password);
        user.setIdentify(Identify);
        user.getDeleteInfo();
        String updateInfo = user.getUpdateInfo();
        if (updateInfo.equals("成功")){
            request.getSession().setAttribute("uSex",uSex);
            request.getSession().setAttribute("uAge",uAge);
            request.getSession().setAttribute("uTel",uTel);
            request.getSession().setAttribute("password",password);
            request.getSession().setAttribute("Message","修改成功");
            response.sendRedirect("/UserManager/ChangeMessage.jsp");

        }
        else {
            request.getSession().setAttribute("Message","修改失败");
            response.sendRedirect("/UserManager/ChangeMessage.jsp");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
