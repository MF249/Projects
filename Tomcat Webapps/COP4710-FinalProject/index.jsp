<!doctype html>

<%-- start scriptlet --%>
<%
	String username = (String) session.getAttribute("username");
	if (username == null) username = " ";
	String password = (String) session.getAttribute("password");
	if (password == null) password = " ";
	String message = (String) session.getAttribute("message");
	if (message == null) message = " ";
	boolean validate = false;
%>
<%-- end scriptlet --%>

<html>
<head>
	<title>Database Final Project</title>
	<style>
	 <!--
		body {
			background: #90F7FF;
			color: black;
			margin: 0;
			height: 100vh;
			display: flex;
			align-items: center;
			justify-content: center;
			font-size: 18px;
			background-size: cover;
		}
		h1 { text-align: center; font-size: 28pt; }
		h2 { text-align: center; font-size: 24pt; }
		input { color: black; font-size: 11pt; }
			input [type="submit"] {color: lime;}
		p { color: black; font-size: 13pt; }
		.main { color: black; }
		#bl { color: #90F7FF; }
	 -->
	 .container {
		 font: 500 1rem 'Quicksand', sans-serif;
		 width: 400px;
		 max-width: 400px;
		 margin: 1rem;
		 padding: 2rem;
		 box-shadow: 0 0 40px rgba(0, 0, 0, 0.2);
		 border-radius: 5px;
		 background: #ffffff;
	 }
	 .form__input {
		 display: block;
		 width: 100%;
		 padding: 0.75rem;
		 box-sizing: border-box;
		 border-radius: 5px;
		 border: 1px solid #dddddd;
		 outline: none;
		 background: #eeeeee;
		 transition: background 0.2s, border-color 0.2s;
	 }
	 .container,
	 .form__input,
	 .form__button {
		 font: 500 1rem 'Quicksand', sans-serif;
	 }

	 .form > *:first-child {
		 margin-top: 0;
	 }

	 .form > *:last-child {
		 margin-bottom: 0;
	 }

	 .form__title {
		 margin-bottom: 2rem;
		 text-align: center;
	 }

	 .form__message {
		 text-align: center;
		 margin-bottom: 1rem;
	 }

	 .form__message--error {
	 }

	 .form__input-group {
		 margin-bottom: 1rem;
	 }

	 .form__input {
		 display: block;
		 width: 100%;
		 padding: 0.75rem;
		 box-sizing: border-box;
		 border-radius: 5px;
		 border: 1px solid #dddddd;
		 outline: none;
		 background: #eeeeee;
		 transition: background 0.2s, border-color 0.2s;
	 }

	 .form__input:focus {
		 border-color: #00CCDC;
		 background: #ffffff;
	 }

	 .form__input-error-message {
		 margin-top: 0.5rem;
		 font-size: 0.85rem;
		 color: #00CCDC;
	 }

	 .form__button {
		 width: 100%;
		 padding: 1rem 2rem;
		 font-weight: bold;
		 font-size: 1.1rem;
		 color: #ffffff;
		 border: none;
		 border-radius: #00CCDC;
		 outline: none;
		 cursor: pointer;
		 background: #00CCDC;
	 }

	 .form__button:hover {
		 background: #00CCDC;
	 }

	 .form__button:active {
		 transform: scale(0.98);
	 }


	</style>
	<!-- connecting to a CDN to access latest JQuery library - as of 11/2021 this was 3.6.0 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"> </script>
	<!-- Javascript to handle erasing the command entry of the user's page -->
</head>

<!-- <body bgcolor="#999999"> -->
<!-- <body bgcolor="#00FF22"> this one is ok -->
<!-- <body bgcolor="#00FFFF"> -->
<body>
	<div class="container">
	<!-- form user to enter credentials -->
		<div class="form" id="login">
			<h1 class="form__title">Login</h1>
			<form action="LoginServlet" method="post">
			<div class="form__message form__message--error"></div>
			<div class="form__input-group">

					<label>User
						<input id="username" type="text" name = "username" class="form__input" autofocus placeholder="username" <%=username%> />
					</label> <br>
					<div class="form__input-error-message"></div>
			</div>
			<div class="form__input-group">
				<label>Password
					<input id="password" type="password" name = "password" class="form__input" autofocus placeholder="password"  <%=password%> />
				</label> <br>
				<div class="form__input-error-message"></div>
			</div>
			<button class="form__button" type="submit" value="Login">Login</button>
			</form>
	<!-- more text below the form area -->
	
	<%
	if (message.contains("admin")) {
	%>
		<jsp:forward page="/Admin/admin.jsp" />
	<%
	}
	if (message.contains("prof")){
	%>
		<jsp:forward page="professor.jsp" />
	<%
	}
	%>
	<p class="main">
		<%=message%> <br>
		<a href="/COP4710-FinalProject/newProfessor.jsp">Create Professor Account </a> <br>
		<a href="/COP4710-FinalProject/professorPassword.jsp">Forgot your password? </a>
	</p>
	</div>
</body>
</html>			