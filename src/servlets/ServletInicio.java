package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class ServletInicio
 */
@WebServlet("/ServletInicio")
public class ServletInicio extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletInicio() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String usuario = request.getParameter("user");
	     String contrasenia = request.getParameter("pass");
	        
	        
	        if (usuario != null && contrasenia != null) {
	        	UsuarioNegocioImpl neg = new UsuarioNegocioImpl();
	        	HttpSession usuarioSession = request.getSession();
	            int existe = neg.validarUsuario(usuario, contrasenia);
	            usuarioSession.setAttribute("username", usuario);
	            
	            
	            if(existe == 0) {
	            	response.sendRedirect("Inicio.jsp");
	            } else if(existe == 1) {	       
	            	usuarioSession.setAttribute("esAdmin", 0);
	            	response.sendRedirect("Transferir.jsp");
	            } else {	            	
	            	usuarioSession.setAttribute("esAdmin", 1);
	            	response.sendRedirect("Reportes.jsp");
	            }
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
