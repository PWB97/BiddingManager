package action.Bid;

import dao.BiddingDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "denyApplyServlet")
public class denyApplyServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String cName = request.getParameter("cName");

        BiddingDao biddingDao = new BiddingDao();
        biddingDao.denyApply(cName);

        request.getRequestDispatcher("/BiddingManagerServlet").forward(request, response);
    }
}
