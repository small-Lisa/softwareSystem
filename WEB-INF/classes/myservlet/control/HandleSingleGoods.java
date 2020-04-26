package myservlet.control;
import mybean.data.Login;
import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HandleSingleGoods extends HttpServlet{
	
	public void init(ServletConfig config)throws ServletException
	{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e){}
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		request.setCharacterEncoding("utf8");
		String singleGoodsMess=request.getParameter("singleGoods");
		if(singleGoodsMess==null||singleGoodsMess.length()==0){
			PrintWriter out=response.getWriter();
			out.println(singleGoodsMess);
			fail(request,response,"没有物品，无法生成订单！");
			return;
		}
		String singleprice=request.getParameter("singleprice");
		if(singleprice==null||singleprice.length()==0){
			fail(request,response,"没有价格，无法生成订单！");
			return;
		}
		float singlesum=Float.parseFloat(singleprice);
		Login loginBean=null;
		HttpSession session=request.getSession(true);
		try{
			loginBean=(Login)session.getAttribute("loginBean");
			boolean b=loginBean.getPhone()==null||loginBean.getPhone().length()==0;
			if(b)
				response.sendRedirect("loginBean");
		}catch(Exception exp){
			response.sendRedirect("loginBean");
		}
		
		String uri="jdbc:mysql://127.0.0.1:3306/software?"+"user=root&password=123&characterEncoding=utf8";
		Connection con;
		PreparedStatement sql;
		try{
			con=DriverManager.getConnection(uri);
			String insertCondition="insert into orderform values(?,?,?,?)";
			sql=con.prepareStatement(insertCondition);
			sql.setInt(1,0);//订单序号
			sql.setString(2,loginBean.getPhone());
			sql.setString(3,singleGoodsMess);
			sql.setFloat(4,singlesum);
			sql.executeUpdate();
			LinkedList car=loginBean.getCar();
			car.remove(singleGoodsMess);
			success(request,response,"生成订单成功！");
		}catch(SQLException exp){
			fail(request,response,"生成订单失败"+exp);
		}
	}
	
	public  void  doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		doPost(request,response);
	}
	
	public void success(HttpServletRequest request,HttpServletResponse response,String backNews)
	{
		response.setContentType("text/html;charset=utf8");
		try{
			PrintWriter out=response.getWriter();
			out.println("<html><body>");
			out.println("<h2>"+backNews+"</h2>") ;
			out.println("返回主页<br>");
			out.println("<br><a href =index.jsp>主页</a>");
			out.println("查看订单<br>");
			out.println("<br><a href =lookOrderForm.jsp>查看订单</a>");
			out.println("</body></html>");
		}catch(IOException e){}
	}
	
	public void fail(HttpServletRequest request,HttpServletResponse response,String backNews)
	{
		response.setContentType("text/html;charset=utf8");
        try {
			PrintWriter out=response.getWriter();
			out.println("<html><body>");
			out.println("<h2>"+backNews+"</h2>") ;
			out.println("返回主页：");
			out.println("<a href=lookShoppingCar.jsp>我的购物车</a>");
			out.println("</body></html>");
        }
        catch(IOException exp){}
	}
	
}