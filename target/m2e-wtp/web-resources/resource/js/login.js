function login() {
	if ($.trim($("#account").val()).length == 0) {
		alert("请输入账号");
		$("#account").focus();
		return;
	}
	if ($.trim($("#password").val()).length == 0) {
		alert("请输入密码");
		$("#password").focus();
		return;
	}

 	var postData = {
		"name" : $.trim($("#account").val()),
		"password" : hex_md5($.trim($("#password").val()))
	}

	$.ajax({
		type: "POST",
		url: "/login/doLogin",           
		data: postData,
		dataType : 'json',
		success: function(data){ 
			if(data.retCode == "0") {
				window.location.href = "/login/welcome";
			} else {
				alert(data.retMsg);       
			}
		},
		error: function(er) {
			alert(er);
		}
	});
}
