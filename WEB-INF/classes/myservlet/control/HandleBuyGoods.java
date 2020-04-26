package myservlet.control;
import mybean.data.Login;
import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HandleBuyGoods extends HttpServlet{
	
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
		String buyGoodsMess=request.getParameter("buyGoods");
		if(buyGoodsMess==null||buyGoodsMess.length()==0){
			fail(request,response,"���ﳵû����Ʒ���޷����ɶ�����");
			return;
		}
		String priceSum=request.getParameter("priceSum");
		if(priceSum==null||priceSum.length()==0){
			fail(request,response,"û�м���۸��ۺϣ��޷����ɶ�����");
			return;
		}
		float sum=Float.parseFloat(priceSum);
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
			sql.setInt(1,0);//�������
			sql.setString(2,loginBean.getPhone());
			sql.setString(3,buyGoodsMess);
			sql.setFloat(4,sum);
			sql.executeUpdate();
			LinkedList car=loginBean.getCar();
			car.clear();
			success(request,response,"���ɶ����ɹ���\n��Ҫ֧�� �� "+sum+"Ԫ��");
		}catch(SQLException exp){
			fail(request,response,"���ɶ���ʧ��"+exp);
		}
	}
	
	public  void  doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		doPost(request,response);
	}
	
	public void success(HttpServletRequest request,HttpServletResponse response,String backNews)
	{
		response.setContentType("text/html;charset=utf8");
		try{
			/*
			PrintWriter out=response.getWriter();
			out.println("<html><body>");
			out.println("<div align='center'>");
			out.println("<h2>"+backNews+"</h2>");
			out.println("<img src='image/pay.jpg' width=200 height=auto></img>");
			out.println("<br><br>������ҳ");
			out.println("<a href =index.jsp>��ҳ</a>");
			out.println("<br><br>�鿴����");
			out.println("<a href =lookOrderForm.jsp>�鿴����</a>");
			out.println("</div></body></html>");*/
			RequestDispatcher dispatcher=request.getRequestDispatcher("alipay.trade.page.pay.jsp");
			dispatcher.forward(request,response);
		}catch(Exception e){}
	}
	
	public void fail(HttpServletRequest request,HttpServletResponse response,String backNews)
	{
		response.setContentType("text/html;charset=utf8");
        try {
			PrintWriter out=response.getWriter();
			out.println("<html><body>");
			out.println("<div align='center'>");
			out.println("<h2>"+backNews+"</h2>") ;
			out.println("������ҳ��");
			out.println("<a href =index.jsp>��ҳ</a>");
			out.println("</div></body></html>");
        }
        catch(IOException exp){}
	}
	
}