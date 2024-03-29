<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
  	<%@page import="entidad.Cliente"%>
  	<%@page import="entidad.Cuenta"%>
 	<%@page import="entidad.Usuario"%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/Style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css">
<meta charset="ISO-8859-1">
<title>Datos del Cliente</title>
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

<jsp:include page="NavBarCliente.jsp"/>

<h1>Datos del Cliente</h1>
<h2> Datos Personales</h2>
<table style="width:100%" border="1" class="table table-striped">
<thead>
		<tr>
			<th>Nro. de Cliente</th>
			<th>DNI</th>
			<th>CUIL</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Sexo</th>
			<th>Nacionalidad</th>
			<th>Fecha de Nac.</th>
			<th>Direcci�n</th>
			<th>Localidad</th>
			<th>Provincia</th>
			<th>Mail</th>
			<th>Telefono</th>
			<th>Usuario</th>
			<th>Contrase�a</th>
		</tr>
</thead>
<tbody>
		<%{
	if(request.getAttribute("ListaCliente")!=null){
		ArrayList<Cliente> listaClientes =((ArrayList<Cliente>)request.getAttribute("ListaCliente"));
		for(Cliente cliente:listaClientes)
		{
	%>
		<tr>
			<form action="ServletDatos" method = "get">
			<td><%= cliente.getNdeCliente()%> <input type="hidden" name="NroCliente" value="<%=cliente.getNdeCliente() %>"></td>
			<td><%= cliente.getDNI()%></td>
			<td><%= cliente.getCUIL()%></td>
			<td><%= cliente.getNombre()%></td>
			<td><%= cliente.getApellido()%></td>
			<td><%= cliente.getSexo()%></td>
			<td><%= cliente.getNacionalidad()%></td>
			<td><%= cliente.getFechaDeNacimiento()%></td>
			<td><%= cliente.getDireccion()%></td>
			<td><%= cliente.getLocalidad()%></td>
			<td><%= cliente.getProvincia()%></td>
			<td><%= cliente.getMail()%></td>
			<td><%= cliente.getTelefono()%></td>
			<td><%= cliente.getUsuario()%></td>
			<td><%= cliente.getContrasenia()%></td>
			</form>  
		</tr>
		<%}} }%>
		
</tbody>


</table >

<h2> Cuentas</h2>
<table style="width:100%" border="1" class="table table-striped">
<thead>
		<tr>
			<th>Nro. de Cuenta</th>
			
			<th>Tipo de Cuenta </th>
			<th>Fecha de Creacion</th>
			<th>CBU</th>
			<th>Saldo</th>
			
		</tr>
</thead>
<tbody>
		
<% 		
{
	if(request.getAttribute("ListaCuentas")!=null){
		ArrayList<Cuenta> listaCuentas =((ArrayList<Cuenta>)request.getAttribute("ListaCuentas"));
		for(Cuenta cuenta:listaCuentas){%>
			<tr>
			<form action="ServletDatos" method = "get">
				<td><%= cuenta.getNroCuenta()%> <input type="hidden" name="NroCuenta" value="<%=cuenta.getNroCuenta() %>"></td>	
				<td><%= cuenta.getTipoDeCuenta()%></td>
				<td><%= cuenta.getFechaCreacion()%></td>
				<td><%= cuenta.getCbu()%></td>
				<td><%= cuenta.getSaldo()%></td> 
			</form> 
			</tr>
			
	<%}}}; %>
</tbody>


</body>
</html>