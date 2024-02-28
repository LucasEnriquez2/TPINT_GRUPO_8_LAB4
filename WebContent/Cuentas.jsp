<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="java.util.ArrayList"%>
  <%@page import="entidad.Cuenta"%>
  <%@page import="entidad.Usuario"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/Style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css">
<meta charset="ISO-8859-1">
<script src="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css"></script>
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
<table id="example" class="table table-striped" style="width:100%">
<thead>
		<tr>
			<th>nro. de cuenta</th>
			<th>nro. de cliente</th>
			<th>Tipo</th>
			<th>Fecha de creacion</th>
			<th>CBU</th>
			<th>Saldo</th>
		</tr>
</thead>
<tbody>
<%if(request.getAttribute("CuentaModificar")==null&&request.getAttribute("CuentaEliminar")==null) {%>

<form action="ServletCuentas" method="get">
<input type="number" name="nroDeCliente" placeholder="Numero de Cliente" onkeypress="return /[0-9]/i.test(event.key)" required>
<input type="submit" name="Buscar" value="Buscar" >
</form>

<form action="ServletCuentas" method="get">
<input type="submit" name="Limpiar" value="Limpiar Filtros" >
</form>

<%}
if(request.getAttribute("CuentaModificar")!=null){
	Cuenta cuenta=(Cuenta)request.getAttribute("CuentaModificar");
	session.setAttribute("NroCuenta", cuenta.getNroCuenta());
	%>
	<tr>
	<form action="ServletCuentas" method="get">
	
				<td><input type="text" name="NroCuenta" value="<%=cuenta.getNroCuenta()%>" readonly="readonly"></td>
				<td><%= cuenta.getNroDeCliente()%></td>
				<td><input type="text" name="tipoCuenta" value="<%=cuenta.getTipoDeCuenta()%>"></td>
				<td><%=cuenta.getFechaCreacion()%></td>
				<td><input type="text" name="cbu" value="<%=cuenta.getCbu()%>"></td>
				<td><input type="text" name="saldo" value="<%=cuenta.getSaldo()%>"></td>
				<td><input type="submit" name="Modificar" value="Confirmar Modificacion" class="btn btn-success"> <input type="submit" name="Cancelar" value="Cancelar" class="btn btn-danger"></td>
	</form>
			</tr>
		</tbody>
		<%}else
if(request.getAttribute("CuentaEliminar")!=null){
	Cuenta cuenta=(Cuenta)request.getAttribute("CuentaEliminar");
	session.setAttribute("NroCuenta", cuenta.getNroCuenta());
	%>
	<tr>
	<form action="ServletCuentas" method="post">
	
				<td><%= cuenta.getNroCuenta()%></td>
				<td><%= cuenta.getNroDeCliente()%></td>
				<td><%= cuenta.getTipoDeCuenta()%></td>
				<td><%= cuenta.getFechaCreacion()%></td>
				<td><%= cuenta.getCbu()%></td>
				<td><%= cuenta.getSaldo()%></td>
				<td><input type="submit" name="btnEliminar" value="Confirmar Eliminacion" class="btn btn-success"> <input type="submit" name="Cancelar" value="Cancelar" class="btn btn-danger"></td>
	</form>
			</tr>
		</tbody>
		<% 
		
}else{
	if(request.getAttribute("ListaCuentas")!=null){
        ArrayList<Cuenta> listaCuentas = ((ArrayList<Cuenta>) request.getAttribute("ListaCuentas"));
        int itemsPorPagina = 10;
        int paginaActual = 1;

        
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
        	paginaActual = Integer.parseInt(pageParam);
        }

        int empieza = (paginaActual - 1) * itemsPorPagina;
        int termina = Math.min(empieza + itemsPorPagina, listaCuentas.size());

        for (int i = empieza; i < termina; i++) {
            Cuenta cuenta = listaCuentas.get(i);
%>
            <tr>
                <form action="ServletCuentas" method="post">
                    <td><%= cuenta.getNroCuenta() %><input type="hidden" name="NroCuenta" value="<%= cuenta.getNroCuenta() %>"></td>
                    <td><%= cuenta.getNroDeCliente() %></td>
                    <td><%= cuenta.getTipoDeCuenta() %></td>
                    <td><%= cuenta.getFechaCreacion() %></td>
                    <td><%= cuenta.getCbu() %></td>
                    <td><%= cuenta.getSaldo() %></td>
                    <td>
                        <input type="submit" name="btnModificar" value="Modificar" class="btn btn-warning">
                        <input type="submit" name="Eliminar" value="Eliminar" class="btn btn-danger">
                    </td>
                </form>
            </tr>
<%
        }
%><br><ul class="pagination"><% 
        // links de paginacion
        int totalPages = (int) Math.ceil((double) listaCuentas.size() / itemsPorPagina);
        for (int pageLink = 1; pageLink <= totalPages; pageLink++) {
        	%>
        	           <li class="page-item"><a class="page-link" href="ServletCuentas?page=<%= pageLink %>"><%= pageLink %></a></li>
<%
        }
    }};
%>
</ul>
</table>
</body>
</html>