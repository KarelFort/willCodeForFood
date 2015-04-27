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

		$("#table-header").html('');

		if (dateFrom == "" || dateTo == "") {
			fnAlert('#datepicker-alert', 'danger', 'Enter both date inputs', 3000);
			return;
		}

		if (dateFrom > dateTo) {
			fnAlert('#datepicker-alert', 'danger', 'Enter the correct date range', 3000);
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
				
				if (jsondata.length > 1 ) {			

					var countCol = 0;
					var arrayColNames = [];
					
					// get column names and draw datatable header
					for (var key in jsondata[0]){
						arrayColNames.push({"data": key});
						$("#table-header").append("<th>" + key + "</th>");
						countCol++;
					}

					// draw datatable body with data
					if (!$.fn.DataTable.isDataTable('#table')) {
						var table =	$('#table').dataTable({
							"data": jsondata,
							"columns": arrayColNames,
							"destroy": true,
							aoColumnDefs: [{
								bSortable: true,
//								aTargets: [-1, -2, -3] // disable sorting on last three columns (icons)
							}]
						});
						return;
					}
					// destroy and draw new one
					else {
						var table =	$('#table').dataTable({
							"data": jsondata,
							"columns": arrayColNames,
							"destroy": true,
							aoColumnDefs: [{
								bSortable: true,
//								aTargets: [-1, -2, -3] // disable sorting on last three columns (icons)
							}]
						});
					}


				}

				else {
					fnAlert('#datepicker-alert', 'warning', 'No data found', 3000);
				}
			},

			failure: function(errMsg) {
				alert(errMsg);
			}
		})
	});

	function fnAlert(idAlert, alertType, text, duration) {
		$(idAlert).append('<div class="alert alert-'+ alertType +'" id="alert">'
				+ '<button type="button" class="close" data-dismiss="alert">'
				+ '<span aria-hidden="true">&times;</span><span class="sr-only">Zavřít</span></button>'
				+ text +'</div>');

		var timeout = window.setTimeout(function () {
			// close pop-up alert
			$('#alert').slideUp(500, function () {
				$('#alert').alert('close');
				clearTimeout(timeout);
			});
		}, duration);
		return;
	}
});