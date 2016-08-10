<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'account.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	         #demo:{
	           color:red;
	         }
	</style>
		<script>
    var EndTime=15*60;
    var intvalue=1;
    var timer2=null;
    function startShow(){
        intvalue ++;
        document.getElementById("demo").innerHTML=" " + Math.floor(((EndTime-intvalue)/60)).toString()+"分"+((EndTime-intvalue)%60).toString()+"秒";
        if(intvalue>=EndTime){
            document.getElementById("demo").innerHTML=" Time Up!";
           // endShow();
        }
    }
    function endShow(){
         window.clearTimeout(timer2);
		//alert("时间已过");
    }
timer2=window.setInterval("startShow()",1000);
</script>
	</head>

	<body>
		<table align="center" >
			<tr>
				<td align="center" colspan="2">
				<span>支付时间</span>
					<br />
					<div id="demo"
					   style="color:red"
					></div>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<span>￥${money }</span>
					 <br/>
					${shopDetail.rtname }&nbsp;&nbsp;${shopDetail.tel }
				</td>
			</tr>
			<tr>
			    <td rowspan="2" align="center" ><img src="<%=path %>/image/xiong/微信.jpg"/></td>
				<td >
					<span style="font-family:楷体;font-weight:bold;font-size:22px " >微信支付</span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" checked="checked"/>
				 </td>
				
			</tr>
			<tr>
				 <td  align="center">
				   <span style="font-family:楷体;font-size:14px">推荐安装微信5.0及以上版本的用户使用</span>
				 </td>
				 </tr>
			<tr>
			     <td rowspan="2" align="center" ><img src="<%=path %>/image/xiong/银行卡.jpg"/></td>
				<td  >
					<span style="font-family:楷体;font-weight:bold;font-size:22px">银行卡支付</span>	
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"/>			
				</td>
			</tr>
             <tr>
               <td align="center" >
                  <span style="font-family:楷体;font-size:14px">支付服务由银联提供，无需开通网银</span>
               </td>
             </tr>
			<tr>
				<td align="center" colspan="2">
					<span style="color:red;font-family:楷体;font-weight:bold;font-size:20px">确认卡支付</span>
				</td>
			</tr>
		</table>
	</body>
</html>
