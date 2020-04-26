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
				response.sendRedirect("login.jsp");//�ض��򵽵�¼ҳ��
            LinkedList<String> car=loginBean.getCar();
            car.add(goods);
            speakSomeMess(request,response,goods); 
		}catch(Exception exp){
			response.sendRedirect("login.jsp");//�ض��򵽵�¼ҳ��
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
			out.println("<h2>"+goods+"���빺�ﳵ</h2>") ;
			out.println("<br><br>�鿴���ﳵ");
			out.println("<a href =lookShoppingCar.jsp>�鿴���ﳵ</a>");
			out.println("<br><br>������");
			out.println("<a href =looksoftware.jsp>������</a>");
			out.println("</div></body></html>");
        }
        catch(IOException exp){}
	}
	
}