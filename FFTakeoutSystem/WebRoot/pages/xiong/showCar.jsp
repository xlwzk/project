<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showCar.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
      <table  width="50%" align="center">
           <tr>
              <td colspan="3" align="center"><a href="shwx!deleteCar.action?sid=${requestScope.sid}">清空购物车</a></td>
           </tr>
          <c:forEach items="${requestScope.list}" var="car">
             <tr>
                <td align="center">${car.muname }</td>
                <td align="center"><span style="color:red">￥${car.ocount*car.muprice}</span></td>
                <td align="center"><a href="shwx!deleteShop.action?sid=${car.ortid}&meuid=${car.omuid}&num=${car.ocount}"><input type="button" value="-"/></a>&nbsp;&nbsp;<span style="color:red">${car.ocount }</span>&nbsp;&nbsp;<a href="shwx!addShop.action?sid=${car.ortid}&meuid=${car.omuid}"><input type="button" value="+"/></a></td>
             </tr>
          </c:forEach>
          <tr>
              <td colspan="3" align="center">总价格为：<span style="color:red;font-size:22px">￥${sumMoney }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color:red;font-size:22px"><a href="shwx!getDetail.action?sid=${requestScope.sid}" style="text-decoration: none">去结算</a></span></td>
           </tr>
      </table>
  </body>
</html>
