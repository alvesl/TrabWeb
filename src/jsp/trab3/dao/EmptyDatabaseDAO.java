package jsp.trab3.dao;

import jsp.trab3.HibernateUtil;
import jsp.trab3.model.UserModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EmptyDatabaseDAO {
	
	public static void eraseDB() {
		removeDatetime();
		removeBooking();
		removeLab();
		removeUser();
		
		UserModel u = new UserModel();
		u.setLogin("adm");
		u.setPassword("adm");
		u.setIsAdmin(true);
		UserDAO.insert(u);		
	}
	
	public static void removeUser () {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query q = session.createQuery("delete UserModel");
		
		try {
			transaction = session.beginTransaction();
			q.executeUpdate();
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
	
	public static void removeBooking () {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query q = session.createQuery("delete BookingModel");
		
		try {
			transaction = session.beginTransaction();
			q.executeUpdate();
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
	
	public static void removeDatetime () {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query q = session.createQuery("delete DateTimeModel");
		
		try {
			transaction = session.beginTransaction();
			q.executeUpdate();
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
	
	public static void removeLab () {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query q = session.createQuery("delete LabModel");
		
		try {
			transaction = session.beginTransaction();
			q.executeUpdate();
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
	
}
