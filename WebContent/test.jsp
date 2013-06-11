
<%@page import="org.hibernate.mapping.OneToOne"%>
<%@ page language="java" import="javax.persistence.*, jsp.trab3.model.*,jsp.trab3.dao.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teste</title>
</head>
<body>

<%
UserModel user = UserDAO.getUser("LucasViado");
LabModel lab = LabDAO.getList().get(0);

BookingModel booking = new BookingModel();
booking.setLab(lab);
booking.setUser(user);

BookingDAO.insert(booking);

//LabModel lab = new LabModel();

//lab.setName("LabGrad");

//LabDAO.insert(lab);

//LabModel l = LabDAO.getList().get(0);

%>

</body>
</html>