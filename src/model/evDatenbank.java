package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class evDatenbank extends Datenbank {

	public static String[] getFrageboegenOfUser(String user) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> frageboegen = new ArrayList<String>();
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT ID as I FROM evFragebogen WHERE Besitzer = ? ORDER BY Bearbeitungsszeit DESC;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				frageboegen.add(rs.getString("I"));
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return frageboegen.toArray(new String[frageboegen.size()]);
	}

	public static boolean hasMagicKey(String id) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 99;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Count(MagicKey) AS I FROM evFragebogen WHERE ID=?;";
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

	public static String getEvalModName(String id) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Modulname as I FROM evFragebogen WHERE ID = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
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

	public static String getSemesterOfModul(String id) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Semester as I FROM evFragebogen WHERE ID = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
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

	public static String getBeschreibungOfModul(String id) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Beschreibung as I FROM evFragebogen WHERE ID = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
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

	public static String newEvaluation(String ersteller, String semester, String modulname, String beschreibung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		String id = Helper.getRandom();
		try {
			conn = Datenbank.getConnection();
			String query = "INSERT INTO `masche`.`evFragebogen` (`ID`, `Ersteller`, `Besitzer`, `Bearbeitungsszeit`, `Semester`, `Modulname`, `Beschreibung`, `Fragenreferenz`, `DurchfuehrungID`) VALUES (?, ?, ?, NOW(), ?, ?, ?, ?, ?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, ersteller);
			pstmt.setString(3, ersteller);
			pstmt.setString(4, semester);
			pstmt.setString(5, modulname);
			pstmt.setString(6, beschreibung);
			pstmt.setString(7, Helper.getRandom());
			String uniqueDurchfuehrungID = Helper.getRandom().substring(0, 7);
			// Stelle sicher, dass die Buchstaben 1, l, 0, O nicht enthalten
			// sind und stelle sicher, dass diese DurchfuehrungsID noch nicht
			// existiert:
			while (uniqueDurchfuehrungID.contains("0") || uniqueDurchfuehrungID.contains("O")
					|| uniqueDurchfuehrungID.contains("1") || uniqueDurchfuehrungID.contains("l")
					|| !durchfuehrungIDisUnique(uniqueDurchfuehrungID)) {
				System.out.println("Versuch: " + uniqueDurchfuehrungID);
				uniqueDurchfuehrungID = Helper.getRandom().substring(0, 7);
			}
			pstmt.setString(8, uniqueDurchfuehrungID);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	protected static boolean durchfuehrungIDisUnique(String id) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 99;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT COUNT(*) AS I FROM evFragebogen WHERE DurchfuehrungID=?;";
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
		if (result > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static String getBeitzerOfEvaluation(String id) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Besitzer as I FROM evFragebogen WHERE ID = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
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

	public static String getDurchfuehrungID(String id) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT DurchfuehrungID as I FROM evFragebogen WHERE ID = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
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

	public static void deleteEvaluation(String id, String sessionID) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		// Name des Benutzers, dem alle gelöschen Evaluationen gehören:
		String besitzer = "geloescht";
		// nur, wenn der Fragebogen im Besitz des Nutzers ist, den er löschen
		// will, kann er das auch:
		if (Datenbank.getUserOfSession(sessionID).equals(evDatenbank.getBeitzerOfEvaluation(id))) {
			try {
				conn = Datenbank.getConnection();
				String query = "UPDATE masche.evFragebogen SET Besitzer=? WHERE ID = ?;";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, besitzer);
				pstmt.setString(2, id);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void editEvaluationMeta(String sessionID, String evaluationID, String beschreibung, String semester,
			String modulname) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		// nur, wenn der Fragebogen im Besitz des Nutzers ist, kann er ihn
		// bearbeiten:
		if (Datenbank.getUserOfSession(sessionID).equals(evDatenbank.getBeitzerOfEvaluation(evaluationID))) {
			try {
				conn = Datenbank.getConnection();
				String query = "UPDATE masche.evFragebogen SET Modulname=?, Semester=?, Beschreibung=? WHERE ID = ?;";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, modulname);
				pstmt.setString(2, semester);
				pstmt.setString(3, beschreibung);
				pstmt.setString(4, evaluationID);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// public static void main(String args[]){
	// addQuestionToEvaluation("Das ist eine kluge Frage",
	// "2bae9rm16htp7f8k1lf54p66re", 1);
	// }

	public static void addQuestionToEvaluation(String frage, String evaluationID, int frageType) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			conn = Datenbank.getConnection();
			String query = "INSERT INTO masche.evFrage (Fragebogenreferenz, FrageID, Art, Frage, Nummer) VALUES ((SELECT Fragenreferenz FROM masche.evFragebogen where ID = ?), ?, ?, ?, ?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, evaluationID);
			pstmt.setString(2, Helper.getRandom());
			pstmt.setInt(3, frageType);
			pstmt.setString(4, Helper.xssCleaner(frage));
			pstmt.setInt(5, getNumbersOfQuestionsToEvaluation(evaluationID));
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static int getNumbersOfQuestionsToEvaluation(String fragebogenID) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT count(*) as I FROM masche.evFrage WHERE Fragebogenreferenz=(SELECT Fragenreferenz FROM masche.evFragebogen where ID = ?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fragebogenID);
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

	public static int getNumbersOfQuestions(String fragebogen) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT count(*) as I FROM masche.evFrage WHERE Fragebogenreferenz=(SELECT Fragenreferenz FROM masche.evFragebogen where ID = ?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fragebogen);
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

	protected static int getNumbersOfReferencedQuestions(String fragebogenReferenz) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = Datenbank.getConnection();
			String query = "SELECT count(*) as I FROM masche.evFrage WHERE Fragebogenreferenz= ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fragebogenReferenz);
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

	public static int getNumberOfQuestions(String questionid) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = Datenbank.getConnection();
			String query = "Select Nummer as I From masche.evFrage where FrageID = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionid);
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

	public static String[] getAllQuestionsIDsOfEvaluation(String fragebogenID) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> user = new ArrayList<String>();
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT FrageID as I FROM masche.evFrage where Fragebogenreferenz=(SELECT Fragenreferenz FROM masche.evFragebogen where ID = ?) ORDER BY Nummer ASC;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fragebogenID);
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

	public static String getQuestionOfID(String frageID) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Frage as I FROM masche.evFrage where FrageID=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, frageID);
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

	public static String getFirstQuestionIdOfFragebogen(String FragebogenId) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT FrageID AS I From evFrage WHERE Fragebogenreferenz = (SELECT Fragenreferenz FROM evFragebogen WHERE ID=?) AND Nummer = '0';";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, FragebogenId);
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

	public static String getNextQuestionId(String QuestionId) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT FrageID AS I From evFrage WHERE Fragebogenreferenz = (SELECT Fragebogenreferenz FROM evFrage WHERE FrageID=?) AND Nummer = (SELECT Nummer FROM evFrage WHERE FrageID=?)+1;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, QuestionId);
			pstmt.setString(2, QuestionId);
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

	public static String getQuestiontypeOfID(String frageID) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Art as I FROM masche.evFrage where FrageID=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, frageID);
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

	public static void deleteQuestion(String questionid, String sessionId) {

		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			conn = Datenbank.getConnection();



			String query = "UPDATE masche.evFrage SET Nummer = Nummer - 1 WHERE Fragebogenreferenz = ? and Nummer > ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, getFragebogenreferenzOfQuestions(questionid));
			pstmt.setInt(2, getNumberOfQuestions(questionid));
			pstmt.executeUpdate();
			query = "delete from masche.evFrage where FrageID = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionid);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void questionDown(String questionid, String fragebogen, String sessionId) {

		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			conn = Datenbank.getConnection();

			ResultSet rs = null;
			int result = 1000;
			String nextQuestionID = null;
			String query = "SELECT Nummer as I FROM masche.evFrage where FrageID=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("I");
			}
			// Eine Frage kann nicht weiter nach hinten verschoben werden, wenn
			// sie schon die letzte Frage ist:
			if (getNumbersOfReferencedQuestions(fragebogen) - 1 > result) {
				query = "SELECT FrageID as I FROM evFrage WHERE Fragebogenreferenz = ? AND Nummer = ?;";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, fragebogen);
				pstmt.setInt(2, getNumberOfQuestions(questionid) + 1);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					nextQuestionID = rs.getString("I");
				}
				query = "UPDATE masche.evFrage SET Nummer = Nummer - 1 WHERE FrageID = ?;";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, nextQuestionID);
				pstmt.executeUpdate();
				query = "UPDATE masche.evFrage SET Nummer = Nummer + 1 WHERE FrageID = ?;";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, questionid);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void questionUp(String questionid, String fragebogen, String sessionId) {

		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			conn = Datenbank.getConnection();

			ResultSet rs = null;
			int result = -1;
			String prevQuestionID = null;
			String query = "SELECT Nummer as I FROM masche.evFrage where FrageID=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("I");
			}
			// Eine Frage kann nicht weiter nach vorn verschoben werden, wenn
			// sie nicht die erste ist.
			if (0 < result) {
				query = "SELECT FrageID as I FROM evFrage WHERE Fragebogenreferenz = ? AND Nummer = ?;";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, fragebogen);
				pstmt.setInt(2, getNumberOfQuestions(questionid) - 1);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					prevQuestionID = rs.getString("I");
				}
				query = "UPDATE masche.evFrage SET Nummer = Nummer + 1 WHERE FrageID = ?;";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, prevQuestionID);
				pstmt.executeUpdate();
				query = "UPDATE masche.evFrage SET Nummer = Nummer - 1 WHERE FrageID = ?;";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, questionid);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected static String getFragebogenreferenzOfQuestions(String questionid) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";

		try {
			conn = Datenbank.getConnection();
			String query = "Select Fragebogenreferenz as I From masche.evFrage where FrageID = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionid);
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

	public static String getFragebogenreferenzOfFragebogen(String FragebogenID) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";

		try {
			conn = Datenbank.getConnection();
			String query = "Select Fragenreferenz as I From masche.evFragebogen where ID = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, FragebogenID);
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

	public static void updateQuestion(String frage, String questionID, String sessionID) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			conn = Datenbank.getConnection();


			String query = "UPDATE masche.evFrage SET Frage = ? WHERE FrageID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, frage);
			pstmt.setString(2, questionID);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateEvalTimestamp(String evalID) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			conn = Datenbank.getConnection();

			String query = "UPDATE masche.evFragebogen SET Bearbeitungsszeit = NOW() WHERE ID = ?";
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
