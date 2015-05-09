/* Format date to "yyyy-mm-dd" */
function formatDate(date) {
	var d = new Date(date),
	month = '' + (d.getMonth() + 1),
	day = '' + d.getDate(),
	year = d.getFullYear();

	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;

	return [year, month, day].join('-');
}


/* Set pop-up alert */ 
function fnAlert(idAlert, alertType, text, duration) {
	$(idAlert).append('<div class="alert alert-'+ alertType +'" id="alert">'
			+ '<button type="button" class="close" data-dismiss="alert">'
			+ '<span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'
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