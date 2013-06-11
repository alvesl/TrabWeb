<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Autenticação</title>
<link href="./css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="span4">
	<div class='well'>
		<h3>Autenticação</h3>
		<form action="${authBean.action}" method="post">
			<div class='row-fluid'>
				<p>${authBean.resource}</p>
				<input name='user' type="text" />
			</div>
			<div class='row-fluid'>
				<p>Senha</p>
				<input name='senha' type="password" />
			</div>
			<input type="submit" value='Entra' />
		</form>
	</div>
</div>
</body>
</html>
