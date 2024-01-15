package servlet;

import model.User;
import service.TypeService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="user_add",urlPatterns = "/user_add")
public class AdminUserAddServlet extends HttpServlet {
    private UserService uService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String typeName=request.getParameter("typeName");
        System.out.println("!!!!!!这里是AdminUserAddServlet");
        String name=request.getParameter("name");
        String phone=request.getParameter("phone");
        String lendNum=request.getParameter("lendNum");
        String maxNum=request.getParameter("maxNum");
        //String uid=request.getParameter("uid");
        String password=request.getParameter("password");
        String role=request.getParameter("role");
        String account=request.getParameter("account");
        System.out.println("!!"+name);
        System.out.println("!!"+account);
        System.out.println("!!"+password);
        System.out.println("!!"+role);
        //System.out.println("!!"+uid);
        System.out.println("!!"+maxNum);
        System.out.println("!!"+lendNum);
        User user=new User();
        user.setName(name);
        user.setPhone(phone);
        user.setLendNum(Integer.parseInt(lendNum));
        //user.setUid(Integer.parseInt(uid));
        user.setMaxNum(Integer.parseInt(maxNum));
        user.setAccount(account);
        user.setPassword(password);
        user.setRole(Integer.parseInt(role));
        try {
            uService.addUser(user);
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
