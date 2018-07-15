package action.Company;

import dao.CompanyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "changeCompanyServlet")
public class changeCompanyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String oName = request.getParameter("oName");
        String cName = request.getParameter("cName");
        String address = request.getParameter("address");
        String cTel = request.getParameter("cTel");

        CompanyDao companyDao = new CompanyDao();
        if (companyDao.changeCompany(oName, cName, address, cTel) != -1)
            request.getRequestDispatcher("/Alert/success.jsp").forward(request, response);
        else
            request.getRequestDispatcher("/Alert/fail.jsp").forward(request, response);
    }
}
