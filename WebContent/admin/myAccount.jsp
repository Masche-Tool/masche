<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Datenbank"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<title>Dozentenansicht</title>
<jsp:include page="misc/html-head.jsp" />
<script>
	jQuery(function() {
		$("#submit")
				.click(
						function() {
							$(".error").hide();
							var hasError = false;
							var passwordVal = $("#password").val();
							var checkVal = $("#password-check").val();
							if (passwordVal == '') {
								$("#password")
										.after(
												'<span class="error">Bitte das Passwort zwei Mal eingeben um persönliche Daten zu ändern!</span>');
								hasError = true;
							} else if (checkVal == '') {
								$("#password-check")
										.after(
												'<span class="error">Bitte das Passwort bestätigen.</span>');
								hasError = true;
							} else if (passwordVal != checkVal) {
								$("#password-check")
										.after(
												'<span class="error">Passwörter stimmen nicht überein!</span>');
								hasError = true;
							}
							if (hasError == true) {
								return false;
							}
						});
	});
</script>
<style type="text/css">
table {
	border-collapse: separate;
	border-spacing: 1em;
}
</style>
</head>
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
	if (sessionType == 1 || sessionType == 2) {
%>
<body>

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
				<h1>Mein Profil</h1>
			</div>
			<form class="form-signin" method="post" name="form1"
				id="form-password" action="../UpdateUser">
				<div class="col-sm-10">
					<table>
						<tr>
							<td>Benutzername</td>
							<td><%String user = Datenbank.getUserOfSession(sessionId); out.print(user);%>
								&nbsp;&nbsp;&nbsp;(Der Benutzername kann nicht geändert werden.)</td>
						</tr>
						<tr>
							<td>Titel</td>
							<td><input class="form-control" placeholder="Titel"
								type="text" name="titel" id="titel"
								value="<%out.print(Datenbank.getUserTitel(user));%>"></td>
						</tr>
						<tr>
							<td>Name</td>
							<td><input class="form-control" placeholder="Titel"
								type="text" name="name" id="name"
								value="<%out.print(Datenbank.getUserName(user));%>" required></td>
						</tr>
						<tr>
							<td>Passwort</td>
							<td><input class="form-control" placeholder="Passwort"
								type="password" name="password" id="password" value="" size="45"
								required></td>
						</tr>
						<tr>
							<td>Passwortbestätigung</td>
							<td><input class="form-control" placeholder="Passwort"
								type="password" name="password-check" id="password-check"
								value="" required></td>
						<tr>
							<td>E-Mailadresse</td>
							<td><input class="form-control" placeholder="E-Mail"
								type="text" name="mail" id="mail" value="<%out.print(Datenbank.getUserEMail(user));%>" autofocus
								required></td>
						</tr>
					</table>
				</div>
				<div class="col-sm-2">
					<input class="btn btn-primary" role="button" type="submit"
						value="Übernehmen" id="submit">

				</div>
			</form>
		</div>
		<jsp:include page="misc/footer.jsp" />
	</div>
	<%
		}
	%>
</body>
</html>
