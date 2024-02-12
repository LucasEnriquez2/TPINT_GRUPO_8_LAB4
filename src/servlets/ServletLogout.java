package servlets;

import java.awt.Component;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class ServletLogout
 */
@WebServlet("/ServletLogout")
public class ServletLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtenemos la sesión actual
		int resultado=JOptionPane.showConfirmDialog(null, "Esta seguro que desea cerrar sesion?", "Confirmacion", JOptionPane.YES_NO_OPTION);
       
		if(resultado==0) {
		HttpSession sessionUsuario = request.getSession(false);
        
        if (sessionUsuario != null) {
            // Invalidar la sesión si existe
            sessionUsuario.invalidate();
        }

        // Redirigir a la página de inicio de sesión o a cualquier otra página deseada
        response.sendRedirect("Inicio.jsp");
		}else {
			
			response.sendRedirect("Reportes.jsp");
		}
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
