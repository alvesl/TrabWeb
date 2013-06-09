
<%@ page language="java" import="javax.persistence.*,jsp.trab3.model.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Testee</title>
</head>
<body>

<%


UserModel usr = new UserModel();

usr.setLogin("LucasViado");
usr.setPassword("carlogay");
usr.setIsAdmin(false);
usr.setFullName("CrisChupaRola");
usr.setEmail("noemail@mail.com");

Test.TestSave(usr);



%>

</body>
</html>