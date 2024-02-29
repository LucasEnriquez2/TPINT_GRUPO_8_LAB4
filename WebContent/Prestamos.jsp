<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>

<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Solicitud"%>
<%@page import="entidad.Prestamo"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/Style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css">
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


<jsp:include page="NavBarCliente.jsp"/>
	
<% 

ArrayList<Cuenta> listaCuentas = null;
if (request.getAttribute("ListaCuentas") != null) {
    listaCuentas = (ArrayList<Cuenta>)request.getAttribute("ListaCuentas");
}

ArrayList<Solicitud> listaSolicitudes = null;
if (request.getAttribute("ListaSolicitudes") != null) {
    listaSolicitudes = (ArrayList<Solicitud>)request.getAttribute("ListaSolicitudes");
}

ArrayList<Prestamo> listaPrestamos = null;
if (request.getAttribute("ListaPrestamos") != null) {
    listaPrestamos = (ArrayList<Prestamo>)request.getAttribute("ListaPrestamos");
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


<br>


<h2> Mis solicitudes </h2>

<table border="1" class="table table-striped">
    <tr>
        <th>Numero de Solicitud</th>
        <th>Numero de Cuenta</th>
        <th>Fecha</th>
        <th>Importe Solicitado</th>
        <th>Importe a pagar</th>
        <th>Plazo</th>
        <th>Monto</th>
        <th>Estado</th>
    </tr>
    

    <%
 
    if (listaSolicitudes != null) {
    	
        for (Solicitud sol : listaSolicitudes) {
        	if(!"Aprobado".equals(String.valueOf(sol.getEstado()))){
   			
    %>
            <tr>
                <td><%= sol.getNdeSolicitud() %> </td>
                <td><%= sol.getNroCuenta() %> </td>
                <td><%= sol.getFecha()%> </td>
                <td><%= sol.getImporteSolicitado() %> </td>
                <td><%= sol.getImporteAPagar() %> </td>
                <td><%= sol.getPlazo() %> </td>
                <td><%= sol.getMonto() %> </td>
                <td><%= sol.getEstado() %> </td>
                
            </tr>
    <%      
        	}
   			}
    	
        
	}
    
    %>
</table>


<br><br>
<h2> Mis prestamos </h2>

<table border="1" class="table table-striped">
    <tr>
        <th>Numero de Prestamo</th>
        <th>Numero de Cuenta</th>
        <th>Fecha</th>
        <th>Importe Solicitado</th>
        <th>Importe a pagar</th>
        <th>Plazo</th>
        <th>Monto</th>
        <th>Cuotas Pagas</th>
        <th>Estado</th>
    </tr>
    

    <%
 
    if (listaSolicitudes != null) {
    	
        for (Prestamo pre : listaPrestamos) {
        	
   			
    %>
            <tr>
                <td><%= pre.getNdeSolicitud() %> </td>
                <td><%= pre.getNroCuenta() %> </td>
                <td><%= pre.getFecha()%> </td>
                <td><%= pre.getImporteSolicitado() %> </td>
                <td><%= pre.getImporteAPagar() %> </td>
                <td><%= pre.getPlazo() %> </td>
                <td><%= pre.getMonto() %> </td>
                <td><%= pre.getCuotasPagas() %> </td>
                <td><%= pre.getEstado() %> </td>
                
            </tr>
    <%      
        	
   			}
    	
        
	}
    
    %>
</table>


<%
int errorp=0;
if(request.getAttribute("errorp")!=null)
	errorp = (int)request.getAttribute("errorp");

%>


<% if(errorp==1) 
    {
%>
    <script>
        alert("El monto ingresado debe ser mayor a 0");
    </script>
<%} %>

<% if(errorp==2) 
    {

%>
    <script>
        alert("Solicitud de prestamo creada correctamente.");
    </script>
<%
	response.sendRedirect("ServletPrestamos");
    } %>




<script>
    function validarFormulario() {
        
        var monto = document.getElementById("monto").value.trim();
        
        if (monto === "") {
            alert("Por favor, ingrese un monto válido.");
            return false; // Evita que el formulario se envíe si la validación no pasa
        }
        
        if(confirm("Seguro que desea adquirir este prestamo?")){
        	return true; // Envía el formulario si la validación pasa
        } else {
        	return false;
        }
        
        
        
        
        
        
    }
</script>

</body>
</html>