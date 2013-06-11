
<%@page import="java.sql.Time"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
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
<<<<<<< Updated upstream
UserModel user = UserDAO.getUser("LucasViado");
LabModel lab = LabDAO.getList().get(0);
=======
UserModel user = new UserModel();
LabModel lab = new LabModel();
DateTimeModel dt = new DateTimeModel();
dt.setDate(Timestamp.valueOf("2013-01-02 12:00:00"));
dt.setTime(Time.valueOf("12:00:00"));
>>>>>>> Stashed changes

user.setId(1);
lab.setId(1);
lab.setName("LabGrad");


BookingModel booking1 = new BookingModel();
booking1.setLab(lab);
booking1.setUser(user);
booking1.setId(1);
booking1.getDateTimes().add(dt);

BookingModel bk = new BookingModel();
bk.setId(17);

//BookingDAO.insert(booking1);
BookingDAO.getWeek(lab, "2013-01-01");
//BookingDAO.remove(bk);

//LabModel lab = new LabModel();

//lab.setName("LabGrad");

//LabDAO.insert(lab);

//LabModel l = LabDAO.getList().get(0);

%>

</body>
</html>