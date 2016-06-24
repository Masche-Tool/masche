<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<title>Dozentenansicht</title>
<jsp:include page="misc/html-head.jsp" />
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
			<h1>Neue Evaluation anlegen</h1>
			<form action="../DoNewEvaluation" method="post">
				<div class="col-sm-9">

					<p>
						<input type="text" id="inputModulname" class="form-control"
							placeholder="Evaluationsname" name="modulname" required autofocus>
					</p>
					<p>
						<textarea id="inputShortDescription" class="form-control"
							placeholder="Eine kurze Beschreibung der Evaluation (maximal 1024 Zeichen)"
							name="beschreibung" rows="3" required></textarea>
					</p>
					<p>
						<select name="semester" id="inputSemester" class="form-control"
							required>
							<option></option>
							<option>Sommersemester 2016</option>
							<option>Wintersemester 2016/17</option>
							<option>Sommersemester 2017</option>
							<option>Wintersemester 2017/18</option>
							<option>Sommersemester 2018</option>
							<option>Wintersemester 2018/19</option>
							<option>Sommersemester 2020</option>
							<option>Wintersemester 2020/21</option>
							<option>Sommersemester 2021</option>
							<option>Wintersemester 2021/22</option>
							<option>Sommersemester 2022</option>
							<option>Wintersemester 2022/23</option>
							<option>Sommersemester 2023</option>
							<option>Wintersemester 2023/24</option>
							<option>Sommersemester 2025</option>
						</select>
					</p>
				</div>
				<div class="col-sm-3">
					<button class="btn btn-primary" style="width: 100%;" type="submit">Anlegen</button>
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
