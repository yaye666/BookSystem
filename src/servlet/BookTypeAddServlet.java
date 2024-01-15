package servlet;

import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet(name = "type_add",urlPatterns = "/type_add")
public class BookTypeAddServlet extends HttpServlet {
    private TypeService tService = new TypeService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeName=request.getParameter("typeName");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(typeName);
        System.out.println(typeName);
        try {
            tService.addType(typeName);
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
