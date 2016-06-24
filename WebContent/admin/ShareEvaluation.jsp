<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<title>Dozentenansicht: Magic Code</title>
<jsp:include page="misc/html-head.jsp" />
</head>
<body>
	<%
		Cookie[] cookies = request.getCookies();
		String sessionId = "0";
		String id = request.getParameter("id");
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("masche_sessionID".equals(cookie.getName())) {
					sessionId = cookie.getValue();
				}
			}
			Cookie cookie = new Cookie("masche_sessionID", sessionId);
			cookie.setMaxAge(7200);//Gueltigkeit des Cookie in Sekunden. 2 Stunden.
			response.addCookie(cookie);
		}
		int sessionType = Datenbank.getSessionType(sessionId);
		if (sessionType == 1 || sessionType == 2) {
			
	%>
	<nav class="navbar navbar-inverse navbar-fixed-top"> <jsp:include
		page="misc/navbarPart1.jsp" /> <%
 	if (sessionType == 1) {
 %>
	<li><a href="showUsers.jsp"> <span style="color: white"><i
				class="glyphicon glyphicon-asterisk"></i></span> Benutzerverwaltung
	</a></li>
	<%
		}
	%> <jsp:include page="misc/navbarPart2.jsp" /> </nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<h1>Magic Key für <%out.print(evDatenbank.getEvalModName(id));%></h1>
				<h3><%out.print(evDatenbank.getSemesterOfModul(id));%></h3>
				<p>Geben Sie diesen Schlüssel (z.B. per E-Mail) an eine Kollegin
					oder einen Kollegen weiter. Wenn er oder sie einen Account in
					diesem System hat, können damit die Fragen Ihrer Evaluation
					übernommen werden. Die Auswertungen können dann ebenfalls
					automatisch miteinander verglichen werden, da die Evaluationen
					geleichartig sind. Der Titel und die Beschreibung Ihrer Evaluation
					können weiterhin geändert werden.</p>
				<p>
					<input type="text" placeholder="Some kind of magic" name="magic"
						value="<%out.print(dvDatenbank.getMagicKey(id));%>" class="form-control" autofocus>
				</p>

			</div>
		</div>
		<jsp:include page="misc/footer.jsp" />
	</div>
	<%
		}
	%>
</body>
</html>
