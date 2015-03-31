package kerio.client.statistic.model;

import java.sql.*;

public class Driver {

	//set connection details
	private static String dbURL = "jdbc:mysql://localhost:3306/kerio";
	private static String login = "root";
	private static String password = "password";
	
	public static void main(String[] args){
		
		try{
			
			Connection newConnection = DriverManager.getConnection(dbURL, login, password);
			
			Statement stmnt = newConnection.createStatement();
			
			ResultSet result = stmnt.executeQuery("select \nAPPLICATION as Device,\ncount(APPLICATION) as Count,"
					+ "\n(Count(APPLICATION)* 100 / (Select Count(*) From Clients where CLIENT_NAME like \"ActiveSync%\")) as Percentage\n"
					+ "from Clients \nwhere CLIENT_NAME like \"ActiveSync%\"\ngroup by APPLICATION\norder by Count desc");
			
			while(result.next()){
				System.out.println(result.getString("Device") + ", " + result.getString("Count")+ ", " + result.getString("Percentage"));
				
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
}
