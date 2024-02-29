package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import entidad.Solicitud;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ServletSolicitudes
 */
@WebServlet("/ServletSolicitudes")
public class ServletSolicitudes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSolicitudes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

    	
		//CUENTAS
		ClienteNegocioImpl neg = new ClienteNegocioImpl();
		
		
		
		
		// APROBAR RECHAZAR.
		
		String estado = "";
		String nsolicitud = "";
	
		
		if (request.getParameter("Aprobar")!=null) {
			estado = "Aprobado";
			nsolicitud = request.getParameter("NroDeSolicitud");
			neg.AprobarRechazarSolicitud(estado, nsolicitud);
			RequestDispatcher rd = request.getRequestDispatcher("/PrestamosPendientes.jsp");
		    rd.forward(request, response);
			return;
		};
		
		if(request.getParameter("Rechazar")!=null) {
			nsolicitud = request.getParameter("NroDeSolicitud");
			estado = "Rechazado";
			neg.AprobarRechazarSolicitud(estado, nsolicitud);
			RequestDispatcher rd = request.getRequestDispatcher("/PrestamosPendientes.jsp");
		    rd.forward(request, response);
			return;
		}
		
		//SOLICITUDES
		
		List<Solicitud> listaDeSolicitudes = neg.ListarTodasLasSolicitudes();
		request.setAttribute("ListaDeSolicitudes", listaDeSolicitudes);
		
		
		
		
		
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/PrestamosPendientes.jsp");
	    rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
