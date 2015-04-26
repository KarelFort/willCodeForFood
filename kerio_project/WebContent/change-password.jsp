<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon" href="img/favicon.ico" type="image/vnd.microsoft.icon" />

<title>Change password | ClientStatistics - Kerio Connect</title>

<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/dashboard.css" rel="stylesheet" type="text/css">
<link href="css/dataTables.bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="css/styl.css" rel="stylesheet" type="text/css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->  
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index"> <img
						src="img/logo_v2.png" alt="ClientStatistics Home" width="273"
						height="34" border="0" /></a>
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="administration">Queries edit</a></li>
					<li><a href="change-password">Change password</a></li>
					<li><a href="logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h1 class="page-header">Change Password</h1>
				<form action="change-password" method="post" >
						<label>New password:</label>  (required)
						<input type="text" name="password" class="form-control" required> 						
						 
						<label>Info about password:</label>  (required)
						<input type="text" name="passwordInfo" class="form-control" value="${passwordInfo}" required>			

		
						<input type="submit" value="change password" class="btn">
				</form>					
			</div>
		</div>
	</div>
</body>
</html>
