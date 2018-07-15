package action.Bid;

import dao.BiddingDao;
import model.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BiddingManagerServlet")
public class BiddingManagerServlet extends HttpServlet {
    public BiddingManagerServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tmp = request.getParameter("currentPage");
        BiddingDao biddingDao = new BiddingDao();
        int currentPage;

        try {
            //当前页数
            currentPage = Integer.valueOf(tmp);
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        PageModel pm=new PageModel();
        pm.setCurrentPage(currentPage);
        pm=biddingDao.getBiddingByPm(pm);
        request.setAttribute("pm", pm);

        request.getRequestDispatcher("/BiddingManager/BiddingManager.jsp").forward(request, response);
    }
}
