<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="entidad.Usuario"%>
    <%@page import="entidad.Movimiento"%>
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

<jsp:include page="NavBarAdmin.jsp"/>


	<script>
	function validarFormulario() {
            var minimo = parseInt(document.getElementById("min").value);
            var maximo = parseInt(document.getElementById("max").value);

            if (minimo >= maximo) {
                alert("El valor máximo debe ser mayor que el valor mínimo.");
                return false; // Evita que el formulario se envíe si la validación falla.
            }
            return true; // Envía el formulario si la validación es exitosa.
        }
	</script>
	<form action="ServletReportes" method="post" onsubmit="return validarFormulario()">
		<p> Buscar los movimientos que se encuentren entre el monto minimo y maximo ingresado.</p>
		
		
        <input type="number" name="min" id="min" required placeholder="Minimo" ><br><br>
        
        <input type="number" name="max" id= "max" required placeholder="Maximo"><br><br>
    
        <input type="submit" value="Buscar">      
    </form>
	
	<br> <br>
	
	<%
	if(request.getAttribute("ListaMovimientos")!=null) 
	{
		ArrayList<Movimiento> listaMovimientos = (ArrayList<Movimiento>) request.getAttribute("ListaMovimientos");
		%>
	
	<table border="1" class="table table-striped"> 
	
	<tr>
		<th>Importes Positivos</th> <th>Importes Negativos</th> <th>Transferencias</th> 
		<th>Altas de Cuenta</th> <th>Altas de Prestamo</th> <th>Pagos de prestamo</th> <th>Numero de Movimientos</th>
	</tr>
	
	<tr>
		<td><%= request.getAttribute("TotalPositivo") %> </td>  
		<td><%= request.getAttribute("TotalNegativo") %> </td>  
		<td><%= request.getAttribute("Transferencias") %> </td>  
		<td><%= request.getAttribute("AltaDeCuentas")%> </td>  
		<td><%= request.getAttribute("AltaDePrestamos") %> </td>
		<td><%= request.getAttribute("PagoDePrestamos") %> </td> 
		<td><%= request.getAttribute("Movimientos") %> </td>
	</tr>
	</table>
	
	<br> <br>
	
	<table border="1" class="table table-striped"> 
	<tr>
		<th>Id Movimiento</th> <th>Nro de Cuenta</th> <th>Tipo de Movimiento</th> 
		<th>Fecha</th> <th>Detalle</th> <th>Importe</th>
	</tr>
	
	<%
	
	int itemsPorPagina = 10;
    int paginaActual = 1;
	
    String numeroPagina = request.getParameter("pagina");
    if (numeroPagina != null && !numeroPagina.isEmpty()) {
        paginaActual = Integer.parseInt(numeroPagina);
    }

    int empieza = (paginaActual - 1) * itemsPorPagina;
    int termina = Math.min(empieza + itemsPorPagina, listaMovimientos.size());
    
	for(int i = empieza; i < termina; i++)
	{
		Movimiento mov = listaMovimientos.get(i); %>
	<tr>
		<td><%= mov.getIdMovimiento() %> </td>  
		<td><%= mov.getNdeCuenta() %> </td>  
		<td><%= mov.getTipoDeMovimiento() %> </td>  
		<td><%= mov.getFecha()%> </td>  
		<td><%= mov.getDetalle() %> </td>
		<td><%= mov.getImporte() %> </td> 
	</tr>
	<%} %>
	<br><ul class="pagination"><% 
		        // links de paginacion
		        int totalPaginas = (int) Math.ceil((double) listaMovimientos.size() / itemsPorPagina);
		        for (int pageLink = 1; pageLink <= totalPaginas; pageLink++) {
		        	%>
		        	 <li class="page-item"><a class="page-link" href="ServletReportes?pagina=<%= pageLink %>"><%= pageLink %></a></li>
	<%
		        }};%>
	
	
	</table>
</body>
</html>