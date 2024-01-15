package servlet;

import model.History;
import model.User;
import service.HistoryService;
import service.UserService;
import utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name="endtime_change",urlPatterns = "/endtime_change")
public class ChangeReturnTimeServlet extends HttpServlet {
    private HistoryService hService=new HistoryService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer hid=Integer.parseInt(request.getParameter("hid"));
        System.out.println("这里是延期还书Servlet");
        String endTime= request.getParameter("endTime");
        History history=new History();
        history.setHid(hid);
        System.out.println(hid);
        System.out.println(endTime);
        history.setEndTime(DateUtils.stringToDate(endTime));//该方法由DateUtils提供
        try {
            hService.changeReturnTime(history);
        } catch (SQLException e) {
            e.printStackTrace();
        }

            response.getWriter().print("ok");




    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
