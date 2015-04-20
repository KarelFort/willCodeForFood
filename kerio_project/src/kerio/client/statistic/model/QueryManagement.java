package kerio.client.statistic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryManagement {

	// set connection details
	private static String dbURL = "jdbc:mysql://localhost:3306/kerio";
	private static String login = "root";
	private static String password = "password";
	private static Statement stmnt;
	private static String tableCols = "statement, name, info";
	private static String dbName = "kerio_all.clients_sql_statements";

	public static void init() {
		try {
			Connection newConnection = DriverManager.getConnection(dbURL,
					login, password);
			stmnt = newConnection.createStatement();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void addQuery(String statement, String name, String info ) {
		String addStatement = "INSERT INTO " + dbName
				+ " (" +tableCols+ ") VALUES ( '" + statement + "', '" +name + "', '" + info + "')";
		try {
			stmnt.executeUpdate(addStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String[] getQuery(int id) {
		String getStatement = "SELECT " +tableCols+ " FROM " + dbName
				+ " WHERE ID=" + id;
		ResultSet result;
		String[] resultArray = null;
		try {
			result = stmnt.executeQuery(getStatement);
			ResultSetMetaData rsmd = result.getMetaData();
			int col = rsmd.getColumnCount();
			resultArray = new String[col];
			while (result.next()) {
				for (int i = 0; i < col; i++) {
					resultArray[i] = result.getString(rsmd
							.getColumnLabel(i + 1));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultArray;
	}

	
	public static List<String> getAllQueries() {
		String getStatement = "SELECT STATEMENT FROM " + dbName;
		ResultSet result;
		List<String> resultList = new ArrayList<String>();
		try {
			result = stmnt.executeQuery(getStatement);
			while (result.next()) {
				resultList.add(result.getString("STATEMENT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}


	public static void updateQuery(int id, String statement, String name, String info) {
			
		String updateStatement = "UPDATE " +dbName+ " set STATEMENT = '" +statement+ "', NAME = '" +name+"', INFO = '" +info+ "' WHERE id= "+id;
		try {
			stmnt.executeUpdate(updateStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
