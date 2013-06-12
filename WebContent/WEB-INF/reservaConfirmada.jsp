<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reserva Confirmada</title>
</head>
<body>
<h1>Reserva Confirmada</h1>

<h3>Usuário: ${ confirmationBean.userName } </h3>
<c:forEach var="datetime" items="${confirmationBean.bookingModel.dateTimes}" varStatus="status">
	<p>Dia: <c:out value="${datetime.date}" />, hora: <c:out value="${datetime.time}" /></p>
</c:forEach>	
	
</body>
</html>