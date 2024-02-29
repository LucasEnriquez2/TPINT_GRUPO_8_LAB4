package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
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
import entidad.Cuenta;
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
    	
		//OBTENER SESSION Y USUARIO
    	HttpSession usuarioSession = request.getSession();
    	ClienteNegocioImpl neg = new ClienteNegocioImpl();
		int numcliente = neg.ObtenerNdeCliente((String) usuarioSession.getAttribute("username"));
    	
		//CUENTAS
		CuentaNegocioImpl negC = new CuentaNegocioImpl();
		List<Cuenta> listaCuentas = negC.ListarCuentaPorUsuario((String) usuarioSession.getAttribute("username"));
		request.setAttribute("ListaCuentas", listaCuentas);
		
		
		// MOVIMIENTOS
		MovimientoNegocioImpl mov = new MovimientoNegocioImpl();
	    List<Movimiento> listaMovimientos = mov.ListarMovimientos(numcliente);
	    request.setAttribute("ListaMovimientos", listaMovimientos);
	    
	    //LOGICA
	    	
	    String radiovalue = request.getParameter("radioCuentas");
	    
	    if(radiovalue==null) {
	    	radiovalue = "todas";
	    }
	    
	    
	    request.setAttribute("RadioValue", radiovalue);
	    
	    if(request.getParameter("BuscarFiltro") != null) {

	    if (!request.getParameter("idMov").isEmpty()) {
	    	int i = Integer.parseInt(request.getParameter("idMov"));
	        Iterator<Movimiento> iterator = listaMovimientos.iterator();
	        while (iterator.hasNext()) {
	        	Movimiento mov1 = iterator.next();
	        	if (mov1.getIdMovimiento() != i) {
	            	iterator.remove();
	        	}
	            
	        }
	    }
	    
	    if (!"Seleccione una opcion".equals(request.getParameter("tipo"))) {
	        Iterator<Movimiento> iterator = listaMovimientos.iterator();
	        String tipo = request.getParameter("tipo");
	        while (iterator.hasNext()) {
	        	Movimiento m = iterator.next();
	            if (!m.getTipoDeMovimiento().equals(tipo)) {
	                iterator.remove();
	            }
	        }
	    }
	    
	    if (!request.getParameter("txtFechaDesde").isEmpty() && !request.getParameter("txtFechaHasta").isEmpty()) {
	        Iterator<Movimiento> iterator = listaMovimientos.iterator();
	        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date fechaDesde = null;
	        java.util.Date fechaHasta = null;

	        try {
	            fechaDesde = dateFormat.parse(request.getParameter("txtFechaDesde"));
	            fechaHasta = dateFormat.parse(request.getParameter("txtFechaHasta"));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        

	        while (iterator.hasNext()) {
	            Movimiento mov1 = iterator.next();
	            Date fechaNac = mov1.getFecha();
	 
	            if (fechaNac.after(fechaHasta) || fechaNac.before(fechaDesde)) {
	                iterator.remove();
	            }
	        }
	    }
	    
	    if (!request.getParameter("minimo").isEmpty() && !request.getParameter("maximo").isEmpty()) {
	        Iterator<Movimiento> iterator = listaMovimientos.iterator();

	        while (iterator.hasNext()) {
	            Movimiento m = iterator.next();
	            float saldo = m.getImporte();
	            int minimo = Integer.parseInt(request.getParameter("minimo"));
	            int maximo = Integer.parseInt(request.getParameter("maximo"));
	 
	            if (saldo<minimo || saldo>maximo) {
	                iterator.remove();
	            }
	        }
	    }
	    
	    }
	    
	    if(request.getParameter("Limpiar")!=null) {
			MovimientoNegocioImpl mov1 = new MovimientoNegocioImpl();
		    List<Movimiento> listaMovimientos1 = mov1.ListarMovimientos(numcliente);
		    request.setAttribute("ListaMovimientos", listaMovimientos1);
	        RequestDispatcher rd = request.getRequestDispatcher("/Movimientos.jsp");
	        rd.forward(request, response);
		}
	    
	    
        RequestDispatcher rd = request.getRequestDispatcher("/Movimientos.jsp");
        rd.forward(request, response);
        
	    
    }
        
    


	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
        
   

	}
}
