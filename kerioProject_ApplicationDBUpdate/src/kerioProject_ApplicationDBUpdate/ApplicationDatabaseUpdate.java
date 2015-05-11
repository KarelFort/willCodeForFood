package kerioProject_ApplicationDBUpdate;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class ApplicationDatabaseUpdate {
	
	private String applicationdbURL = "";
	private String applicationdbLogin = "";
	private String applicationdbPassword = "";
	private String keriodbLogin = "";
	private String keriodbPassword = "";	
	private String keriodbURL = "";
	private Statement stmnt1, stmnt2;
	private Connection keriodbConnection = null;
	private Connection applicationdbConnection = null;
	private ResultSet result = null;
	private ArrayList<Integer> keriodbResultList = new ArrayList<Integer>();
	private ArrayList<Integer> appdbResultList = new ArrayList<Integer>();
	
	
	public static void main(String[] args) {
		ApplicationDatabaseUpdate appDBUpdate = new ApplicationDatabaseUpdate();
		appDBUpdate.loadConnectionData(args[0]);
		appDBUpdate.initConnections();
	}
	
	private void loadConnectionData(String filePath)
	{
		BufferedReader reader = null;
		String splitter = ";";
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(filePath));
			reader.readLine();
			line = reader.readLine();
			String[] data = line.split(splitter);
			
			applicationdbURL = data[0];
			applicationdbLogin = data[1];
			applicationdbPassword = data[2];
			keriodbURL = data[3];
			keriodbLogin = data[4];
			keriodbPassword = data[5];
		}
		catch (IOException e){
			System.out.println(e);
		}catch(IndexOutOfBoundsException e){
			System.out.println(e);
		}
	}

	private void initConnections() {
		String errorMsg = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			applicationdbConnection = DriverManager.getConnection(applicationdbURL,
					applicationdbLogin, applicationdbPassword);
			stmnt1 = applicationdbConnection.createStatement();
			result = stmnt1.executeQuery("SELECT DISTINCT reminder_id FROM clients");
			while(result.next()) {
				appdbResultList.add(result.getInt(1));
			}
			
			keriodbConnection = DriverManager.getConnection(keriodbURL, keriodbLogin, keriodbPassword);
			stmnt2 = keriodbConnection.createStatement();
			result = stmnt2.executeQuery("SELECT DISTINCT reminder_id FROM clients");
			while(result.next()) {
				keriodbResultList.add(result.getInt(1));
			}
			
			compareReminderIDs(appdbResultList, keriodbResultList);

		} catch (Exception e) {
			System.out.println(e);
			errorMsg = exceptionStacktraceToString(e);			
			insertLogsInfo(false, errorMsg);
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
		String errorMsg = "";
		int lastID = 0;
		
		try {
			result = stmnt1.executeQuery("SELECT id FROM clients ORDER BY id DESC LIMIT 1");
			if (result.next()) {
				lastID = result.getInt(1);
				lastID++;
			}
			
			for(int i = 0; i < reminderIDs.size(); i++) {
				result = stmnt2.executeQuery("SELECT user_id, client_name, application," 
						+ "application_binary, client_os, usage_count, protocol, reminder_id,"
						+ "report_id FROM clients WHERE reminder_id = " + reminderIDs.get(i));

				while (result.next()) {
					// debug text
					/*System.out.println("client_os, usage_count, protocol, reminder_id, report_id, timestamp) VALUES(\'" + lastID + "\',\"" + result.getString(1) 
							+ "\",\"" + result.getString(2) + "\",\"" + result.getString(3) + "\",\"" + result.getString(4) + "\",\"" + result.getString(5)
							+ "\",\"" + result.getString(6) + "\",\"" + result.getString(7) + "\",\"" + result.getString(8) + "\",\"" + result.getString(9) 
							+ "\",\'" + timestamp + "\')");
					*/
					
					stmnt1.executeUpdate("INSERT INTO clients (id, user_id, client_name, application, application_binary,"
							+ "client_os, usage_count, protocol, reminder_id, report_id, timestamp) VALUES(\'" + lastID + "\',\"" + result.getString(1) 
							+ "\",\"" + result.getString(2) + "\",\"" + result.getString(3) + "\",\"" + result.getString(4) + "\",\"" + result.getString(5)
							+ "\",\"" + result.getString(6) + "\",\"" + result.getString(7) + "\",\"" + result.getString(8) + "\",\"" + result.getString(9) 
							+ "\",\'" + timestamp + "\')");
					
					lastID++;
				}	
			} 
		} catch (SQLException e) {
			System.out.println(e);
			finished = false;
			errorMsg = exceptionStacktraceToString(e);
				
		} finally {				
			insertLogsInfo(finished, errorMsg);
		}
	}
	
	private void insertLogsInfo(boolean finished, String error) {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		
		try {
			stmnt1.executeUpdate("INSERT INTO logs (time, finished, error_msg) VALUES(\'" + timestamp + "\'," + finished + ",\"" + error + "\")");
		
		} catch (SQLException e) {
			System.out.println(e);
		} 
	}
	
	private static String exceptionStacktraceToString(Exception e)
	{
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    e.printStackTrace(ps);
	    ps.close();
	    return baos.toString();
	}
}

