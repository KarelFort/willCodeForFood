package kerio.client.statistic.model;

import java.io.PrintWriter;
import java.sql.*;

import org.json.JSONArray;
import org.json.JSONObject;

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
					+ "\n(Count(APPLICATION)* 100 / (Select Count(*) From Clients_all where CLIENT_NAME like \"ActiveSync%\")) as Percentage\n"
					+ "from Clients_all \nwhere CLIENT_NAME like \"ActiveSync%\"\ngroup by APPLICATION\norder by Count desc");

			JSONArray jArray = new JSONArray();


			while(result.next()){
				//				System.out.println(result.getString("Device") + ", " + result.getString("Count")+ ", " + result.getString("Percentage"));

				String  type_json=result.getString("Device");
				String count_json=result.getString("Count");
				String perc_json=result.getString("Percentage");

				JSONObject jObj = new JSONObject();

				jObj.put("device", type_json);
				jObj.put("count", count_json);
				jObj.put("percentage", perc_json);

				jArray.put(jObj);
			}

			// with this format communicates datatables.js
			JSONObject jObjDevice = new JSONObject();
			jObjDevice.put("data", jArray);
			System.out.println(jObjDevice);

			PrintWriter writer = new PrintWriter("WebContent/data/result.json", "UTF-8");
			writer.println(jObjDevice);
			writer.close();
			
		}catch(Exception exc){
			exc.printStackTrace();
		}

	}

}
