$(document).ready(function() {

	$.ajax({ 
		type: 'GET', 
		url: 'data/result.json', 
		data: { get_param: 'value' }, 
		dataType: 'json',
		success: function (data) { 
			console.log(data);

			$('#table').dataTable( {
				"processing": true,
				"serverSide": false,
				"ajax": "data/result.json",
				"columns": [
				            { "data": "device" },
				            { "data": "percentage" },
				            { "data": "count" }
				            ],
				            aoColumnDefs: [
				                           {
				                        	   bSortable: true,
//				                        	   aTargets: [-1, -2, -3] // disable sorting on last three columns (icons)
				                           }
				                           ]
			});
		}

	});
});

