/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author steffen
 *
 */
public class Datenbank {
	protected static Connection getConnection() throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/masche?charSet=UNICODE";
		String username = "USERNAME";
		String password = "PASSWORD";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	private static boolean int2Boolean(int i) throws Exception {
		{
			if (i == 1) {
				return true;
			}
			if (i == 0) {
				return false;
			}
			if (i != 0 && i != 1) {
				throw new Exception("No Boolean!");
			}
			return false;
		}

	}

	public static boolean checkPW(String user, String pw) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT count(*) as I FROM masche.USER WHERE User=(?) and pw=(PASSWORD(?));";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("I");
			}
			pstmt.close();
			conn.close();
			if (int2Boolean(result)) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void setNewSession(String username, String session, String ip, String useragent) throws Exception {
		try {
			Connection conn = null;
			java.sql.PreparedStatement pstmt = null;
			conn = Datenbank.getConnection();
			String query = "INSERT INTO `masche`.`SESSIONS` (`User`, `Session`, `timeCreated`) VALUES (?, ?, NOW());";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, session);
			pstmt.executeUpdate();
			query = "UPDATE masche.USER SET lastLogin=NOW(), lastLoginIP=?, lastLoginUserAgent=? WHERE User=(?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ip);
			pstmt.setString(2, useragent);
			pstmt.setString(3, username);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getSessionType(String session) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = Datenbank.getConnection();
			String query = "Select USER.type as I from USER, SESSIONS where USER.user = SESSIONS.User and SESSIONS.Session = (?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, session);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("I");
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void deleteAllRunningSessionOfUser(String user) throws SQLException {
		try {
			java.sql.Connection conn = null;
			java.sql.PreparedStatement pstmt = null;
			conn = Datenbank.getConnection();

			String query = "DELETE FROM masche.SESSIONS WHERE User=(?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addNewUser(String username, String pw) throws SQLException {
		try {
			Connection conn = null;
			java.sql.PreparedStatement pstmt = null;
			conn = Datenbank.getConnection();
			String query = "INSERT INTO `masche`.`USER` (`User` ,`pw` ,`type`, `Name`) VALUES (?, PASSWORD(?), ?, ?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, pw);
			pstmt.setInt(3, 2);
			pstmt.setString(4, username);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String[] getAllUsers() {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> user = new ArrayList<String>();
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT User as I FROM `USER` ORDER BY type ASC, I;";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user.add(rs.getString("I"));
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user.toArray(new String[user.size()]);
	}

	public static void deleteUser(String username) throws SQLException {
		try {
			java.sql.Connection conn = null;
			java.sql.PreparedStatement pstmt = null;
			conn = Datenbank.getConnection();
			String query = "DELETE FROM masche.USER WHERE User=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.executeUpdate();
			query = "DELETE FROM masche.SESSIONS WHERE User=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getUserStatus(String user) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT type as I FROM `USER` WHERE User = (?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("I");
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == 1) {
			return "Admin";
		}
		if (result == 2) {
			return "Dozent";
		}
		return "Error";
	}

	public static String getUserLastLoginTime(String user) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = Datenbank.getConnection();
			String query = "Select USER.lastLogin as I from USER where USER.User = (?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("I");
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.substring(0, 16);
	}

	public static String getUserLastLoginIP(String user) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = Datenbank.getConnection();
			String query = "Select USER.lastLoginIP as I from USER where USER.User = (?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("I");
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getUserLastLoginUserAgent(String user) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = Datenbank.getConnection();
			String query = "Select USER.lastLoginUserAgent as I from USER where USER.User = (?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("I");
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getUserName(String user) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = Datenbank.getConnection();
			String query = "Select USER.Name as I from USER where USER.User = (?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("I");
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getUserEMail(String user) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = Datenbank.getConnection();
			String query = "Select USER.EMail as I from USER where USER.User = (?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("I");
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getSystemURL() {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT SystemURL as I FROM hostInfos  where ID = 0;";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("I");
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getUserTitel(String user) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = Datenbank.getConnection();
			String query = "Select USER.titel as I from USER where USER.User = (?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("I");
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getUserOfSession(String session) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = Datenbank.getConnection();
			String query = "Select SESSIONS.User as I from SESSIONS where SESSIONS.Session = (?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, session);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("I");
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public static void setNewPassword(String username, String pw) throws SQLException {
		try {
			java.sql.Connection conn = null;
			java.sql.PreparedStatement pstmt = null;
			conn = Datenbank.getConnection();
			String query = "UPDATE masche.USER SET pw=PASSWORD(?) WHERE User=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pw);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setAllUserData(String newPassword, String titel, String name, String mail, String user)
			throws SQLException {
		try {
			if (titel.length() == 0){
				titel = "";
			}
			java.sql.Connection conn = null;
			java.sql.PreparedStatement pstmt = null;
			conn = Datenbank.getConnection();
			String query = "UPDATE masche.USER SET pw=PASSWORD(?), titel=?, Name=?, EMail=? WHERE User=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, titel);
			pstmt.setString(3, name);
			pstmt.setString(4, mail);
			pstmt.setString(5, user);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
