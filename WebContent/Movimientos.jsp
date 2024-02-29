<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Movimiento"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Cuenta"%>
<%@page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/Style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css">
    <meta charset="ISO-8859-1">
    <title>Movimientos</title>
</head>
<body>

<%
HttpSession sesion = request.getSession();
if (sesion.getAttribute("username") == null) {
    // No está logeado.
    response.sendRedirect("Inicio.jsp");
} else {
    int esAdmin = (int) sesion.getAttribute("esAdmin");
    if (esAdmin == 1) {
        response.sendRedirect("Reportes.jsp");
    }
}
%>

<jsp:include page="NavBarCliente.jsp"/>

<%
ArrayList<Movimiento> listaMovimientos = null;
if (request.getAttribute("ListaMovimientos") != null) {
    listaMovimientos = (ArrayList<Movimiento>)request.getAttribute("ListaMovimientos");
}

ArrayList<Cuenta> listaCuentas = null;
if (request.getAttribute("ListaCuentas") != null) {
    listaCuentas = (ArrayList<Cuenta>)request.getAttribute("ListaCuentas");
}

String radiovalue = "";
if (request.getAttribute("RadioValue") != null) {
    radiovalue = (String)request.getAttribute("RadioValue");
}

%>
<br> <br>


<fieldset>
  <legend>Seleccione una opcion:</legend>

 

<form action="ServletMovimientos" method="post">

  <div>
    <input type="radio" id="louie" name="radioCuentas" value="todas" checked/>
    <label for="todas">Todas</label>
  </div>
  
  <%
    if (listaCuentas != null) {
        for (Cuenta cta : listaCuentas) {
        	if(cta.getEstado()==1){
       
    %>
   
  <div>
    <input type="radio" id=<%= cta.getNroCuenta() %> name="radioCuentas" value=<%=cta.getNroCuenta()%> />
    <label for="<%= cta.getNroCuenta() %>"><%= cta.getNroCuenta() %> - <%= cta.getTipoDeCuenta() %> </label>
  </div>

 <%			}
        }
    }
    %>
  
  <br>
  <input type="submit" value="Buscar"></input>
 </form>
</fieldset>

<form id="formulario" action="ServletMovimientos" method="get">
	<br> <br>
	   <input type="number" name="idMov" placeholder="Id Movimiento" onkeypress="return /[0-9]/i.test(event.key)">
	   	Tipo de Movimiento: <select name="tipo" id="tipo">
		<option value="Seleccione una opcion"> Seleccione una opcion</option>
		<option value="Alta de Cuenta"> Alta de Cuenta</option>
		<option value=Transferencia> Transferencia </option>
		<option value="Alta de Prestamo"> Alta de Prestamo </option>
		<option value="Pago de Prestamo"> Pago de Prestamo </option>
		</select>
		Fecha: 
		Desde <input type="date" name="txtFechaDesde" onkeypress="return //i.test(event.key)" >
		Hasta <input type="date" name="txtFechaHasta" onkeypress="return //i.test(event.key)" >
		Importe:  
		<input type="number" name="minimo" placeholder="Minimo" onkeypress="return /[0-9]/i.test(event.key)" > 
		<input type="number" name="maximo" placeholder="Maximo" onkeypress="return /[0-9]/i.test(event.key)" >
	   <input type="submit" name="BuscarFiltro" value="Buscar">

</form>

<form id="formulario" action="ServletMovimientos" method="get">
    <input type="submit" name="Limpiar" value="Limpiar Filtros">
</form>

<br> <br>
<table border="1" class="table table-striped">
    <tr>
        <th>Id Movimiento</th>
        <th>Nro de Cuenta</th>
        <th>Tipo de Movimiento</th>
        <th>Fecha</th>
        <th>Detalle</th>
        <th>Importe</th>
    </tr>
    

    <%
 
    if (listaMovimientos != null) {
    	
        for (Movimiento mov : listaMovimientos) {
   			if(String.valueOf(mov.getNdeCuenta()).equals(radiovalue) || "todas".equals(radiovalue)){
    %>
            <tr>
                <td><%= mov.getIdMovimiento() %> </td>
                <td><%= mov.getNdeCuenta() %> </td>
                <td><%= mov.getTipoDeMovimiento() %> </td>
                <td><%= mov.getFecha()%> </td>
                <td><%= mov.getDetalle() %> </td>
                <td><%= mov.getImporte() %> </td>
                
            </tr>
    <%      	
   			}
    	}
        
	}
    
    %>
</table>

</body>
</html>