<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>

<%@page import="entidad.Cuenta"%>
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
					<a href="ServletPrestamos">Prestamos</a>
				</li>
				<li>
					<a href="DatosClientes.jsp">Mis datos</a>
				</li>
				<li>
					<a href="ServletLogout">Cerrar sesi�n</a>
				</li>
		</ul>
	</div>
	
<% 

ArrayList<Cuenta> listaCuentas = null;
if (request.getAttribute("ListaCuentas") != null) {
    listaCuentas = (ArrayList<Cuenta>)request.getAttribute("ListaCuentas");
}

%>	


	
<h1>Prestamos</h1>
<h2> Pedido de Prestamo</h2>

<form action="ServletPrestamos" method="get" class="formPrestamos" onsubmit="return validarFormulario()"> 
	Ingresar cantidad de dinero: 
	<input type="number" id="monto" name="monto">
	
	<br><br>
	
	Seleccionar cantidad de cuotas a pagar:
 	<select name="cuotas" id="cuotas">
 		<option value="3">3</option>
  		<option value="6">6</option>
  		<option value="12">12</option>
	</select>
	
	<br><br>
	
	Seleccionar cuenta de deposito del prestamo:
  	<select name="cuenta" id="cuenta">
<%
    if (listaCuentas != null) {
        for (Cuenta cta : listaCuentas) {
        	if(cta.getEstado()==1){
    %>
  		
	<option value="<%= cta.getNroCuenta()%>"> <%= cta.getNroCuenta()%> - <%= cta.getTipoDeCuenta()%> - $ <%= cta.getSaldo()%> </option>
<%
    }
        }
        
    		}
      

%>
</select>
	
<br><br>

	<input type="submit" class="btnAceptar" name="btnAceptar" value="Aceptar"> <br><br>
</form>




<script>
    function validarFormulario() {
        
        var monto = document.getElementById("monto").value.trim();
        
        if (monto === "") {
            alert("Por favor, ingrese un monto v�lido.");
            return false; // Evita que el formulario se env�e si la validaci�n no pasa
        }
        
        return true; // Env�a el formulario si la validaci�n pasa
        
    }
</script>
</body>
</html>