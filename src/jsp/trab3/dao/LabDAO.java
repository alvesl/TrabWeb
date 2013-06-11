package jsp.trab3.dao;

import java.util.List;

import jsp.trab3.HibernateUtil;
import jsp.trab3.model.LabModel;
import jsp.trab3.model.UserModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class LabDAO {

	public static void insert (LabModel lab) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();;
		Session session = sessionFactory.openSession();;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();;
			session.save(lab);
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
	
	public static void remove (LabModel lab) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();;
		Session session = sessionFactory.openSession();;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();;
			session.delete(lab);
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
	
	public static List<LabModel> getList() {
		
		List<LabModel> list = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();;
		Session session = sessionFactory.openSession();;
		Query query = session.createQuery("from LabModel LAB");
		
		try {
			list = query.list();
		} 
		catch (Exception e) {
			
		}
		
		
		return list;
	}
	
	public static LabModel getLab(String labname) {

		LabModel lab = null;
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from LabModel LAB where NAME = '" + labname +"'");
		
		
		try {
			
			lab = (LabModel)q.uniqueResult();
		} catch (Exception e) {
			
		}
		
		return lab;
	}
	
}
