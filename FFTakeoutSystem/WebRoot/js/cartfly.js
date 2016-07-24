$(function() {
	var offset = $("#end").offset();
	$(".addcar").click(function(event) {
		// 判断用户是否登录
		if($("#userid").attr("userid")==undefined){
			$("#s-modal-body").text("请您先登录");
			$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
			$(this).attr("data-toggle","modal");
			$(this).attr("data-target",".bs-example-modal-sm");
		}else{
			// 加入购物车代码
			var id = $("#userid").attr("userid");
			var pid = $("#pid").attr("pid");
			var counts = $("#goodscount").val();
			$.post("/cm/data",{"method":"setOrder","id":id,"pid":pid,"counts":counts});
			$("#cart").attr("data-toggle","popover");
			var addcar = $(this);
			var img = $("#goodspic").attr('src');
			var flyer = $("<img class='u-flyer img-circle' src='"+ img + "' width='80' height='80' style='border:2px #573f2f solid;'>");
			flyer.fly({
				start : {
					left : event.pageX, // 开始位置（必填）#fly元素会被设置成position:
					top : event.pageY // 开始位置（必填）
				},
				end : {
					left : offset.left + 10, // 结束位置（必填）
					top : offset.top + 10, // 结束位置（必填）
					width : 0, // 结束时宽度
					height : 0 // 结束时高度
				},
				onEnd : function() { // 结束回调
					$("#cart").popover("show");
					setTimeout(function(){
						$("#cart").popover("hide");
					}, 1000);
				}
			});
		}
	});
	
	// 商品数量
	$("#goodscount").numberspinner({    
	    min: 1,    
	    max: 100,    
	    editable: true   
	});  

	// 立即购买
	$("#buyNow").click(function(){
		var number = $("#goodscount").val();
		$.getJSON("/cm/data",{
			"method":"getGoodsInfoById",
			"pid":$("#pid").attr("pid")
		},function(json){
			var desc = json.DESCRIPT;
			var info = "";
			for(var i = 0;i<desc.length;i+=45){
				info+=desc.substring(i,(i+45)||desc.length-1)+"<br/>";
			}
			var $table = $("<table align='center' class='table table-hover'><tr><td>商品名</td><td>" +json.BRAND+" "+json.PTYPE+"</td></tr><tr><td>预览图</td><td><img height='350' class='img-rounded' src='/cm/"+json.PICTURE+"'/></td></tr><tr><td>商品类别" +"</td><td>" +json.PNAME+"</td></tr><tr><td>商品描述</td><td>" +info+"</td>" +"</tr><tr><td>商品价格</td><td>" +json.PRICE+"</td></tr><tr><td>购买数量</td><td>" +number+"</td></tr><tr><td>总计</td><td id='money'>" +(number*json.PRICE)+"</td></tr></table>");
			$("#b-modal-body").empty().append($table);
		});
		$(this).attr("data-toggle","modal");
		$(this).attr("data-target",".bs-example-modal-lg");
	});
	
	// 确认购买
	$("#confirmBuy").click(function(){
		var number = $("#goodscount").val();
		$(this).attr("data-toggle","modal");
		$(this).attr("data-target",".bs-example-modal-sm");
		$.post("/cm/data",{
			"method":"moneyEnough",
			"userid":$("#userid").attr("userid"),
			"money":$("#money").text(),
		},function(res){
			if(res=="1"){
				$.post("/cm/data",{
					"method":"buyGoods",
					"userid":$("#userid").attr("userid"),
					"money":$("#money").text(),
					"pid":$("#pid").attr("pid"),
					"number":number
				},function(res){
					if(res=="1"){
						$("#s-modal-body").text("购买成功！");
						$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
						$("#goodsSales").text((parseInt($("#goodsSales").text(), 10)+parseInt(number, 10)));
					}else{
						$("#s-modal-body").text("发生异常，购买失败，请您重新购买！");
						$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
					}
				});
			}else if(res=="0"){
				$("#s-modal-body").text("您的余额不足，请先充值！");
				$("#s-modal-body-addon").removeClass().addClass("glyphicon glyphicon-exclamation-sign");
			}
		});
	});
});
//style="width:262px;height:270px;background-repeat:no-repeat;background-image:url(<%=path%>/${item.PICTURE});background-position:center center"
