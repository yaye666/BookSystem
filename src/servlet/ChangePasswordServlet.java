package servlet;

import model.Book;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="password_change",urlPatterns = "/password_change")
public class ChangePasswordServlet extends HttpServlet {
    private UserService uService=new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String OldPassword=request.getParameter("OldPassword");
        String NewPassword=request.getParameter("NewPassword");

        User user;

        boolean flag=false;
        user=(User)request.getSession().getAttribute("user");
        user.setPassword(NewPassword);
        try {
            flag=uService.isPasswordRight(user,OldPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(flag==true){//旧密码正确
            try {

                uService.updateUser(user);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.getWriter().print("ok");
        }else{
            try {
                uService.updateUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.getWriter().print("no");
        }



    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
