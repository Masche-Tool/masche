package presenter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Helper;
import model.dvDatenbank;

/**
 * Servlet implementation class Runeval
 */
@WebServlet("/Runeval")
public class Runeval extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String durchfuerungID = request.getParameter("id");
		Cookie[] cookies = request.getCookies();
		String sessionId = Helper.getRandom();
		// Wenn der Nutzer die Umfrage bereits gemacht hat, dann bekommt er
		// keine neuen Cookie mit einer Session..
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("masche_durchfuehrung_sessionID".equals(cookie.getName())) {
					sessionId = cookie.getValue();
				}
			}
		}
		Cookie cookie = new Cookie("masche_durchfuehrung_sessionID", sessionId);
		cookie.setMaxAge(1800);//Gueltigkeit des Cookie einstellen. 30 Minuten
		response.addCookie(cookie);

		String preparedForward = "window.location.replace(\"frage.jsp\")";
		try {
			// Ist die Session bereits beim Client, so wird auch keine neue in
			// der Datenbank angelegt.
			if (dvDatenbank.alreadyParticipated(sessionId) == false) {
				// Ist die Umfrage nicht mehr freigeschaltet, so kann keine neue
				// Session angelegt werden
				if (dvDatenbank.freigeschaltet(durchfuerungID)) {
					dvDatenbank.setNewSession(sessionId, durchfuerungID);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
						+ "<html> \n" + "<head> \n"
						+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> \n"
						+ "<title> Weiterleitung... </title> \n" + "<script>" + preparedForward + "</script>"
						+ "</head> \n" + "<body> \n" + "Weiterleiten..." + "</body> \n" + "</html>");

	}

}
