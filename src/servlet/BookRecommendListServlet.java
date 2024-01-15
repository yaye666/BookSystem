package servlet;

import model.Page;
import model.Type;
import model.User;
import service.BookService;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="recommend",urlPatterns = "/recommend")
public class BookRecommendListServlet extends HttpServlet {
    private BookService bService=new BookService();
    private TypeService tService = new TypeService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //String word=request.getParameter("word");
        //System.out.println("这里是bookListServlet");
        //System.out.println(word);
        int rank=1;
        String word=null;
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
        Page p = bService.getBookPage(pageNumber,word,rank);
        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p = bService.getBookPage(pageNumber,word,rank);
            }
        }
        //用于刷新分类列表
        try {
            List<Type> typeList = tService.queryAll(null,null);
            request.setAttribute("typeList", typeList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //request.setAttribute("word", word);
        request.setAttribute("p", p);
        User user=(User)request.getSession().getAttribute("user");


        Integer role=user.getRole();
        System.out.println("当前用户是"+role);
        //String typename=request.getParameter(request);

        if(role==2){
            request.getRequestDispatcher("/admin/admin_recommend.jsp").forward(request, response);
        }else if(role==1){
            request.getRequestDispatcher("/user/recommend11.jsp").forward(request,response);
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
