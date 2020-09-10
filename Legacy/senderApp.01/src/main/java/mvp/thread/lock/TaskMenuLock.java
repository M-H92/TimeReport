package mvp.thread.lock;

public class TaskMenuLock {
	
	private static TaskMenuLock lock;
	public synchronized static TaskMenuLock getTaskMenuLock() {
		
		System.out.println("isTaskMenuLockNull ? = " + TaskMenuLock.lock == null);
		
		if(TaskMenuLock.lock == null)
			TaskMenuLock.lock = new TaskMenuLock();
		
		return TaskMenuLock.lock;
	}
	
	private TaskMenuLock() {
		System.out.println("Creating new instance of TaskMenuLock");
	}
	
	private String[] taskPaths;
	
	//if isUpdating, can't get taskPaths
	private boolean isUpdating;
	
	public synchronized void setTaskPaths(String[] value)
	{
		System.out.println("Entering setTaskPaths. isUpdating = " + isUpdating);
		while(!isUpdating) {
			try {
				System.out.println("Starting to wait into setTaskPaths");
				wait();
				System.out.println("Waiting is over in setTaskPaths. isUpdating = " + isUpdating);
			}
			catch(InterruptedException e) {
				System.out.println("Thread died while trying to set taskPaths : " + e.getMessage());
				Thread.currentThread().interrupt();
				System.exit(0);
			}
		}
		
		this.taskPaths = value.clone();
		isUpdating = false;
		notify();
	}

	public synchronized String[] getTaskPaths() 
	{
		while(isUpdating) {
			
			try {
				wait();
			} catch(InterruptedException e) {
				System.out.println("Thread died while trying to get taskPaths : " + e.getMessage());
				Thread.currentThread().interrupt();
				System.exit(0);
			}
		}
		
		return this.taskPaths.clone();
	}
	
	public synchronized void startUpdating() 
	{
		this.isUpdating = true;
		notify();
	}

	
	public boolean isUpdating() {
		return isUpdating;
	}

	public void setUpdating(boolean isUpdating) {
		this.isUpdating = isUpdating;
	}
	
	
	
	
}
