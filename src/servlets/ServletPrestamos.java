package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;

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
		
		//OBTENER SESSION Y USUARIO
    	HttpSession usuarioSession = request.getSession();
    	
		
    	
		//CUENTAS
		CuentaNegocioImpl negC = new CuentaNegocioImpl();
		List<Cuenta> listaCuentas = negC.ListarCuentaPorUsuario((String) usuarioSession.getAttribute("username"));
		request.setAttribute("ListaCuentas", listaCuentas);
		
		if(request.getParameter("btnAceptar")!=null) {
			
			String Monto = request.getParameter("monto");
			String Cuotas = request.getParameter("cuotas");
			String Cuenta = request.getParameter("cuenta");
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
	
	    		if(Float.parseFloat(Monto) <= 0){
	    			
	    			out.print("<p style='color: red;'> El monto ingresado debe ser mayor a 0.</p>"
	    	                + "<a href='ServletPrestamos'> Regresar </a>");
	    	        out.flush();
	    	        
	    	        
	   
	    			
	    		} 
			

			response.sendRedirect("Prestamos.jsp");
		}
		
		
		
    		
    		
    		
      
        	
        
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/Prestamos.jsp");
	     rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
