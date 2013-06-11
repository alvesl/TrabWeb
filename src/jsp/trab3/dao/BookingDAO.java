package jsp.trab3.dao;

import jsp.trab3.HibernateUtil;
import jsp.trab3.model.BookingModel;

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
	
}
