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
				<h1>Magic Key eingeben</h1>
				<p>Um einen Fragebogen einer anderen Dozentin bzw. eines anderen Dozenten zu importieren, so fragen Sie nach dem Magic Key der Evaluation.</p>
				<form action="../DoImport" method="get">
					<p>
						<input type="text" class="form-control"
							placeholder="gültiger Magic Key" name="magickey" autofocus
							required>
					</p>
					<p>
						<input type="text" class="form-control"
							placeholder="Name meiner Evaluation" name="name" required>
					</p>
					<p>
						<input type="text" class="form-control"
							placeholder="Zeitraum meiner Evaluation" name="zeitraum" required>
					</p>
					<p>
						<input type="text" class="form-control"
							placeholder="Eine kurze Beschreibung meiner Evaluation"
							name="beschreibung" required>
					</p>
					<p class="text-right">
						<button class="btn btn-primary" type="submit">Importieren</button>
					</p>
				</form>
			</div>
		</div>
		<jsp:include page="misc/footer.jsp" />
	</div>
	<%
		}
	%>
</body>
</html>
