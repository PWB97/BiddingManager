package action.User;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String uName = request.getParameter("uName");
        String password = request.getParameter("password");
        User user = new User();
        user.setuName(uName);
        user.setPassword(password);
        String LogInfo1 = user.getLogInfo1();
        if (LogInfo1.equals("登录成功")){
            response.sendRedirect("/newsListServlet");
            String username = user.getuName();
            String uTel = user.getuTel();
            String uSex = user.getuSex();
            String uAge = user.getuAge();
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("uSex",uSex);
            request.getSession().setAttribute("uAge",uAge);
            request.getSession().setAttribute("uTel",uTel);
            request.getSession().setAttribute("password",password);
            request.getSession().setAttribute("Identify","CompanyUser");
        }
        else{
            String LogInfo2 = user.getLogInfo2();
            if (LogInfo2.equals("登录成功")){
                response.sendRedirect("/ProjectManagerServlet");
                String username = user.getuName();
                String uTel = user.getuTel();
                String uSex = user.getuSex();
                String uAge = user.getuAge();
                request.getSession().setAttribute("username",username);
                request.getSession().setAttribute("uSex",uSex);
                request.getSession().setAttribute("uAge",uAge);
                request.getSession().setAttribute("uTel",uTel);
                request.getSession().setAttribute("password",password);
                request.getSession().setAttribute("Identify","GovernmentUser");
            }
            else{
                String LogInfo3 = user.getLogInfo3();
                if (LogInfo3.equals("登录成功")){
                    response.sendRedirect("/UserManagerServlet");
                    String username = user.getuName();
                    String uTel = user.getuTel();
                    String uSex = user.getuSex();
                    String uAge = user.getuAge();
                    request.getSession().setAttribute("username",username);
                    request.getSession().setAttribute("uSex",uSex);
                    request.getSession().setAttribute("uAge",uAge);
                    request.getSession().setAttribute("uTel",uTel);
                    request.getSession().setAttribute("password",password);
                    request.getSession().setAttribute("Identify","ManagerUser");
            }
                else{
                    request.getSession().setAttribute("Message","用户名或密码错误");
                    response.sendRedirect("/UserManager/ShowMessage.jsp");
                }
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
