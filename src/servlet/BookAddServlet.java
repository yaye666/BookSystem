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
import java.util.List;

@WebServlet(name="book_add",urlPatterns = "/book_add")
public class BookAddServlet extends HttpServlet {
    private BookService bService=new BookService();
    //private TypeService tService = new TypeService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeName = request.getParameter("typeName");
        System.out.println("!!!!!!这里是AddServlet");
        System.out.println(typeName);

        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");
        String num = request.getParameter("num");
        String press = request.getParameter("press");
        String tid = request.getParameter("typeName");
        //System.out.println(tid);
        System.out.println(bookName);
        System.out.println(author);
        System.out.println(num);
        System.out.println(press);
        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthor(author);
        book.setTid(Integer.parseInt(tid));
        book.setNum(Integer.parseInt(num));
        book.setPress(press);
        book.setTypeName(typeName);


        try {
            bService.addBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.getWriter().print("ok");
        //request.getRequestDispatcher("/type?method=list").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
