$(document).ready(function() {
	$(function() {

		var dateFrom;
		var dateTo;
		var id;

		$("#datepicker-from").datepicker({
			dateFormat: 'yy-mm-dd',
		});

		$("#datepicker-to").datepicker({
			dateFormat: 'yy-mm-dd',
		});
	});

	$("#btn-execute").click(function() {

		dateFrom = $("#datepicker-from").val();
		dateTo = $("#datepicker-to").val();
		id = $("#datepicker-query-id").val();

		if (dateFrom == "" || dateTo == "") {
			alert("Enter both date ranges");
			return;
		}

		if (dateFrom > dateTo) {
			alert("Enter the correct date range");
			return;
		}

		//send req with dates
		$.ajax
		({
			type: "POST",
			//the url where you want to sent the dates to
			url: 'query-datepicker',
			dataType: 'json',
			async: false,
			data: JSON.stringify({ "id": id, "date1": dateFrom, "date2" : dateTo }),
			success: function () {
				alert("Request sent!"); 
				
			},
			failure: function(errMsg) {
				alert(errMsg);
			}
		})

	});
});