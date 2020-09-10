package mvp.gui;

import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.*;

import mvp.bl.model.Timesheet;
import mvp.mqtt.sender.Publisher;

import java.awt.*;

public class FormTask extends JFrame implements ActionListener {


    // Components of the Form 
    private Container c; 
    
    //Title
    private JLabel title; 
    
    //idEmployee
    private JLabel idEmployee; 
    private JTextField tfidEmployee; 
    
    //task list
    private JLabel task; 
    private JComboBox cbTask;
    private String tasks[] = {"Failed to load task list"};
    
    //StartDate
    private Date startDate = new Date();
    private Date endDate = new Date();
    private JLabel date; 
    private JComboBox day; 
    private String days[] 
            = { "1", "2", "3", "4", "5", 
                "6", "7", "8", "9", "10", 
                "11", "12", "13", "14", "15", 
                "16", "17", "18", "19", "20", 
                "21", "22", "23", "24", "25", 
                "26", "27", "28", "29", "30", 
                "31" }; 
    private JComboBox month; 
    private String months[] 
            = { "01", "02", "03", "04", 
                "05", "06", "07", "08", 
                "09", "10", "11", "12" }; 
    private JComboBox year; 
    private String years[] 
            = { "2020", "2021", "2022", "2023", 
                "2024", "2025", "2026", "2027", 
                "2028", "2029", "2030", "2031", 
                "2032", "2033", "2034", "2035", 
                "2036", "2037", "2038", "2039"}; 
    
    //Start Hours
    private JLabel start; 
    private JComboBox hour; 
    private String hours[] 
            = { "0","1", "2", "3", "4", "5", 
                "6", "7", "8", "9", "10", 
                "11", "12", "13", "14", "15", 
                "16", "17", "18", "19", "20", 
                "21", "22", "23"}; 
    private JComboBox minute;
    private String minutes[] 
            = { "0", "5", "10", "15", "20", 
                "25", "30", "35", "40", "45", 
                "50", "55"}; 
    
    //End Hours
    private JLabel end;
    private JComboBox endHour;
    private JComboBox endMinute;
    
    
    //private JLabel add; 
    //private JTextArea tadd; 
    //private JCheckBox term; 
    
    private JButton sub; 
    private JButton reset; 
    private JTextArea tout;
    
    private Dictionary<String, String> taskMap = new Hashtable();
    private Timesheet timesheet = new Timesheet();
  
    // constructor, to initialize the components 
    // with default values. 
    public FormTask(String[] menu) 
    { 
        setTitle("mvp task"); 
        setBounds(300, 90, 900, 500); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setResizable(false); 
  
        c = getContentPane(); 
        c.setLayout(null); 
  
        /*
         * Title 
         */
        title = new JLabel("Task submition"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(300, 30); 
        title.setLocation(300, 30); 
        c.add(title); 
  
        /*
         * Employee id
         */
        idEmployee = new JLabel("My id"); 
        idEmployee.setFont(new Font("Arial", Font.PLAIN, 20)); 
        idEmployee.setSize(100, 20); 
        idEmployee.setLocation(100, 100); 
        c.add(idEmployee); 
  
        tfidEmployee = new JTextField(); 
        tfidEmployee.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tfidEmployee.setSize(220, 20); 
        tfidEmployee.setLocation(200, 100); 
        c.add(tfidEmployee); 
  
  
        /*
         * Task
         */
        task = new JLabel("Task"); 
        task.setFont(new Font("Arial", Font.PLAIN, 20)); 
        task.setSize(100, 20); 
        task.setLocation(100, 150); 
        c.add(task); 
        
        if(menu != null && menu.length != 0) {
        	setTaskMap(menu.clone());
        }
        cbTask = new JComboBox(tasks); 
        cbTask.setFont(new Font("Arial", Font.PLAIN, 15)); 
        cbTask.setSize(220, 20); 
        cbTask.setLocation(200, 150); 
        cbTask.addActionListener(this);
        c.add(cbTask); 

        /*
         * Date
         */
        date = new JLabel("Date"); 
        date.setFont(new Font("Arial", Font.PLAIN, 20)); 
        date.setSize(100, 20); 
        date.setLocation(100, 200); 
        c.add(date); 
        
        day = new JComboBox(days); 
        day.setFont(new Font("Arial", Font.PLAIN, 15)); 
        day.setSize(50, 20); 
        day.setLocation(200, 200); 
        c.add(day); 
  
        month = new JComboBox(months); 
        month.setFont(new Font("Arial", Font.PLAIN, 15)); 
        month.setSize(60, 20); 
        month.setLocation(270, 200); 
        c.add(month); 
  
        year = new JComboBox(years); 
        year.setFont(new Font("Arial", Font.PLAIN, 15)); 
        year.setSize(60, 20); 
        year.setLocation(350, 200); 
        c.add(year); 
        
        /*
         * Start hour
         */

        start = new JLabel("Start"); 
        start.setFont(new Font("Arial", Font.PLAIN, 20)); 
        start.setSize(100, 20); 
        start.setLocation(100, 250); 
        c.add(start); 
        
        hour = new JComboBox(hours); 
        hour.setFont(new Font("Arial", Font.PLAIN, 15)); 
        hour.setSize(60, 20); 
        hour.setLocation(200, 250); 
        c.add(hour); 
        
        JLabel temp = new JLabel("h"); 
        temp.setFont(new Font("Arial", Font.PLAIN, 20)); 
        temp.setSize(50, 20); 
        temp.setLocation(265, 250); 
        c.add(temp); 
  
        minute = new JComboBox(minutes); 
        minute.setFont(new Font("Arial", Font.PLAIN, 15)); 
        minute.setSize(60, 20); 
        minute.setLocation(300, 250); 
        c.add(minute); 

        
        
        /*
         * End hour
         */

        end = new JLabel("End"); 
        end.setFont(new Font("Arial", Font.PLAIN, 20)); 
        end.setSize(100, 20); 
        end.setLocation(100, 300); 
        c.add(end); 
        
        endHour = new JComboBox(hours); 
        endHour.setFont(new Font("Arial", Font.PLAIN, 15)); 
        endHour.setSize(60, 20); 
        endHour.setLocation(200, 300); 
        c.add(endHour); 

        temp = new JLabel("h"); 
        temp.setFont(new Font("Arial", Font.PLAIN, 20)); 
        temp.setLocation(265, 300); 
        temp.setSize(50, 20); 
        c.add(temp); 
  
        endMinute = new JComboBox(minutes); 
        endMinute.setFont(new Font("Arial", Font.PLAIN, 15)); 
        endMinute.setSize(60, 20); 
        endMinute.setLocation(300, 300); 
        c.add(endMinute); 
        
        
        /*
         * sublit button
         */
        
        sub = new JButton("Submit"); 
        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sub.setSize(100, 20); 
        sub.setLocation(150, 350); 
        sub.addActionListener(this); 
        c.add(sub); 
  
        /*
         * reset button
         */
        
        reset = new JButton("Reset"); 
        reset.setFont(new Font("Arial", Font.PLAIN, 15)); 
        reset.setSize(100, 20); 
        reset.setLocation(270, 350); 
        reset.addActionListener(this); 
        c.add(reset); 
  
        /*
         * review area
         */
        
        tout = new JTextArea(); 
        tout.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tout.setSize(300, 300); 
        tout.setLocation(500, 100); 
        tout.setLineWrap(true); 
        tout.setEditable(false); 
        c.add(tout); 
  
        setVisible(true); 
    } 
  
    // method actionPerformed() 
    // to get the action performed 
    // by the user and act accordingly 
    public void actionPerformed(ActionEvent e) 
    { 
        if (e.getSource() == sub) { 
        	this.submitBtnPress();
        } 
  
        else if (e.getSource() == reset) { 
            this.resetBtnPress();
        } 
        else if (e.getSource() == cbTask) {
        	this.taskCBSelection();
        }
    } 
    
    private void setTaskMap(String[] menuItems) {
    	
    	this.tasks = new String[menuItems.length];
    	int i = 0;
    	for(String taskPath : menuItems) {
    		String[] splittedPath = taskPath.split("/");
    		tasks[i] = (i+1) + ") " + splittedPath[splittedPath.length-1];
    		
    		this.taskMap.put(tasks[i], taskPath);
    		i++;
    	}
    }
    
    private void submitBtnPress() {

    	int idEmployee;
    	try {
    		idEmployee = Integer.parseInt(this.tfidEmployee.getText());
    		
    	}
    	catch (Exception e){
    		tout.setText("Erreur id employé\nVérifier que"
    				+ "\n  l'id soit correcte"
    				+ "\n  l'id soit un entier"
    				+ "\n  l'id soit écrit en chiffres.");
    		return;
    	}
    	
    	
    	String task = taskMap.get( this.cbTask.getSelectedItem());
    	int idTask = Integer.parseInt(task.split("/")[0]);
    	
    	try {
			this.formatDate();
		} catch (ParseException e) {
			tout.setText("Erreur date\nVérifier que la date\nsoit correctement entrée.");
			return;
		}
    	
    	this.timesheet = new Timesheet(idEmployee, idTask, this.startDate, this.endDate);
    	
        tout.setText(
            "\n  Employee id : "
              + timesheet.getEmployee().getId() 
              + "\n\n  " + "Task id : " + timesheet.getTask().getId()
              + "\n\n  " + "Task name : " + task.split("/")[task.split("/").length -1]
    		  + "\n\n  " + "Start date : " + timesheet.getStartDate()
    		  + "\n\n  " + "End date : " + timesheet.getEndDate());
        tout.setEditable(false); 

        
        this.sendTimesheet();
    }
    private void resetBtnPress() {
    	String def = ""; 
        tfidEmployee.setText(def); 
        tout.setText(def); 
        day.setSelectedIndex(0); 
        month.setSelectedIndex(0); 
        year.setSelectedIndex(0); 
    }
    
    private void taskCBSelection() {
    	String spacing = "  ";
    	String feedBackText = "\n" + spacing + "Selected task : \n";
    	
    	boolean isFirst= true;
    	for(String partialPath : taskMap.get(cbTask.getSelectedItem()).split("/")) {
    		System.out.println(partialPath);
    		if(isFirst) {
    			isFirst = false;
    		}
    		else {
    		spacing += "  ";
    		feedBackText += spacing + "⇒ " +  partialPath + "\n";
    		}
    	}
	
    	tout.setText(feedBackText);
    }
    
    private void formatDate() throws ParseException {
    	/*
    	*rough timeStamp of start and end hours + minutes.
    	*if end timeStamp is lower or equal to start timeStamp,
    	*it means the employee worked night shift
    	*endDate is then set to the next day
    	*/
    	int startTimeStamp = Integer.parseInt(this.hour.getSelectedItem().toString());
    	startTimeStamp = startTimeStamp * 60;
    	startTimeStamp += Integer.parseInt(this.minute.getSelectedItem().toString());
    	
    	int endTimeStamp = Integer.parseInt(this.endHour.getSelectedItem().toString());
    	endTimeStamp = endTimeStamp * 60;
    	endTimeStamp += Integer.parseInt(this.endMinute.getSelectedItem().toString());
    	
    	String unformattedStartDate = this.year.getSelectedItem().toString();
    	unformattedStartDate += "-" + this.month.getSelectedItem().toString();
    	unformattedStartDate +=  "-" + this.day.getSelectedItem().toString();
    	unformattedStartDate += " ";

    	String unformattedEndDate ="";
    	if(endTimeStamp <= startTimeStamp) {
        	unformattedEndDate = this.year.getSelectedItem().toString();
        	unformattedEndDate += "-" + this.month.getSelectedItem().toString();
        	unformattedEndDate +=  "-" + (Integer.parseInt(this.day.getSelectedItem().toString()) +1);
        	unformattedEndDate += " ";
    	}
    	else
    	{
    		unformattedEndDate = unformattedStartDate;
    	}
    	
    	unformattedStartDate += Integer.parseInt(this.hour.getSelectedItem().toString());
    	unformattedStartDate += ":" + Integer.parseInt(this.minute.getSelectedItem().toString());
    	unformattedStartDate += ":00";

    	unformattedEndDate += Integer.parseInt(this.endHour.getSelectedItem().toString());
    	unformattedEndDate += ":" + Integer.parseInt(this.endMinute.getSelectedItem().toString());
    	unformattedEndDate += ":00";
    	

		startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(unformattedStartDate);
		endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(unformattedEndDate);
    	
    }
    
    private void sendTimesheet() {
    	new Publisher().publishTimesheet(this.timesheet);
    }
    
}
