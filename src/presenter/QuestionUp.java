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
import model.evDatenbank;

/**
 * Servlet implementation class QuestionUp
 */
@WebServlet("/QuestionUp")
public class QuestionUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String questionid = request.getParameter("questionID");
			String evaluationID = request.getParameter("evaluationID");
			Cookie[] cookies = request.getCookies();
			String sessionId = "0";
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("masche_sessionID".equals(cookie.getName())) {
						sessionId = cookie.getValue();
					}
				}
			}
			int sessionType = Datenbank.getSessionType(sessionId);
			if (sessionType == 1 || sessionType == 2) {
				evDatenbank.questionUp(questionid, evDatenbank.getFragebogenreferenzOfFragebogen(evaluationID), sessionId);
				String preparedForward = "window.location.replace(\"admin/editEvaluation.jsp?id=" + evaluationID
						+ "\")";

				out.println(
						"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
								+ "<html> \n" + "<head> \n"
								+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> \n"
								+ "<title> Weiterleitung... </title> \n" + "<script>" + preparedForward + "</script>"
								+ "</head> \n" + "<body> \n" + "Weiterleiten..." + "</body> \n" + "</html>");
			} else {
				String preparedForward = "window.location.replace(\"index.jsp\")";
				out.println(
						"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
								+ "<html> \n" + "<head> \n"
								+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> \n"
								+ "<title> Weiterleitung... </title> \n" + "<script>" + preparedForward + "</script>"
								+ "</head> \n" + "<body> \n" + "Weiterleiten..." + "</body> \n" + "</html>");
			}

		} catch (

		Exception e)

		{
			e.printStackTrace();

		}
	}

}
