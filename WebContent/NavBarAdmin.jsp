<div class="nav">
<%
HttpSession sesion = request.getSession(); 

%>
- Bienvenido,&nbsp;<% 
	if(sesion.getAttribute("username")!=null){
		%><%=sesion.getAttribute("username")%><% 
	} %>
		<ul>
				<li>
					<a href="ServletCuentas?Listar=1">Cuentas</a>
				</li>
				<li>
					<a href="ServletClientes?Listar=1">Clientes</a>
				</li>
				<li>
					<a href="ServletSolicitudes">Prestamos</a>
				</li>
				<li>
					<a href="Reportes.jsp">Reportes</a>
				</li>
				<li>
					<a href="ServletLogout">Cerrar sesion</a>
				</li>
		</ul>
</div>