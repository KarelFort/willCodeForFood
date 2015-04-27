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
//		var table;
//		table.clear();
		dateFrom = $("#datepicker-from").val();
		dateTo = $("#datepicker-to").val();
		id = $("#datepicker-query-id").val();

		$("#table-header").html('');

		if (dateFrom == "" || dateTo == "") {
			alert("Enter both date ranges");
			return;
		}

		if (dateFrom > dateTo) {
			alert("Enter the correct date range");
			return;
		}


		//send request with dates
		$.ajax({
			type: "POST",
			//the url where you want to sent the dates to
			url: 'query-datepicker',
			dataType: 'json',
			async: false,
			data: JSON.stringify({ "id": id, "date1": dateFrom, "date2" : dateTo }),

			success: function (data) {

				var jsondata = data["data"];
//				console.log(jsondata);
				if (jsondata.length > 1 ) {			

					var countCol = 0;
					var arrayColNames = [];
					var pom = jsondata[0];

					for (var key in pom){
						arrayColNames.push({"data": key});
						$("#table-header").append("<th>" + key + "</th>");
						countCol++;
					}
					
					if (table != undefined) {
						table.destroy();
					}

					//draw table
					table =	$('#table').dataTable( {
//						"processing": true,
//						"serverSide": true,
//						"ajax": {
//						"url": "data/result.json",
//						"url": jsondata,
//						"dataType": "json",
//						async: false,
//						},
						"data": jsondata,
						"columns": arrayColNames,
						aoColumnDefs: [
						               {
						            	   bSortable: true,
//						            	   aTargets: [-1, -2, -3] // disable sorting on last three columns (icons)
						               }
						               ]
					});

				}
				else {
					$("#datepicker-alert").append('<div class="alert alert-warning" id="alert">'
							+ '<button type="button" class="close" data-dismiss="alert">'
							+ '<span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'
							+ 'No data found</div>');

					// timer for alert
					var timeout = window.setTimeout(function () {
						// close alert
						$('#alert').slideUp(500, function () {
							$('#alert').alert('close');
							clearTimeout(timeout);
						});
					}, 3000);
					return;


				}
			},
			failure: function(errMsg) {
				alert(errMsg);
			}
		})
	});
});