<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon" href="img/favicon.ico"
	type="image/vnd.microsoft.icon" />

<title>ClientStatistics - Kerio Connect</title>

<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/dashboard.css" rel="stylesheet" type="text/css">
<link href="css/dataTables.bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="css/styl.css" rel="stylesheet" type="text/css">


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<script src="js/lib/jquery.min.js"></script>
<script src="js/lib/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript"
	src="js/lib/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript"
	src="js/lib/dataTables.bootstrap.js"></script>
<script type="text/javascript" language="javascript"
	src="js/lib/d3.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

</head>

<body>
	<jsp:include page="components/header.jsp" />
	
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="components/menu.jsp" />

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div id="div-header">
					<h1 class="page-header" id="query-name">Welcome</h1>
				</div>

				<p>Welcome to ClientStatistics application, analytical and
					reporting tool for KerioConnect product.</p>
				<p>Select query from the side menu for displaying query. Click on
					Admin button for creating, editing and deleting queries.</p>

				<br>

				<p>Database updated ${lastUpdate}</p>
			</div>
		</div>
	</div>
</body>
</html>
