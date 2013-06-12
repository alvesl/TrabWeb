package jsp.trab3.dao;

import java.util.List;

import jsp.trab3.HibernateUtil;
import jsp.trab3.model.UserModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDAO {

	public static void insert (UserModel user) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();;
		Session session = sessionFactory.openSession();;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();;
			session.save(user);
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
	
	public static void remove (UserModel user) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();;
		Session session = sessionFactory.openSession();;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();;
			session.delete(user);
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

	public static UserModel getUser(String username) {

		UserModel user = null;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from UserModel USER where LOGIN = '" + username +"'");
		
		
		try {
			
			user = (UserModel)q.uniqueResult();
		} catch (Exception e) {
			
		}
		
		return user;
	}
	
	public static UserModel getUserById(int id) {

		UserModel user = null;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from UserModel USER where id = " + id);
		
		
		try {
			
			user = (UserModel)q.uniqueResult();
		} catch (Exception e) {
			
		}
		
		return user;
	}
	
	public static List<UserModel> getUsers() {
		
		List<UserModel> list = null ;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();;
		Session session = sessionFactory.openSession();;
		Query query = session.createQuery("from UserModel USER");
		
		try {
			list = query.list();
		} 
		catch (Exception e) {
			
		}
		
		
		return list;
		
		
	}
	
}
