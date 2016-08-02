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
    
    <title>My JSP 'showDetail.jsp' starting page</title>
    
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
    <table width="50%" align="center">
         <tr>
            <td colspan="3" align="center">&nbsp;${mapdetail.username }
           
               <c:if test="${mapdetail.gender=='女'}">
                  &nbsp;女士&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               </c:if>
                <c:if test="${mapdetail.gender=='男'}">
                  &nbsp;先生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               </c:if> 
               ${mapdetail.tel }
            </td >
         </tr>
         <tr>
           <td colspan="3" align="center">
              ${mapdetail.address }
           </td>
         </tr>
         <tr>
            <td colspan="3" align="center">&nbsp;&nbsp;&nbsp;&nbsp;${mapdetail.rtname }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               &nbsp;&nbsp;&nbsp;&nbsp;由商家配送&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
         </tr>
          <c:forEach items="${requestScope.list}" var="car">
             <tr>
                <td align="center">&nbsp;&nbsp;&nbsp;&nbsp;${car.muname }</td>
                <td align="center">x<span style="color:red">${car.ocount}&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
                <td align="center"><span style="color:red">￥${car.ocount*car.muprice}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
             </tr>
          </c:forEach>
          <tr>
            <td colspan="3" align="center">&nbsp;&nbsp;&nbsp;&nbsp;待支付：<span style="color:red;font-size:22px">￥${sumMoney }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color:red;font-size:22px"><a href="" style="text-decoration: none">提交订单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span></td>
          </tr>
    </table>
  </body>
</html>
