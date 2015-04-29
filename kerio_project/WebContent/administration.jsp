<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon" href="img/favicon.ico"
	type="image/vnd.microsoft.icon" />

<title>Administration | ClientStatistics - Kerio Connect</title>

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
	<jsp:include page="components/headerAdmin.jsp" />

	<div class="container-fluid">
		<div class="row">
			<jsp:include page="components/menuAdmin.jsp" />
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<!-- displaying message added in GeneralFilter-->
				<p class="alert-${requestScope.message_type}">${requestScope.message}</p>

				<h1 class="page-header">Queries edit</h1>
				<p>Choose query on the left to editing.</p>
				<br>
				<h1 class="page-header">Add query</h1>
				<a href="add-query">
					<button type="button" class="btn btn-primary">Add query</button>
				</a>
			</div>
		</div>
	</div>
</body>
</html>
