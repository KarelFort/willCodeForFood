<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon" href="img/favicon.ico" type="image/vnd.microsoft.icon" />

<title>Edit statistic | ClientStatistics - Kerio Connect</title>

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
				<br>
				
				<form action="delete-query" method="post" class="pull-right">		
						<input type="hidden" name="id" value="${gueryToEdit.id}" />
		
						<input type="submit" value="Delete" class="btn btn-danger">
				</form>	
				<h1 class="page-header">Edit statistic</h1>
				
				<form action="edit-query" method="post" >
						<label>Name:</label>  (required)
						<input type="text" name="name" class="form-control" value="${gueryToEdit.name}" required> 
						
						<label>Description:</label>  (required)
						<input type="text" name="info" class="form-control" value="${gueryToEdit.info}" required>
						 
						<label>Query:</label>  (required)
						
						<textarea rows="7" cols="50" name="statement" class="form-control" value="${gueryToEdit.statement}" required>${gueryToEdit.statement}</textarea>					

						<input type="hidden" name="id" value="${gueryToEdit.id}" />
						<br>
						<input type="submit" value="Save" class="btn btn-success">
				</form>
						
			</div>
		</div>
	</div>
</body>
</html>
