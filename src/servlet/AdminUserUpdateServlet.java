package servlet;

import model.Book;
import model.User;
import service.BookService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="user_update",urlPatterns = "/user_update")
public class AdminUserUpdateServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String typeName=request.getParameter("typeName");
        System.out.println("!!!!!!这里是AdminUserUpdateServlet");
        String name=request.getParameter("name");
        String phone=request.getParameter("phone");
        String lendNum=request.getParameter("lendNum");
        String maxNum=request.getParameter("maxNum");
        String uid=request.getParameter("uid");
        System.out.println(name);
        System.out.println(phone);
        System.out.println(lendNum);
        System.out.println(maxNum);
        System.out.println(uid);
        User user=new User();
        user.setUid(Integer.parseInt(uid));
        user.setName(name);
        user.setPhone(phone);
        user.setLendNum(Integer.parseInt(lendNum));
        user.setMaxNum(Integer.parseInt(maxNum));


        //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try {
            uService.updateUser(user);
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
