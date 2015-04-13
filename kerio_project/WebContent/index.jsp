<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon" href="img/favicon.ico" type="image/vnd.microsoft.icon" />

<title>Analytický a reportovací nástroj</title>

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
					<li><a href="administrace">Administrace</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#">Verze ActiveSync<span
							class="sr-only">(current)</span></a></li>
					
					<li><a href="#">...</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div id="aktualizovat-tlacitko">
					<a href="index">
						<button type="button" class="btn btn-primary" >Aktualizovat databázi</button>
					</a>
					<p> Poslední aktualizace 12. 4. 2015 v 16:55</p>
				</div>
				
				<h1 class="page-header">Verze ActiveSync</h1>

				<h5>Procentuální zastoupení jednotlivých verzí ActiveSync</h5>
				<h5>select APPLICATION as Device,count(APPLICATION) as Count,(Count(APPLICATION)*100, (Select Count(*) From Clients where CLIENT_NAME like "ActiveSync%")) as Percentage from Clients where CLIENT_NAME like "ActiveSync%" group by APPLICATION order by Count desc;</h5>
				
				<br>

				<table id="tabulka" class="display" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Device</th>
							<th>Count</th>
							<th>Percentage</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>ActiveSync4.0</td>
							<td>165</td>
							<td>16.5</td>
						</tr>
						<tr>
							<td>ActiveSync4.1</td>
							<td>380</td>
							<td>38</td>
						</tr>
						<tr>
							<td>ActiveSync4.5</td>
							<td>455</td>
							<td>45.5</td>
						</tr>
												
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" 
		src="js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="js/dataTables.bootstrap.js"></script>
	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
			$('#tabulka').dataTable();
		});
	</script>
</body>
</html>
