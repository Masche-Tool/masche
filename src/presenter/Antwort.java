package presenter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Datenbank;
import model.Helper;
import model.dvDatenbank;

/**
 * Servlet implementation class Antwort
 */
@WebServlet("/Antwort")
public class Antwort extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String antwort = request.getParameter("antwort");
			String frage = request.getParameter("frage");
			Cookie[] cookies = request.getCookies();
			String sessionId = "0";
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("masche_durchfuehrung_sessionID".equals(cookie.getName())) {
						sessionId = cookie.getValue();
					}
				}
				Cookie cookie = new Cookie("masche_durchfuehrung_sessionID", sessionId);
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
			}

			// Nur, wenn die Frage noch nicht beantwortet wurde, so wird die
			// Antwort gespeichert:
			if (frage.equals(dvDatenbank.getQuestionIdForParicipant(sessionId))) {
				if (antwort != null && antwort.length() > 0) {
					dvDatenbank.setAnswerForQuestion(sessionId, antwort, frage);
				}
			}
			if (dvDatenbank.nextQuestionExists(frage)) {
				String preparedForward = "window.location.replace(\"frage.jsp\")";
				out.println(
						"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
								+ "<html> \n" + "<head> \n"
								+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> \n"
								+ "<title> Weiterleitung... </title> \n" + "<script>" + preparedForward + "</script>"
								+ "</head> \n" + "<body> \n" + "Weiterleiten..." + "</body> \n" + "</html>");
			} else {
				String preparedForward = "window.location.replace(\"finish.jsp\")";
				out.println(
						"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
								+ "<html> \n" + "<head> \n"
								+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> \n"
								+ "<title> Weiterleitung... </title> \n" + "<script>" + preparedForward + "</script>"
								+ "</head> \n" + "<body> \n" + "Weiterleiten..." + "</body> \n" + "</html>");
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

}
