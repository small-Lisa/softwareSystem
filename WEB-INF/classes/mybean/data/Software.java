package mybean.data;

public class Software{
	String softname="",softpic="";
	int downloads=0;
	float capacity=0,score=5;
	String backNews="正在查看所有软件";
	
	public void setSoftname(String softname)
	{
		this.softname=softname;
	}
	public String getSoftname()
	{
		return softname;
	}
	
	public void setSoftpic(String softpic)
	{
		this.softpic=softpic;
	}
	public String getSoftpic()
	{
		return softpic;
	}
	
	public void setCapacity(float capacity)
	{
		this.capacity=capacity;
	}
	public float getCapacity()
	{
		return capacity;
	}
	
	public void setScore(float score)
	{
		this.score=score;
	}
	public float getScore()
	{
		return score;
	}
	
	public void setBackNews(String backNews)
	{
		this.backNews=backNews;
	}	
	public String getBackNews()
	{
		return backNews;
	}

}