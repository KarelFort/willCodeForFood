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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index"> <img src="img/logo_v2.png"
					alt="ClientStatistics Home" width="273" height="34" border="0" /></a>
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
					<li><a href="administration">Admin</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<c:choose>
						<c:when test="${fn:length(allQueries) == 0}">
							<p class="alert-warning">Nenalezeny žádné dotazy.</p>
						</c:when>

						<c:otherwise>
							<c:forEach items="${requestScope.allQueries}" var="query">
								<li><a href="query-datepicker?id=${query.id}">${query.name}</a></li>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>

		</div>
	</div>
	
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

		<div id="div-header">

			<h1 class="page-header" id="query-name">Welcome</h1>
		</div>
		
		<p>Welcome to ClientStatistics application, analytical and reporting tool for KerioConnect product. </p>
		<p>Select query from the side menu for displying query. Click on Admin button for creating, editing and deleting queries.</p>
		
		<br>
		
		<p>Database updated 2015-04-12 in 16:55</p>
	</div>

	<!-- 	<script type="text/javascript" language="javascript" -->
	<!-- 		src="js/pieChart.js"></script> -->
</body>
</html>
