<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="entidad.Usuario"%>
    <%@page import="entidad.Movimiento"%>
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
	<form action="ServletReportes" method="post" onsubmit="return validarFormulario();">
		<p> Buscar los movimientos que se encuentren entre el monto minimo y maximo ingresado.</p>
		
		
        <input type="number" name="min" id="min" required placeholder="Minimo" ><br><br>
        
        <input type="number" name="max" id= "max" required placeholder="Maximo"><br><br>
    
        <input type="submit" value="Buscar">      
    </form>
    
	<%
    ArrayList<Movimiento> listaMovimientos=null;
    if(request.getAttribute("ListaMovimientos")!= null)
    {
    	listaMovimientos = (ArrayList<Movimiento>) request.getAttribute("ListaMovimientos");
    } 
  
	%>
	
	<br> <br>
	<table border="1"> 
	<tr>
		<th>Id Movimiento</th> <th>Nro de Cuenta</th> <th>Tipo de Movimiento</th> 
		<th>Fecha</th> <th>Detalle</th> <th>Importe</th>
	</tr>
	
	<%
	if(listaMovimientos!=null) 
	for(Movimiento mov : listaMovimientos){ %>
	<tr>
		<td><%= mov.getIdMovimiento() %> </td>  
		<td><%= mov.getNdeCuenta() %> </td>  
		<td><%= mov.getTipoDeMovimiento() %> </td>  
		<td><%= mov.getFecha()%> </td>  
		<td><%= mov.getDetalle() %> </td>
		<td><%= mov.getImporte() %> </td> 
	</tr>
	<%} %>
	
	
	
	</table>
</body>
</html>