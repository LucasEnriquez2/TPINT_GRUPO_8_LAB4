package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoImpl.DaoCuentaImpl;
import daoImpl.DaoClienteImpl;
import entidad.Cliente;
import entidad.Cuenta;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ServletAltaCuenta
 */
@WebServlet("/ServletAltaCuenta")
public class ServletAltaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAltaCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession sesion = request.getSession();
		
		if(sesion.getAttribute("esAdmin")!=null) {
			if((int)sesion.getAttribute("esAdmin")==1) {
		
		if(request.getParameter("btnCrearCuenta")!=null) {
			ClienteNegocioImpl cliente = new ClienteNegocioImpl();
			ArrayList<Cliente> ListarClientes = (ArrayList<Cliente>)cliente.ListarClientes();
			request.setAttribute("ListarClientes", ListarClientes);
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarCuenta.jsp");
			rd.forward(request, response);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		
		
		if(sesion.getAttribute("esAdmin")!=null) {
			if((int)sesion.getAttribute("esAdmin")==1) {
				
				if(request.getParameter("btnVolver")!=null) {
					CuentaNegocioImpl cuenta = new CuentaNegocioImpl();
					ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) cuenta.ListarCuentas();
					request.setAttribute("ListaCuentas", listaCuentas);
					RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
					rd.forward(request, response);
						}
				
				if(request.getParameter("btnCrearCuenta")!=null) {
					ClienteNegocioImpl cliente = new ClienteNegocioImpl();
					ArrayList<Cliente> ListarClientes = (ArrayList<Cliente>)cliente.ListarClientes();
					request.setAttribute("ListarClientes", ListarClientes);
					RequestDispatcher rd = request.getRequestDispatcher("/AgregarCuenta.jsp");
					rd.forward(request, response);
						}
				/*if(request.getParameter("btnVerificar")!=null) {
					CuentaNegocioImpl cuenta = new CuentaNegocioImpl();
					int nroDeCliente = Integer.parseInt(request.getParameter("txtNroCliente"));	    
					ArrayList<Cuenta> ListarCuenta = (ArrayList<Cuenta>)cuenta.ListarCuenta(nroDeCliente);
					request.setAttribute("ListarCuenta", ListarCuenta);
					RequestDispatcher rd = request.getRequestDispatcher("/AgregarCuenta.jsp");
					rd.forward(request, response);					
				}*/
				
				if(request.getParameter("btnVerificar")!=null) {
					CuentaNegocioImpl cuenta = new CuentaNegocioImpl();
					int nroDeCliente = Integer.parseInt(request.getParameter("SelecCliente"));	    
					ArrayList<Cuenta> ListarCuenta = (ArrayList<Cuenta>)cuenta.ListarCuenta(nroDeCliente);
					request.setAttribute("ListarCuenta", ListarCuenta);
					ClienteNegocioImpl cliente = new ClienteNegocioImpl();
					ArrayList<Cliente> ListarClientes = (ArrayList<Cliente>)cliente.ListarClientes();
					request.setAttribute("ListarClientes", ListarClientes);
					RequestDispatcher rd = request.getRequestDispatcher("/AgregarCuenta.jsp");
					rd.forward(request, response);					
				}
				
		int filas=0;
		int cont=0;
		int error=0;
		if(request.getParameter("btnAceptar")!=null)
		{
			Cuenta cuenta  =  new Cuenta();
			CuentaNegocioImpl cuentas = new CuentaNegocioImpl();
			int nroDeCliente = Integer.parseInt(request.getParameter("SelecCliente"));	 
			
			ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) cuentas.ListarCuenta(nroDeCliente);
		
			for(Cuenta c :listaCuentas) {
				c.getNroCuenta();
				cont++; }
			if(cont>=3) {
				error=1;
				request.setAttribute("error",error);
				ArrayList<Cuenta> ListarCuenta = (ArrayList<Cuenta>)cuentas.ListarCuenta(nroDeCliente);
				request.setAttribute("ListarCuenta", ListarCuenta);
				ClienteNegocioImpl cliente = new ClienteNegocioImpl();
				ArrayList<Cliente> ListarClientes = (ArrayList<Cliente>)cliente.ListarClientes();
				request.setAttribute("ListarClientes", ListarClientes);
				RequestDispatcher rd = request.getRequestDispatcher("/AgregarCuenta.jsp");
				rd.forward(request, response);	
			}
			else {
				error=2;
				request.setAttribute("error",error);
		    
				cuenta.setNroDeCliente(nroDeCliente);
				String valorCuenta=request.getParameter("tipocuenta");
				cuenta.setTipoDeCuenta(valorCuenta);
		
			String fechaString = request.getParameter("txtFecha");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date sqlFecha = null;
			try {
			    java.util.Date fecha = sdf.parse(fechaString);
			    sqlFecha = new java.sql.Date(fecha.getTime());
			} catch (ParseException e) {
			    e.printStackTrace();
			}
				cuenta.setFechaCreacion(sqlFecha);
				cuenta.setCbu(request.getParameter("txtCBU"));
			
		    String saldoString = request.getParameter("txtSaldo");
		    float saldo = Float.parseFloat(saldoString);
		    	cuenta.setSaldo(saldo);
		    	cuenta.setEstado(1);
			
			CuentaNegocioImpl c = new CuentaNegocioImpl();
			filas=c.Agregar(cuenta);
			DaoCuentaImpl c1 = new DaoCuentaImpl();
	        int UltimoNro = c1.obtenerUltimoNroCuenta() + 1;
	        request.setAttribute("UltimoNro", UltimoNro);
			
			ArrayList<Cuenta> listarCuentas = (ArrayList<Cuenta>) c.ListarCuentas();
    		request.setAttribute("ListaCuentas", listarCuentas);
    		
			request.setAttribute("cantFilas", filas);
			RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");   
	        rd.forward(request, response);  
					}
				}
			}
		}
		
	}

}
