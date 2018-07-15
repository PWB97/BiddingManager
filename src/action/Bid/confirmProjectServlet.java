package action.Bid;

import dao.BiddingDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "confirmProjectServlet")
public class confirmProjectServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String cName = request.getParameter("cName");
        String PName = request.getParameter("PName");

        BiddingDao biddingDao = new BiddingDao();
        biddingDao.confirmProject(cName, PName);

        request.getRequestDispatcher("/BiddingManagerServlet").forward(request, response);
    }
}
