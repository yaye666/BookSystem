package servlet;

import model.Page;
import model.Type;
import model.User;
import service.BookService;
import service.ProblemService;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="feedback",urlPatterns = "/feedback")
public class FeedBackServlet extends HttpServlet {
    private ProblemService pService =new ProblemService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user=(User)request.getSession().getAttribute("user");
        Integer role=user.getRole();
        Integer uid=user.getUid();
        int pageNumber = 1;
        if(request.getParameter("pageNumber") != null) {
            try {
                pageNumber=Integer.parseInt(request.getParameter("pageNumber") ) ;
            }
            catch (Exception e)
            {

            }

        }
        if(pageNumber<=0)
            pageNumber=1;
        Page p = pService.getProblemPage(pageNumber,role,uid);
        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p = pService.getProblemPage(pageNumber,role,uid);
            }
        }
        request.setAttribute("p", p);

        //System.out.println("role:"+role);
        //System.out.println("这里是FeedBack");
        if(role==2){
            request.getRequestDispatcher("/admin/admin_feedback.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("/user/myproblem.jsp").forward(request,response);
        }


    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
