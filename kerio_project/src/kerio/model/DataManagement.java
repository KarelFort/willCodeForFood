package kerio.model;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;

import javax.servlet.ServletContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataManagement extends DbConnection{
	private static final String tableName = "client_sql_statements";
	private static Statement stmnt;
	private static String where = "where";
	private static String from = "from";
	
	
	public DataManagement(ServletContext servletContext) throws ClassNotFoundException {
		super(servletContext);
		stmnt = super.getStmnt();
		// TODO Auto-generated constructor stub
	}

	
	
	public JSONObject dataService(int id, String date1, String date2){
		
		String query = getQuery(id);
		String resultQuery = setQuery(query,date1,date2);
		System.out.println(resultQuery);

		ResultSet result = null;
		try {
			result = stmnt.executeQuery(resultQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject resultJson = null;
		try {
			resultJson = resultToJson(result);
			System.out.println(resultJson);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
/*		PrintWriter writer;
		try {
			File f = new File("C:/Users/karel/Desktop/test.json");
			writer = new PrintWriter(f,
					"UTF-8");
			if(result != null){
				writer.println(resultJson);
			}else System.err.println("ResultSet null!");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return resultJson;
	}
	
	private static String getQuery(int id){
		String selection = "STATEMENT";
		String query = null;
		String getStatement = "SELECT "+selection+" FROM " + tableName
				+ " WHERE ID=" + id;
		ResultSet getQuery;
		try {
			getQuery = stmnt.executeQuery(getStatement);
			if(getQuery.next()){
				query = getQuery.getString(selection);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return query;
	}
	


	private static JSONObject resultToJson(ResultSet result)
			throws SQLException, JSONException {

		JSONArray jArray = new JSONArray();
		JSONObject jObjDevice = new JSONObject();
		JSONObject jTypes = new JSONObject();
		int rows = 0;
		boolean haveTypes = false;
		String[] types = null;
		// PrintStream out = System.out;
		try {
			while (result.next()) {
				ResultSetMetaData rsmd = result.getMetaData();
				JSONObject jObj = new JSONObject();
				int cols = rsmd.getColumnCount();
				if(!haveTypes){
					types = new String[cols];
				}
				for (int i = 1; i <= cols; i++) {
					int type = rsmd.getColumnType(i);
					if (type == Types.BIGINT || type == Types.INTEGER) {
						// out.print("integer: "+result.getInt(i));
						if(!haveTypes) jTypes.put(rsmd.getColumnLabel(i), "number");
						jObj.put(rsmd.getColumnLabel(i), result.getInt(i));
					} else if (type == Types.DECIMAL) {
						// out.print("decimal: "+result.getBigDecimal(i));
						if(!haveTypes) jTypes.put(rsmd.getColumnLabel(i), "number");;
						jObj.put(rsmd.getColumnLabel(i),
								result.getBigDecimal(i));
					} else {
						// out.print("Neco jinyho: "+result.getString(i));
						if(!haveTypes) jTypes.put(rsmd.getColumnLabel(i), "string");;
						jObj.put(rsmd.getColumnLabel(i), result.getString(i));

					}
				}
				haveTypes = true;
				jArray.put(jObj);
				// out.println();
				rows++;
			}
			System.out.println("Number of rows: "+ rows);
			
			jObjDevice.put("data", jArray);
			jObjDevice.put("dataType", jTypes);
			
		} catch (org.json.JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return jObjDevice;
	}

	private static String setQuery(String query, String date1, String date2){
		int position;
		String include = "TIMESTAMP between '" + date1 + "' and '" + date2 + "'";
		// Move to correct position
		if(query.contains(where)){
			position = query.lastIndexOf(where) + where.length() + 1;
			include = String.format(include+"%s", " and "); 
		}else{
			position = query.lastIndexOf(from) + from.length() + 1;
			while(true){
				if(query.charAt(position) != ' '){
					position++;
				}else {
					position++;
					break;
				}
			}
			include = String.format("%s"+include, where+" ");
		}
		// Modify query by defined timestamp range
		String resultQuery = new StringBuilder(query).insert(
				position,include).toString();
				
		return resultQuery;
	}
	

}
