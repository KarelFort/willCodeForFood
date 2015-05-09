$(document).ready(function() {
	$("#div-chartpicker").hide();
	var clickCount = 0;

	/* jQuery datepicker (calendar) */
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

	/* Show statistic */
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

				//prepare data for datatable and chart
				var jsonData = data["data"];
				var jsonDataType = data["dataType"];

				if (jsonData.length > 1 ) {

					clickCount++;

					/** Pie chart **/
					//set datatypes used in DB to selectors
					var aTextType = ["string", "varchar"];
					var aNumType = ["int", "integer", "number"];
					var countText = 0, countNum = 0;

					$("#div-chartpicker").show();

					//for the first time fulfill options and create default chart
					if (clickCount == 1) {

						//set options to chartpicker + default values for chart
						for (var j in jsonDataType){
							if (aTextType.indexOf(jsonDataType[j]) > -1) {
								$("#chart-selector-text").append("<option>" + j + "</option>");
								countText++;				
							}

							if (aNumType.indexOf(jsonDataType[j]) > -1) {
								$("#chart-selector-number").append("<option>" + j + "</option>");
								countNum++;
							}
						}

						removeChart();
						createChart(jsonData); 
					}

					//handlers for updating datetypes  in chart
					$("#chart-selector-text").change(function() {
						removeChart();
						createChart(jsonData);
					});

					$("#chart-selector-number").change(function() {
						removeChart();
						createChart(jsonData);
					});

					/** Datatables **/
					createTable(jsonData);

				}

				else {
					fnAlert('#datepicker-alert', 'warning', 'No data found', 3000);
				}
			},

			error: function(XMLHttpRequest, textStatus, errorThrown) {
				fnAlert('#datepicker-error', 'warning', 'No data found. Check the correctness of the query.', 3000);
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
		return [1,'desc'];
	}  
}

//handle errors
$.ajaxSetup({
	statusCode: {
		500: function(data) {
			alert("500 Internal Server Error. Check the correctness of the query.");
		}    
	}});



