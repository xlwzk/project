$(function() {
	//查询所有用户
	$("#findalluser").click(function() {
		$.getJSON("shwk!getUserList.action?v=" + Math.random(), Userrollback)
	})
	//添加用户
	$("#addUser").click(function() {
					var s = "<form  action='shwk!addUser.action'  method='post' enctype='multipart/form-data'>";
						s+="<table align='center'>";
						s += "<tr><td>姓名：<input type='text' id='username' name='username'/></td></tr>"
						s += "<tr><td>密码：<input type='text' id='pwd' name='pwd'/></td></tr>"
						s += "<tr><td>电话：<input type='text' id='tel' name='tel'/></td></tr>"
						s += "<tr><td>邮件：<input type='text' id='email' name='email'/></td></tr>"
						s += "<tr><td>地址：<input type='text' id='address' name='address'/></td></tr>"
						s += "<tr><td>真实姓名：<input type='text' id='realname' name='realname'/></td></tr>"
						s += "<tr><td>余额：<input type='text' id='balance' name='balance'/></td></tr>"
						s += "<tr><td>性别:<input type='radio' id='gender' name='gender' value='男' checked='checked'>男<input type='radio' id='gender' name='gender' value='女'> 女</td></tr>"
						s += "<tr><td>权限:<select id='authority' name='authority'><option value='1'>普通用户</option><option value='2'>配送员</option><option value='3'>店主</option><option value='4'>普通管理员</option><option value='5'>系统管理员</option> </select></td></tr>"
						s += "<tr><td>图片：<input type='file' id='photo' name='photo' accept='image/gif, image/jpeg, image/png'/></td></tr>"
						s += "<tr><td><input type='submit' value='提交'/><input type='reset' value='取消'/></td></tr>"
						s +="</table>"
						s += "</form>"
						$("#mydiv").html(s);
					})
	//查询所有商家
	$("#findallrest").click(function() {
		$.getJSON("shwkrest!getRestList.action?v=" + Math.random(), Restrollback)
	})
	//添加商家
	$("#addRest").click(function() {
		$.getJSON("shwk!getAllUser.action?v=" + Math.random(), function(data){
			var getAllUser = data.rows;
			var str="";
			for ( var i = 0; i < getAllUser.length; i++) {
				var k = getAllUser[i]
				str +="<option value="+k.userid+">"+k.username+"</option>"	                   
			}
			var s = "<form  action='shwkrest!addRest.action'  method='post' enctype='multipart/form-data'>";
			s+="<table align='center'>";
			s += "<tr><td>店名：<input type='text' id='rtname' name='rtname'/></td></tr>"
			s += "<tr><td>地址：<input type='text' id='rtaddr' name='rtaddr'/></td></tr>"
			s += "<tr><td>店主:<select id='rtowner' name='rtowner'>"+str+"</select></td></tr>"
			s += "<tr><td>图片：<input type='file' id='photo' name='photo' accept='image/gif, image/jpeg, image/png'/></td></tr>"
			s += "<tr><td>公告：<textarea rows='3' cols='20' id='rtcontent' name='rtcontent'></textarea></td></tr>"
			s += "<tr><td>营业时间：<input type='text' id='rtonbuz' name='rtonbuz'/></td></tr>"
			s += "<tr><td>营业状态:<select id='rtstatus' name='rtstatus'><option value='0'>正在营业</option><option value='1'>休息中</option></select></td></tr>"
			s += "<tr><td><input type='submit' value='提交'/><input type='reset' value='取消'/></td></tr>"
			s +="</table>"
			s += "</form>"
			$("#mydiv").html(s);	
		})
	})
	
	//查询所有商家评论
	$("#findaddMess").click(function(){
		$.getJSON("shwkmess!getMessList.action?v=" + Math.random(), Messrollback)
	})
	
	//查询所有菜单
	$("#findallmenu").click(function(){
		$.getJSON("shwkmenu!getMenuList.action?v=" + Math.random(), Menurollback)
	})
	
	//查询所有菜单评论
	$("#findallmenumsg").click(function(){
		$.getJSON("shwkmenumsg!getMenuMsgList.action?v=" + Math.random(), MenuMsgrollback)
	})
		
	//查询所有订单
	$("#findallorder").click(function(){
		$.getJSON("shwkorder!getOrderList.action?v=" + Math.random(), Orderrollback)
	})
	
	//查询所有订单支付方式
	$("#findallpay").click(function(){
		$.getJSON("shwkpay!getPayList.action?v=" + Math.random(), Payrollback)
	})
	
	//查询所有礼品
	$("#findallgift").click(function(){
		$.getJSON("shwkgift!getGiftList.action?v=" + Math.random(), Giftrollback)
	})
	
	//添加礼品
	$("#addgift").click(function() {
			var s = "<form  action='shwkgift!addGift.action'  method='post' enctype='multipart/form-data'>";
			s+="<table align='center'>";
			s += "<tr><td>礼品名：<input type='text' id='gname' name='gname'/></td></tr>"
		    s += "<tr><td>图片：<input type='file' id='photo' name='photo' accept='image/gif, image/jpeg, image/png'/></td></tr>"
		    s += "<tr><td>所需积分:<input type='text' id='greqscore' name='greqscore'/></td></tr>"
		    s += "<tr><td>总库存数:<input type='text' id='gsum' name='gsum'/></td></tr>"	
			s += "<tr><td>描述：<textarea rows='3' cols='20' id='gdesc' name='gdesc'></textarea></td></tr>"
			s += "<tr><td><input type='submit' value='提交'/><input type='reset' value='取消'/></td></tr>"
			s +="</table>"
			s += "</form>"
			$("#mydiv").html(s);	
		})
		
		//查询所有礼品兑换记录
		$("#findallgiftRec").click(function(){
			$.getJSON("shwkgiftrec!getGiftRecordList.action?v=" + Math.random(), GiftRecrollback)
		})
	})




//用户回调函数
function Userrollback(data) {
	var userid=$("#id").val()
	var page = data.pages;
	var sum = data.total;
	var getAllUser = data.rows;
	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr><td>编号</td><td>姓名</td><td>密码</td><td>邮箱</td><td>地址</td><td>真实姓名</td><td>余额</td><td>积分</td><td>性别</td><td>注册日期</td><td>权限</td><td>图片</td><td>操作</td></tr>"
	for ( var i = 0; i < getAllUser.length; i++) {
		var k = getAllUser[i];
		var authority = "";
		if (k.authority == 1) {
			authority = "普通用户"
		} else if (k.authority == 2) {
			authority = "配送员"
		} else if (k.authority == 3) {
			authority = "店主"
		} else if (k.authority == 4) {
			authority = "普通管理员"
		} else if (k.authority == 5) {
			authority = "系统管理员"
		}
		s += "<tr align='center'>"
		s += "<td id='userid'>" + k.userid + "</td>";
		s += "<td id='username'>" + k.username + "</td>";
		s += "<td id='pwd'>" + k.pwd + "</td>";
		s += "<td id='email'>" + k.email + "</td>";
		s += "<td id='address'>" + k.address + "</td>";
		s += "<td id='realname'>" + k.realname + "</td>";
		s += "<td id='balance'>" + k.balance + "</td>";
		s += "<td id='score'>" + k.score + "</td>";
		s += "<td id='gender'>" + k.gender + "</td>";
		s += "<td id='regdate'>" + k.regdate + "</td>";
		s += "<td id='authority'>" + authority + "</td>";
		s += "<td id='photo'> <img src=" + k.photo
				+ " width='50px' heigth='50px'/></td>";
		s += "<td>";
		if (userid==k.userid) {
			s += "<font color='red'>当前用户</font>";
		}else{
			s += "<span onclick='findbyid(" + k.userid + ")'>修改</span>";
			s += "&nbsp;";
			s += "&nbsp;";
			s += "<span onclick='deleUser(" + k.userid + ")'>删除</span>";	
		}
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center'>"
	s += "<tr valign='bottom' align='center'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getUserList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getUserList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getUserList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getUserList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
}

//商家回调函数
function Restrollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllRest = data.rows;
	
	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr align='center'><td>编号</td><td>店名</td><td>地址</td><td>店主名</td><td>图片</td><td>公告</td><td>开店时间</td><td>营业时间</td><td>营业状态</td><td>操作</td></tr>"
	for ( var i = 0; i < getAllRest.length; i++) {
		var k = getAllRest[i];
		var status = "";
		if (k.rtstatus == 0) {
			status = "正在营业"
		} else if (k.rtstatus == 1) {
			status = "休息中"
		} 
		s += "<tr align='center'>"
		s += "<td id='userid'>" + k.rtid + "</td>";
		s += "<td id='username'>" + k.rtname + "</td>";
		s += "<td id='pwd'>" + k.rtaddr + "</td>";
		s += "<td id='email'>" + k.owner + "</td>";
		s += "<td id='photo'> <img src=" + k.rtpic
		+ " width='50px' heigth='50px'/></td>";

		s += "<td id='address'>" + k.rtcontent + "</td>";
		s += "<td id='realname'>" + k.rtdate + "</td>";
		s += "<td id='balance'>" + k.rtonbuz + "</td>";
		s += "<td id='score'>" + status + "</td>";
		s += "<td>";
		s += "<span onclick='findrestbyid(" + k.rtid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleRest(" + k.rtid + ")'>删除</span>";	
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center'>"
	s += "<tr valign='bottom' align='center'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getRestList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getRestList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getRestList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getRestList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
}

//商家评论回调函数
function Messrollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllMess = data.rows;
	
	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr align='center'><td>编号</td><td>评论人</td><td>店铺名</td><td>评论内容</td><td>评论时间</td><td>操作</td></tr>"
	for ( var i = 0; i < getAllMess.length; i++) {
		var k = getAllMess[i];
		s += "<tr align='center'>"
		s += "<td id='mid'>" + k.mid + "</td>";
		s += "<td id='musername'>" + k.musername + "</td>";
		s += "<td id='mrtname'>" + k.mrtname + "</td>";
		s += "<td id='mcontent'>" + k.mcontent + "</td>";
		s += "<td id='mdate'>" + k.mdate + "</td>";
		s += "<td>";
		s += "<span onclick='findmessbyid(" + k.mid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleMess(" + k.mid + ")'>删除</span>";	
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center'>"
	s += "<tr valign='bottom' align='center'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getMessList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getMessList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getMessList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getMessList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
}

//菜单回调函数
function Menurollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllMenu = data.rows;
	
	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr align='center'><td>编号</td><td>店名</td><td>菜名</td><td>价格</td><td>图片</td><td>类型</td><td>描述</td><td>销量</td><td>是否售完</td><td>操作</td></tr>"
	for ( var i = 0; i < getAllMenu.length; i++) {
		var k = getAllMenu[i];
		var status = "";
		if (k.mustatus == 0) {
			status = "正在销售"
		} else if (k.mustatus == 1) {
			status = "已售完"
		} 
		s += "<tr align='center'>"
		s += "<td id='muid'>" + k.muid + "</td>";
		s += "<td id='mrtname'>" + k.mrtname + "</td>";
		s += "<td id='muname'>" + k.muname + "</td>";
		s += "<td id='muprice'>" + k.muprice + "</td>";
		s += "<td id='mupic'> <img src=" + k.mupic
		+ " width='50px' heigth='50px'/></td>";
		s += "<td id='mutype'>" + k.mutype + "</td>";
		s += "<td id='mudesc'>" + k.mudesc + "</td>";
		s += "<td id='musale'>" + k.musale + "</td>";
		s += "<td id='status'>" + status + "</td>";
		s += "<td>";
		s += "<span onclick='findmenubyid(" + k.muid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleMenu(" + k.muid + ")'>删除</span>";	
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center'>"
	s += "<tr valign='bottom' align='center'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getMenuList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getMenuList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getMenuList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getMenuList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
}

//菜单评论回调函数
function MenuMsgrollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllMenuMsg = data.rows;
	
	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr align='center'><td>编号</td><td>评论人</td><td>菜名</td><td>评论内容</td><td>评分</td><td>评论时间</td><td>操作</td></tr>"
	for ( var i = 0; i < getAllMenuMsg.length; i++) {
		var k = getAllMenuMsg[i];
		var str=""
		if (k.mmscore==1) {
			str="★☆☆☆☆"
		}else if (k.mmscore==2) {
			str="★★☆☆☆"
		}else if (k.mmscore==3) {
			str="★★★☆☆"
		}else if (k.mmscore==4) {
			str="★★★★☆"
		}else if (k.mmscore==5) {
			str="★★★★★"
		}
		s += "<tr align='center'>"
		s += "<td id='mmid'>" + k.mmid + "</td>";
		s += "<td id='mmusername'>" + k.mmusername + "</td>";
		s += "<td id='mmmuname'>" + k.mmmuname + "</td>";
		s += "<td id='mmcontent'>" + k.mmcontent + "</td>";
		s += "<td id='mmscore'>" + str + "</td>";
		s += "<td id='mmdate'>" + k.mmdate + "</td>";
		s += "<td>";
		s += "<span onclick='findmenumsgbyid(" + k.mmid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleMenumsg(" + k.mmid + ")'>删除</span>";	
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center'>"
	s += "<tr valign='bottom' align='center'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getMenuMsgList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getMenuMsgList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getMenuMsgList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getMenuMsgList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
}

//订单回调函数
function Orderrollback(data) {
	alert("AAAAAAAAA")
	var page = data.pages;
	var sum = data.total;
	var getAllOrder = data.rows;
	
	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr align='center'><td>编号</td><td>下单用户</td><td>菜名</td><td>店名</td><td>数量</td><td>配送员</td><td>下单时间</td><td>UUID</td><td>订单状态</td><td>操作</td></tr>"
	for ( var i = 0; i < getAllOrder.length; i++) {
		alert(getAllOrder[i].OID)
		var k = getAllOrder[i];
		
		var str=""
		if (k.OSTATUS==0) {
			str="购物车中"
		}else if (k.OSTATUS==1) {
			str="已下单（未支付）"
		}else if (k.OSTATUS==2) {
			str="已支付"
		}else if (k.OSTATUS==3) {
			str="商家已接单"
		}else if (k.OSTATUS==4) {
			str="配送中"
		}else if (k.OSTATUS==5) {
			str="订单完成"
		}
		s += "<tr align='center'>"
		s += "<td id='oid'>" + k.OID + "</td>";
		s += "<td id='ousername'>" + k.OUSERNAME + "</td>";
		s += "<td id='omuname'>" + k.OMUNAME + "</td>";
		s += "<td id='ortname'>" + k.ORTNAME + "</td>";
		s += "<td id='ocount'>" + k.OCOUNT + "</td>";
		s += "<td id='osendername'>" + k.OSENDERNAME + "</td>";
		s += "<td id='odate'>" + k.ODATE + "</td>";
		s += "<td id='ouuid'>" + k.OUUID + "</td>";
		s += "<td id='ostatus'>" + str + "</td>";
		s += "<td>";
		s += "<span onclick='findorderbyid(" + k.OID + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleOrder(" + k.OID + ")'>删除</span>";	
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center'>"
	s += "<tr valign='bottom' align='center'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getMenuMsgList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getMenuMsgList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getMenuMsgList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getMenuMsgList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
}

//订单支付方式回调函数
function Payrollback(data) {

	var page = data.pages;
	var sum = data.total;
	var getAllPay = data.rows;
	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr align='center'><td>编号</td><td>支付方式</td><td>订单编号</td><td>操作</td></tr>"
	for ( var i = 0; i < getAllPay.length; i++) {
		var k = getAllPay[i];
		s += "<tr align='center'>"
		s += "<td id='pid'>" + k.pid + "</td>";
		s += "<td id='ptype'>" + k.ptype + "</td>";
		s += "<td id='poid'>" + k.poid + "</td>";
		s += "<td>";
		s += "<span onclick='findpaybyid(" + k.pid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='delepay(" + k.pid + ")'>删除</span>";	
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center'>"
	s += "<tr valign='bottom' align='center'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getPayList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getPayList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getPayList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getPayList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
}

//礼物回调函数
function Giftrollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllGift = data.rows;
	
	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr align='center'><td>编号</td><td>礼物名</td><td>图片</td><td>所需积分</td><td>已换数量</td><td>总共库存</td><td>描述</td><td>操作</td></tr>"
	for ( var i = 0; i < getAllGift.length; i++) {
		var k = getAllGift[i];
		s += "<tr align='center'>"
		s += "<td id='userid'>" + k.gid + "</td>";
		s += "<td id='username'>" + k.gname + "</td>";
		s += "<td id='photo'> <img src=" + k.gpic
		+ " width='50px' heigth='50px'/></td>";
		s += "<td id='pwd'>" + k.greqscore + "</td>";
		s += "<td id='email'>" + k.gcount + "</td>";
		s += "<td id='address'>" + k.gsum + "</td>";
		s += "<td id='realname'>" + k.gdesc + "</td>";
		s += "<td>";
		s += "<span onclick='findgiftbyid(" + k.gid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleGift(" + k.gid + ")'>删除</span>";	
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center'>"
	s += "<tr valign='bottom' align='center'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getGiftList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getGiftList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getGiftList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getGiftList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
}

//礼物兑换记录
function GiftRecrollback(data) {
	var page = data.pages;
	var sum = data.total;
	var getAllGiftRec = data.rows;
	
	var s = "<table align='center' border='1' width='90%'>";
	s += "<tr align='center'><td>编号</td><td>礼物名</td><td>兑换人</td><td>兑换数量</td><td>兑换时间</td><td>是否费送</td><td>操作</td></tr>"
	for ( var i = 0; i < getAllGiftRec.length; i++) {
		var k = getAllGiftRec[i];
		var str=""
		if (k.grstatus==0) {
			str="未配送"
		}else if (k.grstatus==1) {
			str="已配送"
		}
		s += "<tr align='center'>"
		s += "<td id='grid'>" + k.grid + "</td>";
		s += "<td id='grgname'>" + k.grgname + "</td>";
		s += "<td id='grusername'>" + k.grusername + "</td>";
		s += "<td id='grnum'>" + k.grnum + "</td>";
		s += "<td id='grdate'>" + k.grdate + "</td>";
		s += "<td id='grstatus'>" + str + "</td>";
		s += "<td>";
		s += "<span onclick='findGiftRecbyid(" + k.grid + ")'>修改</span>";
		s += "&nbsp;";
		s += "&nbsp;";
		s += "<span onclick='deleGiftRec(" + k.grid + ")'>删除</span>";	
		s += "</td>";
		s += "</tr>";
	}
	s += "</table>";
	s += "<table align='center'>"
	s += "<tr valign='bottom' align='center'>"
	s += "<td colspan='3' width='100%'>"
	if (page != 1) {
		s += "<a  onclick='getGiftRecList(1)'><font color='blue'>首页</font></a>"

	} else {
		s += "<span>首页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page > 1) {
		s += "<a  onclick='getGiftRecList(" + (page - 1)
				+ ")'><font color='blue'>上一页</font></a>"
	} else {
		s += "<span>上一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page < sum) {
		s += "<a  onclick='getGiftRecList(" + (page + 1)
				+ ")'><font color='blue'>下一页</font></a>"
	} else {
		s += "<span>下一页</span>"
	}
	s += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	if (page != sum) {
		s += "<a onclick='getGiftRecList(" + (sum)
				+ ")'><font color='blue'>尾页</font></a>"
	} else {
		s += "<span>尾页</span>"
	}
	s += "</td>"
	s += "</tr>"
	s += "</table>"
	$("#mydiv").html(s);
}



//分页查询用户
function getUserList(pages) {
	$.ajax( {
		url : "shwk!getUserList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Userrollback
	})
}

//分页查询商家
function getRestList(pages) {
	$.ajax( {
		url : "shwkrest!getRestList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Restrollback
	})
}

//分页查询商家评论
function getMessList(pages) {
	$.ajax( {
		url : "shwkmess!getMessList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Messrollback
	})
}

//分页查询菜单
function getMenuList(pages) {
	$.ajax( {
		url : "shwkmenu!getMenuList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Menurollback
	})
}

//分页查询菜单评论
function getMenuMsgList(pages) {
	$.ajax( {
		url : "shwkmenumsg!getMenuMsgList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : MenuMsgrollback
	})
}

//分页查询订单支付方式
function getPayList(pages){
	$.ajax( {
		url : "shwkpay!getPayList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Payrollback
	})
}

//分页查询礼品
function getGiftList(pages){
	$.ajax( {
		url : "shwkgift!getGiftList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : Giftrollback
	})
}

//分页查询礼品兑换记录
function getGiftRecList(pages){
	$.ajax( {
		url : "shwkgiftrec!getGiftRecordList.action?v=" + Math.random(),
		type : "post",
		data : {
			"page" : pages
		},
		dataType : "json",
		success : GiftRecrollback
	})
}



//通过id查询用户
function findbyid(id){
	$.ajax( {
		url : "shwk!FindbyId.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data){
			var us=data.user;
			var s = "<form  action='shwk!UpdaUser.action'  method='post' enctype='multipart/form-data'>";
			s+="<table align='center'>";
			s += "<tr><td><input type='hidden' id='userid' name='userid' value="+us.userid+" /></td></tr>"
			s += "<tr><td>姓名：<input type='text' id='username' name='username' value="+us.username+" /></td></tr>"
			s += "<tr><td>密码：<input type='text' id='pwd' name='pwd' value="+us.pwd+" /></td></tr>"
			s += "<tr><td>电话：<input type='text' id='tel' name='tel' value="+us.tel+" /></td></tr>"
			s += "<tr><td>邮件：<input type='text' id='email' name='email' value="+us.email+" /></td></tr>"
			s += "<tr><td>地址：<input type='text' id='address' name='address'value="+us.address+" /></td></tr>"
			s += "<tr><td>真实姓名：<input type='text' id='realname' name='realname' value="+us.realname+" /></td></tr>"
			s += "<tr><td>余额：<input type='text' id='balance' name='balance' value="+us.balance+" /></td></tr>"
			s += "<tr><td>积分：<input type='text' id='score' name='score' value="+us.score+" /></td></tr>"
			s += "<tr><td>注册日期：<input type='text' id='regdate' name='regdate' value="+us.regdate+" /></td></tr>"
			if (us.gender=='男') {
				s += "<tr><td>性别:<input type='radio' id='gender' name='gender' value='男' checked='checked'>男<input type='radio' id='gender' name='gender' value='女'> 女</td></tr>"
			}else{
				s += "<tr><td>性别:<input type='radio' id='gender' name='gender' value='男' >男<input type='radio' id='gender' name='gender' value='女' checked='checked'> 女</td></tr>"
			}
			if (us.authority==1) {
				s += "<tr><td>权限:<select id='authority' name='authority'><option value='1' selected='selected'>普通用户</option><option value='2'>配送员</option><option value='3'>店主</option><option value='4'>普通管理员</option><option value='5'>系统管理员</option> </select></td></tr>"
			}else if(us.authority==2){
				s += "<tr><td>权限:<select id='authority' name='authority'><option value='1' >普通用户</option><option value='2' selected='selected'>配送员</option><option value='3'>店主</option><option value='4'>普通管理员</option><option value='5'>系统管理员</option> </select></td></tr>"	
			}else if(us.authority==3){
				s += "<tr><td>权限:<select id='authority' name='authority'><option value='1' >普通用户</option><option value='2'>配送员</option><option value='3' selected='selected'>店主</option><option value='4'>普通管理员</option><option value='5'>系统管理员</option> </select></td></tr>"	
			}else if(us.authority==4){
				s += "<tr><td>权限:<select id='authority' name='authority'><option value='1' >普通用户</option><option value='2'>配送员</option><option value='3'>店主</option><option value='4' selected='selected'>普通管理员</option><option value='5'>系统管理员</option> </select></td></tr>"	
			}else {
				s += "<tr><td>权限:<select id='authority' name='authority'><option value='1' >普通用户</option><option value='2'>配送员</option><option value='3'>店主</option><option value='4'>普通管理员</option><option value='5' selected='selected'>系统管理员</option> </select></td></tr>"	
			}
			s += "<tr><td><input type='hidden' id='pic' name='pic' value="+us.photo+" /></td></tr>"
			s += "<tr><td>图片：<img src="+us.photo+" width=50px height:50px/><input type='file' id='photo' name='photo' accept='image/gif, image/jpeg, image/png'/></td></tr>"
			s += "<tr><td><input type='submit' value='提交'/><input type='button' id='btn' value='取消'/></td></tr>"
			s +="</table>"
			s += "</form>"
			$("#mydiv").html(s);
			$("#btn").click(function() {
				$.getJSON("shwk!getUserList.action?v=" + Math.random(), Userrollback) 
			})
		}
	})
}

//通过id查询商家
function findrestbyid(id){
	$.ajax( {
		url : "shwkrest!FindRestbyId.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data){
			var ts=data.rest;
			$.getJSON("shwk!getAllUser.action?v=" + Math.random(), function(data){
				var getAllUser = data.rows;
				var str="";
				for ( var i = 0; i < getAllUser.length; i++) {
					var k = getAllUser[i]   
					if (ts.rtowner==k.userid) {
							str +="<option value="+k.userid+"  selected='selected'>"+k.username+"</option>"
					}
					str +="<option value="+k.userid+">"+k.username+"</option>"	
					
				}
			var s = "<form  action='shwkrest!UpdaRest.action'  method='post' enctype='multipart/form-data'>";
			s +="<table align='center'>";
			s += "<tr><td><input type='hidden' id='rtid' name='rtid' value="+ts.rtid+" /></td></tr>"
			s += "<tr><td>店名：<input type='text' id='rtname' name='rtname' value="+ts.rtname+" /></td></tr>"
			s += "<tr><td>地址：<input type='text' id='rtaddr' name='rtaddr' value="+ts.rtaddr+" /></td></tr>"
			s += "<tr><td>店主：<select id='rtowner' name='rtowner'>"+str+"<select></td></tr>"
			s += "<tr><td><input type='hidden' id='pic' name='pic' value="+ts.rtstatus+" /></td></tr>"
			s += "<tr><td>图片：<img src="+ts.rtpic+" width=50px height:50px/><input type='file' id='photo' name='photo' accept='image/gif, image/jpeg, image/png'/></td></tr>"
			s +="<tr><td>公告<textarea rows='3' cols='20' id='rtcontent' name='rtcontent'>"+ts.rtcontent+"</textarea></td></tr>"
			s += "<tr><td>开店时间：<input type='text' id='rtdate' name='rtdate' value="+ts.rtdate+" /></td></tr>"
			s += "<tr><td>营业时间：<input type='text' id='rtonbuz' name='rtonbuz'value="+ts.rtonbuz+" /></td></tr>"
			if (ts.rtstatus==0) {
				s += "<tr><td>营业状态:<select id='rtstatus' name='rtstatus'><option value='0' selected='selected'>正在营业</option><option value='1'>休息中</option> </select></td></tr>"
			}else if(ts.rtstatus==1){
				s += "<tr><td>营业状态:<select id='rtstatus' name='rtstatus'><option value='0' >正在营业</option><option value='1' selected='selected'>休息中</option> </select></td></tr>"	
			}
			s += "<tr><td><input type='submit' value='提交'/><input type='button' id='btn' value='取消'/></td></tr>"
			s +="</table>"
			s += "</form>"
			$("#mydiv").html(s);
			$("#btn").click(function() {
				$.getJSON("shwkrest!getRestList.action?v=" + Math.random(), Restrollback) 
			})
		})
		}
	})
}

//通过id查询商家评论
function findmessbyid(id){
	$.ajax( {
		url : "shwkmess!FindMessbyId.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data){		
			var ts=data.mess;
			var str="";
			var str2="";
			$.getJSON("shwk!getAllUser.action?v=" + Math.random(), function(data){
				var getAllUser = data.rows;
				for ( var i = 0; i < getAllUser.length; i++) {
					var k = getAllUser[i]   	
					if (ts.muserid==k.userid) {
							str +="<option value="+k.userid+"  selected='selected'>"+k.username+"</option>"
					}
					str +="<option value="+k.userid+">"+k.username+"</option>"	
					
				}
			$.getJSON("shwkrest!getAllRest.action?v=" + Math.random(), function(data){
					var getAllRest = data.rows;
					for ( var i = 0; i < getAllRest.length; i++) {
						var k = getAllRest[i]                  
						if (ts.mrtid==k.rtid) {
								str2 +="<option value="+k.rtid+"  checked='checked'>"+k.rtname+"</option>"
						}
						str2 +="<option value="+k.rtid+">"+k.rtname+"</option>"				
					}
					
			var s = "<form  action='shwkmess!UpdaMess.action'  method='post' enctype='multipart/form-data'>";
			s +="<table align='center'>";
			s += "<tr><td><input type='hidden' id='mid' name='mid' value="+ts.mid+" /></td></tr>"
			s += "<tr><td>用户：<select id='muserid' name='muserid'>"+str+"<select></td></tr>"
			s += "<tr><td>店铺名：<select id='mrtid' name='mrtid'>"+str2+"<select></td></tr>"
			s +="<tr><td>评论：<textarea rows='3' cols='20' id='mcontent' name='mcontent'>"+ts.mcontent+"</textarea></td></tr>"
			s += "<tr><td>开店时间：<input type='text' id='mdate' name='mdate' value="+ts.mdate+" /></td></tr>"
			s += "<tr><td><input type='submit' value='提交'/><input type='button' id='btn' value='取消'/></td></tr>"
			s +="</table>"
			s += "</form>"
			$("#mydiv").html(s);
			$("#btn").click(function() {
				$.getJSON("shwkmess!getMessList.action?v=" + Math.random(),Messrollback) 
			})
			})
			})
		}
	})
}

//通过id查询菜单
function findmenubyid(id){
	$.ajax( {
		url : "shwkmenu!FindMenubyId.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data){
			var ts=data.menu;
			var str="";
			$.getJSON("shwkrest!getAllRest.action?v=" + Math.random(), function(data){
				var getAllRest = data.rows;
				for ( var i = 0; i < getAllRest.length; i++) {
					var k = getAllRest[i]                  
					if (ts.mrtid==k.rtid) {
							str +="<option value="+k.rtid+"  selected='selected'>"+k.rtname+"</option>"
					}
					str +="<option value="+k.rtid+">"+k.rtname+"</option>"				
				}
			var s = "<form  action='shwkmenu!UpdaMenu.action'  method='post' enctype='multipart/form-data'>";
			s+="<table align='center'>";
			s += "<tr><td><input type='hidden' id='muid' name='muid' value="+ts.muid+" /></td></tr>"
			s += "<tr><td>店铺名：<select id='murtid' name='murtid'>"+str+"<select></td></tr>"
			s += "<tr><td>菜名：<input type='text' id='muname' name='muname' value="+ts.muname+" /></td></tr>"
			s += "<tr><td>价格：<input type='text' id='muprice' name='muprice' value="+ts.muprice+" /></td></tr>"
			s += "<tr><td><input type='hidden' id='pic' name='pic' value="+ts.mupic+" /></td></tr>"
			s += "<tr><td>图片：<img src="+ts.mupic+" width=50px height:50px/>" +
			"<input type='file' id='photo' name='photo' accept='image/gif, image/jpeg, image/png'/></td></tr>"
			s += "<tr><td>类型：<input type='text' id='mutype' name='mutype' value="+ts.mutype+" /></td></tr>"
			s += "<tr><td>描述：<input type='text' id='mudesc' name='mudesc'value="+ts.mudesc+" /></td></tr>"
			s += "<tr><td>销量：<input type='text' id='musale' name='musale' value="+ts.musale+" /></td></tr>"
			if (ts.mustatus==0) {
				s += "<tr><td>是否售完:<select id='mustatus' name='mustatus'><option value='0' selected='selected'>正在销售</option><option value='1'>已售完</option></td></tr>"
			}else if(ts.mustatus==1){
				s += "<tr><td>是否售完:<select id='mustatus' name='mustatus'><option value='0' >正在销售</option><option value='1' selected='selected'>已售完</option></select></td></tr>"	
			}
			s += "<tr><td><input type='submit' value='提交'/><input type='button' id='btn' value='取消'/></td></tr>"			
			s +="</table>"
			s += "</form>"
			$("#mydiv").html(s);
			$("#btn").click(function() {
				$.getJSON("shwkmenu!getMenuList.action?v=" + Math.random(), Userrollback) 
			})
			})
		}
	})
}

//通过id查询菜单评论
function findmenumsgbyid(id){
	$.ajax( {
		url : "shwkmenumsg!FindMenuMsgbyId.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data){		
			var ts=data.menumsg;
			var str="";
			var str2="";
			$.getJSON("shwk!getAllUser.action?v=" + Math.random(), function(data){
				var getAllUser = data.rows;
				
				for ( var i = 0; i < getAllUser.length; i++) {
					var k = getAllUser[i]   
					if (ts.mmuserid==k.userid) {
							str +="<option value="+k.userid+"  selected='selected'>"+k.username+"</option>"
					}
					str +="<option value="+k.userid+">"+k.username+"</option>"			
				}
			$.getJSON("shwkmenu!getAllMenu.action?v=" + Math.random(), function(data){
					var getAllMenu = data.rows;
					for ( var i = 0; i < getAllMenu.length; i++) {
						var k = getAllMenu[i]     
						if (ts.mmmuid==k.muid) {
								str2 +="<option value="+k.muid+"  selected='selected'>"+k.muname+"</option>"
						}
						str2 +="<option value="+k.muid+">"+k.muname+"</option>"				
					}
					
			var s = "<form  action='shwkmenumsg!UpdaMenuMsg.action'  method='post' enctype='multipart/form-data'>";
			s +="<table align='center'>";
			s += "<tr><td><input type='hidden' id='mmid' name='mmid' value="+ts.mmid+" /></td></tr>"
			s += "<tr><td>用户：<select id='mmuserid' name='mmuserid'>"+str+"<select></td></tr>"
			s += "<tr><td>菜名：<select id='mmmuid' name='mmmuid'>"+str2+"<select></td></tr>"
			s +="<tr><td>评论：<textarea rows='3' cols='20' id='mmcontent' name='mmcontent'>"+ts.mmcontent+"</textarea></td></tr>"
			s +="<tr><td>评分：</td></tr>"	
			if (ts.mmscore==1) {
				s += "<tr><td>评分:<input type='radio' id='mmscore' name='mmscore' value='1' checked='checked'>★☆☆☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='2'>★★☆☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='3'>★★★☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='4'>★★★★☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='5'>★★★★★</td></tr>"
			}else if (ts.mmscore==2) {
				s += "<tr><td>评分:<input type='radio' id='mmscore' name='mmscore' value='1' >★☆☆☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='2' checked='checked'>★★☆☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='3'>★★★☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='4'>★★★★☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='5'>★★★★★</td></tr>"			
			}else if (ts.mmscore==3) {
				s += "<tr><td>评分:<input type='radio' id='mmscore' name='mmscore' value='1' >★☆☆☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='2'>★★☆☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='3' checked='checked'>★★★☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='4'>★★★★☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='5'>★★★★★</td></tr>"	
			}else if (ts.mmscore==4) {
				s += "<tr><td>评分:<input type='radio' id='mmscore' name='mmscore' value='1' >" +
						"★☆☆☆☆<input type='radio' id='mmscore' name='mmscore' value='2'>★★☆☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='3'>★★★☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='4' checked='checked'>★★★★☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='5'>★★★★★</td></tr>"	
			}else if(ts.mmscore==5){
				s += "<tr><td>评分:<input type='radio' id='mmscore' name='mmscore' value='1' >★☆☆☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='2'>★★☆☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='3'>★★★☆☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='4'>★★★★☆" +
						"<input type='radio' id='mmscore' name='mmscore' value='5' checked='checked'>★★★★★</td></tr>"
			}
			s += "<tr><td>评论时间：<input type='text' id='mmdate' name='mmdate' value="+ts.mmdate+" /></td></tr>"
			s += "<tr><td><input type='submit' value='提交'/><input type='button' id='btn' value='取消'/></td></tr>"
			s +="</table>"
			s += "</form>"
			$("#mydiv").html(s);
			$("#btn").click(function() {
				$.getJSON("shwkmenumsg!getMenuMsgList.action?v=" + Math.random(),MenuMsgrollback) 
			})
			})
			})
		}
	})
}

//通过id查询订单支付方式
function findpaybyid(id){
	$.ajax( {
		url : "shwkpay!FindPaybyId.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data){		
			var ts=data.pay;
			var s = "<form  action='shwkpay!UpdaPay.action'  method='post' enctype='multipart/form-data'>";
			s +="<table align='center'>";
			s += "<tr><td><input type='hidden' id='pid' name='pid' value="+ts.pid+" /></td></tr>"
			s += "<tr><td>订单编号："+ts.poid+"</td></tr>"	
			if (ts.ptype=='余额支付') {
				s += "<tr><td>支付方式:<input type='radio' id='ptype' name='ptype' value='余额支付' checked='checked'>余额支付" +
					"<input type='radio' id='ptype' name='ptype' value='货到付款'>货到付款</td></tr>"
			}else if (ts.ptype=='货到付款') {
				s += "<tr><td>评分:<input type='radio' id='ptype' name='ptype' value='余额支付' >余额支付" +
					"<input type='radio' id='ptype' name='ptype' value='货到付款' checked='checked'>货到付款</td></tr>"			
			}
			s += "<tr><td><input type='submit' value='提交'/><input type='button' id='btn' value='取消'/></td></tr>"
			s +="</table>"
			s += "</form>"
			$("#mydiv").html(s);
			$("#btn").click(function() {
				$.getJSON("shwkpay!getPayList.action?v=" + Math.random(), Payrollback) 
			})
		}
	})
}

//通过id查询礼品
function findgiftbyid(id){
	$.ajax( {
		url : "shwkgift!findGiftById.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data){
			var ts=data.gift;
			var s = "<form  action='shwkgift!UpdaGift.action'  method='post' enctype='multipart/form-data'>";
			s +="<table align='center'>";
			s += "<tr><td><input type='hidden' id='gid' name='gid' value="+ts.gid+" /></td></tr>"
			s += "<tr><td>礼品名：<input type='text' id='gname' name='gname' value="+ts.gname+" /></td></tr>"
			s += "<tr><td><input type='hidden' id='pic' name='pic' value="+ts.gpic+" /></td></tr>"
			s += "<tr><td>图片：<img src="+ts.gpic+" width=50px height:50px/><input type='file' id='photo' name='photo' accept='image/gif, image/jpeg, image/png'/></td></tr>"
			s += "<tr><td>所需积分：<input type='text' id='greqscore' name='greqscore' value="+ts.greqscore+" /></td></tr>"
			s += "<tr><td>已兑换数量：<input type='text' id='gcount' name='gcount' value="+ts.gcount+" /></td></tr>"
			s += "<tr><td>总库存数量：<input type='text' id='gsum' name='gsum'value="+ts.gsum+" /></td></tr>"
			s +="<tr><td>公告<textarea rows='3' cols='20' id='gdesc' name='gdesc'>"+ts.gdesc+"</textarea></td></tr>"
			s += "<tr><td><input type='submit' value='提交'/><input type='button' id='btn' value='取消'/></td></tr>"
			s +="</table>"
			s += "</form>"
			$("#mydiv").html(s);
			$("#btn").click(function() {
				$.getJSON("shwkgift!getGiftList.action?v=" + Math.random(), Giftrollback)
			})
		}
	})
}

//通过id查询礼品兑换记录
function findGiftRecbyid(id){
	alert(id)
	$.ajax( {
		url : "shwkgiftrec!FindGiftRecordbyId.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data){		
			var ts=data.grec;
			alert("AAAAAAAAAAAAAAAA")
			var str="";
			var str2="";
			$.getJSON("shwk!getAllUser.action?v=" + Math.random(), function(data){
				var getAllUser = data.rows;		
				for ( var i = 0; i < getAllUser.length; i++) {
					var k = getAllUser[i]   
					if (ts.gruserid==k.userid) {
							str +="<option value="+k.userid+"  selected='selected'>"+k.username+"</option>"
					}
					str +="<option value="+k.userid+">"+k.username+"</option>"			
				}
			$.getJSON("shwkgift!getAllGift.action?v=" + Math.random(), function(data){
					var getAllGift = data.rows;
					for ( var i = 0; i < getAllGift.length; i++) {
						var k = getAllGift[i]     
						if (ts.grgid==k.gid) {
								str2 +="<option value="+k.gid+"  selected='selected'>"+k.gname+"</option>"
						}
						str2 +="<option value="+k.gid+">"+k.gname+"</option>"				
					}
					
			var s = "<form  action='shwkgiftrec!UpdaGiftRecord.action'  method='post' enctype='multipart/form-data'>";
			s +="<table align='center'>";
			s += "<tr><td><input type='hidden' id='grid' name='grid' value="+ts.grid+" /></td></tr>"
			s += "<tr><td>兑换人：<select id='gruserid' name='gruserid'>"+str+"<select></td></tr>"
			s += "<tr><td>礼物名：<select id='grgid' name='grgid'>"+str2+"<select></td></tr>"
			s += "<tr><td>兑换数量：<input type='text' id='grnum' name='grnum' value="+ts.grnum+" /></td></tr>"
			s += "<tr><td>兑换日期：<input type='text' id='grdate' name='grdate' value="+ts.grdate+" /></td></tr>"
			if (ts.grstatus==0) {
				s += "<tr><td>是否配送:<select id='grstatus' name='grstatus'><option value='0' selected='selected'>未配送</option><option value='1'>已配送</option></select></td></tr>"
			}else if(ts.grstatus==1){
				s += "<tr><td>是否配送:<select id='grstatus' name='grstatus'><option value='0' >未配送</option><option value='1' selected='selected'>已配送</option></select></td></tr>"	
			}
			s += "<tr><td><input type='submit' value='提交'/><input type='button' id='btn' value='取消'/></td></tr>"
			s +="</table>"
			s += "</form>"
			$("#mydiv").html(s);
			$("#btn").click(function() {
				$.getJSON("shwkmenumsg!getMenuMsgList.action?v=" + Math.random(),MenuMsgrollback) 
			})
			})
			})
		}
	})
}





//删除用户
function deleUser(id){
	$.ajax( {
		url : "shwk!deleUser.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(){
			$.getJSON("shwk!getUserList.action?v=" + Math.random(), Userrollback);
		}
	})
}

//删除商家
function deleRest(id){
	$.ajax( {
		url : "shwkrest!deleRest.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(){
			$.getJSON("shwkrest!getRestList.action?v=" + Math.random(), Restrollback);
		}
	})
}

//删除商家评论
function deleMess(id){
	$.ajax( {
		url : "shwkmess!deleMess.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(){
			$.getJSON("shwkmess!getMessList.action?v=" + Math.random(),Messrollback) 
		}
	})
}

//删除菜单
function deleMenu(id){
	$.ajax( {
		url : "shwkmenu!deleMenu.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(){
			$.getJSON("shwkmenu!getMenuList.action?v=" + Math.random(),Menurollback) 
		}
	})
}

//删除菜单评论
function deleMenumsg(id){
	$.ajax( {
		url : "shwkmenumsg!deleMenuMsg.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(){
			$.getJSON("shwkmenumsg!getMenuMsgList.action?v=" + Math.random(), MenuMsgrollback)
		}
	})
}

//删除订单支付
function delepay(id){
	$.ajax( {
		url : "shwkpay!delePay.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(){
			$.getJSON("shwkpay!getPayList.action?v=" + Math.random(), Payrollback) 
		}
	})
}

//删除礼品
function deleGift(id){
	$.ajax( {
		url : "shwkgift!deleGift.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(){
			$.getJSON("shwkgift!getGiftList.action?v=" + Math.random(), Giftrollback) 
		}
	})
}

//删除礼品兑换记录
function deleGiftRec(id){
	$.ajax( {
		url : "shwkgiftrec!deleGiftRecord.action?v=" + Math.random(),
		type : "post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(){
			$.getJSON("shwkgiftrec!getGiftRecordList.action?v=" + Math.random(), GiftRecrollback)
		}
	})
}
