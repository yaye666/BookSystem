package servlet;

import model.Type;
import model.User;
import service.BookService;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet(name="book_borrow",urlPatterns = "/book_borrow")
public class BorrowBookServlet extends HttpServlet {
    private BookService bService = new BookService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid=request.getParameter("bid");
        User user=(User)request.getSession().getAttribute("user");
        System.out.println("这里是BorrowBookServlet");
        System.out.println("用户"+user.getName());
        System.out.println("书号"+Integer.parseInt(bid));
        try {
            bService.borrowBook(user,bid);
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
