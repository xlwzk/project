
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
		        	if(fiag){
		        		 $("#myTable").datagrid("appendRow",{});// 添加一个新行
			        	 index =  $("#myTable").datagrid("getRows").length-1; // 得到新增行的下标
			        	   $("#myTable").datagrid("beginEdit",index);// 让这一行处于编辑状态
			        	        	    	 fiag=false;
			        	        	    	 op="add";
		        	}
		        	 
		        	
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
		        	 var rows =  $("#myTable").datagrid("getSelections");
		        	 if(rows.length==0){
		        		 $.messager.alert("提示","请选择一行","info")
		        	 }else if(rows.length>1){
		        		  $.messager.alert("提示","一次只能选择一行","info")
		        	 }else{
		        		   if(fiag){
		        			   op="update";  // 是修改操作
		        			   fiag=false; // 表示现在不能做其他操作
		        			   r=rows[0]; // 获得选中的行
		        			   // 根据行来取到当前的下标
		        			   index=$("#myTable").datagrid("getRowIndex",r);  // 通过行来取到当前的下标
		        		       $("#myTable").datagrid("beginEdit",index); // 让此行处于编辑状态
		        		   }
		        	 }
		        	 
		        	 
		         }},
		         {text:"保存",iconCls:"icon-save",handler:function(){
		        	 $("#myTable").datagrid("endEdit",index); // 结束编辑状态
		        	 fiag=true;
		        	
		         }},
		         {text:"取消",iconCls:"icon-cancel",handler:function(){
		        	 $("#myTable").datagrid("rejectChanges");// 回滚操作，让没有保存的数据，全部取消
		        	 flag = true;
		        	 
		         }}
			
		],
		// 当结束编辑的时候的必须运行的事件
		onAfterEdit:function(index,r){// 当前下标，当前行的数据（JSon）
		
		     if(op=="add"){
		    $.post("cs!AddMenu.action?v="+Math.random(),r,function(data){
		          	var fiag=eval("("+data+")");
		          	if(fiag.addfiag>0){
		          		$.messager.show({
			          		showType:"slide",
			          		showSpeed:600,
			          		title:"提示",
			          		msg:"添加成功",
			          		timeout:3000   		
			          	});        		
		          	}else{
		          		$.messager.show({
			          		showType:"slide",
			          		showSpeed:600,
			          		title:"提示",
			          		msg:"添加失败",
			          		timeout:3000   		
			          	});      
		          	}
		             
		    }) 
		    $("#mytable").datagrid("load");
		     }
		     if(op=="update"){
		    	 $.post("cs!UpdateMenu.action?v="+Math.random(),r,function(data){
		    		 var fiag=eval("("+data+")");
		             if(fiag.updatefiag>0){
		            	 $.messager.show({
				          		showType:"slide",
				          		showSpeed:600,
				          		title:"提示",
				          		msg:"修改成功",
				          		timeout:3000   		
				          	}); 
		             }else{
		            	 $.messager.show({
				          		showType:"slide",
				          		showSpeed:600,
				          		title:"提示",
				          		msg:"修改失败",
				          		timeout:3000   		
				          	}); 
		            	 
		             }
			          	       	
			    }) 
			    	 $("#mytable").datagrid("reload");
		    	 
		     }
		     
		     

	           },
	
		
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
			    	 
			         formatter:function(value,row,index){return '<img src="'+row.MUPIC+'" style="height:60px"/>';}
			                  
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
				    		 return "<span style='color:red'><b>已接单</b></span>";		    
				    		 }else if(value==4){
		    	               	 return "<span style='color:red'><b>配送中</b></span>";
				    	          }else if(value==5){
			    		 return "<span style='color:red'><b>交易成功</b></span>";
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






/** ******************************************************参考部分******************************************************** */


