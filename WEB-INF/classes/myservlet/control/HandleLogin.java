package myservlet.control;
import mybean.data.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class HandleLogin extends HttpServlet
{
    public void init(ServletConfig config)throws ServletException
    {
        super.init(config);
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){}
    }

    public String handleString(String s)
    {
        try
        {
            byte bb[] = s.getBytes("iso-8859-1");
            s = new String(bb);
        }
        catch(Exception e){}
        return s;
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
    {
        Connection con;
        Statement sql;
        String phone = request.getParameter("phone").trim();
        String password = request.getParameter("password").trim();
        phone = handleString(phone);
        password = handleString(password);
        String uri = "jdbc:mysql://127.0.0.1:3306/software?"+"user=root&password=123&characterEncoding=utf-8";
        boolean boo = (phone.length()>0)&&(password.length()>0);
        try
        {
            con = DriverManager.getConnection(uri);
            String condition = "select * from user where phone='"+phone+"' and password='"+password+"'";
            sql = con.createStatement();
            if(boo)
            {
                ResultSet rs = sql.executeQuery(condition);
                boolean m = rs.next();
                if(m==true)
                {
                    success(request,response,phone,password);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");  //�Ĺ�
                    dispatcher.forward(request,response);
                }
                else
                    {
                        String backNews="��������˺Ų����ڣ������벻��ȷ";
                        fail(request,response,phone,backNews);
                    }
            }
            else
                {
                    String backNews="�������û���������";
                    fail(request,response,phone,backNews);
                }
             con.close();
        }
        catch(Exception exp)
        {
            String backNews = "" + exp;
            fail(request, response, phone, backNews);
        }
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
    {
        doPost(request,response);
    }

    public void success(HttpServletRequest request,HttpServletResponse response,String phone,String password)
    {
        Login loginBean = null;
        HttpSession session = request.getSession(true);
        try
        {
            loginBean = (Login)session.getAttribute("loginBean");
            if(loginBean==null)
            {
                loginBean=new Login();
                session.setAttribute("loginBean",loginBean);
                loginBean=(Login)session.getAttribute("loginBean");
            }
            String judge_phone=loginBean.getPhone();
            if(judge_phone.equals(phone))
            {
                loginBean.setBackNews(phone+"�Ѿ���½��");
                loginBean.setPhone(phone);
            }
            else
                {
                    loginBean.setBackNews(phone+"��½�ɹ�");
                    loginBean.setPhone(phone);
                }
        }
        catch(Exception ee)
        {
            loginBean=new Login();
            session.setAttribute("loginBean",loginBean);
            loginBean.setBackNews(phone+"��½�ɹ�");
            loginBean.setPhone(phone);
        }
    }

    public void fail(HttpServletRequest request,HttpServletResponse response,String phone,String backNews)
    {	
        response.setContentType("text/html;charset=utf-8");
        try
        {
            PrintWriter out = response.getWriter();
			out.println("<body background='image/back3.png'><font size=3>");
			out.println("<title>Lisasasa�������</title>");
			out.println("<div align='center'>");
			out.println("<br><br><br>");
			out.println("<img src='image/welcome.png' width=1400 height=200></img><br>");
            out.println("<html><body>");
            out.println("<h2>"+phone+"&nbsp��½����ҳ��<br>"+backNews+"</h2>");
            out.println("<a href=login.jsp><&nbsp���ص�¼</a>");
            out.println("<a href=index.jsp><&nbsp������ҳ</a>");
            out.println("</body></html>");
        }
        catch (IOException e){}
		
    }

}