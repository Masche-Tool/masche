<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Anmelden</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div id="anmelden">
			<form class="form-signin" action="login" method="post">
				<h2 class="form-signin-heading">Anmelden</h2>
				<p>
					<input type="text" id="inputUsername" class="form-control" placeholder="Benutzername" name="user" required autofocus>
				</p>
				<p>
					<input type="password" id="inputPassword" class="form-control" placeholder="Passwort" name="password1" required>
				</p>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Anmelden</button>
			</form>
		</div>
	</div>
</body>
</html>