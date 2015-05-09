package kerio.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import kerio.data.Query;

public class QueryManagement extends DbConnection{

	private static Statement stmnt;
	private static final String tableCols = "statement, name, info";
	private static final String tableName = "client_sql_statements";
	
	public QueryManagement(ServletContext servletContext) throws ClassNotFoundException {
		super(servletContext);
		stmnt = super.getStmnt();
		// TODO Auto-generated constructor stub
	}

	public Query getQueryWithDate(int id){
		Query query = getQuery(id);
		
		String getStatement = "SELECT MIN(TIMESTAMP) AS minDate, MAX(TIMESTAMP) AS maxDate FROM Clients";
		ResultSet result;
		if(stmnt != null){
		try {
			result = stmnt.executeQuery(getStatement);
			result.next();
			Date dateMin = result.getTimestamp("minDate");
			Date dateMax = result.getTimestamp("maxDate");
			String dateStringMin = new SimpleDateFormat("yyyy-MM-dd").format(dateMin);
			String dateStringMax = new SimpleDateFormat("yyyy-MM-dd").format(dateMax);
			query.setMinDate(dateStringMin);
			query.setMaxDate(dateStringMax);
			
//			System.out.println("date1 : "+query.getMinDate());
//			System.out.println("date2 : "+query.getMaxDate());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
		return query;
	}
	
/**
 * Insert new query to database
 * @param statement
 * @param name
 * @param info
 */
	public void addQuery(String statement, String name, String info) {
		String addStatement = "INSERT INTO " + tableName + " (" + tableCols
				+ ") VALUES ( '" + statement + "', '" + name + "', '" + info
				+ "')";		
		try {
			stmnt.executeUpdate(addStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns one query (identified by ID) as an object with properties id, name, statement and info
	 * @param id
	 * @return
	 */
	public Query getQuery(int id) {
		Query query = new Query();
		
		String getStatement = "SELECT * FROM " + tableName
				+ " WHERE ID=" + id;
		ResultSet result;
		if(stmnt != null){
		try {
			result = stmnt.executeQuery(getStatement);
			result.next();
			
			query.setId(result.getInt("id"));
			query.setName(result.getString("name"));
			query.setStatement(result.getString("statement"));
			query.setInfo(result.getString("info"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return query;
	}
	
	/**
	 * delete query identified by ID
	 * @param id
	 */
	public void deleteQuery(int id) {		
		String deleteStatement = "DELETE FROM " + tableName + " WHERE ID=" + id;
		
		try {
			stmnt.executeUpdate(deleteStatement);					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns all queries from database as a list of Query objects
	 * @return
	 */
	public List<Query> getAllQueries() {
		List<Query> allQueries = new ArrayList<Query>();
		
		String getStatement = "SELECT * FROM " + tableName + " ORDER BY name ASC";
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
			e.printStackTrace();
		}
		return allQueries;
	}

	/**
	 * updates name, statement and info of query idetified by ID
	 * @param id
	 * @param statement
	 * @param name
	 * @param info
	 */
	public void updateQuery(int id, String statement, String name,
			String info) {

		String updateStatement = "UPDATE " + tableName + " set STATEMENT = '"
				+ statement + "', NAME = '" + name + "', INFO = '" + info
				+ "' WHERE id= " + id;
		try {
			stmnt.executeUpdate(updateStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
