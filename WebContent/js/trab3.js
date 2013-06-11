$(document).ready(function(){
	$('#datepicker').datepicker().on('changeDate', function(e) {
	    clickedDate = new Date(e.date.toString());
	    day = clickedDate.getUTCDate();
	    month = clickedDate.getUTCMonth() + 1;
	    year = clickedDate.getUTCFullYear();
	
	    params = 'date=' + day + '-' + month + '-' + year;
	    window.location.href = "http://" + window.location.host + window.location.pathname + '?' + params;
	});
});