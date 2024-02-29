package servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import negocioImpl.MovimientoNegocioImpl;

/**
 * Servlet implementation class ServletTransferir
 */
@WebServlet("/ServletTransferir")
public class ServletTransferir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferir() {
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
    	
    	
    	int error = 0;
    	if(request.getParameter("btnConfirmar")!=null)
		{
			
			String NroDeCuenta = request.getParameter("ncuenta");
			String Monto = request.getParameter("monto");
			String Detalle = request.getParameter("detalle");
			String Cbu = request.getParameter("cbu");
		
			CuentaNegocioImpl negM = new CuentaNegocioImpl();
			MovimientoNegocioImpl negMov = new MovimientoNegocioImpl();

			// CBU = 1  El CBU no existe. Por favor, verifique el CBU ingresado.
			// MONTO = 2   No posee este monto actualmente para realizar una transferencia y/o el monto ingresado debe ser mayor a 0
			// TRANSFERENCIA EXITOSA = 3
		
	        // Verificar la respuesta del usuario
		
        	if (!negM.ExisteCbu(Cbu)) {
    			
    			error = 1;
    			request.setAttribute("error", error);
    			RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");
                rd.forward(request, response);
    			return;
    			
    		}
    		
    		if(!negM.PuedeTransferir(Float.parseFloat(Monto), NroDeCuenta)){
    			error = 2;
    			request.setAttribute("error", error);
    			RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");
                rd.forward(request, response);
    			return;
    		}
    		
    		error = 3;
    		request.setAttribute("error", error);		
    		negMov.CrearMovimiento(NroDeCuenta, Cbu, Monto, Detalle);
    		RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");
            rd.forward(request, response);
            return;
		}
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");
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
