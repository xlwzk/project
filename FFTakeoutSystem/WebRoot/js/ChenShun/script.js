
///**
// * 我的资料
// * @return
// */
//function ts_user(){	
//	
//
//	    
//	$("#myTable").html("ssss");
//
//	$('#searchDiv').panel('close');
//
//	
//	
//}


/**
 * 我的菜单
 * 
 * @return
 */
function ts_menu(){
	$("#myTable").html("");
	
	$('#searchDiv').panel('open');
	 var s="   菜单名称：<input type='text'  id='name'/>";
     s+="  菜单价格 :<input type='text'  id='price'/>"
     s+="   菜单类型: <input  type='text'  name='type'/>"
	  s+=" <a id='searchBtn'>查询</a>"	
     $("#searchDiv").html(s);
	  var fiag=true;
	  var index=0;  // 记录所在行的下表
	  var op;
	  $("input[name=type]").combobox({    // 自动加载数据库的所有菜的品种
		  url:"cs!LoadType.action?v="+Math.random(),
		  valueField:"MUTYPE",
		  textField:"MUTYPE",     
		  panelHeight:"108"  // 设置高度
		  });

	  $('#searchBtn').linkbutton({  // 渲染成按钮
	  	iconCls:'icon-search'  
	  }) 

	// 单击查询时
	$("#searchBtn").click(function(){
	var name=$("#name").val();
	var price=$("#price").val();
	var type=$(":input[name=type]").val();
	$("#myTable").datagrid("load",{"name":name,"price":price,"type":type}); // 传值
	})
	$("#searchDiv").panel("expand");// 默认展开
	$("#myTable").datagrid({
		toolbar:[
		         {text:"新增",iconCls:"icon-add",handler:function(){ 
		        	 
		        	 //添加首先添加窗口值改变
				    	$("#mydiv").dialog({
				    		iconCls:'icon-add',
				    		title:"添加"	
				    	});
				    	$("#mydiv").dialog("open");//打开添加窗口
				    	$(document).ready(function(){  $("input[name=muname]").focus();});
				    	$("#addBtn").click(function(){
				    		 
				            $.post("cs!AddMenu.action?v="+Math.random(),$("#myfrm").serialize(),function(data){
				            	  var o = eval("("+data+")");
				                  if(o.addfiag){   //判断是否是否有null值
				                
				    		    	  $.messager.alert("添加提示","添加成功","info")
				    		    	  $("#mydiv").dialog("close");//关闭
				    	    				$("#myfrm").form("clear");  //表单清空
				    	    				$("#myTable").datagrid("reload");  //从当前页加载
				                 }else{
				                	  $.messager.alert("添加提示","请填写完整信息","info")
				                	 
				                 }
				            	
				                  $('#mydiv').dialog({ 
				                	  onClose: function () { 
				                	  //解决弹出窗口关闭后，验证消息还显示在最上面 
				                	  $('.tooltip.tooltip-right').hide(); 
				                	  } 
				                	  });
				            })
				      
				    		
				    	})
				    	 $("#quxiao").click(function(){         //单机取消时
	    		         $("#mydiv").dialog("close");//关闭窗口
	    	})
				    	
		        	
		        	
		         }},
		         {text:"删除",iconCls:"icon-remove",handler:function(){
		        	  // 获取用户选中的一行，返回值是数组方式
				    	
			    	  var rows = $("#myTable").datagrid("getSelections");  // 取得所有选中的数据（数据类型是ListMap>）
			    	  if(rows.length==0){   // 如果用户没有选择
			    		  $.messager.alert('删除提示','请选择要删除的数据...','question'); 
			    	}else{
			          var ids="";
			          $.each(rows,function(index,m){
			          	ids+=m.MUID+",";
			        })
			        $.post("cs!DeleteMenu.action?v="+Math.random(),{"ids":ids},function(data)  // 有问题
			        {
			        	var fiag=eval("("+data+")");
			             alert(fiag.deletefiag)
			          if(fiag.deletefiag>0){
			        	  $.messager.alert("删除提示",'删除成功','warning'); 
			        	  $("#myTable").datagrid("reload");  // 重新加载数据，但是数据会停留在当前页
			          }else{
			        	  $.messager.alert("删除提示",'删除失败','warning'); 
			        	   
			          }
			           	        	
			        })    		
		        	 
			    	}
		        	 
		         }},
		         {text:"修改",iconCls:"icon-edit",handler:function(){
//		        
		        		var rows=$("#myTable").datagrid("getSelections");
						if(rows.length==0){
							   $.messager.alert("修改提示",'请选择勾上修改内容','warning'); 
							
						}else if(rows.length>1){		
							  $.messager.alert("修改提示",'抱歉！一次只能修改一行','warning'); 
						}else{
							$("#mydiv").dialog({
								conCls:"icon-update",
							    title:"修改"
							})
						  
							var r=rows[0]//通过下标取到选中的行
							           
					    $("#myfrm").form("load",{"muname":r.MUNAME,"muprice":r.MUPRICE,"mutype":r.MUTYPE,"mudesc":r.DESC,"mustatus":r.MUSTATUS,"mudesc":r.MUDESC});
							   $("img[name=image]").attr("src",r.MUPIC);
							  
							$("#mydiv").dialog("open");
							
							$("#addBtn").click(function(){   //执行修改操作
							    $.post("cs!UpdateMenu.action?v="+Math.random()+"&muid="+r.MUID,$("#myfrm").serialize(),function(data){
							    $("#mydiv").dialog("close");//关闭对话框
							    $("#myfrm")[0].reset(); //重置表单数据 
							    $("#myTable").datagrid("reload");//重新加载数据	
							    var fiag=eval("("+data+")");
							    if(fiag.updatefiag){
							         $.messager.alert("修改提示","修改成功","info");
					
							         checkOnSelect =true;
						             $("#myfrm").datagrid("selectRow", rowIndex);
							    }else{
							    	  $.messager.alert("修改提示","修改成失败","info");
							    }
							    })
							    
								
							})
					    	 $("#quxiao").click(function(){         //单机取消时
			    		         $("#mydiv").dialog("close");//关闭窗口
			    	})
						
						
							
						}
		        	 
		        	 
		        	 
		        	 
		        	 
		        	 
		        	 
		        	 
		         }},
		       
		         {text:"导出Excl数据",iconCls:"icon-undo",handler:function(){
		        	 
		        	// $.post("cs!ExportMenu.action?v="+Math.random(),null,function(){})
		        	 window.location="cs!ExportMenu.action?v="+Math.random();
		         }},
		       
		         {text:"查看数据图表",iconCls:"icon-redo",handler:function(){
		        	 window.location="pages/chenshun/dataview.jsp"  
		     
		         }},
		       
			
		],

	           

		 // 加载数据
		   idField:'MUID',// 標識id字段
			url:"cs!LoadMenu.action?v="+Math.random(), // 从哪里加载数据
			fitColumns:true,// 适应宽度，防止 出现左右滚动条
			method:"post",
			loadMsg:"正在加载，请稍等....",
			striped:true,// 奇偶行变行
			pagination:true,// 显示分页
			pageList:[1,2,3,5,10,20,30,50],
			pageSize:5,// 默认显示5条
			rownumbers:true,// 显示行号
			singleSelect:false,// 只允许中一行
			
			columns:[[	    
			      {field:'R',title:'编号',width:300,align:'center',checkbox:true},
			      {field:'MUNAME',title:'菜单名称',width:300,align:'center',sortable:true,
			    	  editor:{   // 编辑状态
			    	    type:"validatebox",
			            options:{
			    	     required:true,
			    	     missingMessage:"菜单名称不能为空"
			                }    	  
			          }     	    
			      
			      },
			     {field:'MUPRICE',title:'菜单价格',width:300,align:'center',sortable:true,
			    	  editor:{
			    	    type:"numberbox",
			            options:{
			    	     required:true,
			    	     missingMessage:"菜单价格不能为空",
			    	     min:0 	    	 
			                }
			    	  
			          }     	    	  
			     
			     },
			     {field:'MUTYPE',title:'菜单类型',width:300,align:'center',sortable:true,
			    	 editor:{
			    	   type:"combobox",
			    	   options:{
			    	        data:[{text:"川菜",value:"川菜"},{text:"粤菜",value:"粤菜"},{text:"徽菜",value:"徽菜"},{text:"湘菜",value:"湘菜"},{text:"鲁菜",value:"鲁菜"}],
			    	        textField:"text",
			    	        valueField:"value",
			    	        editable:false,   // 禁止用户输入
			    	        required:true,
			    	        panelHeight:"108",
			    	        missingMessage:"菜品类型不能为空"
			         }
			    	    	  
			      }  
			    	 
			     },	
			     {field:'MUSALE',title:'菜单销量',width:300,align:'center',sortable:true,
			    	 editor:{
			    	    type:"numberbox",
			            options:{
			    	     required:true,
			    	     missingMessage:"菜单销量不能为空",
			               	    	 
			                }
			    	  
			          }     		    
			    	
			     
			     },		
			     {field:'MUSTATUS',title:'菜单状态',width:300,align:'center',sortable:true,formatter:function(value,row,index){
			    	 if(row.MUSTATUS==0){
			    		 return "<span style='color:green'><b>销售中</b></span>";
				    	 }else{
				    		 return "<span style='color:red'><b>已下架</b></span>";
				    	 }
				    	    	 
				     },
			    

			    	 editor:{
			    	   type:"combobox",
			    	   options:{
			    	      data:[{text:"已下架",value:"1"},{text:"销售中",value:"0"}],
			    	        textField:"text",
			    	        valueField:"value",
			    	        editable:false,   // 禁止用户输入
			    	        required:true,
			    	        missingMessage:"菜单状态不能为空"
			         }
			    	    	  
			      }  
				    	
			    	
			     
			     
			     },		
			
			     {title:'图片',field:'image',width:200,heigth:200,align:'center',		    	 
			         formatter:function(value,row,index){
			    	 return '<img src="'+row.MUPIC+'" style="height:60px"/>';
			    	 },
			    	 editors:{   

			    		       text: {   

			    		           init: function(container, options){   

			    		               var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container);   

			    		              return input;   

			    		                                                }
			    		           
			               	
			    	 
			                	      }
			    	          }


			     
			     
			                  
			     }
			     
			     
			    
			    
			    
			]]		
		

	});

}
/**
 * 我的订单
 * 
 * @return
 */
function ts_order(){
	$("#myTable").html("");
	$('#searchDiv').panel('open');  // 打开面板
	 var s="订单状态：<select id='status' style='width: 110px'>";
      s+=" <option value='-1'>全部订单</option>";
      s+=" <option value='0'>购物车中</option>";
      s+=" <option value='1'>已下单(未支付)</option>";
      s+=" <option value='2'>已付款</option>";
      s+=" <option value='3'>已接单</option>";
      s+=" <option value='4'>配送中</option>";
      s+=" <option value='5'>交易成功</option>";
      s+="</select>";   
	  s+=" <a id='searchBtn'>查询</a>"	
    $("#searchDiv").html(s);
	
	  var fiag=true;
	  var index=0;  // 记录所在行的下表
	  var op;
	  $("#status").combobox({    // 加载所有订单
	        editable:false,
		  panelHeight:"146"
		  });

	  $('#searchBtn').linkbutton({  // 渲染成按钮
	  	iconCls:'icon-search'  
	  }) 
  $('#status').combobox('select',-1);  // 默认选中
	// 单击查询时
	$("#searchBtn").click(function(){	
     // 获取combobox选中的 值
    var status=$('#status').combobox('getValue');
	$("#myTable").datagrid("load",{"status":status}); // 传值
	})
	$("#searchDiv").panel("expand");// 默认展开
	$("#myTable").datagrid({
		toolbar:[
		         {text:"导出Excl数据",iconCls:"icon-undo",handler:function(){
		        	 
			        	// $.post("cs!ExportMenu.action?v="+Math.random(),null,function(){})
			        	 window.location="cs!ExportTable.action?v="+Math.random();
			         }},
		         
		         
		         ],
	
		
		 // 加载数据
		   idField:'OID',// 標識id字段
			url:"cs!LoadOrder.action?v="+Math.random(), // 从哪里加载数据
			fitColumns:true,// 适应宽度，防止 出现左右滚动条
			loadMsg:"正在加载，请稍等....",
			method:"post",
		
			striped:true,// 奇偶行变行
			pagination:true,// 显示分页
			pageList:[1,2,3,5,10,20,30,50],
			pageSize:5,// 默认显示5条
			rownumbers:true,// 显示行号
			singleSelect:false,// 只允许中一行
			columns:[[	    
			      {field:'OID',title:'编号',width:300,align:'center',checkbox:true},
			      {field:'USERNAME',title:'客户名称',width:300,align:'center',sortable:true},
			     {field:'MUNAME',title:'菜单名称',width:300,align:'center',sortable:true},
			     {field:'MUPRICE',title:'菜单单价',width:300,align:'center',sortable:true},	
			     {field:'ODATE',title:'订单时间',width:300,align:'center',sortable:true},		
			    {field:'OSTATUS',title:'订单状态',width:300,align:'center',sortable:true,formatter:function(value,row,index){
		    	    if(value==0){ 
		    		 return "<span style='color:green'><b>购物车中</b></span>";
			    	 }else if(value==1){
			    		 return "<span style='color:red'><b>已下单(未支付)</b></span>";
			    	 }else if(value==2){
			    		 return "<span style='color:red'><b>用户已付款</b></span>";  
				    	 }else if(value==3){
				    		 return "<span style='color:#FF8C00'><b>已接单</b></span>";		    
			    		 }else if(value==4){
	    	               	 return "<span style='color:#FFFFFF'><b>配送中</b></span>";
			    	          }else if(value==5){
		    		 return "<span style='color:#999999'><b>交易成功</b></span>";
			    	 }
                     
		     }
		     
		     },		
			     {field:'OCOUNT',title:'菜单数量',width:300,align:'center',sortable:true},
			     {field:'SUM',title:'金额',width:300,align:'center',sortable:true}
			    
			    
			]]		
		

	});
	
	
	
}


/**
 * 柱状数据视图
 * 
 * @return
 */
function  dataview(){
	// 初始化div，准备渲染
	var mychart = echarts.init(document.getElementById("mydiv"));
	
	// 设置相关属性
	var myoption={
			title:{
				text:'数据汇总',
			},
			legend:{
				data:['菜单名称']
			},
			tooltip:{},
			xAxis:{
				data:[]
			},
			yAxis:{},
			series:[{
			        name:'菜单名称',
			        type:'bar',
			        data:[]
			}],
			toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {
                        show: true,
                        type: ['pie', 'funnel']
                    },
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            }
			
	};
	// 渲染---把设置的属性与div关联
	mychart.setOption(myoption);
	
	$.getJSON("echarts!showBar.action?v="+Math.random()).done(function(data){
		mychart.setOption({
			xAxis:{
				data:data.columnsName
			},
			series:[{
		        name:'菜单名称',
		        type:'bar',
		        data:data.vals
			}]
		});
	});
	

}

/**
 * 已处理订单
 * @return
 */
function ts_order_yes(){
	$("#myTable").html("");
	
	 $('div.datagrid-toolbar a').hide();  //隐藏
	
	
	
	$('#searchDiv').panel('close');  // 打开面板

	$("#myTable").datagrid({
		
		      toolbar:[
		         {text:"开始接单",iconCls:"icon-add",handler:function(){  
		        	 setTimeout($("#myTable").datagrid("reload"),1000); //指定1秒刷新一次 
			         }},
		         
		         
		         ],
	
		
		 // 加载数据
		   idField:'OID',// 標識id字段
			url:"cs!LoadOrderYes.action?v="+Math.random(), // 从哪里加载数据
			fitColumns:true,// 适应宽度，防止 出现左右滚动条
			loadMsg:"正在加载，请稍等....",
			method:"post",
		
			striped:true,// 奇偶行变行
			pagination:true,// 显示分页
			pageList:[1,2,3,5,10,20,30,50],
			pageSize:5,// 默认显示5条
			rownumbers:true,// 显示行号
			singleSelect:false,// 只允许中一行
			columns:[[	    
			      {field:'OID',title:'编号',width:300,align:'center',checkbox:true},
			      {field:'USERNAME',title:'客户名称',width:300,align:'center',sortable:true},
			     {field:'MUNAME',title:'菜单名称',width:300,align:'center',sortable:true},
			     {field:'MUPRICE',title:'菜单单价',width:300,align:'center',sortable:true},	
			     {field:'ODATE',title:'订单时间',width:300,align:'center',sortable:true},		
			    {field:'OSTATUS',title:'订单状态',width:300,align:'center',sortable:true,formatter:function(value,row,index){
		    	    if(value==0){ 
		    		 return "<span style='color:green'><b>购物车中</b></span>";
			    	 }else if(value==1){
			    		 return "<span style='color:red'><b>已下单(未支付)</b></span>";
			    	 }else if(value==2){
			    		 return "<span style='color:red'><b>用户已付款</b></span>";  
				    	 }else if(value==3){
				    		 return "<span style='color:#FF8C00'><b>已接单</b></span>";		    
				    		 }else if(value==4){
		    	               	 return "<span style='color:#FFFFFF'><b>配送中</b></span>";
				    	          }else if(value==5){
			    		 return "<span style='color:#999999'><b>交易成功</b></span>";
			    	 }
                    
		     }
		     
		     },		
			     {field:'OCOUNT',title:'菜单数量',width:300,align:'center',sortable:true},
			     {field:'SUM',title:'金额',width:300,align:'center',sortable:true},
			     {field:'opt',title:'操作',width:160,align:'center',  
			            formatter:function(value,rec){  
			                var btn = '<a class="editcls" onclick="editRow(\''+rec.OID+'\')" href="javascript:void(0)">查看详情</a>';  
			                return btn;  
			            }  
			        }  
			    
			]]	,	
			 onLoadSuccess:function(data){  
		        $('.editcls').linkbutton({text:'查看详情',plain:true,iconCls:'icon-add'});  
		    }  
	
	       
		

	});
	
	
	
}

/**
 * 未处理订单
 */
function ts_order_no(){
	$("#myTable").html("");
	
	 $('div.datagrid-toolbar a').hide();  //隐藏
	
	
	
	$('#searchDiv').panel('close');  // 打开面板

	$("#myTable").datagrid({
		
		toolbar:[
		         {text:"开始接单",iconCls:"icon-add",handler:function(){
		        	 setTimeout($("#myTable").datagrid("reload"),1000); //指定1秒刷新一次 
			         }},
		         
		         
		         ],
	
		
		 // 加载数据
		   idField:'OID',// 標識id字段
			url:"cs!LoadOrderNo.action?v="+Math.random(), // 从哪里加载数据
			fitColumns:true,// 适应宽度，防止 出现左右滚动条
			loadMsg:"正在加载，请稍等....",
			method:"post",
		
			striped:true,// 奇偶行变行
			pagination:true,// 显示分页
			pageList:[1,2,3,5,10,20,30,50],
			pageSize:5,// 默认显示5条
			rownumbers:true,// 显示行号
			singleSelect:false,// 只允许中一行
			columns:[[	    
			      {field:'OID',title:'编号',width:300,align:'center',checkbox:true},
			      {field:'USERNAME',title:'客户名称',width:300,align:'center',sortable:true},
			     {field:'MUNAME',title:'菜单名称',width:300,align:'center',sortable:true},
			     {field:'MUPRICE',title:'菜单单价',width:300,align:'center',sortable:true},	
			     {field:'ODATE',title:'订单时间',width:300,align:'center',sortable:true},		
			    {field:'OSTATUS',title:'订单状态',width:300,align:'center',sortable:true,formatter:function(value,row,index){
		    	    if(value==0){ 
		    		 return "<span style='color:green'><b>购物车中</b></span>";
			    	 }else if(value==1){
			    		 return "<span style='color:red'><b>已下单(未支付)</b></span>";
			    	 }else if(value==2){
			    		 return "<span style='color:red'><b>用户已付款</b></span>";  
				    	 }else if(value==3){
				    		 return "<span style='color:#FF8C00'><b>已接单</b></span>";		    
				    		 }else if(value==4){
		    	               	 return "<span style='color:#FFFFFF'><b>配送中</b></span>";
				    	          }else if(value==5){
			    		 return "<span style='color:#999999'><b>交易成功</b></span>";
			    	 }
                     
		     }
		     
		     },		
			     {field:'OCOUNT',title:'菜单数量',width:300,align:'center',sortable:true},
			     {field:'SUM',title:'金额',width:300,align:'center',sortable:true},
			     {field:'opt',title:'操作',width:160,align:'center',  
			            formatter:function(value,rec){  
			                var btn = '<a class="editcls" onclick="editRow(\''+rec.OID+'\')" href="javascript:void(0)">接单</a>';  
			                return btn;  
			            }  
			        }  
			    
			]]	,	
			 onLoadSuccess:function(data){  
		        $('.editcls').linkbutton({text:'接单',plain:true,iconCls:'icon-add'});  
		    }  
	
	       
		

	});
	
	
}
/**
 * 单机接单时
 * @param id
 * @return
 */
function editRow(id){
	  $.post("cs!JieDanOrder.action?v="+Math.random(),{"id":id},function(data){
		  var o = eval("("+data+")");
		  if(o.jiedan==1){
			  $("#myTable").datagrid("reload");  // 重新加载数据，但是数据会停留在当前页
			  $.messager.show({
	          		showType:"slide",
	          		showSpeed:600,
	          		title:"提示",
	          		msg:"接单成功，已通知配送人员！",
	          		timeout:3000   		
	          	});        	
			     
		      }else{
		    	  $.messager.show({
		          		showType:"slide",
		          		showSpeed:600,
		          		title:"提示",
		          		msg:"接单失败",
		          		timeout:3000   		
		          	});        	  
		    	  
		      }
		  
		  
		  })
}







/** ******************************************************参考部分******************************************************** */


