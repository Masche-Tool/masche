package presenter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.avDatenbank;
import model.dvDatenbank;
import model.evDatenbank;

/**
 * Servlet implementation class Analyse
 */
@WebServlet("/AnalyseInit")
public class AnalyseInit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		final int ANZAHLZTEILNEHMERDAMITUMFRAGEAUSWERTBAR = 5;
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String durchfuehrungsID = evDatenbank.getDurchfuehrungID(id);
		String firstQuestion = evDatenbank.getFirstQuestionIdOfFragebogen(id);
		dvDatenbank.setFreigeschaltetToFalse(id);
		Cookie cookie = new Cookie("masche_show_comparison", "0");
		cookie.setMaxAge(2 * 60 * 60);
		response.addCookie(cookie);
		cookie = new Cookie("masche_show_comparison_option", "0");
		cookie.setMaxAge(2 * 60 * 60);
		response.addCookie(cookie);
		int anzahlGleichartigeFrageboegenMitTeilnehmern = 0;
		if (avDatenbank.getNumberOfEqualFragebogen(id) > 1) {
			String[] ids = avDatenbank.getAllIdsOfEqualFragebogen(id);
			for (int i = 0; i < ids.length; i++) {
				if (avDatenbank.getNumberOfParticipantForFirstQuestion(ids[i]) > ANZAHLZTEILNEHMERDAMITUMFRAGEAUSWERTBAR) {
					anzahlGleichartigeFrageboegenMitTeilnehmern++;
					if (anzahlGleichartigeFrageboegenMitTeilnehmern > 1) {
						cookie = new Cookie("masche_show_comparison_option", "1");
						cookie.setMaxAge(2 * 60 * 60);
						response.addCookie(cookie);
						break;
					}
				}
			}
		}
		String preparedForward = "window.location.replace(\"admin/analyse.jsp?frageid=" + firstQuestion + "&durchfID="
				+ durchfuehrungsID + "\")";
		out.println(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
						+ "<html> \n" + "<head> \n"
						+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> \n"
						+ "<title> Weiterleitung... </title> \n" + "<script>" + preparedForward + "</script>"
						+ "</head> \n" + "<body> \n" + "Weiterleiten..." + "</body> \n" + "</html>");

	}
}
