import javax.management.relation.Role;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import mvp.dal.model.Employee;
import mvp.dal.model.EmployeeRoleTaskLink;
import mvp.dal.model.RFID;
import mvp.dal.model.Task;
import mvp.dal.model.Timesheet;
import mvp.dal.provider.SessionManager;
import mvp.dal.provider.TaskProvider;
import mvp.mqtt.receiver.Subscriber;

import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		System.out.println("Hello receiver App");

/*
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Task.class)
				.addAnnotatedClass(mvp.dal.model.Role.class)
				.addAnnotatedClass(EmployeeRoleTaskLink.class)
				.addAnnotatedClass(RFID.class)
				.addAnnotatedClass(Timesheet.class);
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = factory.openSession();
		*/
		/*
		Transaction transaction = session.beginTransaction();
		*/
		/*
		session.save(owner);
		session.save(pet);
		session.save(race);
		*/
/*
		Employee employee = session.get(Employee.class, 1);
		Task dummy = session.get(Task.class, 18);
		Task mvp = session.get(Task.class, 1);
		transaction.commit();
		*/

		//System.out.println(employee);
		/*for(EmployeeRoleTaskLink ert : employee.getERTLink()) 
		{

			System.out.println("---------------------------------------------------");
			System.out.println("---------------------------------------------------");
			System.out.println("---------------------------------------------------");
			System.out.println(ert);
			System.out.println(ert.getEmployee());
			System.out.println(ert.getTask().getName());
			System.out.println(ert.getRole());
			
		}*/
		//System.out.println(session.get(Task.class, 1));
/*
		session.close();*/

		/*
		List<String> topicPath = new ArrayList<String>();
		topicPath.add("");
		dummy.getActiveTopics(topicPath);
		
		for(String path : topicPath) {
			System.out.println(path);
		}*/
		
		
		
		//Latest working code
		/*
		mvp.dal.provider.TaskProvider taskProvider = new mvp.dal.provider.TaskProvider();
		Task mvp = taskProvider.getTask(1);
		Task dummy = taskProvider.getTask(18);
		
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(mvp);
		tasks.add(dummy);
		
		Subscriber subscriber = new Subscriber();
		subscriber.subToTasks(tasks);
		*/
		
		//testing hql
		//new TaskProvider().getAll();
		/*
		List<Task> projectList = new TaskProvider().getProjects();
		for(Task project : projectList) {
			List<String> topicPath = new ArrayList<String>();
			project.getTopics(topicPath);
			for(String path : topicPath) 
			{
				System.out.println(path);
			}
			try {
				Thread.sleep(1000);
				System.out.println("");
				System.out.println("");
				System.out.println("");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
		//Testing subscriber
		
		Subscriber subscriber = new Subscriber();
		//subscriber.subToTimesheet();
		
		/*
		Subscriber subscriber = new Subscriber();
		List<Task> tasks = new TaskProvider().getAll();
		subscriber.subToTasks(tasks);
		*/
		//Subscriber subscriber = new Subscriber();
		subscriber.subToTimesheet();
		try {
			subscriber.subToAskTopicList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
