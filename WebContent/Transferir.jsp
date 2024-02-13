<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="negocioImpl.CuentaNegocioImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/Style.css">
<title>Transferir</title>
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
					<a href="Prestamos.jsp">Prestamo</a>
				</li>
				<li>
					<a href="DatosClientes.jsp">Mis datos</a>
				</li>
				<li>
					<a href="ServletLogout">Cerrar sesión</a>
				</li>
		</ul>
	</div>
	
	
		<form action="ServletTransferir" method="get" class="formTransf" onsubmit="return validarFormulario()">
		<fieldset> <legend> Transferir a otro usuario </legend>
			<br> <br>		
			<p>Origen:</p>
			<div>
				<select name="ncuenta">
				
				 <%
				if((String)sesion.getAttribute("username")!=null){
                CuentaNegocioImpl neg = new CuentaNegocioImpl();
                ArrayList<Cuenta> cuentas = neg.ListarCuentaPorUsuario((String)sesion.getAttribute("username"));
                for (Cuenta cuenta : cuentas) {
                %>
                <option value="<%= cuenta.getNroCuenta()%>"> <%= cuenta.getNroCuenta()%> - <%= cuenta.getTipoDeCuenta()%> - $ <%= cuenta.getSaldo()%> </option>
                <% }}; %>
				</select>
			</div>
	
			<br>
			<label for="cbu">Ingresa el CBU del receptor:</label>
			<div>
				<input type="number" id="cbu" name="cbu" placeholder="Ingresar CBU" minlength="1" maxlength="45"> 
			</div>
			<br>
			<label for="monto">Ingresa un monto:</label>
			<div>
			<input type="number" id="monto" name="monto" placeholder="Monto"  >
			</div>
			<br>
			
			<label for="descripcion">Ingresa una descripcion:</label>
			<div>
			<input type="text" id= "detalle" name="detalle" placeholder="Descripcion" minlength="1" maxlength="45" >
			</div>
			
			<br>
			<input type="reset" name="btnCancelar" class="btnCancelar" value="Cancelar">
			<input type="submit" name="btnConfirmar" class="btnConfirmar" value="Confirmar">
			
		</fieldset>
		</form>

<script>
    function validarFormulario() {
        var cbu = document.getElementById("cbu").value.trim();
        var monto = document.getElementById("monto").value.trim();
        var detalle = document.getElementById("detalle").value.trim();

        if (cbu === "") {
            alert("Por favor, ingrese un CBU válido.");
            return false; // Evita que el formulario se envíe si la validación no pasa
        }
        
        if (monto === "") {
            alert("Por favor, ingrese un monto válido.");
            return false; // Evita que el formulario se envíe si la validación no pasa
        }
        
        if (detalle === "") {
            alert("Por favor, ingrese un detalle válido.");
            return false; // Evita que el formulario se envíe si la validación no pasa
        }
		
        
        return true; // Envía el formulario si la validación pasa
        
    }
</script>
</body>
</html>