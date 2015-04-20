<%@page contentType="text/html;charset=gb2312" language="java" errorPage=""%>

<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>通用应用管理系统</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/fonts-min.css">
<link rel="stylesheet" type="text/css" href="css/reset-min.css">
<link rel="stylesheet" type="text/css" href="css/grids-min.css">
<style type="text/css">
html {
	height: 100%;
	max-height: 100%;
	padding: 0;
	margin: 0;
	border: 0;
	font-size: 76%;
	font-family: verdana, georgia, palatino linotype, times new roman, serif,
		"宋体";
	overflow: hidden;
}

body {
	height: 100%;
	max-height: 100%;
	overflow: hidden;
	padding: 0;
	margin: 0;
	border: 0;
	background-image: url(images/login_images/cloud.gif);
	background-position: center center;
	background-repeat: repeat;
	text-align: center;
}

.logo {
	margin-top: 5%;
}

.foot {
	position: absolute;
	bottom: -20%;
	left: -2%;
}

.main {
	position: absolute;
	left: 50%;
	top: 45%;
	width: 736px;
	height: 385px;
	margin-left: -368px;
	margin-top: -193px;
	background-image: url(images/login_images/lbg.gif);
	background-position: center center;
	background-repeat: no-repeat;
	text-align: center;
}

.form {
	font-size: 13px;
	color: #676767;
	font-weight: bold;
}

.header {
	color: #07519a;
	font-family: '微软雅黑', '黑体';
	letter-spacing: 2px;
}

.style1 {
	width: 407px;
}

.style2 {
	font-size: 20px;
	color: #07519a;
	font-family: '微软雅黑', '黑体';
	letter-spacing: 2px;
	height: 72px;
}

<!--
body222 {
	background-color: #CCCCCC;
}
-->
</style>
</head>
<SCRIPT language=JavaScript>
	window.defaultStatus = "通用应用管理系统";
	function login() {
		if (document.all("user_name").value == ""
				|| document.all("passwd").value == "") {
			alert("用户名和密码不能为空");
		} else {
			form1.submit();
		}
	}
	function reset() {
		
		var form=document.getElementById("form1");
		form.reset();

	}
	function F5() {
		window.location.reload();
	};
</SCRIPT>
<script language="javascript" src="js/public/key.js"></script>
<body scroll="no" onLoad="document.all('user_name').focus()">
	<form name="form1" id="form1" method="post" action="logindo.jsp" target="_self">
		<div class="logo">
			<img alt="" src="images/login_images/logo1.gif" />
		</div>
		<div class="main">
			<div class="form">
				<table style="height: 250px; width: 700px; margin-top: 50px;">
					<tr>
						<td class="style1" rowspan="6"></td>
					</tr>
					<tr>
						<td colspan="2" class="style2"></td>
					</tr>
					<tr>
						<td>用户名：</td>
						<td><input name="user_name" type="text" class="input3"
							id="user_name" size="35" onKeyDown="EnterKeyDo('')"
							style="width:200"></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td>
						    <input type="password" name="passwd" class="input3" id="passwd" size="35" onKeyDown="EnterKeyDo('login()')" style="width:200"> 
							<input name="act" type="hidden" id="act" value="doLogin"> 
							<input type="hidden" name="availwidth" id="availwidth" value="">
							<input type="hidden" name="availheight" id="availheight" value=""></td>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<table>
								<tr>
									<td style="width: 60px;height: 27px"></td>
									<td>
										<p align="center">
											<a href="#" onClick="login()"> <img border="0"
												src="images/login_images/login.gif" width="88" height="27"
												align="left"></a>
									</td>

									<td>
										<p align="center">
											<a href="#" onClick="reset()"> <img border="0"
												src="images/login_images/reset.gif" width="88" height="27"
												align="left"></a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td></td>
						<td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/ncycoa/installpageoffice.jsp">[pageoffce]</a>　</td>
					</tr>
				</table>
			</div>
			<div class="foot">
				<img alt="" src="images/login_images/foot.gif" />
			</div>
		</div>
	</form>

</body>

</html>
<script language="javascript">
	document.all("availwidth").value = window.screen.availWidth;
	document.all("availheight").value = window.screen.availHeight;
</script>