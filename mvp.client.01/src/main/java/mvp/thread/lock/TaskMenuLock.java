package mvp.thread.lock;

public class TaskMenuLock {	
	/*
	 * Static fields
	 */
	private static TaskMenuLock lock;
	
	
	
	/*
	 * Fields
	 */
	private String[] taskPaths;
	
	//if isUpdating, can't get taskPaths
	private boolean isUpdating;
	
	
	
	/*
	 * Static methods
	 */
	public synchronized static TaskMenuLock getTaskMenuLock() {
		
		if(TaskMenuLock.lock == null)
			TaskMenuLock.lock = new TaskMenuLock();
		
		return TaskMenuLock.lock;
	}
	
	
	
	/*
	 * Methods
	 */
	//TODO this is not called correctly. Check that and run tests !
	public synchronized void setTaskPaths(String[] value)
	{
		int timeout = 3;
		while(!isUpdating) {
			try {
				wait(3000);
				timeout--;
				if(timeout <= 0) {					
					throw new Exception();
				}
			}
			catch(InterruptedException e) {
				//TODO read that again and clean it up
				System.out.println("Thread died while trying to set taskPaths : " + e.getMessage());
				Thread.currentThread().interrupt();
				return;
			}
			catch(Exception e) {
				System.out.println("setTaskPaths timeout");
				Thread.currentThread().interrupt();
				return;
			}
		}
		
		this.taskPaths = value.clone();
		isUpdating = false;
		notify();
	}

	/*public synchronized String[] getTaskPaths(int timeout) 
	{
		while(isUpdating) {
			
			try {
				wait(1000);
				timeout--;
				if(timeout <= 0)
					throw new Exception("Timeout. Check server status then try again");
			} catch(InterruptedException e) {
				//TODO read that again and clean it up
				System.out.println("Thread died while trying to get taskPaths : " + e.getMessage());
				Thread.currentThread().interrupt();
				System.exit(0);
			} catch (Exception e) {
				Thread.currentThread().interrupt();
				//System.exit(0);
			}
		}
		
		return this.taskPaths.clone();
	}
	*/
	public synchronized String[] getTaskPaths(int timeout) throws InterruptedException, Exception 
	{
		while(isUpdating) {
			
				if(timeout <= 0) {
					throw new Exception("mvp client info : Timeout. Couldn't reach mvp server...");
				}
				else {
					System.out.println("mvp client info : Waiting for the response from mvp server...");					
				}
				wait(1000);
				timeout--;
		}
		
		return this.taskPaths.clone();
	}
	
	public synchronized void startUpdating() 
	{
		this.isUpdating = true;
		notify();
	}

	
	
	/*
	 * get/set
	 */
	public synchronized void setUpdating(boolean isUpdating) {
		this.isUpdating = isUpdating;
	}
	
	//Pretty much just a simple getter. Name is better suited that way to understand tests in one reading
	public synchronized boolean isUpdating() {
		return isUpdating;
	}
	
	
	
	/*
	 * Ctor
	 */
	private TaskMenuLock() {
		
	}
	
	
}
