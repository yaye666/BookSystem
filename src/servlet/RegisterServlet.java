package servlet;

import dao.UserDao;
import model.Page;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="register",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private UserService uService=new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        String phone=request.getParameter("phone");
        User user=new User();
        user.setRole(1);
        user.setMaxNum(10);
        user.setAccount(account);
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        user.setLendNum(30);
        uService.register(user);
        request.getRequestDispatcher("/login.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
