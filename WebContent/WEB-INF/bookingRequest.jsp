<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pedido de Reserva</title>
<link href="./css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="./css/datepicker.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="./js/trab3.js"></script>
</head>
<body>
<h1>Pedido de Reserva</h1>
<h3>Usuário: ${bookingRequestBean.userName}</h3>
<div class="row-fluid">
	<div class="span3">
		<div id="datepicker" data-date="${bookingRequestBean.dateTime}" data-date-format="dd-mm-yyyy"></div>
	</div>
	<div class="span5">
		<form action="./BookingRequest" class="form-horizontal">
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
		</form>
	</div>
	<div class='span4' id='booking_list'></div>
<div class="row-fluid">
	<div class="span8 offset2">
		${bookingRequestBean.table}
	</div>
</div>
</body>
</html>