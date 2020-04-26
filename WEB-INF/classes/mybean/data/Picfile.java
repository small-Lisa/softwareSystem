package mybean.data;
import org.json.JSONObject;

public class Picfile
{
	String picname="";
	String mess="请上传您的身份证照片！";
	JSONObject idname=null,idnation=null,idaddress=null,idsex=null,idbirth=null,idnumber=null;
	String idmess="";
	
	public void setPicname(String picname)
	{
		this.picname=picname;
	}
	public String getPicname()
	{
		return picname;
	}
	public void setMess(String mess)
	{
		this.mess=mess;
	}
	public String getMess()
	{
		return mess;
	}
	
	public void setIdname(JSONObject idname)
	{
		this.idname=idname;
	}
	public JSONObject getIdname()
	{
		return idname;
	}
	public void setIdnation(JSONObject idnation)
	{
		this.idnation=idnation;
	}
	public JSONObject getIdnation()
	{
		return idnation;
	}
	public void setIdaddress(JSONObject idaddress)
	{
		this.idaddress=idaddress;
	}
	public JSONObject getIdaddress()
	{
		return idaddress;
	}
	public void setIdsex(JSONObject idsex)
	{
		this.idsex=idsex;
	}
	public JSONObject getIdsex()
	{
		return idsex;
	}
	public void setIdbirth(JSONObject idbirth)
	{
		this.idbirth=idbirth;
	}
	public JSONObject getIdbirth()
	{
		return idbirth;
	}
	public void setIdnumber(JSONObject idnumber)
	{
		this.idnumber=idnumber;
	}
	public JSONObject getIdnumber()
	{
		return idnumber;
	}
	public void setIdmess(String idmess)
	{
		this.idmess=idmess;
	}
	public String getIdmess()
	{
		return idmess;
	}
	
}