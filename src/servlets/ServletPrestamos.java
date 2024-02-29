package servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import entidad.Cuenta;
import entidad.Prestamo;
import entidad.Solicitud;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//OBTENER SESSION Y NCLIENTE
    	HttpSession usuarioSession = request.getSession();
    	ClienteNegocioImpl neg = new ClienteNegocioImpl();
		int numcliente = neg.ObtenerNdeCliente((String) usuarioSession.getAttribute("username"));
    	int errorp = 0;
		
    	
		//CUENTAS
		CuentaNegocioImpl negC = new CuentaNegocioImpl();
		List<Cuenta> listaCuentas = negC.ListarCuentaPorUsuario((String) usuarioSession.getAttribute("username"));
		request.setAttribute("ListaCuentas", listaCuentas);
		
		//SOLICITUDES
		
		List<Solicitud> listaSolicitudes = neg.ListarSolicitudes(numcliente);
		request.setAttribute("ListaSolicitudes", listaSolicitudes);
		
		List<Prestamo> listaPrestamos = neg.ListarPrestamos(numcliente);
		request.setAttribute("ListaPrestamos", listaPrestamos);
				
		
		if(request.getParameter("btnAceptar")!=null) {
			
			float Monto = Float.parseFloat(request.getParameter("monto"));
			int Cuotas = Integer.parseInt(request.getParameter("cuotas"));
			int Cuenta = Integer.parseInt(request.getParameter("cuenta"));
			
			// errorp 1 : El monto ingresado debe ser mayor a 0
			// errorp 2 : Solicitud de prestamo creada correctamente.
			
	
	    	if(Monto <= 0){
	    		errorp = 1;
	    		request.setAttribute("errorp", errorp);
    			RequestDispatcher rd = request.getRequestDispatcher("/Prestamos.jsp");
                rd.forward(request, response);
    			return;
	    			
	    			
	    	} else {
	    		errorp=2;
	    		Solicitud sol = new Solicitud(numcliente, Cuenta, Monto, Cuotas, "Pendiente");
	    		neg.generarSolicitud(sol);
	    		request.setAttribute("errorp", errorp);
    			RequestDispatcher rd = request.getRequestDispatcher("/Prestamos.jsp");
                rd.forward(request, response);
    			return;
	    		
	    		
	    		
	    	} 
			

			
		}
		
		
		
    		
    		
    		
      
        	
        
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/Prestamos.jsp");
	     rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		int error = 0;
		if(request.getParameter("Pagar")!=null) {
			if(request.getParameter("NdeCuenta")!=null&&request.getParameter("NdePrestamo")!=null&&request.getParameter("Monto")!=null) {
			String NroDeCuenta = request.getParameter("NdeCuenta");
			String NdePrestamo = request.getParameter("NdePrestamo");
			String Monto = request.getParameter("Monto");
			String CuotasPagas = request.getParameter("CuotasPagas");
			String Detalle="Pago de Cuota del Prestamo N°"+NdePrestamo;
			
			CuentaNegocioImpl negM = new CuentaNegocioImpl();
			MovimientoNegocioImpl negMov = new MovimientoNegocioImpl();
			
			if(!negM.PuedeTransferir(Float.parseFloat(Monto), NroDeCuenta)){
    			error = 1;
    			request.setAttribute("error", error);
    			RequestDispatcher rd = request.getRequestDispatcher("/Prestamos.jsp");
                rd.forward(request, response);
    			return;
    		}
    		
    		error = 2;
    		request.setAttribute("error", error);		
    		negMov.PagarPrestamo(NroDeCuenta, NdePrestamo, Monto, Detalle, CuotasPagas);
    		RequestDispatcher rd = request.getRequestDispatcher("/Prestamos.jsp");
            rd.forward(request, response);
            return;
			}}
	}

}
