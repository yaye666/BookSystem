package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="information_change",urlPatterns = "/information_change")
public class ChangeUserInformationServlet extends HttpServlet {
    private UserService uService=new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String phone=request.getParameter("phone");
        User user;
        user=(User)request.getSession().getAttribute("user");
        user.setPhone(phone);
        user.setName(name);
        System.out.println("这里是informationServlet");
        System.out.println(name);
        System.out.println(phone);
        try {
            uService.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.getWriter().print("ok");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
