/*
 * ajax的javascript默认实现
 * 
 */
$(function() {
	var pwd = $("#inputPassword1-body");
	var reput = $("#inputPassword2-body");
	reput.keyup(function() {
		if (reput.val() != pwd.val()) {
			document.getElementById("regist").disabled = true;
			$("#pass_not_confirm").removeClass("sr-only");
		} else {
			document.getElementById("regist").disabled = false;
			$("#pass_not_confirm").addClass("sr-only");
		}

	});
	// 验证
	$("#input-top1").keyup(function() {
		var regex = /^\S{3,16}$/;
		if (regex.test($("#input-top1").val()) == false) {
			document.getElementById("regist").disabled = true;
			$("#d-top").addClass("has-feedback has-error");
			$("#fb-price").removeClass("sr-only");
		} else {
			document.getElementById("regist").disabled = false;
			$("#d-top").removeClass("has-feedback has-error");
			$("#fb-price").addClass("sr-only");
		}
		$.get("user!uniqueUsername.action", {
			"user.username" : $(this).val()
		}, function(has) {
			if (has == "1") {
				$("#regist").attr("disabled", true);
				$("#name_not_pass").removeClass("sr-only");
			} else {
				$("#regist").attr("disabled", false);
				$("#name_not_pass").addClass("sr-only");
			}
		});
	}).blur(function() {
		$.get("/cm/user", {
			"method" : "uniqueName",
			"username" : $(this).val()
		}, function(has) {
			if (has == "1") {
				$("#regist").attr("disabled", true);
				$("#name_not_pass").removeClass("sr-only");
			} else {
				$("#regist").attr("disabled", false);
				$("#name_not_pass").addClass("sr-only");
			}
		});
	});
	pwd.blur(function() {
		var regex2 = /^[a-z0-9A-Z]{6,18}$/;
		if (regex2.test($("#inputPassword1-body").val()) == false) {
			document.getElementById("regist").disabled = true;
			$("#inputPassword1-body").val("");
			$("#inputPassword2-body").val("");
			$("#pwd_not_pass").removeClass("sr-only");
		} else {
			document.getElementById("regist").disabled = false;
			$("#pwd_not_pass").addClass("sr-only");
		}
	});

	$("#form-regist").submit(function() {
		if ($("#update").val() == "") {
			$("#photoupload").removeClass("sr-only");
			return false;
		} else {
			$("#photoupload").addClass("sr-only");
		}
	});

	$(window).keyup(function(event) {
		if (event.keyCode == 13) {
			$("#form-login").trigger("submit");
			$("#form-regist").trigger("submit");
		}
	});

	$("#update").fileinput({
        showUpload: false, //是否显示上传按钮
		browseClass: "btn btn-info btn-lg"
    });
});