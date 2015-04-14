$(document).ready(function() {
	$(function() {

			var date1 = new Date;
			date1.setHours(0, 0, 0, 0);
			date1.setDate(10);
			var date2 = new Date;
			date2.setHours(0, 0, 0, 0);
			date2.setDate(23);
			$("#datepicker-from").datepicker({
				beforeShowDay: function(date) {
					return [date < date1 || date > date2, ""];
				}
			});
			
			$("#datepicker-to").datepicker({
				
			});
		});
	
	$("#btn-execute").click(function() {
		  alert("Executing query");
		});
	
});