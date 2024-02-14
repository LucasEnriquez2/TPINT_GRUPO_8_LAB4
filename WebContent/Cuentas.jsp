<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="java.util.ArrayList"%>
  <%@page import="entidad.Cuenta"%>
  <%@page import="entidad.Usuario"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/Style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css">
<meta charset="ISO-8859-1">
<script src="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css"></script>
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
	} %>
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
<table id="example" class="table table-striped" style="width:100%">
<thead>
		<tr>
			<th>nro. de cuenta</th>
			<th>nro. de cliente</th>
			<th>Tipo</th>
			<th>Fecha de creacion</th>
			<th>CBU</th>
			<th>Saldo</th>
		</tr>
</thead>
<tbody>
<%if(request.getAttribute("CuentaModificar")==null&&request.getAttribute("CuentaEliminar")==null) {%>
<form action="ServletCuentas" method="get">
<input type="text" name="nroDeCliente" placeholder="Numero de Cliente">
<input type="submit" name="Buscar" value="Buscar" >
</form>
<%}
if(request.getAttribute("CuentaModificar")!=null){
	Cuenta cuenta=(Cuenta)request.getAttribute("CuentaModificar");
	session.setAttribute("NroCuenta", cuenta.getNroCuenta());
	%>
	<tr>
	<form action="ServletCuentas" method="get">
	
				<td><input type="text" name="NroCuenta" value="<%=cuenta.getNroCuenta()%>" readonly="readonly"></td>
				<td><%= cuenta.getNroDeCliente()%></td>
				<td><input type="text" name="tipoCuenta" value="<%=cuenta.getTipoDeCuenta()%>"></td>
				<td><%=cuenta.getFechaCreacion()%></td>
				<td><input type="text" name="cbu" value="<%=cuenta.getCbu()%>"></td>
				<td><input type="text" name="saldo" value="<%=cuenta.getSaldo()%>"></td>
				<td><input type="submit" name="Modificar" value="Confirmar Modificacion" class="btn btn-success"> <input type="submit" name="Cancelar" value="Cancelar" class="btn btn-danger"></td>
	</form>
			</tr>
		</tbody>
		<%}else
if(request.getAttribute("CuentaEliminar")!=null){
	Cuenta cuenta=(Cuenta)request.getAttribute("CuentaEliminar");
	session.setAttribute("NroCuenta", cuenta.getNroCuenta());
	%>
	<tr>
	<form action="ServletCuentas" method="post">
	
				<td><%= cuenta.getNroCuenta()%></td>
				<td><%= cuenta.getNroDeCliente()%></td>
				<td><%= cuenta.getTipoDeCuenta()%></td>
				<td><%= cuenta.getFechaCreacion()%></td>
				<td><%= cuenta.getCbu()%></td>
				<td><%= cuenta.getSaldo()%></td>
				<td><input type="submit" name="btnEliminar" value="Confirmar Eliminacion" class="btn btn-success"> <input type="submit" name="Cancelar" value="Cancelar" class="btn btn-danger"></td>
	</form>
			</tr>
		</tbody>
		<% 
		
}else{
	if(request.getAttribute("ListaCuentas")!=null){
		ArrayList<Cuenta> listaCuentas =((ArrayList<Cuenta>)request.getAttribute("ListaCuentas"));
		for(Cuenta cuenta:listaCuentas){%>
			<tr>
			<form action="ServletCuentas" method ="post">
				<td><%= cuenta.getNroCuenta()%> <input type="hidden" name="NroCuenta" value="<%=cuenta.getNroCuenta() %>"></td>
				<td><%= cuenta.getNroDeCliente()%></td>
				<td><%= cuenta.getTipoDeCuenta()%></td>
				<td><%= cuenta.getFechaCreacion()%></td>
				<td><%= cuenta.getCbu()%></td>
				<td><%= cuenta.getSaldo()%></td>
				<td> <input type="submit" name="btnModificar" value="Modificar" class="btn btn-warning"> <input type="submit" name="Eliminar" value="Eliminar" class="btn btn-danger"> </td>  
			</form> 
			</tr>

	<%}}}; %>

</table>
</body>
</html>