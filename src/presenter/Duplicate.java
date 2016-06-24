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
import model.dvDatenbank;
import model.evDatenbank;

@WebServlet("/Duplicate")
public class Duplicate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
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
				String idToDuplicate = request.getParameter("id");
				String magicKey = dvDatenbank.getMagicKey(idToDuplicate);

				String preparedForward = "window.location.replace(\"admin/admin.jsp\")";
				dvDatenbank.importFromMagicKey(magicKey, evDatenbank.getEvalModName(idToDuplicate) + "-Kopie",
						evDatenbank.getSemesterOfModul(idToDuplicate),
						evDatenbank.getBeschreibungOfModul(idToDuplicate), sessionId);
				out.println(
						"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
								+ "<html> \n" + "<head> \n"
								+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> \n"
								+ "<title> Weiterleitung... </title> \n" + "<script>" + preparedForward + "</script>"
								+ "</head> \n" + "<body> \n" + "Weiterleiten..." + "</body> \n" + "</html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
