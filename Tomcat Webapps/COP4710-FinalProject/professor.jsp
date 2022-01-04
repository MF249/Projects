<!DOCTYPE html>

<%-- start scriptlet --%>
<%
	String destination = (String) session.getAttribute("destination");
	if (destination == null) destination = " ";
%>
<%-- end scriptlet --%>

<html>
<head>
   <title>Login Success</title>
   <meta charset="utf-8" />
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
		margin-left: 35%;
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
		color: #000000;
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
	<h1> Welcome! </h1>
	<h2> Select an professor task to perform: </h2>
	<div class="main">
	<form action="javascript:redirect();">
		<p>
			<input type = "radio" name = "task" value = "new" /><span id="a">Create a new Request Form</span><br />
			<input type = "radio" name = "task" value = "edit" /><span id="a">Edit your Request Form</span><br />
			<input type = "radio" name = "task" value = "delete" /><span id="b">Delete a Request Form</span><br />
		</p>
		<p><input type = "submit" value = "Submit" /></p>
	</form>
	</div>
	<a href="/index.jsp">Log Out </a>
</body>
<script type="text/javascript">
		function redirect()
		{
			var input = document.querySelector('input[name="task"]:checked').value;
			if(input == "new")
				location.href = "/COP4710-FinalProject/newRequest.jsp";
			if(input == "edit")
				location.href = "/COP4710-FinalProject/bookRequest.jsp";
			if(input == "delete")
				location.href = "/COP4710-FinalProject/deleteRequest.jsp";
		}
		
	</script>
</html>
