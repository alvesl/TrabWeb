<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homologação</title>
<link href="./css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="./css/datepicker.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
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
	
	$('span#booking-cell').click(function() {
		$('form').append("<input type='hidden' name='bookID' value='" + $(this).data('bookingid') + "'/>");
		$('form').submit();
	});
});

</script>
</head>
<body>
<h1>Homologação</h1>
<h3>Usuário: ${adminHomologBean.userName}</h3>
<form action="./AdminHomologRequest" class="form-horizontal" method="post">
<div class="row-fluid">
	<div class="span3">
		<div id="datepicker" data-date="${adminHomologBean.dateTime}" data-date-format="dd-mm-yyyy"></div>
	</div>
	<div class="span5">

			<div class="control-group">
				<label class="control-label" for="responsavel">Responsável</label>
				<div class="controls">
					<input type="text" id="responsavel" name="responsavel">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="motivo">Motivo</label>
				<div class="controls">
					<input type="text" id="motivo" name="motivo">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="projeto">Projeto</label>
				<div class="controls">
					<input type="text" id="projeto" name="projeto">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="descricao">Descrição</label>
				<div class="controls">
					<input type="text" id="descricao" name="descricao">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input type="submit" class="btn" value="Pedir reserva">
				</div>
			</div>

		</div>
		</div>
		<div class='span4' id='booking_list'></div>
	<div class="row-fluid">
		<div class="span8 offset2">
			${adminHomologBean.table}
		</div>
	</div>
</form>
</body>
</html>