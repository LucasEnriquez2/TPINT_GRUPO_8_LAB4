<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Solicitud"%>
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


<jsp:include page="NavBarAdmin.jsp"/>

<% 


ArrayList<Solicitud> listaDeSolicitudes = null;
if (request.getAttribute("ListaDeSolicitudes") != null) {
    listaDeSolicitudes = (ArrayList<Solicitud>)request.getAttribute("ListaDeSolicitudes");
}



%>	






<table style="width:100%" border="1">
<thead>
		<tr>
			<th>Nro de Solicitud</th>
			<th>Nro de Cliente</th>
			<th>Nro de CUENTA</th>
			<th>Fecha</th>
			<th>Importe solicitado</th>
			<th>Importe TOTAL a Pagar</th>
			<th>Monto</th>
			
		</tr>
</thead>
<tbody>
		
		
		
		<%
 
    if (listaDeSolicitudes != null) {
    	
    	String pendiente = "Pendiente";
        for (Solicitud sol : listaDeSolicitudes) {
        	if(pendiente.equals(String.valueOf(sol.getEstado()))){
        		
        		
   			
    %>
    	<form action="ServletSolicitudes" method="get"> 
            <tr>
            	
            	<td><%= sol.getNdeSolicitud()%> <input type="hidden" name="NroDeSolicitud" value="<%=sol.getNdeSolicitud() %>"></td>	
                <td><%= sol.getNroCuenta() %> </td>
                <td><%= sol.getFecha()%> </td>
                <td><%= sol.getImporteSolicitado() %> </td>
                <td><%= sol.getImporteAPagar() %> </td>
                <td><%= sol.getPlazo() %> </td>
                <td><%= sol.getMonto() %> </td>
                
                <td><input onclick= "return confirm('Aprobar la solicitud?')" type= "submit"  value="Aprobar" name="Aprobar"  style="color:green"></td>
				<td><input onclick= "return confirm('Rechazar la solicitud?')" type= "submit" value="Rechazar" name="Rechazar"  style="color:red"></td>
             
            </tr>
         </form> 
        
    <%      	
        	}
        	}
    	
        
	}
    
    %>
		
			

		
	</tbody>

</table>

</body>
</html>