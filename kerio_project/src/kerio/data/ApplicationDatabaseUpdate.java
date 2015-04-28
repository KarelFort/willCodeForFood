package kerio.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class ApplicationDatabaseUpdate extends TimerTask {
	
	// set connection details
	private String applicationdbURL = "jdbc:mysql://localhost:3306/client_statistics";
	private String login = "root";
	private String password = "password";
	private String keriodbURL = "jdbc:mysql://localhost:3306/keriodump";
	private Statement stmnt1, stmnt2;
	private Connection keriodbConnection = null;
	private Connection applicationdbConnection = null;
	private ResultSet result = null;
	private ArrayList<Integer> keriodbResultList = new ArrayList<Integer>();
	private ArrayList<Integer> appdbResultList = new ArrayList<Integer>();
	
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR, 1);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		timer.schedule(new ApplicationDatabaseUpdate(), date.getTime(), 1000 * 60 * 60 * 24);	
	}
	
	@Override
	public void run() {
		initConnections();
	}

	public void initConnections() {	
		try {
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			applicationdbConnection = DriverManager.getConnection(applicationdbURL,
					login, password);
			stmnt1 = applicationdbConnection.createStatement();
			result = stmnt1.executeQuery("SELECT DISTINCT reminder_id FROM clients");
			while(result.next()) {
				appdbResultList.add(result.getInt(1));
			}
			
			keriodbConnection = DriverManager.getConnection(keriodbURL, login, password);
			stmnt2 = keriodbConnection.createStatement();
			result = stmnt2.executeQuery("SELECT DISTINCT reminder_id FROM clients");
			while(result.next()) {
				keriodbResultList.add(result.getInt(1));
			}

			compareReminderIDs(appdbResultList, keriodbResultList);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void compareReminderIDs(ArrayList<Integer> appdbList, ArrayList<Integer> keriodbList) {		
		ArrayList<Integer> reminderIDs = new ArrayList<Integer>();
		
		for (int i = 0; i < keriodbList.size(); i++) {
			if(!(appdbList.contains(keriodbList.get(i)))) {
				reminderIDs.add(keriodbList.get(i));
			}
		}
		
		updateDataInAppDB(reminderIDs);
	}
	
	private void updateDataInAppDB(ArrayList<Integer> reminderIDs) {		
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		boolean finished = true;
		int lastID = 0;
		try {
			result = stmnt1.executeQuery("SELECT id FROM clients ORDER BY id DESC LIMIT 1");
			if (result.next()) {
				lastID = result.getInt(1);
				lastID++;
			}
		
		} catch (SQLException e) {
			System.out.println(e);
		} 
		
		for(int i = 0; i < reminderIDs.size(); i++) {
			try {
				result = stmnt2.executeQuery("SELECT user_id, client_name, application," 
						+ "application_binary, client_os, usage_count, protocol, reminder_id,"
						+ "report_id FROM clients WHERE reminder_id = " + reminderIDs.get(i));

				while (result.next()) {
					stmnt1.executeUpdate("INSERT INTO clients (id, user_id, client_name, application, application_binary,"
							+ "client_os, usage_count, protocol, reminder_id, report_id, timestamp) VALUES(\'" + lastID + "\',\"" + result.getString(1) 
							+ "\",\"" + result.getString(2) + "\",\"" + result.getString(3) + "\",\"" + result.getString(4) + "\",\"" + result.getString(5)
							+ "\",\"" + result.getString(6) + "\",\"" + result.getString(7) + "\",\"" + result.getString(8) + "\",\"" + result.getString(9) 
							+ "\",\'" + timestamp + "\')");
					
					lastID++;
				}
				
			} catch (SQLException e) {
				System.out.println(e);
				finished = false;
				
			} finally {
				int id = 2;
				
				if (finished == true) {
					id = 1;
				}
				
				insertLogsInfo(finished, id);
			}
		}
	}
	
	private void insertLogsInfo(boolean finished, int id) {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		
		try {
			stmnt1.executeUpdate("INSERT INTO logs (time, finished, status_stat_id) VALUES(\'" + timestamp + "\'," + finished + ",\'" + id + "\')");
		
		} catch (SQLException e) {
			System.out.println(e);
		} 
		
	}
}
