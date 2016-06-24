<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Datenbank"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<title>Logout</title>
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
		}
		Cookie cookie = new Cookie("masche_sessionID", sessionId);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		Datenbank.deleteAllRunningSessionOfUser(Datenbank.getUserOfSession(sessionId));
	%>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">Abmeldung</a>
		</div>
	</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<h1>Abgemeldet!</h1>
				<p class="text-right">
					<a href="../index.jsp"><button type="button"
							class="btn btn-primary btn-md">Zurück auf Start</button></a>
				</p>
			</div>
		</div>
		<jsp:include page="misc/footer.jsp" />
	</div>
</body>
</html>