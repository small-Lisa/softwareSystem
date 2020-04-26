package myservlet.control;
import mybean.data.Login;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HandlePutGoods extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException
	{ 
      super.init(config);
	}
	
	
    public  void  doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		request.setCharacterEncoding("utf8");
		String goods = request.getParameter("goods");
		System.out.println(goods);
		Login loginBean=null;
		HttpSession session=request.getSession(true);
		try{  
			loginBean=(Login)session.getAttribute("loginBean");
            boolean b=loginBean.getPhone()==null||loginBean.getPhone().length()==0;
            if(b)
				response.sendRedirect("login.jsp");//重定向到登录页面
            LinkedList<String> car=loginBean.getCar();
            car.add(goods);
            speakSomeMess(request,response,goods); 
		}catch(Exception exp){
			response.sendRedirect("login.jsp");//重定向到登录页面
		}
	}
	
	public  void  doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException 
	{
      doPost(request,response);
	}
	
	public void speakSomeMess(HttpServletRequest request,HttpServletResponse response,String goods)
	{
        response.setContentType("text/html;charset=utf8");
        try {
			PrintWriter out=response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<div align='center'>");
			out.println("<br><br><br>");
			out.println("<h2>"+goods+"放入购物车</h2>") ;
			out.println("<br><br>查看购物车");
			out.println("<a href =lookShoppingCar.jsp>查看购物车</a>");
			out.println("<br><br>浏览软件");
			out.println("<a href =looksoftware.jsp>浏览软件</a>");
			out.println("</div></body></html>");
        }
        catch(IOException exp){}
	}
	
}