<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript">
	
	window.onload = function() {
		var loginBtn = document.getElementById("loginBtn");
		loginBtn.onclick = login;
	}
	
	function login() {
	    var id = document.getElementById('id').value;
	    var pw = document.getElementById('password').value;
	    App.login(id, pw);
	}

</script>

<body>

	<div align="center" style=	"font-family: 'Tahoma'; 
								 font-size: 70px;
								 font-weight: bold;
								 border: none;
								 color: darkblue;
								 margin: 0 auto;
								 width: 80%;
								 padding-top: 50px;
								 padding-bottom: 50px;">Fair Pay</div>


		<table style="margin: 0 auto;">

			<tr>
				<td style=	"font-size: 15px; font-family: 'Tahoma'; color: #555555;">ID</td>
			</tr>
			<tr>
				<td>
				<input type="text" id="id" name="uno" style=	"font-family: 'Tahoma'; 
																 font-size: 16px;
																 size: 30px;
																 height: 30px;
																 padding-left: 5px;">
				</td>
			</tr>
			
			<tr height="10px;"></tr>
			
			<tr>
				<td style=	"font-size: 15px; font-family: 'Tahoma'; color: #555555;">Password</td>
			</tr>
			<tr>
				<td>
				<input type="password" id="password" name="upassword" style=	"font-family: 'Tahoma'; 
																		 font-size: 16px;
																		 size: 30px;
																		 height: 30px;
																		 padding-left: 5px;">
				</td>
			</tr>
			
			<tr height="40px;">
			</tr>
		</table>
		
		<div align="center" style="overflow: hidden;">
		<div align="center">
			<div style="font: sans-serif; float: left">
				<input type="button" id="loginBtn" value="Log In" style=	"height: 35px;
																		 width: 100px;
																		 font-family: 'Tahoma';
																		 font-weight: bold;
																		 border-style: solid;
																		 margin-right: 10px;
																		 background-color: white;"/>
			</div>
			<div style="float: left">
				<form id="signup" method="get" action="https://testapi.open-platform.or.kr/oauth/2.0/authorize2">
					<input type="hidden" name="response_type" value="code"> 
					<input type="hidden" name="client_id" value="l7xx1a592643436f43169b078251f7258c59"> 
					<input type="hidden" name="redirect_uri" value="http://70.12.109.163:9090/project/join.jsp"> 
					<input type="hidden" name="scope" value="login inquiry transfer"> 
					<input type="hidden" name="client_info" value="TEST"> 
					<input type="hidden" name="auth_type" value="0"> 
					
					<input type="submit" value="Sign Up" style=	"height: 35px;
																 width: 100px;
																 font-family: 'Tahoma';
																 font-weight: bold;
																 border-style: solid;
																 background-color: white;"/>
					
				</form>
			</div>
			</div>
		</div>
		

</body>
</html>