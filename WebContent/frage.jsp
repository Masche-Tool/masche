<%@page import="model.dvDatenbank"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

<title>Frage</title>
</head>
<body>

	<%
		Cookie[] cookies = request.getCookies();
		String sessionId = "0";
		String questionId = "";
		int frageArt = 0;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("masche_durchfuehrung_sessionID".equals(cookie.getName())) {
					sessionId = cookie.getValue();
				}
			}
			Cookie cookie = new Cookie("masche_durchfuehrung_sessionID", sessionId);
			cookie.setMaxAge(3600);//Gueltigkeit des Cookie in Sekunden. 1/2 Stunde
			response.addCookie(cookie);
			questionId = dvDatenbank.getQuestionIdForParicipant(sessionId);
		}
	%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<h2>
					<%
						out.print(dvDatenbank.getQuestionForParicipant(sessionId));
					%>
				</h2>
			</div>
			<%
				if (dvDatenbank.getQuestionArtdForParicipant(sessionId) == 0) {
			%>
			<div class="col-sm-12">
				<form action="Antwort" method="get">
					<p>
						<input type="hidden" name="frage"
							value="<%out.print(questionId);%>">

						<textarea class="form-control" name="antwort" rows="5" required></textarea>
					</p>
					<p>
						<button class="btn btn-primary" type="submit" style="width: 100%;">Antwort
							abgeben <i class="glyphicon glyphicon-send"></i></button>
					</p>
				</form>
			</div>
			<%
				}
				if (dvDatenbank.getQuestionArtdForParicipant(sessionId) == 1) {
			%>
			<div class="col-sm-12">
				<p>
					<a href="Antwort?antwort=1&frage=<%out.print(questionId);%>"
						class="btn btn-primary" role="button"
						style="width: 100%; background: #006600;"><b>Ja</b></a>
				</p>
			</div>
			<div class="col-sm-12">
				<p>
					<a href="Antwort?antwort=2&frage=<%out.print(questionId);%>"
						class="btn btn-primary" role="button"
						style="width: 100%; background: #cc0000;"><b>Nein</b></a>
				</p>
			</div>
			<%
				}
				if (dvDatenbank.getQuestionArtdForParicipant(sessionId) == 3) {
			%>

			<div class="col-sm-12">
				<p>
					<a href="Antwort?antwort=1&frage=<%out.print(questionId);%>"
						class="btn btn-primary" role="button"
						style="width: 100%; background: #006600;"><b>Stimme ich
							voll zu</b></a>
				</p>
			</div>
			<div class="col-sm-12">
				<p>
					<a href="Antwort?antwort=2&frage=<%out.print(questionId);%>"
						class="btn btn-primary" role="button"
						style="width: 100%; background: #79d279;"><b>Stimme ich
							eher zu</b></a>
				</p>
			</div>
			<div class="col-sm-12">
				<p>
					<a href="Antwort?antwort=3&frage=<%out.print(questionId);%>"
						class="btn btn-primary" role="button"
						style="width: 100%; background: #ff4d4d;"><b>Stimme ich
							eher nicht zu</b></a>
				</p>
			</div>
			<div class="col-sm-12">
				<p>
					<a href="Antwort?antwort=4&frage=<%out.print(questionId);%>"
						class="btn btn-primary" role="button"
						style="width: 100%; background: #cc0000;"><b>Stimme ich
							nicht zu</b></a>
				</p>
			</div>
			<%
				}

				if (dvDatenbank.getQuestionArtdForParicipant(sessionId) == 5) {
			%>

			<div class="col-sm-12">
				<p>
					<a href="Antwort?antwort=1&frage=<%out.print(questionId);%>"
						class="btn btn-primary" role="button"
						style="width: 100%; background: #006600;"><b>Stimme ich
							voll und ganz zu</b></a>
				</p>
			</div>
			<div class="col-sm-12">
				<p>
					<a href="Antwort?antwort=2&frage=<%out.print(questionId);%>"
						class="btn btn-primary" role="button"
						style="width: 100%; background: #40bf40;"><b>Stimme ich
							voll zu</b></a>
				</p>
			</div>
			<div class="col-sm-12">
				<p>
					<a href="Antwort?antwort=3&frage=<%out.print(questionId);%>"
						class="btn btn-primary" role="button"
						style="width: 100%; background: #79d279;"><b>Stimme ich
							eher zu</b></a>
				</p>
			</div>
			<div class="col-sm-12">
				<p>
					<a href="Antwort?antwort=4&frage=<%out.print(questionId);%>"
						class="btn btn-primary" role="button"
						style="width: 100%; background: #ff4d4d;"><b>Stimme ich
							eher nicht zu</b></a>
				</p>
			</div>
			<div class="col-sm-12">
				<p>
					<a href="Antwort?antwort=5&frage=<%out.print(questionId);%>"
						class="btn btn-primary" role="button"
						style="width: 100%; background: #ff1a1a;"><b>Stimme ich
							nicht zu</b></a>
				</p>
			</div>
			<div class="col-sm-12">
				<p>
					<a href="Antwort?antwort=6&frage=<%out.print(questionId);%>"
						class="btn btn-primary" role="button"
						style="width: 100%; background: #cc0000;"><b>Dem
							Widerspreche ich entschieden</b></a>
				</p>
			</div>
			<%
				}
			%>

		</div>
	</div>
</body>
</html>
