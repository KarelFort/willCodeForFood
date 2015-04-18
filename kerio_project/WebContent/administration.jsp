<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon" href="img/favicon.ico" type="image/vnd.microsoft.icon" />

<title>Administrace | Analytický a reportovací nástroj</title>

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
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index">Analytický a
					reportovací nástroj produktu Kerio Connect</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#">Editace dotazů<span
							class="sr-only">(current)</span></a></li>
					<li><a href="#">Nastavení</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">Editace dotazů</h1>

				<br>

				<table id="table" class="display" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Dotaz</th>
							<th>Popis</th>
							<th>Akce</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>SQL dotaz </td>
							<td>Popis dotazu</td>
							<td><span id="editovat"> <span
									class="glyphicon glyphicon glyphicon-pencil" aria-hidden="true"></span>									
							</span> <span id="smazat"> <span
									class="glyphicon glyphicon glyphicon-trash" aria-hidden="true"></span>
							</span></td>
						</tr>
						<tr>
							<td>SQL dotaz </td>
							<td>Popis dotazu</td>
							<td><span id="editovat"> <span
									class="glyphicon glyphicon glyphicon-pencil" aria-hidden="true"></span>									
							</span> <span id="smazat"> <span
									class="glyphicon glyphicon glyphicon-trash" aria-hidden="true"></span>
							</span></td>
						</tr>
						<tr>
							<td>SQL dotaz </td>
							<td>Popis dotazu</td>
							<td><span id="editovat"> <span
									class="glyphicon glyphicon glyphicon-pencil" aria-hidden="true"></span>									
							</span> <span id="smazat"> <span
									class="glyphicon glyphicon glyphicon-trash" aria-hidden="true"></span>
							</span></td>
						</tr>
						<tr>
							<td>SQL dotaz </td>
							<td>Popis dotazu</td>
							<td><span id="editovat"> <span
									class="glyphicon glyphicon glyphicon-pencil" aria-hidden="true"></span>									
							</span> <span id="smazat"> <span
									class="glyphicon glyphicon glyphicon-trash" aria-hidden="true"></span>
							</span></td>
						</tr>
						

					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script src="js/lib/jquery.min.js"></script>
	<script src="js/lib/bootstrap.min.js"></script>
	<script type="text/javascript" language="javascript"
		src="js/lib/jquery.dataTables.min.js"></script>
	<script type="text/javascript" language="javascript"
		src="js/lib/dataTables.bootstrap.js"></script>
	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
			$('#table').dataTable();
		});
	</script>
</body>
</html>
