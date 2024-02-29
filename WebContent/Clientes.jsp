<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
  	<%@page import="entidad.Cliente"%>
  	

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
	}else{
	 response.sendRedirect("Inicio.jsp");}; %>
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
					<a href="ServletLogout">Cerrar sesión</a>
				</li>
				
		</ul>
</div>
<table style="width:100%" border="1">
<thead>
		<tr>
			<th>nro. de cliente</th>
			<th>DNI</th>
			<th>CUIL</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Sexo</th>
			<th>Nacionalidad</th>
			<th>Fecha de nac.</th>
			<th>Dirección</th>
			<th>Localidad</th>
			<th>Provincia</th>
			<th>Mail</th>
			<th>Telefono</th>
			<th>Usuario</th>
			<th>Contraseña</th>
		</tr>
</thead>
<tbody>

<%if(request.getAttribute("ClienteModificar")!=null){
	Cliente cliente=(Cliente)request.getAttribute("ClienteModificar");
	session.setAttribute("NdeCliente", cliente.getNdeCliente());
	%>
	<tr>
	<form action="ServletClientes" method="get">
	
				<td><input name="NdeCliente" value="<%=cliente.getNdeCliente()%>" readonly="readonly"></td>
				<td><input name="DNI" value="<%=cliente.getDNI()%>" readonly="readonly"></td>
				<td><input type="text" name="CUIL" value="<%=cliente.getCUIL()%>"></td>
				<td><input type="text" name="Nombre" value="<%=cliente.getNombre()%>"></td>
				<td><input type="text" name="Apellido" value="<%=cliente.getApellido()%>"></td>
				<td><input type="text" name="Sexo" value="<%=cliente.getSexo()%>"></td>
				<td><input type="text" name="Nacionalidad" value="<%=cliente.getNacionalidad()%>"></td>
				<td><input type="date" name="FechaDeNacimiento" value="<%=cliente.getFechaDeNacimiento()%>"></td>
				<td><input type="text" name="Direccion" value="<%=cliente.getDireccion()%>"></td>
				<td><input type="text" name="Localidad" value="<%=cliente.getLocalidad()%>"></td>
				<td><input type="text" name="Provincia" value="<%=cliente.getProvincia()%>"></td>
				<td><input type="text" name="Mail" value="<%=cliente.getMail()%>"></td>
				<td><input type="text" name="Telefono" value="<%=cliente.getTelefono()%>"></td>
				<td><input type="text" name="Usuario" value="<%=cliente.getUsuario()%>"></td>
				<td><input type="text" name="Contrasenia" value="<%=cliente.getContrasenia()%>"></td>
				<td><input type="submit" name="Modificar" value="Confirmar Modificacion" ></td>
				<td><input type="submit" name="Cancelar" value="Cancelar" ></td>
	</form>
			</tr>
		</tbody>
		
<%}else{
	if(request.getAttribute("ListaClientes")!=null){
		ArrayList<Cliente> listaClientes =((ArrayList<Cliente>)request.getAttribute("ListaClientes"));
		for(Cliente cliente:listaClientes)
		{
	%>
		<tr>
			<form action="ServletClientes" method = "post">
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
			<td><a href="ServletClientes?Fila=<%=cliente.getNdeCliente()%>">Modificar</a></td>  
			<td> <input type="submit" name="btnEliminar" value="Eliminar"> </td>  
			</form>  
		</tr>
		<%}} }%>
	
</table>

<form action="ServletClientes" method="get">

<br> <br>
<h1>Agregar Cliente</h1>

	DNI: <input type="text" name="txtDNI"> <br><br>
	CUIL: <input type="text" name="txtCUIL"> <br><br>
	Nombre: <input type="text" name="txtNombre"> <br><br>
	Apellido: <input type="text" name="txtApellido"> <br><br>
	Sexo: <select name="Sexo" id="sexo">
			<option value="Masculino"> Masculino</option>
			<option value="Femenino"> Femenino </option>
			<option value="Otro"> Otro</option>
			</select> 
	 <br><br>
	Nacionalidad: <input type="text" name="txtNacionalidad"> <br><br>
	Fecha de Nacimiento:  <input type="date" name="txtFecha"> <br><br>
	Direccion:  <input type="text" name="txtDireccion"> <br><br>
	Localidad:  <input type="text" name="txtLocalidad"> <br><br>
	Provincia:  <input type="text" name="txtProvincia"> <br><br>
	Email:  <input type="text" name="txtEmail"> <br><br>
	Telefono:  <input type="text" name="txtTelefono"> <br><br>
	Usuario:  <input type="text" name="txtUsuario"> <br><br>
	Contraseña:  <input type="text" name="txtContrasenia"> <br><br>
	<input type="submit" value="Aceptar" name="btnAceptar"><br><br>

</form>

<%
	int filas=0;
	if(request.getAttribute("cantFilas")!=null)
		filas= (int)request.getAttribute("cantFilas");	
%>

<% if(filas==1) 
	{
%>
		Cliente agregado con éxito
<%} %>
</body>
</html>