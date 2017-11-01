<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/css.css?version=${css_js_version}">
<html>
	<head>
	</head>
	<body>
	    <div class="btn_main">
	        <div class="btn_input">
				<input id="account" type="text" placeholder="请输入账号" name="account" value="${user}" />
	    	</div>
	    </div>
	    <div class="btn_main">
	        <div class="btn_input">
			    <input id="password" type="password" placeholder="请输入登录密码" name="password" />
	    	</div>
	    </div>
	 	</form>  
	
	    <div class="btn_main">
	    	<input type="button" onClick="login()" value="登录" />
	    </div>
	</body>
</html>

<script type = "text/javascript"  src="${ctx}/resource/js/jquery-2.1.4.min.js?version=${css_js_version}"></script>
<script type = "text/javascript"  src="${ctx}/resource/js/jquery.md5.js?version=${css_js_version}"></script>
<script type = "text/javascript"  src="${ctx}/resource/js/login.js?version=${css_js_version}"></script>
