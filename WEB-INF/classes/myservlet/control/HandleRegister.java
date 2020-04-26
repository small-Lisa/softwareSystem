package myservlet.control;
import mybean.data.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class HandleRegister extends HttpServlet{
	
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	//�����ַ���
	public String handleString(String s){
		try{
			byte bb[] = s.getBytes("iso-8859-1");
			s = new String(bb);
		}
		catch(Exception ee){}
		return s;
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		
		String uri="jdbc:mysql://127.0.0.1:3306/software?"+"user=root&password=123&characterEncoding=utf8";
		Connection con;
		PreparedStatement sql;
		Register userBean=new Register();
		request.setCharacterEncoding("utf-8");
		request.setAttribute("userBean",userBean);
		String phone=request.getParameter("phone").trim();
		String logname=request.getParameter("logname").trim();
		String realname=request.getParameter("realname").trim();
		String password=request.getParameter("password").trim();
		String again_password=request.getParameter("again_password").trim();
		
		//�ж����������Ƿ���ͬ
		if(!password.equals(again_password)){
			userBean.setBackNews("�������벻ͬ�����������룡");
			RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request,response);
			return;
		}

		//�ж��û����Ƿ���ȷ
		boolean isLD=true;
		for(int i=0;i<logname.length();i++){
			char c=logname.charAt(i);
			if(!((c<='z'&&c>='a')||(c<='Z'&&c>='A')||(c<='9'&&c>='0')))
				isLD=false;
		}
		String backNews="";
		try{
			con=DriverManager.getConnection(uri);
			String insertCondition="insert into user values(?,?,?,?)";
			sql=con.prepareStatement(insertCondition);
			if(isLD){
				sql.setString(1,handleString(phone));
				sql.setString(2,handleString(logname));
				sql.setString(3,handleString(realname));
				sql.setString(4,handleString(password));
				int m=sql.executeUpdate();
				if(m!=0){
					backNews="ע��ɹ���";
					userBean.setPhone(phone);
					userBean.setLogname(logname);
					userBean.setRealname(realname);
					userBean.setPassword(password);
					userBean.setBackNews(backNews);
				}
			}else{
				backNews="������Ϸ����û�����~";
				userBean.setBackNews(backNews);
			}
			con.close();
		}catch(SQLException e){
			backNews="���ֻ����ѱ�ʹ�ã���ֱ�ӵ�¼��"+e;
            userBean.setBackNews(backNews);
		}
		//ע��ɹ��� ��ת�ϴ����֤ҳ��upcard.jsp
		response.setCharacterEncoding("utf-8");
        RequestDispatcher dispatcher=request.getRequestDispatcher("upcard.jsp");
        dispatcher.forward(request, response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		doPost(request,response);
	}
}