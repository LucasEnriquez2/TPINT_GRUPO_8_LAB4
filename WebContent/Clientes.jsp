<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
  	<%@page import="entidad.Cliente"%>
  	

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/Style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css">
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
<table style="width:100%" class="table table-striped" border="1">
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

<form id="formulario" action="ServletClientes" method="get">
    <input type="number" name="NdeCliente" placeholder="Numero de Cliente" onkeypress="return /[0-9]/i.test(event.key)" required>
    <input type="submit" name="Buscar" value="Buscar">
    
</form>

<form id="formulario" action="ServletClientes" method="get">
    <input type="submit" name="Limpiar" value="Limpiar Filtros">
</form>

<%if(request.getAttribute("ClienteModificar")!=null){
	Cliente cliente=(Cliente)request.getAttribute("ClienteModificar");
	session.setAttribute("NdeCliente", cliente.getNdeCliente());
	%>
	<tr>
	<form action="ServletClientes" method="get">
	
				<td><%=cliente.getNdeCliente()%><input type="hidden" name="NroCliente" value="<%=cliente.getNdeCliente() %>"></td>
				<td><%=cliente.getDNI()%><input type="hidden" name="DNI" value="<%=cliente.getDNI() %>"></td>
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
				<td><%=cliente.getUsuario()%></td>
				<td><input type="text" name="Contrasenia" value="<%=cliente.getContrasenia()%>"></td>
				<td><input type="submit" name="Modificar" value="Confirmar Modificacion" ></td>
				<td><input type="submit" name="Cancelar" value="Cancelar" ></td>
	</form>
			</tr>
		</tbody>
		
		<% }else if(request.getAttribute("ClienteEliminar")!=null){
	Cliente cliente=(Cliente)request.getAttribute("ClienteEliminar");
	session.setAttribute("NdeCliente", cliente.getNdeCliente());
	%>
	<tr>
	<form action="ServletClientes" method="get">
	
				<td><%=cliente.getNdeCliente()%><input type="hidden" name="NroCliente" value="<%=cliente.getNdeCliente() %>"></td>
				<td><%=cliente.getDNI()%></td>
				<td><%=cliente.getCUIL()%></td>
				<td><%=cliente.getNombre()%></td>
				<td><%=cliente.getApellido()%></td>
				<td><%=cliente.getSexo()%></td>
				<td><%=cliente.getNacionalidad()%></td>
				<td><%=cliente.getFechaDeNacimiento()%></td>
				<td><%=cliente.getDireccion()%></td>
				<td><%=cliente.getLocalidad()%></td>
				<td><%=cliente.getProvincia()%></td>
				<td><%=cliente.getMail()%>"></td>
				<td><%=cliente.getTelefono()%></td>
				<td><%=cliente.getUsuario()%></td>
				<td><%=cliente.getContrasenia()%></td>
				<td><input type="submit" name="Eliminar" value="Eliminar" ></td>
				<td><input type="submit" name="Cancelar" value="Cancelar" ></td>
	</form>
			</tr>
		</tbody>
		
<%}else{
	if(request.getAttribute("ListaClientes")!=null){
		ArrayList<Cliente> listaClientes =((ArrayList<Cliente>)request.getAttribute("ListaClientes"));
		int itemsPorPagina = 10;
        int paginaActual = 1;
		
        String numeroPagina = request.getParameter("pagina");
        if (numeroPagina != null && !numeroPagina.isEmpty()) {
            paginaActual = Integer.parseInt(numeroPagina);
        }

        int empieza = (paginaActual - 1) * itemsPorPagina;
        int termina = Math.min(empieza + itemsPorPagina, listaClientes.size());
        
		for(int i = empieza; i < termina; i++)
		{
			Cliente cliente = listaClientes.get(i);
	%>
		<tr>
			<form action="ServletClientes" method = "get">
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
			<td><a href="ServletClientes?FilaE=<%=cliente.getNdeCliente()%>">Eliminar</a></td>  
			</form>  
		</tr>
		<%}
		%><br><ul class="pagination"><% 
		        // links de paginacion
		        int totalPaginas = (int) Math.ceil((double) listaClientes.size() / itemsPorPagina);
		        for (int pageLink = 1; pageLink <= totalPaginas; pageLink++) {
		        	%>
		        	           <li class="page-item"><a class="page-link" href="ServletClientes?pagina=<%= pageLink %>"><%= pageLink %></a></li>
		<%
		        }} };%>
	
</table>

<form action="ServletClientes" method="post" onsubmit="return validarFormulario()">

<br> <br>
<h1>Agregar Cliente</h1>

	DNI: <input type="text" name="txtDNI" onkeypress="return /[0-9]/i.test(event.key)" required> <br><br>
	CUIL: <input type="text" name="txtCUIL" onkeypress="return /[0-9]/i.test(event.key)" required> <br><br>
	Nombre: <input type="text" name="txtNombre" onkeypress="return //i.test(event.key)" required> <br><br>
	Apellido: <input type="text" name="txtApellido" onkeypress="return //i.test(event.key)" required> <br><br>
	Sexo: <select name="Sexo" id="sexo">
			<option value="Masculino"> Masculino</option>
			<option value="Femenino"> Femenino </option>
			<option value="Otro"> Otro</option>
			</select> 
	 <br><br>
	Nacionalidad: <input type="text" name="txtNacionalidad" onkeypress="return //i.test(event.key)" required> <br><br>
	Fecha de Nacimiento:  <input type="date" name="txtFecha" onkeypress="return //i.test(event.key)" required> <br><br>
	Direccion:  <input type="text" name="txtDireccion" onkeypress="return //i.test(event.key)" required> <br><br>
	Localidad:  <input type="text" name="txtLocalidad" onkeypress="return //i.test(event.key)" required> <br><br>
	Provincia:  <input type="text" name="txtProvincia" onkeypress="return //i.test(event.key)" required> <br><br>
	Email:  <input type="text" name="txtEmail" onkeypress="return //i.test(event.key)" required> <br><br>
	Telefono:  <input type="text" name="txtTelefono" onkeypress="return //i.test(event.key)" required> <br><br>
	Usuario:  <input type="text" name="txtUsuario" onkeypress="return //i.test(event.key)" required> <br><br>
	Contraseña:  <input type="password" id="pass1" name="txtContrasenia" onkeypress="return //i.test(event.key)" required> <br><br>
	Repetir Contraseña:  <input type="password" id="pass2" name="txtContrasenia2" onkeypress="return //i.test(event.key)" required> <br><br>
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
	<script>
    	alert("Cliente agregado!");
	</script>
<%} %>

<% if(filas==2) 
	{
	
%>
	<script>
    	alert("Ya existe un cliente con ese DNI o Usuario");
	</script>
<%} %>
<script>
    function validarFormulario() {
        var pass1 = document.getElementById("pass1").value.trim();
        var pass2 = document.getElementById("pass2").value.trim();
        

        if (pass1 != pass2) {
            alert("Las contrasenias no coinciden.");
            document.getElementById("pass1").value = "";
            document.getElementById("pass2").value = "";
            return false; // Evita que el formulario se envíe si la validación no pasa
        }
        
        return true; // Envía el formulario si la validación pasa
        
    }
</script>
</body>
</html>