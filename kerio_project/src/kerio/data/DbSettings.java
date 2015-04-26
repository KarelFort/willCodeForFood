package kerio.data;

import java.text.MessageFormat;

/**
 * Data class for storing Database connection parameters
 *
 */
public class DbSettings {
	private static final String DB_ENCODING = "UTF-8";
	private String connector;
	private String server;
	private int port;
	private String login;
	private String password;
	private String database;

	public String getConnectionUrl() {
		return MessageFormat.format("{0}://{1}:{2,number,#}/{3}?characterEncoding={4}", this.connector, this.server, this.port, this.database, DB_ENCODING);
	}

	public String getConnector(){
		return this.connector;
	}
	
	public void setConnector(String connector){
		this.connector = connector;
	}
	
	public String getServer() {
		return this.server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return this.login;
	}

	public void setUser(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabase() {
		return this.database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
}

