<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="entidad.Cuenta"%>
	<%@page import="entidad.Usuario"%>
	<%@page import="entidad.Cliente"%>
	<%@page import="java.time.LocalDate"%>
	<%@page import="java.time.format.DateTimeFormatter"%>
	<%@page import="java.text.SimpleDateFormat"%>
	<%@page import="java.util.Date"%>
	<%@page import="java.time.LocalDateTime"%>
	<%@page import="java.util.List"%>

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
	} %>
	
	
	<%ArrayList<Cliente> ListarClientes=(ArrayList<Cliente>)request.getAttribute("ListarClientes");%>	
	<%ArrayList<Cuenta> ListarCuenta=(ArrayList<Cuenta>)request.getAttribute("ListarCuenta");%>	
	<%! Date md = new Date();%>
	<%! SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); %>
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


<form action="ServletAltaCuenta" method="post">
<br> <br>
<h1>Agregar Cuenta</h1>
	<!--Nro. de cliente: <input type="text" name="txtNroCliente"> <br><br>
	-->
	<br><br>
	CBU: <input type="text" name="txtCBU"> <br><br>
	<div class="col-md-6 mt-3">
        			<label>Tipo de Cuenta</label>
				    <select name="tipocuenta" >
				    	<option>Seleccione un tipo de cuenta</option>
				    	<option>Caja de Ahorro</option>
				    	<option>Cuenta Corriente</option>
					</select>
        		</div><br>
        <label>Fecha de Creacion</label>
        <input type="text"  name="txtFecha" value=<%= sdf.format(md) %> /><br><br>
    	<label class="form-label">Saldo Inicial</label>
    	<input type="text" name="txtSaldo" value="20000">
    	<div class="col-md-6 mt-3"><br>
        			<label>Asignar a Cliente</label>
        			<select  name="SelecCliente" >
	        				<option value="-1">Seleccione un Cliente</option>
				   		
        			<% if(ListarClientes!=null)
        				for (Cliente cli : ListarClientes) {%>         
        				<option value=<%=cli.getNdeCliente()%>><%= cli.getNdeCliente() +"-"+cli.getNombre() + " " + cli.getApellido()%></option>
        				<% }%>
        			</select>
        		</div><br><br>
        		<!--<input type="submit" value="Verificar" name="btnVerificar"><br>-->
        		<input type="submit" value="Volver" name="btnVolver">
        		<input type="submit" value="Aceptar" name="btnAceptar">
        		
        <% if(ListarCuenta != null) {
		int cont=0;
	    for (Cuenta c : ListarCuenta) {
	        cont++;
	    }
	    if(cont>=3){
	   %> 
	    <script>
      			  alert("El cliente seleccionado alcanzo el limite de cuentas permitidas");
   	 	</script>
	    <% 
      	  
	    }
	    else{%>
	    	 <script>
      				  alert("Cuenta creada y asignada!");
   	 		</script>
	    <% }
	}%> 
    	<br>
        </form>



</body>

</html>