<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon" href="img/favicon.ico" type="image/vnd.microsoft.icon" />

<title>Add query | ClientStatistics - Kerio Connect</title>

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
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h1 class="page-header">Add statistic</h1>

				<form action="add-query" method="post" >
						<label>Name:</label>  (required)
						<input type="text" name="name" class="form-control" required> 
						
						<label>Description:</label>  (required)
						<input type="text" name="info" class="form-control" required>
						 
						<label>Query:</label>  (required)
						<input type="text" name="statement" class="form-control" required> 
						<br>
						<input type="submit" value="Save" class="btn btn-success">
				</form>		
			</div>
		</div>
	</div>
</body>
</html>
