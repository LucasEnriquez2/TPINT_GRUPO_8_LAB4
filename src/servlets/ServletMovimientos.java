package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Movimiento;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;


@WebServlet("/ServletMovimientos")
public class ServletMovimientos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletMovimientos() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	
    	HttpSession usuarioSession = request.getSession();
    	ClienteNegocioImpl neg = new ClienteNegocioImpl();
		int numcliente = neg.ObtenerNdeCliente((String) usuarioSession.getAttribute("username"));
		
    	
    	
        MovimientoNegocioImpl movimiento = new MovimientoNegocioImpl();
        List<Movimiento> listaMovimientos = movimiento.ListarMovimientos(numcliente);

        request.setAttribute("ListaMovimientos", listaMovimientos);
        RequestDispatcher rd = request.getRequestDispatcher("/Movimientos.jsp");
        rd.forward(request, response);
    }
        
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    	
}

}
