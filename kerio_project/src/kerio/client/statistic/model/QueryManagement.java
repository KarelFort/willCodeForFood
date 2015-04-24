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

import kerio.data.Query;

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

	public Query getQuery(int id) {
		Query query = new Query();
		
		String getStatement = "SELECT * FROM " + dbName
				+ " WHERE ID=" + id;
		ResultSet result;
		try {
			result = stmnt.executeQuery(getStatement);
			ResultSetMetaData rsmd = result.getMetaData();
			int col = rsmd.getColumnCount();
			result.next();
			
			query.setId(result.getInt("id"));
			query.setName(result.getString("name"));
			query.setStatement(result.getString("statement"));
			query.setInfo(result.getString("info"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return query;
	}
	
	public void deleteQuery(int id) {		
		String deleteStatement = "DELETE FROM " + dbName + " WHERE ID=" + id;
		
		try {
			stmnt.executeUpdate(deleteStatement);					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Query> getAllQueries() {
		List<Query> allQueries = new ArrayList<Query>();
		
		String getStatement = "SELECT * FROM " + dbName;
		ResultSet result;
		
		try {
			result = stmnt.executeQuery(getStatement);
			while (result.next()) {
				Query query = new Query();

				query.setId(result.getInt("id"));
				query.setName(result.getString("name"));
				query.setStatement(result.getString("statement"));
				query.setInfo(result.getString("info"));

				allQueries.add(query);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allQueries;
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
