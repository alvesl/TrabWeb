$(document).ready(function(){
	$('#datepicker').datepicker().on('changeDate', function(e) {
	    clickedDate = new Date(e.date.toString());
	    day = clickedDate.getUTCDate();
	    month = clickedDate.getUTCMonth() + 1;
	    year = clickedDate.getUTCFullYear();
	    var week = clickedDate.getUTCDay();
	    
	    params = 'date=' + day + '-' + month + '-' + year + '/' + week;
	    window.location.href = "http://" + window.location.host + window.location.pathname + '?' + params;
	});

	$('span#booking-cell').hover(function() {
		$(this).toggleClass('btn-success');
	});
	
	$('span#booking-cell').click(function(){
		var old_val = $("#date_times").val();
		var new_val = old_val + '/' + $(this).data('booking');
		
		$("#date_times").val(new_val);
		$('#booking-list').append("<p class='new-booking-time'>" + $(this).data('booking') + 'horas' + "</p>");
	});
	
	$('#erase-reservation').click(function(){
		$("#date_times").val('');
		$('.new-booking-time').remove();
	});
	
	$('#selectLab').on('change', function() {
		clickedDate = new Date($('#datepicker').data('datepicker').getDate());
	    day = clickedDate.getUTCDate();
	    month = clickedDate.getUTCMonth() + 1;
	    year = clickedDate.getUTCFullYear();
		var week = clickedDate.getUTCDay();
		params = 'date=' + day + '-' + month + '-' + year + '/' + week + '/' + $(this).find(":selected").text();
		window.location.href = "http://" + window.location.host + window.location.pathname + '?' + params;
	});
});