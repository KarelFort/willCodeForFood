package kerio.model;

import java.sql.*;

import javax.servlet.ServletContext;

import kerio.data.DbSettings;



public class DbConnection {

	private final DbSettings dbSettings;
	static boolean connected = false;
	Statement statement = null;
	public DbConnection(ServletContext servletContext) throws ClassNotFoundException{
		super();
		DbSettings DbSett = new DbSettings();
		DbSett.setConnector(servletContext.getInitParameter("dbConnector"));
		DbSett.setServer(servletContext.getInitParameter("dbServer"));
		DbSett.setPort(Integer.parseInt(servletContext.getInitParameter("dbPort")));
		DbSett.setDatabase(servletContext.getInitParameter("dbName"));
		DbSett.setUser(servletContext.getInitParameter("dbLogin"));
		DbSett.setPassword(servletContext.getInitParameter("dbPassword"));
		this.dbSettings = DbSett;

		Class.forName("com.mysql.jdbc.Driver");

	}
	

	
	protected Statement getStmnt(){
		try {
			Connection connection = DriverManager.getConnection(this.dbSettings.getConnectionUrl(), this.dbSettings.getUser(),
					this.dbSettings.getPassword());
			statement = connection.createStatement();
			return statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
