<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/Style.css">
<meta charset="ISO-8859-1">
<title>Prestamos</title>
</head>
<body>

<%
HttpSession sesion = request.getSession(); 
if (session.getAttribute("username") != null) {
    int esAdmin = (int) session.getAttribute("esAdmin");
    if (esAdmin == 1) {
    	response.sendRedirect("Reportes.jsp");
    }
} else {
	// No esta logeado.
	response.sendRedirect("Inicio.jsp");
}
%>
<div class="nav">
- Bienvenido,&nbsp;<%
	if(sesion.getAttribute("username")!=null){
		%><%=sesion.getAttribute("username")%><% 
	}; %>
		<ul>
				<li>
					<a href="Transferir.jsp">Transferir</a>
				</li>
				<li>
					<a href="Movimientos.jsp">Movimientos</a>
				</li>
				<li>
					<a href="Prestamos.jsp">Prestamo</a>
				</li>
				<li>
					<a href="DatosClientes.jsp">Mis datos</a>
				</li>
				<li>
					<a href="ServletLogout">Cerrar sesión</a>
				</li>
		</ul>
	</div>
<h1>Prestamos</h1>
<h2> Pedido de Prestamo</h2>

Ingresar cantidad de dinero: <input type="text" name="txtCantPrestamo"> <br><br>
Seleccionar cantidad de cuotas a pagar:
  <select name="CantCuotas" id="cuotas">
  <option value="3">3</option>
  <option value="6">6</option>
  <option value="12">12</option>
</select>
<br><br>

Seleccionar cuenta de deposito del prestamo:
  <select name="CuentaCliente" id="cuenta">
  <option value="1">N° Cuenta 1</option>
  <option value="2">N° Cuenta 2</option>
  <option value="3">N° Cuenta 3</option>
</select>
<br><br>
<input type="submit" value="Aceptar" name="btnAceptar"> <br><br>


<h2> Pago de Prestamo</h2>

Seleccionar cuenta:
  <select name="CuentaCliente" id="cuenta">
  <option value="1">N° Cuenta 1</option>
  <option value="2">N° Cuenta 2</option>
  <option value="3">N° Cuenta 3</option>
</select>
<br><br>

 Seleccionar cuotas a pagar:<br><br>

<table style="width:100%" border="1">
<thead>
		<tr>
			<th>Nro. de Prestamo</th>
			<th>Nro. de Cuota</th>
			<th>Importe a Pagar</th>
			<th>Selecc.</th>
		</tr>
</thead>
<tbody>
		<tr>
			<td></td>
			<td></td>
			<td></td>
 			<td><input type="checkbox" name="name1" />&nbsp;</td>
		</tr>
		
</tbody>
</table>
<br>
<input type="submit" value="Pagar" name="btnPagarr"> <br><br>
</body>
</html>