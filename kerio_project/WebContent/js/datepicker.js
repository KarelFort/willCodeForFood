$(document).ready(function() {

	var dateFrom;
	var dateTo;
	var id;

	var today = new Date();
	var defaultTo = formatDate(today);
	$("#datepicker-to").val(defaultTo);
	var defaultFrom = formatDate(today.setMonth(today.getMonth() - 2));
	$("#datepicker-from").val(defaultFrom);

	$("#datepicker-from").datepicker({
		dateFormat: 'yy-mm-dd',
		maxDate: '0'
	});

	$("#datepicker-to").datepicker({
		dateFormat: 'yy-mm-dd',
		maxDate: '0',
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
					var aoColNames = new Array();	
					var aColNames = new Array();

					// get column names and draw datatable header
					for (var key in jsondata[0]){
						aoColNames.push({"data": key});
						aColNames.push(key);
						$("#table-header").append("<th>" + key + "</th>");
						countCol++;
					}

					var tableSorting = isInArray(aColNames);			

					// draw datatable body with data
					if (!$.fn.DataTable.isDataTable('#table')) {
						var table =	$('#table').dataTable({
							"data": jsondata,
							"columns": aoColNames,
							"destroy": true,
							"aoColumnDefs": [{
								bSortable: true,
//								aTargets: [-1, -2, -3] // disable sorting on last three columns (icons)
							}],
							"order": tableSorting,
						});
						return;
					}
					// destroy and draw new one
					else {
						var table =	$('#table').dataTable({
							"data": jsondata,
							"columns": aoColNames,
							"destroy": true,
							"aoColumnDefs": [{
								bSortable: true,
//								aTargets: [-1, -2, -3] // disable sorting on last three columns (icons)
							}],
							"order": [[ 0, "desc" ]]
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

});


function isInArray(aoColNames) {
	if (aoColNames.indexOf("Count") > -1) {
		var index = aoColNames.indexOf("Count");
		return [index,'desc'];
	}

	if (aoColNames.indexOf("Percentage") > -1) {
		var index = aoColNames.indexOf("Percentage");
		return [index,'desc'];
	}

	else {
		alert("I found nothing");
		return [1,'desc'];
	}  
}


function formatDate(date) {
	var d = new Date(date),
	month = '' + (d.getMonth() + 1),
	day = '' + d.getDate(),
	year = d.getFullYear();

	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;

	return [year, month, day].join('-');
}



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