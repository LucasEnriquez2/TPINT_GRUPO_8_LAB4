package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Movimiento;
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		int minimo = Integer.parseInt(request.getParameter("min"));
	    int maximo = Integer.parseInt(request.getParameter("max"));
	    
		if(minimo != maximo && maximo > minimo) {
			
			MovimientoNegocioImpl movimiento = new MovimientoNegocioImpl();
			ArrayList<Movimiento> listaMovimientos = (ArrayList<Movimiento>) movimiento.ListarMovimientoParametrizados(minimo, maximo);
			request.setAttribute("ListaMovimientos", listaMovimientos);
			RequestDispatcher rd = request.getRequestDispatcher("/Reportes.jsp");
			rd.forward(request, response);
			
		}
		
	}

}
