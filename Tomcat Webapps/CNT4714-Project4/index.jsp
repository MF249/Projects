<!doctype html>

<%-- start scriptlet --%>
<%
	// insert session definitions here
	String sqlStatement = (String) session.getAttribute("sqlStatement");
	if (sqlStatement == null) sqlStatement = "";
	String message = (String) session.getAttribute("message");
	if (message == null) message = " ";
%>
<%-- end scriptlet --%>

<html>
<head>
	<title>CNT 4714 Remote Database System</title>
	<style>
	 <!--
		body { text-align: center; background: black; color: white; font-family: Arial; }
		h1 { color: yellow; font-size: 28pt; }
		h2 { color: cyan; font-size: 24pt; }
		 input { color: yellow; background: #665D1E; font-weight: bold; font-size: 16pt; }
			input [type="submit] {color: lime;}
			input [type="reset"] {color: red;}
		p { color: white; font-size: 13pt; }
		table { color: black; font-family: Arial; border: 3px solid black; }
		textarea { background: blue; color: white; font-family: Verdana; font-size: 15pt; width: 900px; height: 275px; }
		th, td { padding: 10px; border: 3px solid black; }
		.main { color: white; }
		#bl { color: #708090; }
	 -->
	</style>
	<!-- connecting to a CDN to access latest JQuery library - as of 11/2021 this was 3.6.0 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"> </script>
	<!-- Javascript to handle erasing the command entry of the user's page -->
	<script type="text/javascript">
		function eraseText() {
			// next line illustrates a straight Javascript technique
			document.getElementById("cmd").innerHTML = " ";
			// next line illustrates a JQuery technique
			// $("#cmd").html("");
		}
	</script>
	<!-- Javascript to handle erasing the results area of the user's page -->
	<script type="text/javascript">
		function eraseData() {
			// next line illustrates a straight Javascript technique
			// document.getElementById("data").innerHTML = " ";
			// next line illustrates a JQuery technique
			$("#data").remove();
		}
	</script>
</head>

<!-- <body bgcolor="#999999"> -->
<!-- <body bgcolor="#00FF22"> this one is ok -->
<!-- <body bgcolor="#00FFFF"> -->
<body>
	<!-- header elements for page title information -->
	<h1> Welcome to the Fall 2021 Project 4 Enterprise Database System </h1>
	<h2> A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container </h2>
	<hr>
	<p class="main">
		You are connected to the Project 4 Enterprise System database as a root user. <br>
		Please enter any valid SQL query or update command in the box below. <br>
	</p>
	<!-- form user entering SQL commands -->
	<form name="MySQLServlet" action="/Project4/MySQLServlet" method="post">
		<br>
		<textarea id="sqlStatement" name="sqlStatement" rows=8 cols=60><%=sqlStatement%> </textarea> <br>
		<br>
		<input type="submit" value="Execute Command" /> &nbsp; &nbsp; &nbsp; 
		<input type="reset" value="Reset Form" onclick="javascript:eraseText();" /> &nbsp; &nbsp; &nbsp;
		<input type="button" value = "Clear Results" onclick="javascript:eraseData();" />
	</form>
	<!-- more text below the form area -->
	<p class="main"> <br /> All execution results will appear below this line. </p>
	<hr>
	<center>
		<p>
			<b class="main">Database Results:</b><br>
			<table id="data" style="color: black; font-family: Arial; text-align: center; border-spacing: 5px;">
				<%=message%>
			<!-- table element for displaying all results inbound from servlet -->
			<!-- table is target area for returned results which must include HTML table elements -->
			</table>
		</p>
	</center>
</body>
</html>