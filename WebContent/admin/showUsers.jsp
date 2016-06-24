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
		if (sessionType == 1) {
	%>
	<nav class="navbar navbar-inverse navbar-fixed-top"> <jsp:include
		page="misc/navbarPart1.jsp" />
	<li><a href="showUsers.jsp"> <span style="color: white"><i
				class="glyphicon glyphicon-asterisk"></i></span> Benutzerverwaltung
	</a></li>
	<jsp:include page="misc/navbarPart2.jsp" /> </nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<h1>Benutzerverwaltung</h1>
			</div>
			<div class="col-sm-10">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Benutzer</th>
							<th>Name</th>
							<th>Status</th>
							<th>Letzter Login</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<%
							String user[] = Datenbank.getAllUsers();
								for (int i = 0; i < user.length; i++) {
									out.println("<tr><td><i class=\"glyphicon glyphicon-user\"></i> " + user[i] + "</td><td>" + Datenbank.getUserTitel(user[i]) + " " + Datenbank.getUserName(user[i]) + "</td><td>"
											+ Datenbank.getUserStatus(user[i]) + " </td><td>" + Datenbank.getUserLastLoginTime(user[i])
											+ "</td><td  align=\"right\"><a href=\"PasswordReset.jsp?user=" + user[i]
											+ "\" class=\"btn btn-primary\" role=\"button\"><i class=\"glyphicon glyphicon glyphicon-wrench\"></i> Passwort</a></td><td  align=\"right\"><a href=\"../DeleteUser?deleteuser="
											+ user[i]
											+ "\" class=\"btn btn-danger\" role=\"button\" id=\"delete"+i+ "\"><i class=\"glyphicon glyphicon-trash\"></i></a></td></tr>");
								}
						%>
					</tbody>
				</table>
			</div>
			<div class="col-sm-2">
				<a href="newUser.jsp" class="btn btn-primary" role="button">Neuen
					Benutzer</a>
			</div>
		</div>
		<jsp:include page="misc/footer.jsp" />
	</div>

	<script type="text/javascript">
		<%for (int i = 0; i < user.length; i++) {
			 %>
		$("#delete<%out.print(i);%>").popConfirm({
			title : "Nutzer wirklich löschen?",
			content : "Diese Aktion ist unwiederbringlich!",
			placement : "bottom",
			yesBtn : "LÖSCHEN",
			noBtn : "nicht löschen"
		});
	<% 		}%>
	</script>
	<%
		}
	%>
</body>
</html>
