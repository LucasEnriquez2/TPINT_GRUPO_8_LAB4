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
					<a href="Transferir.jsp">Transferir</a>
				</li>
				<li>
					<a href="ServletMovimientos">Movimientos</a>
				</li>
				<li>
					<a href="ServletPrestamos">Prestamo</a>
				</li>
				<li>
					<a href="ServletDatos?Listar=1">Mis datos</a>
				</li>
				<li>
					<a href="ServletLogout">Cerrar sesión</a>
				</li>
		</ul>
	</div>