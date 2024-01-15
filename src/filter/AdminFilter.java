package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "AdminFilter",urlPatterns = "/admin/*")
@WebFilter("/*") //所有请求都会被过滤
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //获取请求的路径
        String uri=request.getRequestURI();
        //判断路径是否进行拦截
        if(uri.contains("/login")||uri.contains("/static")||uri.contains("/register")){
            //登录界面和静态资源都需放行
            chain.doFilter(request, response);
        }else {
            User user = (User) request.getSession().getAttribute("user");
            if(user!=null){
                //用户存在则放行
                chain.doFilter(request, response);
            }else{
                response.sendRedirect("login.jsp");
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
