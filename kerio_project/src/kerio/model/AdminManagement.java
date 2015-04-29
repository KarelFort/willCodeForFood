package kerio.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class AdminManagement extends DbConnection {

	private Statement stmnt;

	public AdminManagement(ServletContext servletContext)
			throws ClassNotFoundException {
		super(servletContext);
		stmnt = super.getStmnt();
		// TODO Auto-generated constructor stub
	}

	/**
	 * returns info about password from DB
	 * 
	 * @return
	 */
	public String getPasswordInfo() {
		String passwordInfo = null;

		String getStatement = "SELECT value FROM client_statistics.settings WHERE name = 'passwordInfo'";
		ResultSet result;
		try {
			result = stmnt.executeQuery(getStatement);
			result.next();
			passwordInfo = result.getString("value");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passwordInfo;
	}
	
	/**
	 * checks, if the password for signin is correct
	 * @return boolean 
	 */
	public boolean passwordIsCorrect(String password) {		
		String passwordFromDB = null;
		String getStatement = "SELECT value FROM client_statistics.settings WHERE name = 'password'";
		ResultSet result;
		try {
			result = stmnt.executeQuery(getStatement);
			result.next();
			passwordFromDB = result.getString("value");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String hashedPassword = doHash(password);
		if (hashedPassword.equals(passwordFromDB)){
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * updates password (hashed) and info about password in the database
	 * 
	 * @param newPass
	 * @param newInfo
	 * @throws SQLException
	 */
	public void changePassword(String newPass, String newInfo)
			throws SQLException {
		String hashedPass = this.doHash(newPass); // hashing the password to MD5

		String updateStatement = "UPDATE client_statistics.settings SET value = '"
				+ hashedPass.toString() + "' WHERE name = 'password';";
		String updateStatement1 = "UPDATE client_statistics.settings SET value = '"
				+ newInfo + "' WHERE name = 'passwordInfo';";

		stmnt.executeUpdate(updateStatement);
		stmnt.executeUpdate(updateStatement1);
	}

	/**
	 * converts new password into SHA256 hash
	 * 
	 * @param newPass
	 * @return
	 */
	public String doHash(String newPass) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] arrayBytes = digest.digest((newPass).getBytes("UTF-8"));
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0; i < arrayBytes.length; i++) {
				stringBuffer.append(Integer.toString(
						(arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return stringBuffer.toString();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			return null;
		}
	}

	

}
