package servlet;

import model.Book;
import model.Type;
import service.BookService;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="book_update",urlPatterns = "/book_update")
public class BookUpdateServlet extends HttpServlet {
    private BookService bService = new BookService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        String tid=request.getParameter("typeName");
        String num=request.getParameter("num");
        String press=request.getParameter("press");
        System.out.println(bid);
        Book book=new Book();
        book.setTid(Integer.parseInt(tid));
        book.setBid(Integer.parseInt(bid));
        book.setPress(press);
        book.setNum(Integer.parseInt(num));

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try {
            bService.updateBook(book);
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
