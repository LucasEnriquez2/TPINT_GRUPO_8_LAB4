<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">

	<jsp:include page="css\Inicio.css"></jsp:include>
</style>
<title>Login</title>
</head>
<body>

<div class="container text-center" style="width:100%;height:100%">

<div>
	<h1 class="titulo">Iniciar Sesión</h1>
</div>

<form action="ServletInicio" method="get" name="login" class="login">
		<div class="form-group">
			<input type="text" class="" name="user" placeholder="Usuario">
		</div>
		<div class="form-group">
			<input type="password" class="" name="pass" placeholder="Contrasenia">
		</div>
		<div class="form-group">
			<input type="submit" class="" value="Ingresar" name="btnIngresar"> 
		</div>
		</form>
	</div>
</body>
</html>