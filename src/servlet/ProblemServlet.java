package servlet;

import model.Problem;
import model.User;
import service.ProblemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/problem")
public class ProblemServlet extends HttpServlet {
    private ProblemService pService =new ProblemService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String typeName=request.getParameter("typeName");
        System.out.println("这里是problempost");
        System.out.println("!!!!!");
        request.setCharacterEncoding("UTF-8");
        String title=request.getParameter("title");
        String page=request.getParameter("page");
        String content=request.getParameter("content");
        String link=request.getParameter("link");
        Problem problem=new Problem();
        problem.setContent(content);
        problem.setLink(link);
        problem.setPage(page);
        problem.setTitle(title);
        problem.setStatus(0);//默认未解决
        User user=(User)request.getSession().getAttribute("user");
        Integer uid=user.getUid();
        problem.setUid(uid);
        Date d=new Date();
        problem.setTime(d);//设置当前时间
        try {
            pService.addProblem(problem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //response.getWriter().print("ok");
       // request.getRequestDispatcher("/feedback?method=myplist");
        //response.sendRedirect("");
        request.getRequestDispatcher("/feedback?method=myplist").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.doGet(request, response);
    }
}
