$(document).ready(function() {
	$(function() {

		var dateFrom;
		var dateTo;

		//send req: id and date range and display it correctly 



		var date1 = new Date;
		date1.setHours(0, 0, 0, 0);
		date1.setDate(10);

		var date2 = new Date;
		date2.setHours(0, 0, 0, 0);
		date2.setDate(23);

		$("#datepicker-from").datepicker({
			dateFormat: 'yy-mm-dd',
//			beforeShowDay: function(date) {
//			return [date < date1 || date > date2, ""];
//			}
		});

		$("#datepicker-to").datepicker({
			dateFormat: 'yy-mm-dd',
		});
	});

	$("#btn-execute").click(function() {

		dateFrom = $("#datepicker-from").val();
		dateTo = $("#datepicker-to").val();

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
			url: 'index',
			dataType: 'json',
			async: false,
			data: JSON.stringify({ "dateFrom": dateFrom, "dateTo" : dateTo }),
			success: function () {
				alert("Request sent!"); 
			},
			failure: function(errMsg) {
				alert(errMsg);
			}
		})

	});
});