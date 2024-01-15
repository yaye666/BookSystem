package servlet;

import model.History;
import model.User;
import service.BookService;
import service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="book_return",urlPatterns = "/book_return")
public class ReturnBookServlet extends HttpServlet {
    private HistoryService hService = new HistoryService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hid=request.getParameter("hid");
        System.out.println("这里是returnBook");
        System.out.println(hid);
        //User user = (User) request.getSession().getAttribute("user");
        //Integer role=user.getRole();
        try {
            hService.returnBook(hid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().print("ok");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
