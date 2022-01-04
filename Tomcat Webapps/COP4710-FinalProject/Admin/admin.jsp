<!DOCTYPE html>
<html>
<head>
   <title>Admin Home</title>
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

	a {
		text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
		color: #000000;
		text-decoration: none
	}

	@media (max-width: 600px) {
		.main {
			border-radius: 0px;
		}}

	.center{
		text-align: center;
		padding: 10px;
		font-size: 24px;
	}

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
	<h2> Select an admin task to perform: </h2>
	<div class="main">
	<div class="center"><a href = "/COP4710-FinalProject/Admin/newAdmin.jsp">Create a new Admin</a></div>
	<div class="center"><a href = "/COP4710-FinalProject/Admin/deleteAdmin.jsp">Delete an existing Admin</a></div>
	<div class="center"><a href = "/COP4710-FinalProject/Admin/passwordChange.jsp">Change account password</a></div>
	<div class="center"><a href = "/COP4710-FinalProject/Admin/modifyDatabase.jsp">Add/modify faculty database</a></div>
	<div class="center"><a href = "/COP4710-FinalProject/Admin/emailBlast.jsp">Deadline email blast</a></div>
	<div class="center"><a href = "/COP4710-FinalProject/Admin/indvReminder.jsp">Send individual reminder</a></div>
	<div class="center"><a href = "/COP4710-FinalProject/Admin/indvReminder.jsp">Create final list of book requests</a></div>
	<div class="center"><a href = "/COP4710-FinalProject/Admin/viewRequest.jsp">View Requests for a semester</a></div>
	</div>
	<a href="/index.jsp">Log Out </a>
</body>
<script type="text/javascript">
		function redirect()
		{
			var input = document.querySelector('input[name="task"]:checked').value;
			if(input == "new")
				location.href = "/COP4710-FinalProject/Admin/newAdmin.jsp";
			if(input == "delete")
				location.href = "/COP4710-FinalProject/Admin/deleteAdmin.jsp";
			if(input == "pass")
				location.href = "/COP4710-FinalProject/Admin/passwordChange.jsp";
			if(input == "database")
				location.href = "/COP4710-FinalProject/Admin/modifyDatabase.jsp";
			if(input == "blast")
				location.href = "/COP4710-FinalProject/Admin/emailBlast.jsp";
			if(input == "remind")
				location.href = "/COP4710-FinalProject/Admin/indvReminder.jsp";
			if(input == "final")
				location.href = "/COP4710-FinalProject/Admin/finalizeRequest.jsp";
			if(input == "view")
				location.href = "/COP4710-FinalProject/Admin/viewRequest.jsp";
		}
		
	</script>
</html>