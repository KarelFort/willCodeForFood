$(document).ready(function() {
	$(function() {

		$('#query-name').html("Query");

		$('#query').html("Select APPLICATION as Device, count(APPLICATION) as Count,(Count(APPLICATION)* 100 / (Select Count(*) " +
				"<br/>From Clients " +
				"<br/>Where CLIENT_NAME like \"ActiveSync%\")) as Percentage " +
				"<br/>From Clients Where CLIENT_NAME like \"ActiveSync%\" " +
		"<br/>Group by APPLICATION Order by Count desc");
		
		$('#description').html("Select type, percentage and count of mobile devices");




	});


});
