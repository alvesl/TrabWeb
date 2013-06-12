<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Criação de usuários</title>
<link href="./css/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
onload = function() {
    if (!document.getElementsByTagName || !document.createTextNode) return;
    var rows = document.getElementById('my_table').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function() {
            var hidden = document.getElementById('delUser');
            hidden.value = "del" + this.cells[1].innerHTML;
        }
    }
}
function getIsAdmin() {
	
	var hid = document.getElementById("isAdminHidden");
	var e = document.getElementById("select");
	hid.value = e.options[e.selectedIndex].value;
	
}

</script>
</head>
<body>
<h1>Cadastro de Usuário</h1>
<p>
<a href="/trab3/">Logout</a>
</p>
<form action="./UserCreation" method="post">
	<table>
		<tr>
			<td valign="top">
					<fieldset>
						<legend>Novo Usuário:</legend>
						<table>
							<tr>
								<td align="right">Username:</td><td><input name="login" type="text"/></td>
							</tr>
							<tr>
								<td align="right">Senha:</td><td><input name="pass" type="password"/></td>
							</tr>
							<tr>
								<td align="right">Confirmação da Senha:</td><td><input name="confPass" type="password"/></td>
							</tr>
							<tr>
								<td align="right">Nome Completo:</td><td><input name="fullName" type="text"/></td>
							</tr>
							<tr>
								<td align="right">e-Mail:</td><td><input name="email" type="text"/></td>
							</tr>
							<tr>
								<td align="right">Papel:</td>
								<td>
									<select id="select">
										<option value="0">Usuario</option>
										<option value="1">Administrador</option>
									</select>
									<input type="hidden" name="isAdmin" id="isAdminHidden" />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2"><input type="submit" name="option" value="Cadastrar" onclick="getIsAdmin()" /></td>
							</tr>
						</table>
					</fieldset>
			</td>
			<td valign="top">
				<fieldset>
					<legend>Usuários Cadastrados</legend>
					<table id="my_table">
							<c:forEach var="user" 
							              items="${users}" 
							              varStatus="status">
							<tr>
								<td><input type="submit" name="option" value="X"/></td><td width="100%"><c:out value="${user.login}"/></td>
							</tr>
							</c:forEach>	
					</table>
					<input type="hidden" name="delUser"  value="del" id="delUser" />
				</fieldset>
			</td>
		</tr>
	</table>
</form>
</body>

</body>


</html>