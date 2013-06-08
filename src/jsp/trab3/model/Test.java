package jsp.trab3.model;

import jsp.trab3.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Test {

	public static void TestSave(UserModel usr) {
		
		SessionFactory sessionFactory;
		Session session;
		Transaction transaction;
		UserModel user;
		sessionFactory = HibernateUtil.getSessionFactory();
		
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
		
		session.save(usr);
		transaction.commit();
		session.close();
		
		/*EntityManagerFactory factory = Persistence.createEntityManagerFactory("trab3DB");
		EntityManager em = factory.createEntityManager();


		em.getTransaction().begin();

		em.persist(usr);
		em.getTransaction().commit();*/
	}
	
}
