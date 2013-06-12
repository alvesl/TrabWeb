package jsp.trab3.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jsp.trab3.HibernateUtil;
import jsp.trab3.model.BookingModel;
import jsp.trab3.model.DateTimeModel;
import jsp.trab3.model.LabModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BookingDAO {

	public static void insert (BookingModel booking) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();;
		Session session = sessionFactory.openSession();;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();;
			session.save(booking);
			transaction.commit();
		}
		catch (Exception e) {
			if(transaction != null) transaction.rollback();
		}
		finally {
			session.flush();
			session.close();
		}
	}
	
	public static void remove (BookingModel booking) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query q = session.createQuery("delete DateTimeModel DATETIME where booking_ID = " + booking.getId() +"");
		
		try {
			transaction = session.beginTransaction();
			q.executeUpdate();
			session.delete(booking);
			transaction.commit();
		}
		catch (Exception e) {
			if(transaction != null) transaction.rollback();
		}
		finally {
			session.flush();
			session.close();
		}
	}
	
	public static ArrayList<ArrayList<BookingModel>> getWeekByUser(LabModel lab, String date, int userId) {
		ArrayList<ArrayList<BookingModel>> list = new ArrayList<ArrayList<BookingModel>>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		
		try {
			for(int i = 0 ; i <= 6 ; i++)
			{
				list.add(getByUser(lab, date, userId));
				c.setTime(sdf.parse(date));
				c.add(Calendar.DATE, 1);  // number of days to add
				date = sdf.format(c.getTime());
			}
		}
		catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		return list;
	}
	
	public static ArrayList<ArrayList<BookingModel>> getWeek(LabModel lab, String date) {
		ArrayList<ArrayList<BookingModel>> list = new ArrayList<ArrayList<BookingModel>>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		
		try {
			for(int i = 0 ; i <= 6 ; i++)
			{
				list.add(get(lab, date));
				c.setTime(sdf.parse(date));
				c.add(Calendar.DATE, 1);  // number of days to add
				date = sdf.format(c.getTime());
			}
		}
		catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		return list;
	}
	
	public static ArrayList<BookingModel> getByUser(LabModel lab, String date, int userId) {

		ArrayList<BookingModel> list = new ArrayList<BookingModel>();
				
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from BookingModel b join b.dateTimes d where b.user.id = " + Integer.toString(userId) + " and b.lab.name = '" + lab.getName() +"' and d.date = '" + date + "' order by b.id, d.date");
		
		List<Object> l = null;
		try {	
			l = q.list();
		} 
		catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		for(int i = 0 ; i < l.size(); i++)
		
		{
			BookingModel b = (BookingModel)((Object[])l.get(i))[0];
			
			if((list.size() == 0) || (list.get(list.size() - 1).getId() != b.getId()))
			{
				list.add(b);
				b.setDateTimes(new ArrayList<DateTimeModel>());
			}
			
			list.get(list.size() - 1).getDateTimes().add((DateTimeModel)((Object[])l.get(i))[1]);
		}
		
		return list;
	}
	
	public static ArrayList<BookingModel> get(LabModel lab, String date) {

		ArrayList<BookingModel> list = new ArrayList<BookingModel>();
				
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from BookingModel b join b.dateTimes d where b.lab.name = '" + lab.getName() +"' and d.date = '" + date + "' order by b.id, d.date");
		
		List<Object> l = null;
		try {	
			l = q.list();
		} 
		catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		for(int i = 0 ; i < l.size(); i++)
		
		{
			BookingModel b = (BookingModel)((Object[])l.get(i))[0];
			
			if((list.size() == 0) || (list.get(list.size() - 1).getId() != b.getId()))
			{
				list.add(b);
				b.setDateTimes(new ArrayList<DateTimeModel>());
			}
			
			list.get(list.size() - 1).getDateTimes().add((DateTimeModel)((Object[])l.get(i))[1]);
		}
		
		return list;
	}
	
	public static BookingModel getBooking(String id) {

		BookingModel booking = null;
				
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from BookingModel b where b.id = '" + id +"'");

		try {	
			booking = (BookingModel)q.uniqueResult();
		} 
		catch (Exception e) {
			System.out.print(e.getMessage());
		}
		finally {
			session.flush();
			session.close();
		}
		
		return booking;
	}
	
	public static void homolog (int bookingId, boolean isDef, String msg) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		
		//Query q = session.createQuery("updade BookingModel b set b.isHomolog = 0, b.isDefered = " + shouldDef +", b.deferedObs = "+ msg + " where b.id = " + bookingId +"");
		
		try {
			transaction = session.beginTransaction();
			BookingModel booking = getBooking(Integer.toString(bookingId));
			booking.setHomolog(false);
			booking.setDefered(isDef);
			booking.setDeferedObs(msg);
			session.update(booking);
			//q.executeUpdate();
			transaction.commit();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			if(transaction != null) transaction.rollback();
		}
		finally {
			session.flush();
			session.close();
		}
	}
	
}
