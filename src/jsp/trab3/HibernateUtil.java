package jsp.trab3;



import org.hibernate.*;
import org.hibernate.cfg.*;


public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	
	static {
		
		try {
			sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
			
		} catch (HibernateException ex)
		{
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
}

