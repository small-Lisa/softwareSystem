package myservlet.control;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HandleDownload extends HttpServlet{
	
	public void init(ServletConfig config)throws ServletException
	{
		super.init(config);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		request.setCharacterEncoding("utf8");
		String dir="C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/LisasasaSoftware/installpack/";	
		String fileName=Integer.toString(i)+".zip";
		String fileName="1.zip";
		String filePath=dir+fileName;
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		try{
			File f=new File(filePath);
			FileInputStream in=new FileInputStream(f);
			OutputStream out=response.getOutputStream();
			int n=0;
			byte b[]=new byte[500];
			while((n=in.read(b))!=-1)
				out.write(b,0,n);
			out.close();
			in.close();
		}catch(Exception e){}
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		doPost(request,response);
	}
	
}