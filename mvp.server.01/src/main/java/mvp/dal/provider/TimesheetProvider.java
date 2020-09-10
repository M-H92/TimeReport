package mvp.dal.provider;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import mvp.dal.model.Task;
import mvp.dal.model.Timesheet;

public class TimesheetProvider {

	public List<Timesheet> getAll()
	{
		SessionManager session = SessionManager.getSessionManager();
		session.openTransaction();
		String hql = "from timesheet";
		Query query = session.getSession().createQuery(hql);
		List<Timesheet> list = query.getResultList();
		
		return list;
	}
	
	
	public Timesheet getTimesheet(int id) 
	{
		SessionManager sessionManager = SessionManager.getSessionManager();
		sessionManager.openTransaction();
		Timesheet timesheet = sessionManager.getSession().get(Timesheet.class, id);
		sessionManager.commitTransaction();
		sessionManager.closeSession();
		return timesheet;
	}
	
	/**
	 * return int returnCode
	 * 		0 = ok
	 * 		1 = ko
	 */
	public int createTimesheet(Timesheet timesheet) {
		int returnCode = 0;
		SessionManager sessionManager = SessionManager.getSessionManager();
		sessionManager.openTransaction();
		try {
			
			sessionManager.getSession().save(timesheet);
			sessionManager.commitTransaction();
		}
		catch(Exception e) {
			returnCode = 1;
			sessionManager.rollback();
		}
		finally {
			sessionManager.closeSession();
			
		}
		return returnCode;
	}
	

}
