<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
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
	var bookId;
	
	$('#datepicker').datepicker().on('changeDate', function(e) {
	    clickedDate = new Date(e.date.toString());
	    day = clickedDate.getUTCDate();
	    month = clickedDate.getUTCMonth() + 1;
	    year = clickedDate.getUTCFullYear();
		var week = clickedDate.getUTCDay();
		$('#labHidden').val($('#selectLab').find(":selected").text());
	    params = 'date=' + day + '-' + month + '-' + year + '/' + week + '/' + $('#selectLab').find(":selected").text();
	    window.location.href = "http://" + window.location.host + window.location.pathname + '?' + params;
	});

	
	$('#defereAct').click(function() {
		
		$('#labHidden').val($('#selectLab').find(":selected").text());
		$('form').append("<input type='hidden' name='bookEmpty' value='true'/>");
		$('form').append("<input type='hidden' name='bookID' value='go'/>");
		$('form').append("<input type='hidden' name='acao' value='Defere'/>");
		$('form').submit();
		
	});

	$('#indefereAct').click(function() {
		$('#labHidden').val($('#selectLab').find(":selected").text());
		$('form').append("<input type='hidden' name='bookEmpty' value='true'/>");
		$('form').append("<input type='hidden' name='bookID' value='go'/>");
		$('form').append("<input type='hidden' name='acao' value='Indefere'/>");
		$('form').submit();
	});	
		
	$('span#booking-cell').click(function() {
		clickedDate = new Date($('#datepicker').data('datepicker').getDate());
	    day = clickedDate.getUTCDate();
	    month = clickedDate.getUTCMonth() + 1;
	    year = clickedDate.getUTCFullYear();
		var week = clickedDate.getUTCDay();
		$('form').append("<input type='hidden' name='bookID' value='" + $(this).data('bookingid') + "'/>");
		$('form').append("<input type='hidden' name='datePost' value='"+ day + '-' + month + '-' + year+ '/' + week + '/' + $('#selectLab').find(":selected").text()+"'/>");
		$('#labHidden').val($('#selectLab').find(":selected").text());
		$('#var').val($(this).data('bookingid'));
		
		$('form').submit();
	});
	
	$('#selectLab').on('change', function() {
		  $('#labHidden').val($(this).find(":selected").text());
		  $('form').submit();
	});
	
});

</script>
</head>
<body>
<a href="/trab3/">Logout</a>
<h1>Homologação</h1>
<h3>Sala:</h3>
<form action="./AdminHomologRequest" class="form-horizontal" method="post">
<div class="row-fluid">
	<div class="span3">
		<div class="control-group">
		
			<select id="selectLab">
					<c:forEach var="lab" 
					              items="${labs}" 
					              varStatus="status">
					<option><c:out value="${lab.name}"/> </option>
					</c:forEach>	
			</select>
			<input type="hidden" name="labName" id="labHidden" />
		</div>
		<div id="datepicker" data-date="${adminHomologBean.dateTime}" data-date-format="dd-mm-yyyy"></div>
	</div>

		<div class="span5">
			${adminHomologBean.table}
		</div>


		<div class="span2">
				<div class="control-group">
					<label class="control-label" for="responsavel">Responsável</label>
					<div class="controls">
						<input type="text" id="responsavel" name="responsavel" value="${selectedBooking.responsavel}" disabled>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="motivo">Motivo</label>
					<div class="controls">
						<input type="text" id="motivo" name="motivo" value="${selectedBooking.motivo}" disabled>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="projeto">Projeto</label>
					<div class="controls">
						<input type="text" id="projeto" name="projeto" value="${selectedBooking.projeto}" disabled>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="descricao">Descrição</label>
					<div class="controls">
						<input type="text" id="descricao" name="descricao" value="${selectedBooking.descricao}" disabled>
					</div>
				</div>
				<div class="control-group offset9">
				<table border="1">
					<tr>
						<td align="center">Dia</td>
						<td align="center">Horário</td>
					</tr>
						<c:forEach var="datetime" 
						              items="${selectedBookingBean}" 
						              varStatus="status">
						<tr>
							<td width="100%"><c:out value="${datetime.date}"/></td>
							<td width="100%"><c:out value="${datetime.time}"/></td>
						</tr>
						</c:forEach>						
					
					<tr>
						<td colspan="2">
							Motivo ou observações:
							<textarea name="obs" rows="7" cols="30"></textarea>
						</td>
					</tr>
					<tr>
						<td align="center">
								<input type="button" id="indefereAct"  name="acao" class="btn" value="Indefere" />
						</td>
						<td align="center">
								<input type="button" id="defereAct" name="acao" class="btn" value="Defere" />
						</td>
					</tr>
				</table>				
				</div>
	
			</div>	


</div>
	
<input type="hidden" id="var" />
</form>

</body>
</html>