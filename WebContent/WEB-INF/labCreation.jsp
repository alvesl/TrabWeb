<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Laboratórios</title>
<link href="./css/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
onload = function() {
    if (!document.getElementsByTagName || !document.createTextNode) return;
    var rows = document.getElementById('my_table').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function() {
            var hidden = document.getElementById('labSelector');
            hidden.value = "del" + this.cells[1].innerHTML;
        }
    }
}
</script>
</head>
<body>
<h1>Cadastro de Unidades</h1>
<p>
<a href="menu.html">Início</a> | <a href="..">Logout</a>
</p>
<form action="./LabCreation" method="post">
	<table>
		<tr>
			<td valign="top">
				
					<fieldset>
						<legend>Nova Unidade:</legend>
						Nome da Unidade: <input name="labName" type="text"/>
						<input type="submit" name="option" value="Cadastrar"/>
					</fieldset>
			</td>
			<td valign="top">
					<table border="1" id="my_table">
						<tr>
							<td colspan="2">Lista de Unidades Cadastradas:</td>
						</tr>
						<c:forEach var="lab" 
						              items="${labs}" 
						              varStatus="status">
						<tr>
							<td><input type="submit" name="option" value="X"/></td><td width="100%"><c:out value="${lab.name}"/></td>
						</tr>
						</c:forEach>						
					</table>
			</td>
		</tr>
	</table>
	<input type="hidden" value="del" name="hidden" id="labSelector">
</form>

</body>
</html>