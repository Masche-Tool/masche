package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class avDatenbank extends Datenbank {

	public static int getNumberOfQuestionParticipantsWithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Count(Antwort) AS I FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getNumberOfQuestionParticipants(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Count(Antwort) AS I FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType1Percent1WithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? AND Antwort='1')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			pstmt.setString(3, questionID);
			pstmt.setString(4, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	public static int getType1Percent2WithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? AND Antwort='2')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			pstmt.setString(3, questionID);
			pstmt.setString(4, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType3Percent1WithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? AND Antwort='1')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			pstmt.setString(3, questionID);
			pstmt.setString(4, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}	
	public static int getType3Percent2WithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? AND Antwort='2')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			pstmt.setString(3, questionID);
			pstmt.setString(4, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}	
	public static int getType3Percent3WithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? AND Antwort='3')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			pstmt.setString(3, questionID);
			pstmt.setString(4, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}	
	
	public static int getType3Percent4WithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? AND Antwort='4')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			pstmt.setString(3, questionID);
			pstmt.setString(4, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType5Percent1WithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? AND Antwort='1')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			pstmt.setString(3, questionID);
			pstmt.setString(4, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType5Percent2WithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? AND Antwort='2')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			pstmt.setString(3, questionID);
			pstmt.setString(4, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType5Percent3WithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? AND Antwort='3')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			pstmt.setString(3, questionID);
			pstmt.setString(4, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType5Percent4WithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? AND Antwort='4')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			pstmt.setString(3, questionID);
			pstmt.setString(4, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType5Percent5WithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? AND Antwort='5')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			pstmt.setString(3, questionID);
			pstmt.setString(4, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType5Percent6WithThisDurchfuehrung(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? AND Antwort='6')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			pstmt.setString(3, questionID);
			pstmt.setString(4, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}

	public static int getType1Percent1(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND Antwort='1')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	public static int getType1Percent2(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND Antwort='2')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType3Percent1(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND Antwort='1')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}	
	public static int getType3Percent2(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND Antwort='2')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}	
	public static int getType3Percent3(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND Antwort='3')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}	
	
	public static int getType3Percent4(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND Antwort='4')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType5Percent1(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND Antwort='1')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType5Percent2(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND Antwort='2')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType5Percent3(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND Antwort='3')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType5Percent4(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND Antwort='4')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType5Percent5(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND Antwort='5')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getType5Percent6(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int numberOfParticipants = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT (SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND Antwort='6')/(SELECT COUNT(Antwort) FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=?)*100+0.5 AS I;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, questionID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				numberOfParticipants = rs.getInt("I");;
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numberOfParticipants;
	}
	
	public static int getNumberOfParticipantForFirstQuestion(String fragebogenId) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT COUNT(Antwort) AS I FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, evDatenbank.getFirstQuestionIdOfFragebogen(fragebogenId));
			pstmt.setString(2, evDatenbank.getDurchfuehrungID(fragebogenId));
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
	
	
	public static int getNumberOfEqualFragebogen(String fragebogenId) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT count(*) AS I FROM masche.evFragebogen WHERE Fragenreferenz=(SELECT Fragenreferenz FROM masche.evFragebogen WHERE ID=?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fragebogenId);
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
	
	public static String[] getAllIdsOfEqualFragebogen(String fragebogenId) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> user = new ArrayList<String>();
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT ID AS I FROM masche.evFragebogen WHERE Fragenreferenz=(SELECT Fragenreferenz FROM masche.evFragebogen WHERE ID=?);";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fragebogenId);
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
	
	
	public static String[] getAllFreitextantworten(String questionID, String durchfuehrung) {
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> user = new ArrayList<String>();
		try {
			conn = Datenbank.getConnection();
			String query = "SELECT Antwort AS I FROM evAntwort JOIN participantSESSIONS ON evAntwort.participantSession=participantSESSIONS.participantSESSIONS WHERE evAntwort.FrageID=? AND participantSESSIONS.DurchfuehrungID=? ORDER BY I ASC;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, questionID);
			pstmt.setString(2, durchfuehrung);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user.add(Helper.xssCleaner(rs.getString("I")));
			}
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user.toArray(new String[user.size()]);
	}
	
	
	
}
