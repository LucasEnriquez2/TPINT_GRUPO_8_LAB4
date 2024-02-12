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

import entidad.Cliente;
import entidad.Movimiento;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;


@WebServlet("/ServletMovimientos")
public class ServletMovimientos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletMovimientos() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        //int nroCliente = Integer.parseInt(request.getParameter("NdeCliente"));
    	
    	/*
    	LINEA DE ARRIBA COMENTADA PARA QUE NO DE ERROR Y EL 1 QUE PASA POR PARAMETRO EN LA LINEA 33 
    	ESTA PARA PROBAR QUE FUNCIONA 
    	SOLO HAY QUE HACERLE LLEGAR EL NRO DE CLIENTE LOGEADO Y REEMPLAZARLO CON EL 1
    	*/
    	
        MovimientoNegocioImpl movimiento = new MovimientoNegocioImpl();
        List<Movimiento> listaMovimientos = movimiento.ListarMovimientos(1);

        request.setAttribute("ListaMovimientos", listaMovimientos);
        RequestDispatcher rd = request.getRequestDispatcher("/Movimientos.jsp");
        rd.forward(request, response);
    }
        
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    	
}

}
