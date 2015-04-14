<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!--  <link rel="icon" href="../../favicon.ico">    TODO : doplnit favicon-->

<title>Analytický a reportovací nástroj</title>

<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/dashboard.css" rel="stylesheet" type="text/css">
<link href="css/dataTables.bootstrap.css" rel="stylesheet"
	type="text/css">


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript"
	src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript"
	src="js/dataTables.bootstrap.js"></script>
<script type="text/javascript" language="javascript" src="dataTable.js"></script>
<script type="text/javascript" language="javascript" src="js/d3.min.js"></script>

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
				<a class="navbar-brand" href="index">Analytický a reportovací
					nástroj produktu Kerio Connect</a>
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
					<li class="active"><a href="#">Mobilní zařízení<span
							class="sr-only">(current)</span></a></li>
					<li><a href="#">SQL dotaz 2</a></li>
					<li><a href="#">SQL dotaz 3</a></li>
					<li><a href="#">SQL dotaz 4</a></li>
					<li><a href="#">SQL dotaz 5</a></li>
					<li><a href="#">SQL dotaz 6</a></li>
					<li><a href="#">SQL dotaz 7</a></li>
					<li><a href="#">SQL dotaz 8</a></li>
					<li><a href="#">SQL dotaz 9</a></li>
					<li><a href="#">SQL dotaz 10</a></li>
					<li><a href="#">SQL dotaz 11</a></li>
					<li><a href="#">...</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<p>Poslední aktualizace 12. 4. 2015 v 16:55</p>


				<h1 class="page-header">Mobilní zařízení</h1>

				<h4>Popis dotazu</h4>
				<h5 id="popis"></h5>
				<br />

				<h4>SQL dotaz</h4>
				<h5 id="dotaz" class="jumbotron"></h5>

				<br>
				<h4>Tabulka</h4>
				<table id="tabulka" class="table table-striped table-bordered"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Zařízení</th>
							<th>Zastoupení</th>
							<th>Počet</th>
						</tr>
					</thead>
				</table>

				<h4>Graf</h4>

				<div id="chart"></div>
			</div>
		</div>
	</div>

	<script type="text/javascript" language="javascript" src="pieChart.js"></script>

</body>
</html>
