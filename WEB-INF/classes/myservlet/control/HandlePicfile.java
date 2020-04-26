package myservlet.control;
import mybean.data.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;

import com.baidu.aip.ocr.AipOcr;
//import com.bdxh.framework.commons.util.ConfigMgr;
import org.json.JSONObject;

public class HandlePicfile extends HttpServlet{
	
	//设置APPID/AK/SK
	private static String APP_ID="18066858";
	private static String API_KEY="6uIEh2BaAXYlaNMzHvTMNzMO";
	private static String SECRET_KEY="sgGAwAQ8RbjUUYzrGmGiBSTSwTf8VxTU";
	
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	//处理字符串
	public String handleString(Object obj){
		String s=String.valueOf(obj);
		try{
			byte bb[] = s.getBytes("iso-8859-1");
			s = new String(bb);
		}
		catch(Exception ee){}
		return s;
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{	
		/*if(userBean==null){
			RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request, response);
			return;
		}*/
		Picfile cardBean=new Picfile();
		request.setCharacterEncoding("utf-8");
		request.setAttribute("cardBean",cardBean);
		HttpSession session = request.getSession();
		//String phone=uerBean.getPhone();
		String picname="";
		try{
			String tempfilename = (String) session.getId();
			File dir=new File("C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/LisasasaSoftware/creditCard");
			dir.mkdir();
			File tempf=new File(dir,tempfilename);
			FileOutputStream o=new FileOutputStream(tempf);
			//上传信息
			InputStream in=request.getInputStream();
			byte b[]=new byte[10000];
			int n;
			while((n=in.read(b))!=-1){
				o.write(b,0,n);
			}
			o.close();
			in.close();
			//读取临时文件tempf
			RandomAccessFile randomRead=new RandomAccessFile(tempf,"r");
			int second=1;
			String secondLine=null;
			//---文件第2行 文件名字
			while(second<=2){
				secondLine=randomRead.readLine();
				second++;
			}
			int position=secondLine.lastIndexOf("=");
			picname=secondLine.substring(position+2,secondLine.length()-1);
			randomRead.seek(0);
			//获取第四行回车符号的位置
			long forthEndPosition=0;
			int forth=1;
			while((n=randomRead.readByte())!=-1&&(forth<=4))
			{
				if(n=='\n'){
					forthEndPosition=randomRead.getFilePointer();
					forth++;
				}
			}
			//将文件存入磁盘
			byte cc[]=picname.getBytes("ISO-8859-1");
			picname=new String(cc);
			File picfile=new File(dir,picname);
			RandomAccessFile randomWriter=new RandomAccessFile(picfile,"rw");
			//获取临时文件tempf中的内容
			//---内容的最后位置，倒数第六行
			randomRead.seek(randomRead.length());
			long endPosition=randomRead.getFilePointer();
			long mark=endPosition;
			int j=1;
			while((mark>=0)&&(j<=6))
			{
				mark--;
				randomRead.seek(mark);
				n=randomRead.readByte();
				if(n=='\n'){
					endPosition=randomRead.getFilePointer();
					j++;
				}
			}
			//randomRead流只要文件tempf的第4行结束位置
			randomRead.seek(forthEndPosition);
			long startPoint=randomRead.getFilePointer();
			//---存入picfile
			while(startPoint<endPosition-1)
			{
				n=randomRead.readByte();
				randomWriter.write(n);
				startPoint=randomRead.getFilePointer();
			}
			randomWriter.close();
			randomRead.close();
			cardBean.setMess("身份证上传成功！");
			cardBean.setPicname(picname);
			tempf.delete();	
			
			//获取身份证信息
			String picPath=new String(dir+"/"+picname);
			AipOcr client=new AipOcr(APP_ID,API_KEY,SECRET_KEY);
			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);
			HashMap<String,String> options=new HashMap<String,String>();
			options.put("detect_direction","false");
			options.put("detect_risk","false");
			String idCardSide="front";
			String idmess="进入识别中。。";
			try{
				JSONObject res=client.idcard(picPath,idCardSide,options);
				System.out.println(res.toString(2));
				if(res!=null){
					JSONObject idcard=new JSONObject();
					JSONObject words_result=res.getJSONObject("words_result");
					
					JSONObject idname=(JSONObject)words_result.get("姓名");
					JSONObject idnation=(JSONObject)words_result.get("民族");
					JSONObject idaddress=(JSONObject)words_result.get("住址");
					JSONObject idsex=(JSONObject)words_result.get("性别");
					JSONObject idbirth=(JSONObject)words_result.get("出生");
					JSONObject idnumber=(JSONObject)words_result.get("公民身份号码");
					/*
					idcard.put("idname",words_result.getJSONObject("姓名").get("words"));
					idcard.put("idnation",words_result.getJSONObject("民族").get("words"));
					idcard.put("idaddress",words_result.getJSONObject("住址").get("words"));
					idcard.put("idsex",words_result.getJSONObject("性别").get("words"));
					idcard.put("idbirth",words_result.getJSONObject("出生").get("words"));
					idcard.put("idnumber",words_result.getJSONObject("公民身份号").get("words"));
					idMess=idcard.toString(2);
					*/
					cardBean.setIdname(idname);
					cardBean.setIdnation(idnation);
					cardBean.setIdaddress(idaddress);
					cardBean.setIdsex(idsex);
					cardBean.setIdbirth(idbirth);
					cardBean.setIdnumber(idnumber);
					idmess="识别成功！";
					//上传至数据库
					try{
						String uri="jdbc:mysql://127.0.0.1:3306/software?"+"user=root&password=123&characterEncoding=utf8";
						Connection con;
						PreparedStatement sql;
						con=DriverManager.getConnection(uri);
						String insertCondition="insert into usercard values(?,?,?,?,?,?,?)";
						sql=con.prepareStatement(insertCondition);
						sql.setString(1,handleString(idname.get("words")));
						sql.setString(2,handleString(idnation.get("words")));
						sql.setString(3,handleString(idaddress.get("words")));
						sql.setString(4,handleString(idsex.get("words")));
						sql.setString(5,handleString(idbirth.get("words")));
						sql.setString(6,handleString(idnumber.get("words")));
						sql.setString(6,handleString(picname));
						con.close();
						int m=sql.executeUpdate();
						if(m!=0){
						idmess="上传数据库成功！";}
					}catch(SQLException e){
						idmess="上传数据库失败！"+e;
						cardBean.setIdmess(idmess);
					}
				}else{
					idmess="该图片无内容";
				}
				cardBean.setIdmess(idmess);
			}catch(Exception e){
				idmess="识别不成功！"+e;
				cardBean.setIdmess(idmess);
			}
			
			//上传成功后 跳转上传身份证页面upcard.jsp
			response.setCharacterEncoding("utf-8");
			RequestDispatcher dispatcher=request.getRequestDispatcher("upcard.jsp");
			dispatcher.forward(request, response);
				
		}catch(Exception e){}
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		doPost(request,response);
	}
	
}