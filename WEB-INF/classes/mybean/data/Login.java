package mybean.data;
import java.util.*;
public class Login
{
	String phone="", backNews="δ��¼";
	LinkedList<String>car;  //�û����ﳵ
	public Login()
	{
		car=new LinkedList<String>();
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setBackNews(String s)
	{
		backNews = s;
	}
	public String getBackNews()
	{
		return backNews;
	}
	
	public LinkedList<String>getCar()
	{
		return car;
	}
}