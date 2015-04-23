package kerio.client.statistic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QueryManagement {

	// set connection details
	private String dbURL = "jdbc:mysql://localhost:3306/client_statistics";
	private String login = "root";
	private String password = "";
	private Statement stmnt;
	private String tableCols = "statement, name, info";
	private String dbName = "client_statistics.client_sql_statements";
	
	

	public QueryManagement(){
		init();
	}

	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			Connection newConnection = DriverManager.getConnection(dbURL,
					login, password);
			stmnt = newConnection.createStatement();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void addQuery(String statement, String name, String info) {
		String addStatement = "INSERT INTO " + dbName + " (" + tableCols
				+ ") VALUES ( '" + statement + "', '" + name + "', '" + info
				+ "')";		
		try {
			stmnt.executeUpdate(addStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getQuery(int id) {
		String getStatement = "SELECT " + tableCols + " FROM " + dbName
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

	public List<String> getAllQueries() {
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

	public void updateQuery(int id, String statement, String name,
			String info) {

		String updateStatement = "UPDATE " + dbName + " set STATEMENT = '"
				+ statement + "', NAME = '" + name + "', INFO = '" + info
				+ "' WHERE id= " + id;
		try {
			stmnt.executeUpdate(updateStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
