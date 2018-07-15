package action.Bid;

import dao.BiddingDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bidProjectServlet")
public class bidProjectServlet extends HttpServlet {

    public bidProjectServlet() {super();}

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pid = Integer.valueOf(request.getParameter("PID"));
        String uName = request.getSession().getAttribute("username").toString();

        BiddingDao biddingDao = new BiddingDao();
        int i = biddingDao.requestForBidding(uName, pid);
        if (i == 2) {
            response.sendRedirect("/Alert/already.jsp");
        } else {
            request.getRequestDispatcher("/projectDetailServlet").forward(request, response);
        }
    }
}
