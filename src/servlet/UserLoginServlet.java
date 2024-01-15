package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name="login",urlPatterns = "/login")
public class UserLoginServlet extends HttpServlet {
    private UserService uService=new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        User user=uService.login(account,password);
       // HttpSession s=request.getSession();
        if(user==null) {//登录失败
            request.setAttribute("msg", "用户名、邮箱或者密码错误，请重新登录！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else {//登陆成功跳转index页面
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
