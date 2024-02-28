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

<div class="nav">
    - Bienvenido,&nbsp;<%
    if (sesion.getAttribute("username") != null) {
        out.print(sesion.getAttribute("username"));
    }
    %>
    <ul>
        <li>
            <a href="Transferir.jsp">Transferir</a>
        </li>
        <li>
            <a href="ServletMovimientos">Movimientos</a>
            
        </li>
        <li>
            <a href="ServletPrestamos">Prestamos</a>
        </li>
        <li>
            <a href="ServletDatos?Listar=1">Mis datos</a>
        </li>
        <li>
            <a href="ServletLogout">Cerrar sesión</a>
        </li>
    </ul>
</div>

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

<br> <br>
<table border="1">
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