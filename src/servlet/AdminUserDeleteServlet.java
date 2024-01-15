package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "user_delete",urlPatterns = "/user_delete")
public class AdminUserDeleteServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        uService.delete(Integer.parseInt(uid));
        response.getWriter().print("ok");
       //System.out.println("！！！！！！！！！！！！！！！！！！！！！");
        //request.getRequestDispatcher("/admin/admin_user.jsp").forward(request, response);
        //response.setHeader("refresh", "1");
        /*if(isSuccess) {
            request.setAttribute("msg", "用户删除成功");
        }else {
            request.setAttribute("failMsg", "用户存在借阅订单，暂无法删除");
        }*/
        //request.getRequestDispatcher("/admin/admin_user.jsp").forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
