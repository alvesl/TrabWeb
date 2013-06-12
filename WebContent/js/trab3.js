$(document).ready(function(){
	$('#datepicker').datepicker().on('changeDate', function(e) {
	    clickedDate = new Date(e.date.toString());
	    day = clickedDate.getUTCDate();
	    month = clickedDate.getUTCMonth() + 1;
	    year = clickedDate.getUTCFullYear();
	
	    params = 'date=' + day + '-' + month + '-' + year;
	    window.location.href = "http://" + window.location.host + window.location.pathname + '?' + params;
	});

	$('span#booking-cell').hover(function() {
		$(this).toggleClass('btn-success');
	});
	
	$('span#booking-cell').click(function(){
		$('form').append("<input class='new-booking-time' type='hidden' value='" + $(this).data('booking') + "'/>");
		$('#booking-list').append("<p class='new-booking-time'>" + $(this).data('booking') + 'horas' + "</p>");
	});
	
	$('#erase-reservation').click(function(){
		$('.new-booking-time').remove();
	});
});