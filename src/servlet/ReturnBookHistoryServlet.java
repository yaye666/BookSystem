package servlet;

import model.Page;
import model.User;
import service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="history",urlPatterns ="/history" )
public class ReturnBookHistoryServlet extends HttpServlet {
    private HistoryService hService = new HistoryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        User user=(User)request.getSession().getAttribute("user");
        Integer uid=user.getUid();
        Integer role=user.getRole();
        if(request.getParameter("pageNumber") != null) {
            try {
                pageNumber=Integer.parseInt(request.getParameter("pageNumber") ) ;
            }
            catch (Exception e)
            {

            }

        }
        Page p;
        if(pageNumber<=0)
            pageNumber=1;
        //p=hService.getHistoryPage(pageNumber,null,1);
        if(role==2){
            //request.getRequestDispatcher("/admin/admin_borrow.jsp").forward(request, response);
            p = hService.getHistoryPage(pageNumber,null,2);
        }else{
            //request.getRequestDispatcher("/user/history.jsp").forward(request,response );
            p = hService.getHistoryPage(pageNumber,uid,2);
        }


        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                if(role==2){
                    //request.getRequestDispatcher("/admin/admin_borrow.jsp").forward(request, response);
                    p = hService.getHistoryPage(pageNumber,null,2);
                }else{
                    //request.getRequestDispatcher("/user/history.jsp").forward(request,response );
                    p = hService.getHistoryPage(pageNumber,user.getUid(),2);
                }
            }
        }

        request.setAttribute("p", p);


        System.out.println("!!"+role);
        //System.out.println();
        //String typename=request.getParameter(request);

        if(role==2){
            request.getRequestDispatcher("/admin/admin_history.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/user/history.jsp").forward(request,response );
        }



    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
