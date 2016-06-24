<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<title>Evaluation starten</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/qrcode.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

</head>
<body>

	<div id="anmelden">
		<form class="form-signin" action="Runeval" method="get">
			<p>
				<input type="text" id="id" class="form-control"
					placeholder="Evaluations ID eingeben" name="id" required autofocus>
			</p>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Evaluation starten</button>
		</form>
	</div>
	</div>
	<jsp:include page="admin/misc/footer.jsp" />
</body>
</html>