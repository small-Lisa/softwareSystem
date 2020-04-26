package myservlet.control;
import mybean.data.DataByPage;
import com.sun.rowset.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HandleSearch extends HttpServlet{
	
	CachedRowSetImpl rowSet=null;
	
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		request.setCharacterEncoding("utf8");
		String searchMess=request.getParameter("searchMess");
		String radioMess=request.getParameter("r");
		if(searchMess==null)
			searchMess="";
		if(radioMess==null)
			radioMess="number";
		HttpSession session=request.getSession(true);
		String searchCon=null;
		String radioCon=null;
		searchCon="where softname like '%"+searchMess+"%'";
		if(radioMess.equals("number")){
			radioCon="select * from softer "+searchCon+" order by downloads DESC";
		}
		else if(radioMess.equals("grade")){
			radioCon="select * from softer "+searchCon+" order by score DESC";
		}
		//获取页码
		DataByPage dataBean=null;
		try{
			dataBean=(DataByPage)session.getAttribute("dataBean");
			if(dataBean==null){
				dataBean=new DataByPage();
				session.setAttribute("dataBean",dataBean);
			}
		}catch(Exception exp){
			dataBean=new DataByPage();  
			session.setAttribute("dataBean",dataBean);
		}
		String uri="jdbc:mysql://127.0.0.1:3306/software";
		Connection con=null;
		try{
			con=DriverManager.getConnection(uri,"root","123");
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=sql.executeQuery(radioCon);
			rowSet=new CachedRowSetImpl();
			rowSet.populate(rs);
			dataBean.setRowSet(rowSet);
			con.close();
		}catch(SQLException exp){}
		response.setCharacterEncoding("utf-8");
		RequestDispatcher dispatcher=request.getRequestDispatcher("looksoftware.jsp");
		dispatcher.forward(request, response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}
	
}