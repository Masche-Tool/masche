package presenter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetComparisonOff
 */
@WebServlet("/SetComparisonOff")
public class SetComparisonOff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetComparisonOff() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String FrageId = request.getParameter("frageid");
		String durchfuehrungsID = request.getParameter("durchfID");
		Cookie cookie = new Cookie("masche_show_comparison", "0");
		cookie.setMaxAge(2 * 60 * 60);
		response.addCookie(cookie);
		String preparedForward = "window.location.replace(\"admin/analyse.jsp?frageid=" + FrageId + "&durchfID="
				+ durchfuehrungsID + "\")";
		out.println(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
						+ "<html> \n" + "<head> \n"
						+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> \n"
						+ "<title> Weiterleitung... </title> \n" + "<script>" + preparedForward + "</script>"
						+ "</head> \n" + "<body> \n" + "Weiterleiten..." + "</body> \n" + "</html>");

	}

}
