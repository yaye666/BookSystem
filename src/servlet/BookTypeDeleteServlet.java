package servlet;

import service.TypeService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "type_delete",urlPatterns = "/type_delete")
public class BookTypeDeleteServlet extends HttpServlet {
    private TypeService tService = new TypeService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tid = request.getParameter("tid");
        try {
            tService.delete(Integer.parseInt(tid));
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
