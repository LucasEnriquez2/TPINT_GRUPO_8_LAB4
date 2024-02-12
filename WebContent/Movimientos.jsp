<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Movimiento"%>
<%@page import="entidad.Cliente"%>
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
    // No est� logeado.
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
            <a href="ServletMovimientos?Listar=1">Movimientos</a>
            
        </li>
        <li>
            <a href="Prestamos.jsp">Prestamo</a>
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
ArrayList<Movimiento> listaMovimientos = null;
if (request.getAttribute("ListaMovimientos") != null) {
    listaMovimientos = (ArrayList<Movimiento>) request.getAttribute("ListaMovimientos");
}


int NdeCliente = 0;
if (sesion.getAttribute("NdeCliente") != null) {
    NdeCliente = (int) sesion.getAttribute("NdeCliente");
}
%>

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
    %>
</table>

<%

request.setAttribute("NdeCliente", NdeCliente);
sesion.removeAttribute("NdeCliente");
%>

</body>
</html>