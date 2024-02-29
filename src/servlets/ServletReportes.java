package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Movimiento;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

/**
 * Servlet implementation class ServletReportes
 */
@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MovimientoNegocioImpl movimiento = new MovimientoNegocioImpl();
		
		int minimo = 0;
		int maximo = 0;
		
		HttpSession usuarioSession = request.getSession();
		
		if(request.getParameter("Buscar")!=null) 
		{
			minimo = Integer.parseInt(request.getParameter("min"));
			maximo = Integer.parseInt(request.getParameter("max"));
			usuarioSession.setAttribute("minimo", minimo);
			usuarioSession.setAttribute("maximo", maximo);
		}
		
		minimo = Integer.parseInt(usuarioSession.getAttribute("minimo").toString());
		maximo = Integer.parseInt(usuarioSession.getAttribute("maximo").toString());
		
		ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
	
		listaMovimientos = (ArrayList<Movimiento>) movimiento.ListarMovimientoParametrizados(minimo,maximo);
		
		request.setAttribute("ListaMovimientos", listaMovimientos);
			

			String pagina = request.getParameter("pagina");
			request.setAttribute("pagina",pagina);

			
			float totalPositivo = 0;
			float totalNegativo = 0;
			int transferencias = 0;
			int altaDeCuentas = 0;
			int altaDePrestamos = 0;
			int pagoDePrestamos = 0;
			int movimientos = 0;
			
			for (Movimiento m: listaMovimientos) {
				
				movimientos += 1;
				if(m.getImporte() > 0) {
					totalPositivo += m.getImporte();
				}
				else {
					totalNegativo += m.getImporte();
				}
				
				if (m.getTipoDeMovimiento().equals("Transferencia")) {
					transferencias += 1;
				}
				if (m.getTipoDeMovimiento().equals("Alta de Cuenta")) {
					altaDeCuentas += 1;
				}
				if (m.getTipoDeMovimiento().equals("Alta de Prestamo")) {
					altaDePrestamos += 1;
				}
				if (m.getTipoDeMovimiento().equals("Pago de Prestamo")) {
					pagoDePrestamos += 1;
				}
				
			}
			
			request.setAttribute("TotalPositivo", totalPositivo);
			request.setAttribute("TotalNegativo", totalNegativo);
			request.setAttribute("Transferencias", transferencias);
			request.setAttribute("AltaDeCuentas", altaDeCuentas);
			request.setAttribute("AltaDePrestamos", altaDePrestamos);
			request.setAttribute("PagoDePrestamos", pagoDePrestamos);
			request.setAttribute("Movimientos", movimientos);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Reportes.jsp");
			rd.forward(request, response);
	        
	        return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		
		
			
		
			
			
		
		
		
	}

}
