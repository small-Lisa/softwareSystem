<!DOCTYPE html>
<%@page contentType="text/html;charset=utf-8"%>
<%@ page import="mybean.data.Login" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<html>
<head><%@include file="head0.jsp"%></head>
<head><%@include file="head.txt"%></head>
<head>
<style type="text/css">
	table.mt {
        width: auto;
        margin: 15px 20px 20px 20px;
        border-top: 1px solid grey;
        border-spacing: 0px;
        color: black;
    }
    table.mt thead tr td,table.mt tbody tr td {
        border-bottom: 1px solid grey;
        padding: 15px 20px;
		font-size:20px;
		font-family:楷体;
    }
    table.mt thead tr td{
        font-weight: bold;
		font-family:黑体;
		font-size:25px;
		background: rgba(171,192,228,0.65);
    }
    }
    table.mt tbody tr:nth-child(odd) {
        background: rgba(171,192,228,0.65);
    }
</style>
</head>
<body>
<div align="center">
<%
	if(loginBean==null)
		response.sendRedirect("login.jsp");
	else{
		boolean b=loginBean.getPhone().length()==0;
		if(b)
			response.sendRedirect("login.jsp");
	}
	LinkedList car=loginBean.getCar();
	if(car==null)
		out.print("<h2>您的购物车没有物品！<br>快去逛逛吧~</h2>");
	else{
		Iterator<String> iterator=car.iterator();
		StringBuffer buyGoods=new StringBuffer();
		int n=0;
		double priceSum=0;
		
		out.print("<table class='mt'>");
		out.print("<thead><tr><td>购物车</td><td colspan='2'>操作</td></tr></thead>");
		while(iterator.hasNext())
		{
			String goods=iterator.next();
			String showGoods="";
			n++;
			int index=goods.lastIndexOf("#");
			if(index!=-1){
				priceSum+=Double.parseDouble(goods.substring(index+1));
				showGoods=goods.substring(0,index);
			}
			buyGoods.append(n+":"+showGoods);
			String del="<form  action='deleteServlet' method = 'post'>"+
				"<input type ='hidden' name='delete' value= "+goods+">"+
                "<input type ='submit'  value='删除' ></form>";
			String singleorderForm = "<form action='alipay.trade.page.pay.jsp' method='post'>"+
			"<input type ='hidden' name='buyGoods' value= "+buyGoods+" >"+ 
            "<input type ='hidden' name='priceSum' value= "+priceSum+" >"+           
            "<input type ='submit'  value='购买'></form>";
            out.print("<tr><td>"+showGoods+"</td>");
            out.print("<td>"+del+"</td>");
			out.print("<td>"+singleorderForm+"</td></tr>");
		}
		out.print("</table>");
		
		String orderForm = "<form action='buyGoodsServlet' method='post'>"+
			"<input type ='hidden' name='buyGoods' value= "+buyGoods+" >"+ 
            "<input type ='hidden' name='priceSum' value= "+priceSum+" >"+           
            "<input type ='submit'  value='生成订单'></form>";
        out.print(orderForm); 
	}
%>
</div>
</body>
</html>
