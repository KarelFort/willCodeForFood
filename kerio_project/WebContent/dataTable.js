$(document).ready(function() {
	$('#dotaz').html("Select APPLICATION as Device, count(APPLICATION) as Count,(Count(APPLICATION)* 100 / (Select Count(*) " +
			"<br/>From Clients " +
			"<br/>Where CLIENT_NAME like \"ActiveSync%\")) as Percentage " +
					"<br/>From Clients Where CLIENT_NAME like \"ActiveSync%\" " +
							"<br/>Group by APPLICATION Order by Count desc");
	$('#popis').html("Výběr typu, procentuálního zastoupení z výběru a počtu mobilních zažízení");
	

	$.ajax({ 
		type: 'GET', 
		url: 'result.json', 
		data: { get_param: 'value' }, 
		dataType: 'json',
		success: function (data) { 
			console.log(data);

   $('#tabulka').dataTable( {
        "processing": true,
        "serverSide": false,
        "ajax": "result.json",
        "columns": [
            { "data": "adevice" },
            { "data": "percentage" },
            { "data": "count" }
        ],
        aoColumnDefs: [
                       {
                           bSortable: true,
//                           aTargets: [-1, -2, -3] // disable sorting on last three columns (icons)
                       }
                   ]
    } );



//			
//			$('#tabulka').dataTable({
//		        "processing": true,
//		        "serverSide": true,
//		        "ajax": data,// <-- your array of objects
////		        "columns": [
////		            { "data": "adevice" }, // <-- which values to use inside object
////		            { "data": "percentage" },
////		            { "data": "count" }
////		        ]
//		  });
//			
//
//

		}

	});


});



//$(document).ready(function() {
//    $('#tabulka').dataTable( {
//        "processing": true,
//        "serverSide": true,
//        "ajax": "result.json",
//        "columns": [
//            { "data": "adevice" },
//            { "data": "percentage" },
//            { "data": "count" }
//        ]
//    } );
//} );