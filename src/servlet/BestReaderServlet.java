package servlet;

import model.History;
import model.Page;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="bestreader",urlPatterns = "/bestreader")
public class BestReaderServlet extends HttpServlet {
    private UserService uService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        int rank=1;
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
        Page p = uService.getUserPage(pageNumber,rank);
        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p = uService.getUserPage(pageNumber,rank);
            }
        }
        request.setAttribute("p", p);
        User user=(User)request.getSession().getAttribute("user");
        Integer role=user.getRole();
        if(role==2){
            request.getRequestDispatcher("/admin/admin_bestreader.jsp").forward(request, response);
        }else{
            List list=p.getList();
            System.out.println("---------");
            System.out.println(list.get(0).toString());
            System.out.println(list.get(1).toString());
            System.out.println(list.get(2).toString());
            request.getRequestDispatcher("/user/bestreader.jsp").forward(request,response);

        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
