<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Datenbank"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<title>Benutzerverwaltung</title>
<jsp:include page="misc/html-head.jsp" />
</head>
<body>
	<%
	Cookie[] cookies = request.getCookies();
	String sessionId = "0";
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
	String user = request.getParameter("user");
	if (sessionType == 1 && user != null && user.length() > 0 ) {
	%>
<nav class="navbar navbar-inverse navbar-fixed-top">
<jsp:include page="misc/navbarPart1.jsp" />
<li><a href="showUsers.jsp"> <span style="color: white"><i
			class="glyphicon glyphicon-asterisk"></i></span> Benutzerverwaltung
</a></li>
<jsp:include page="misc/navbarPart2.jsp" /> </nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<h1>
					Neues Passwort für
					<%
					out.print(user);
				%>
				</h1>
				<form class="form-signin" action="../DoPasswordReset" method="post">
					<input type="hidden" name="user" value="<%out.print(user);%>">
					<p>
						<input type="password" id="inputPassword" class="form-control"
							placeholder="Passwort" name="password" required>
					</p>
					<p class="text-right">
						<button class="btn btn-primary" type="submit">setzen</button>
					</p>
				</form>
			</div>
			<div class="col-sm-12"></div>
		</div>
		<jsp:include page="misc/footer.jsp" />
	</div>
	<%
		}
	%>
</body>
</html>
