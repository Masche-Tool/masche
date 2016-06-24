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

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String username = request.getParameter("user");
			String pw = request.getParameter("password1");
			
			if (username != null && username.length() > 0 && pw != null
					&& pw.length() > 0) {
				if (Datenbank.checkPW(username, pw)) {
					String sessionId = Helper.getRandom();
					Cookie cookie = new Cookie("masche_sessionID", sessionId);
					cookie.setMaxAge(2 * 60 * 60);
					response.addCookie(cookie);
					Datenbank.setNewSession(username, sessionId, request.getRemoteAddr(), request.getHeader("User-Agent"));
				}
				String preparedForward = "window.location.replace(\"admin/admin.jsp\")";
				out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
