<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!--  <link rel="icon" href="../../favicon.ico">    TODO : doplnit favicon-->

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
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp#"> <img
					src="img/logo_v2.png" alt="ClientStatistics Home" width="273"
					height="34" border="0" /></a>
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>

				</button>

				<!-- 				<a class="navbar-brand" href="index">Analytický a reportovací -->
				<!-- 					nástroj produktu Kerio Connect</a> -->

			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="administrace">Login</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#">Mobile devices<span
							class="sr-only">(current)</span></a></li>
					<li><a href="#">SQL dotay 2</a></li>
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
		</div>
	</div>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div id="div-header">
			<p>Last updated 12. 4. 2015 v 16:55</p>

			<h1 class="page-header" id="query-name"></h1>
		</div>
		<div id="div-description" class="collapse in">
			<ul class="nav nav-tabs" id="ic_tabs">
				<li class="active"><a href="#tab1" data-toggle="tab">Description</a></li>
				<li><a href="#tab2" data-toggle="tab">SQL</a></li>
			</ul>
			<div id="ic_tabsContent" class="tab-content">
				<div class="tab-pane fade in active" id="tab1">
					<h5 id="description"></h5>
				</div>
				<div class="tab-pane fade" id="tab2">
					<h5 id="query"></h5>
				</div>
			</div>
		</div>

		<br>

		<div id="div-datepicker">

			<h4>Date range</h4>

			<div style="padding: 0px">
				<div class="input-group col-lg-4 col-md-4 col-sm-4 col-xs-4">
					<input type="text" class="form-control" id="datepicker-from"
						style="cursor: pointer; background-color: white"
						placeholder="mm/dd/yyyy" readonly="readonly"> <span
						class="input-group-btn">
						<button class="btn btn-default" type="button" disabled>
							<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
						</button>
					</span>
				</div>
				<!-- /input-group -->

				<br>
				<div class="input-group col-lg-4 col-md-4 col-sm-4 col-xs-4">
					<input type="text" class="form-control" id="datepicker-to"
						style="cursor: pointer; background-color: white"
						placeholder="mm/dd/yyyy" readonly="readonly"> <span
						class="input-group-btn">
						<button class="btn btn-default" type="button" disabled>
							<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
						</button>
					</span>
				</div>
				<!-- /input-group -->

			</div>
			<!-- /.col-lg-6 -->
			<br>
			<div>
				<button type="button" class="btn btn-primary" id="btn-execute">Execute
					query</button>
			</div>
		</div>

		<br>

		<div id="div-table">
			<h4>Table</h4>
			<table id="table" class="table table-striped table-bordered"
				cellspacing="0" width="100%">

				<thead>
					<tr>
						<th>Device</th>
						<th>Percentage</th>
						<th>Count</th>
					</tr>
				</thead>
			</table>
		</div>
		<br>
		<div id="div-chart">
			<h4>Chart</h4>
			<div id="chart"></div>
		</div>
	</div>
	<script type="text/javascript" language="javascript"
		src="js/queryHeader.js"></script>
	<script type="text/javascript" language="javascript"
		src="js/datepicker.js"></script>
	<script type="text/javascript" language="javascript"
		src="js/dataTable.js"></script>
	<script type="text/javascript" language="javascript"
		src="js/pieChart.js"></script>
</body>
</html>
