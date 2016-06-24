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

/**
 * Servlet implementation class DoPasswordReset
 */
@WebServlet("/DoPasswordReset")
public class DoPasswordReset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoPasswordReset() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String newPassword = request.getParameter("password");
			String user = request.getParameter("user");
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
			if (sessionType == 1) {
				if (user != null && user.length() > 0 && newPassword != null && newPassword.length() > 0) {
					String preparedForward = "window.location.replace(\"admin/showUsers.jsp\")";
					Datenbank.setNewPassword(user, newPassword);
					out.println(
							"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
									+ "<html> \n" + "<head> \n"
									+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> \n"
									+ "<title> Weiterleitung... </title> \n" + "<script>" 
									+ preparedForward
									+ "</script>" + "</head> \n" + "<body> \n" + "Weiterleiten..." + "</body> \n"
									+ "</html>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
