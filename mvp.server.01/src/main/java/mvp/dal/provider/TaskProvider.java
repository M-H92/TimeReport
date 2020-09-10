package mvp.dal.provider;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import mvp.dal.model.Task;

public class TaskProvider {

	public List<Task> getAll()
	{
		SessionManager session = SessionManager.getSessionManager();
		session.openTransaction();
		String hql = "from task";
		Query query = session.getSession().createQuery(hql);
		List<Task> list = query.getResultList();
		
		return list;
	}
	
	
	public List<Task> getActive()
	{

		SessionManager session = SessionManager.getSessionManager();
		session.openTransaction();
		String hql = "from task where idStatus = 1";
		Query query = session.getSession().createQuery(hql);
		List<Task> list = query.getResultList();
		
		return list;
	}
	
	public Task getTask(int id) 
	{
		SessionManager sessionManager = SessionManager.getSessionManager();
		sessionManager.openTransaction();
		Task task = sessionManager.getSession().get(Task.class, id);
		sessionManager.commitTransaction();
		sessionManager.closeSession();
		return task;
	}
	
	public List<Task> getProjects()
	{
		SessionManager session = SessionManager.getSessionManager();
		session.openTransaction();
		String hql = "from task where parentTask is null";
		Query query = session.getSession().createQuery(hql);
		List<Task> list = query.getResultList();
		session.closeSession();
		return list;
	}
	

}
