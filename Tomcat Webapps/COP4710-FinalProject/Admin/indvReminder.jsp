<!DOCTYPE html>

<%-- start scriptlet --%>
<%
	String name = (String) session.getAttribute("name");
	String email = (String) session.getAttribute("email");
	String deadline = (String) session.getAttribute("deadline");
	String message = (String) session.getAttribute("message");
	if (message == null) message = " ";
%>
<%-- end scriptlet --%>

<html>
<head>

   <title>Account Creation/Deletion</title>

	<style>
		body {
			background-color: #F3EBF6;
			font-family: 'Ubuntu', sans-serif;
		}

		.main {
			background-color: #FFFFFF;
			width: 400px;
			height: 400px;
			margin: 7em auto;
			border-radius: 1.5em;
			box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
		}

		.sign {
			padding-top: 40px;
			color: #1CCBA6;
			font-family: 'Ubuntu', sans-serif;
			font-weight: bold;
			font-size: 23px;
		}

		.un {
			width: 76%;
			color: rgb(38, 50, 56);
			font-weight: 700;
			font-size: 14px;
			letter-spacing: 1px;
			background: rgba(136, 126, 126, 0.04);
			padding: 10px 20px;
			border: none;
			border-radius: 20px;
			outline: none;
			box-sizing: border-box;
			border: 2px solid rgba(0, 0, 0, 0.02);
			margin-bottom: 50px;
			margin-left: 46px;
			text-align: center;
			margin-bottom: 27px;
			font-family: 'Ubuntu', sans-serif;
		}

		form.form1 {
			padding-top: 5px;
		}

		.pass {
			width: 76%;
			color: rgb(38, 50, 56);
			font-weight: 700;
			font-size: 14px;
			letter-spacing: 1px;
			background: rgba(136, 126, 126, 0.04);
			padding: 10px 20px;
			border: none;
			border-radius: 20px;
			outline: none;
			box-sizing: border-box;
			border: 2px solid rgba(0, 0, 0, 0.02);
			margin-bottom: 50px;
			margin-left: 46px;
			text-align: center;
			margin-bottom: 27px;
			font-family: 'Ubuntu', sans-serif;
		}


		.un:focus, .pass:focus {
			border: 2px solid rgba(0, 0, 0, 0.18) !important;

		}

		        .submit {
            cursor: pointer;
            border-radius: 5em;
            color: #fff;
            background: linear-gradient(to right, #9C27B0, #1CCBA6);
            border: 0;
            padding-left: 40px;
            padding-right: 40px;
            padding-bottom: 10px;
            padding-top: 10px;
            font-family: 'Ubuntu', sans-serif;
            margin-left: 36%;
            font-size: 13px;
            box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
        }

		.forgot {
			text-shadow: 0px 0px 7px rgba(117, 117, 117, 0.12);
			color: black;
			padding-top: 15px;
		}

		a {
			text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
			color: #1CCBA6;
			text-decoration: none
		}

		@media (max-width: 600px) {
			.main {
				border-radius: 0px;
			}}


	</style>
   <meta charset="utf-8" />
   <style type="text/css">
    <!--
	    body { background: #61DFC5; color: black; }
		h1 { text-align: center; font-size: 28pt; }
		h2 { text-align: center; font-size: 24pt; }
		input { color: black; font-size: 11pt; }
			input [type="submit"] {color: lime;}
		p { color: black; font-size: 15pt; }
		.main { color: black; }
	-->
	</style>
</head>
<body>
<div class="main">
	<p class="sign" align="center">Send Reminder</p>
		<form action="/COP4710-FinalProject/EmailRequestSingle" method="post">
			<input type = "text" align="center" name = "name" autocomplete="off" class="un" autofocus placeholder = "Enter Name" <%=name%> />
			<input type = "text" align="center" name = "email" autocomplete="off" class="pass" autofocus placeholder = "Enter Email" <%=email%> />
			<input type = "text" align="center" name = "deadline" autocomplete="off" class="un" autofocus placeholder = "Deadline" <%=deadline%> />

			<input type = "submit" class = "submit" align="center" value = "Send"/></a> 
		</form>
		<p class="forgot" align="center"><a href="/COP4710-FinalProject/Admin/admin.jsp">Back</a></p>
		<p align="center" class="forgot" ><%=message%> </p>
		<% message = " "; %>
</div>

</body>
</html>
