<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/Style.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
HttpSession sesion = request.getSession(); 
if (session.getAttribute("username") != null) {
    int esAdmin = (int) session.getAttribute("esAdmin");
    if (esAdmin == 0) {
    	response.sendRedirect("Transferir.jsp");
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
					<a href="ServletCuentas?Listar=1">Cuentas</a>
				</li>
				<li>
					<a href="ServletClientes?Listar=1">Clientes</a>
				</li>
				<li>
					<a href="PrestamosPendientes.jsp">Prestamos</a>
				</li>
				<li>
					<a href="Reportes.jsp">Reportes</a>
				</li>
				<li>
					<a href="ServletLogout">Cerrar sesion</a>
				</li>
		</ul>
</div>
<table style="width:100%" border="1">
<thead>
		<tr>
			<th>nro. de prestamo</th>
			<th>nro. de cuenta</th>
			<th>nro. de cliente</th>
			<th>Fecha</th>
			<th>Importe solicitado</th>
			<th>Importe total a pagar</th>
			<th>Cantidad de cuotas</th>
			<th>Cuota Mensual</th>
		</tr>
</thead>
<tbody>
		<tr>
			<td>3625498</td>
			<td>66532</td>
			<td>00001</td>
			<td>01/03/2023</td>
			<td>350000</td>
			<td>420000</td>
			<td>12</td>
			<td>35000</td>
			<td><a href="" style="color:green">Aprobar</a></td>
			<td><a href="" style="color:red">Rechazar</a></td>
		</tr>
		<tr>
			<td>3625499</td>
			<td>66552</td>
			<td>00002</td>
			<td>01/03/2023</td>
			<td>100000</td>
			<td>120000</td>
			<td>10</td>
			<td>12000</td>
			<td><a href="" style="color:green">Aprobar</a></td>
			<td><a href="" style="color:red">Rechazar</a></td>
		</tr>
		<tr>
			<td>3625500</td>
			<td>66542</td>
			<td>00003</td>
			<td>01/03/2023</td>
			<td>1500000</td>
			<td>1800000</td>
			<td>18</td>
			<td>100000</td>
			<td><a href="" style="color:green">Aprobar</a></td>
			<td><a href="" style="color:red">Rechazar</a></td>
		</tr>
	</tbody>

</table>
</body>
</html>