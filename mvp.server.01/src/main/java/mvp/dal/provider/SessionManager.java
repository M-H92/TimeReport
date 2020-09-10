package mvp.dal.provider;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import mvp.dal.model.Employee;
import mvp.dal.model.EmployeeRoleTaskLink;
import mvp.dal.model.RFID;
import mvp.dal.model.Task;
import mvp.dal.model.Timesheet;

/**
 * SessionManager class
 * singleton. Use getSessionManager() to retrive the instance
 * configure hibernate and manage session / transactions
 */
public class SessionManager {
	
	private static SessionManager sessionManager;
	
	public static SessionManager getSessionManager() 
	{
		if(SessionManager.sessionManager == null) 
		{
			return SessionManager.sessionManager = new SessionManager();
		}
		else
		{
			SessionManager.sessionManager.openSession();
			return SessionManager.sessionManager;
		}
	}

	private Session session;
	private Transaction transaction;
	private SessionFactory factory;
	
	public Session getSession() {
		if(this.session == null)
		{
			this.session = this.factory.openSession();
		}
		
		return this.session;
	}
	
	public void setUp() {
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Task.class)
				.addAnnotatedClass(mvp.dal.model.Role.class)
				.addAnnotatedClass(EmployeeRoleTaskLink.class)
				.addAnnotatedClass(RFID.class)
				.addAnnotatedClass(Timesheet.class);
		
		this.factory = configuration.buildSessionFactory();
		this.session = factory.openSession();
	}
	
	public void openTransaction() {
		this.transaction = session.beginTransaction();
	}
	
	public void commitTransaction() {
		this.transaction.commit();
	}
	
	
	public void openSession() {
		if(this.session.isOpen()) {
			this.closeSession();
		}
		this.setUp();
	}
	
	public void closeSession() {
		this.session.close();
	}
	
	private SessionManager() 
	{
		this.setUp();
	}

	public void rollback() {
		this.transaction.rollback();
		this.closeSession();
		
	}
}
