package servlet;

import model.Problem;
import service.ProblemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="problem_solve",urlPatterns = "/problem_solve")
public class ProblemSolveServlet extends HttpServlet {
    private ProblemService pService =new ProblemService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String typeName=request.getParameter("typeName");
        System.out.println("这里是problemsolve");
        Integer pid=Integer.parseInt(request.getParameter("pid"));
        System.out.println("pid:"+pid);

        Integer status=Integer.parseInt(request.getParameter("status"));
        System.out.println("status:"+status);
        Problem problem=new Problem();
        problem.setStatus(status);
        problem.setPid(pid);

        try {
            pService.updateProblem(problem);
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
