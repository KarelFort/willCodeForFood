<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon" href="img/favicon.ico"
	type="image/vnd.microsoft.icon" />

<title>Query Datepicker | ClientStatistics - Kerio Connect</title>

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
					<h1 class="page-header" id="query-name">${queryDatepicker.name}</h1>
				</div>

				<div id="datepicker-alert"></div>

				<div id="div-description" class="collapse in">
					<ul class="nav nav-tabs" id="ic_tabs">
						<li class="active"><a href="#tab1" data-toggle="tab"><b>Description</b></a></li>
						<li><a href="#tab2" data-toggle="tab"><b>SQL</b></a></li>
					</ul>
					<div id="ic_tabsContent" class="tab-content">
						<div class="tab-pane fade in active" id="tab1">
							<h5 id="description">${queryDatepicker.info}</h5>
						</div>
						<div class="tab-pane fade" id="tab2">
							<h5 id="query">
								<textarea rows="7" cols="50" name="statement"
									class="form-control" readonly="readonly"
									style="background-color: white">${queryDatepicker.statement}</textarea>
							</h5>

						</div>
					</div>
				</div>

				<br>

				<div id="div-datepicker">
					<b>Select time interval</b> <input type="hidden" name="id"
						id="datepicker-query-id" value="${queryDatepicker.id}" /> <br>

					<div style="padding: 0px">

						<h5>From</h5>

						<div class="input-group col-lg-4 col-md-4 col-sm-4 col-xs-4">
							<input type="text" class="form-control" id="datepicker-from"
								name="datepicker-from"
								style="cursor: pointer; background-color: white"
								placeholder="yyyy-mm-dd" readonly="readonly"> <label
								class="input-group-addon btn" for="datepicker-from"> <span
								class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</label>
						</div>
						<!-- /input-group -->

						<h5>To</h5>

						<div class="input-group col-lg-4 col-md-4 col-sm-4 col-xs-4">
							<input type="text" class="form-control" id="datepicker-to"
								name="datepicker-to"
								style="cursor: pointer; background-color: white"
								placeholder="yyyy-mm-dd" readonly="readonly"> <label
								class="input-group-addon btn" for="datepicker-to"> <span
								class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
							</label>

						</div>
						<!-- /input-group -->

					</div>

					<!-- /.col-lg-6 -->
					<br>
					<div>
						<button type="button" class="btn btn-primary" id="btn-execute">Show
							statistics</button>
					</div>
				</div>

				<br> <br>

				<div id="div-table">
					<!-- 					<h4>Table</h4> -->
					<table id="table" class="table table-striped table-bordered"
						cellspacing="0" width="100%">

						<thead>
							<tr id="table-header">
							</tr>
						</thead>
					</table>
				</div>

				<br>

				<div id="div-chartpicker">
					<b>Select columns</b>

					<div class="input-group col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<select class="form-control " id="chart-selector-text">
						</select>
					</div>
					<!-- /input-group -->
					<br>
					<div class="input-group col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<select class="form-control" id="chart-selector-number">
						</select>
					</div>
					<!-- /input-group -->
				</div>

				<!-- /.col-lg-6 -->
				<br>

				<div id="div-chart">
					<div id="chart"></div>
				</div>

			</div>
		</div>
	</div>
	<script type="text/javascript" language="javascript" src="js/util.js"></script>
	<script type="text/javascript" language="javascript"
		src="js/datepicker.js"></script>
	<script type="text/javascript" language="javascript"
		src="js/pieChart.js"></script>

</body>
</html>
