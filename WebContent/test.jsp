
<%@ page language="java" import="javax.persistence.*,jsp.trab3.model.*,jsp.trab3.dao.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teste</title>
</head>
<body>

<%


UserModel usr = new UserModel();

usr.setLogin("teste");
usr.setPassword("1234");
usr.setIsAdmin(false);
usr.setFullName("testebom");
usr.setEmail("noemail@mail.com");

UserModel user = UserDAO.getUser("teste", "1234");
UserDAO.remove(user);


%>

<%=user.getLogin() %>

</body>
</html>