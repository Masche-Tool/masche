package model;

import java.sql.Connection;
import java.sql.ResultSet;

public class dvDatenbank extends Datenbank {

	public static void setNewSession(String sessionId, String durchfuerungID) throws Exception {
		try {
			Connection conn = null;
			java.sql.PreparedStatement pstmt = null;
			conn = Datenbank.getConnection();
			String query = "INSERT INTO masche.participantSESSIONS (participantSESSIONS, DurchfuehrungID) VALUES (?, ?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sessionId);
			pstmt.setString(2, durchfuerungID);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getQuestionForParicipant(String participantSession) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "Eine Teilnahme ist nicht mehr möglich!";

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Frage AS I FROM masche.evFrage WHERE Fragebogenreferenz = (SELECT Fragenreferenz FROM masche.evFragebogen where DurchfuehrungID = (SELECT DurchfuehrungID FROM participantSESSIONS where participantSESSIONS = ?)) AND Nummer = (SELECT count(*) as I FROM masche.evAntwort WHERE participantSession=?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, participantSession);
			pstmt.setString(2, participantSession);
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

	public static String getQuestionIdForParicipant(String participantSession) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "Diese Frage existiert nicht!";

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT FrageID AS I FROM masche.evFrage WHERE Fragebogenreferenz = (SELECT Fragenreferenz FROM masche.evFragebogen where DurchfuehrungID = (SELECT DurchfuehrungID FROM participantSESSIONS where participantSESSIONS = ?)) AND Nummer = (SELECT count(*) as I FROM masche.evAntwort WHERE participantSession=?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, participantSession);
			pstmt.setString(2, participantSession);
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

	public static int getQuestionArtdForParicipant(String participantSession) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 99;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Art AS I FROM masche.evFrage WHERE Fragebogenreferenz = (SELECT Fragenreferenz FROM masche.evFragebogen where DurchfuehrungID = (SELECT DurchfuehrungID FROM participantSESSIONS where participantSESSIONS = ?)) AND Nummer = (SELECT count(*) as I FROM masche.evAntwort WHERE participantSession=?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, participantSession);
			pstmt.setString(2, participantSession);
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

	public static String getMagicKey(String fragebogenID) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "99";

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Count(MagicKey) AS I FROM evFragebogen WHERE ID=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fragebogenID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("I");
			}
			if (result.equals("0")) {
				query = "UPDATE masche.evFragebogen SET MagicKey = ? WHERE evFragebogen.ID = ?;";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, Helper.getRandom());
				pstmt.setString(2, fragebogenID);
				pstmt.executeUpdate();
			}
			query = "SELECT MagicKey AS I FROM evFragebogen WHERE ID=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fragebogenID);
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

	public static void importFromMagicKey(String magicKey, String name, String zeitraum, String beschreibung,
			String session) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			conn = Datenbank.getConnection();
			String query = "insert into masche.evFragebogen (ID, Fragenreferenz, Ersteller, Besitzer, Bearbeitungsszeit, DurchfuehrungID, Semester, Modulname, Beschreibung, AbgeleitetVon) VALUES"
					+ " (?, ?, ?, ?, NOW(), ?, ?, ?, ?, ?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, Helper.getRandom());
			pstmt.setString(2, getFragenreferenzForMagicKey(magicKey));
			pstmt.setString(3, getErstellerForMagicKey(magicKey));
			pstmt.setString(4, Datenbank.getUserOfSession(session));
			String uniqueDurchfuehrungID = Helper.getRandom().substring(0, 7);
			while (uniqueDurchfuehrungID.contains("0") || uniqueDurchfuehrungID.contains("O")
					|| uniqueDurchfuehrungID.contains("1") || uniqueDurchfuehrungID.contains("l")
					|| !evDatenbank.durchfuehrungIDisUnique(uniqueDurchfuehrungID)) {
				System.out.println("Versuch: " + uniqueDurchfuehrungID);
				uniqueDurchfuehrungID = Helper.getRandom().substring(0, 7);
			}
			pstmt.setString(5, uniqueDurchfuehrungID);
			pstmt.setString(6, zeitraum);
			pstmt.setString(7, name);
			pstmt.setString(8, beschreibung);
			pstmt.setString(9, getIDForMagicKey(magicKey));
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static String getFragenreferenzForMagicKey(String magicKey) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "0";
		try {
			conn = Datenbank.getConnection();
			String query = "Select Fragenreferenz AS I from evFragebogen Where MagicKey=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, magicKey);
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

	public static String getErstellerForMagicKey(String magicKey) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "0";
		try {
			conn = Datenbank.getConnection();
			String query = "Select Ersteller AS I from evFragebogen Where MagicKey=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, magicKey);
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

	protected static String getIDForMagicKey(String magicKey) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "0";
		try {
			conn = Datenbank.getConnection();
			String query = "Select ID AS I from evFragebogen Where MagicKey=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, magicKey);
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

	public static void setAnswerForQuestion(String session, String answer, String frageID) throws Exception {
		try {
			Connection conn = null;
			java.sql.PreparedStatement pstmt = null;
			conn = Datenbank.getConnection();
			String query = "INSERT INTO masche.evAntwort (participantSession, Antwort, FrageID, Zeit) VALUES (?, ?, ?, NOW());";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, session);
			pstmt.setString(2, Helper.xssCleaner(answer));
			pstmt.setString(3, Helper.xssCleaner(frageID));
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean nextQuestionExists(String latestQuestionID) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 99;

		try {
			conn = Datenbank.getConnection();
			String query = "select Count(*) AS I From evFrage WHERE Fragebogenreferenz=(SELECT Fragebogenreferenz From evFrage WHERE FrageID=?) AND Nummer=(SELECT Nummer From evFrage WHERE FrageID=?)+1;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, latestQuestionID);
			pstmt.setString(2, latestQuestionID);
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
			return true;
		} else {
			return false;
		}

	}

	public static boolean alreadyParticipated(String sessionId) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 99;

		try {
			conn = Datenbank.getConnection();
			String query = "select Count(*) AS I From participantSESSIONS WHERE participantSESSIONS=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sessionId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("I");
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result >= 1) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean freigeschaltet(String id) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 99;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT freigeschaltet AS I FROM evFragebogen WHERE DurchfuehrungID=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
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
			return true;
		} else {
			return false;
		}
	}
	public static boolean istAbgeleitet(String id) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 99;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Count(AbgeleitetVon) AS I FROM evFragebogen WHERE ID=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
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
			return true;
		} else {
			return false;
		}
	}
	
	
	public static void setFreigeschaltetToFalse(String evalID) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			conn = Datenbank.getConnection();
			String query = "UPDATE masche.evFragebogen SET freigeschaltet='0' WHERE evFragebogen.ID = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, evalID);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
