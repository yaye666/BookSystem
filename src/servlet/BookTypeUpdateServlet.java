package servlet;

import com.sun.glass.ui.CommonDialogs;
import model.Type;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="type_update",urlPatterns = "/type_update")
public class BookTypeUpdateServlet extends HttpServlet {
    private TypeService tService = new TypeService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tid = request.getParameter("tid");
        String typeName=request.getParameter("typeName");

        Type type=new Type();
        type.setTid(Integer.parseInt(tid));
        type.setTypeName(typeName);
        System.out.println(type.getTid());
        System.out.println(type.getTypeName());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try {
            tService.updateType(type);
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
