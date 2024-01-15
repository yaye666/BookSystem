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

@WebServlet(name="problem_delete",urlPatterns = "/problem_delete")
public class ProblemDeleteServlet extends HttpServlet {
    private ProblemService pService =new ProblemService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String typeName=request.getParameter("typeName");
        System.out.println("这里是problemdelete");
        Integer pid=Integer.parseInt(request.getParameter("pid"));
        Problem problem=new Problem();
        problem.setPid(pid);

        try {
            pService.deleteProblem(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().print("ok");
        //request.getRequestDispatcher("/type?method=list").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.doGet(request, response);
    }
}
