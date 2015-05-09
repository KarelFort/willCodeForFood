function createTable(jsonData) {
	
	var countCol = 0;
	var aoColNames = new Array();	
	var aColNames = new Array();
	
	// get column names and draw datatable header
	for (var key in jsonData[0]){
		aoColNames.push({"data": key}); 
		aColNames.push(key);
		$("#table-header").append("<th>" + key + "</th>");
		countCol++;
	}

	var tableSorting = isInArray(aColNames);			

	// draw datatable body with data
	if (!$.fn.DataTable.isDataTable('#table')) {
		var table =	$('#table').dataTable({
			"data": jsonData,
			"columns": aoColNames,
			"destroy": true,
			"aoColumnDefs": [{
				bSortable: true,
//				aTargets: [-1, -2, -3] // disable sorting on last three columns (icons)
			}],
			"order": tableSorting,
		});
		return;
	}
	//destroy and draw new one
	else {
		var table =	$('#table').dataTable({
			"data": jsonData,
			"columns": aoColNames,
			"destroy": true,
			"aoColumnDefs": [{
				bSortable: true,
//				aTargets: [-1, -2, -3] // disable sorting on last three columns (icons)
			}],
			"order": [[ 0, "desc" ]]
		});
	}
}