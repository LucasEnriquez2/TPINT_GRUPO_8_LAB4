package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuenta;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ServletDatos
 */
@WebServlet("/ServletDatos")
public class ServletDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("Listar")!=null) {
			HttpSession usuarioSession = request.getSession();
    		ClienteNegocioImpl cliente = new ClienteNegocioImpl();
    		ArrayList<Cliente> listaCliente = (ArrayList<Cliente>) cliente.ListarCliente((String) usuarioSession.getAttribute("username"));
    		request.setAttribute("ListaCliente", listaCliente);
    		
    		CuentaNegocioImpl negC = new CuentaNegocioImpl();
    		List<Cuenta> listaCuentas = negC.ListarCuentaPorUsuario((String) usuarioSession.getAttribute("username"));
    		request.setAttribute("ListaCuentas", listaCuentas);
    		
    		RequestDispatcher rd = request.getRequestDispatcher("/DatosClientes.jsp");
    		rd.forward(request, response);
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
