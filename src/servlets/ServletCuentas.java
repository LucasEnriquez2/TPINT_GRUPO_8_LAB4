package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoImpl.DaoCuentaImpl;
import javax.swing.JOptionPane;

import entidad.Cliente;
import entidad.Cuenta;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ServletClientes
 */
@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCuentas() {
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
				
				if(request.getParameter("page")!=null) {
					String page=request.getParameter("page");
					request.setAttribute("ListaCuentas", null);
					CuentaNegocioImpl cuenta = new CuentaNegocioImpl();
					ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) cuenta.ListarCuentas();
					request.setAttribute("ListaCuentas", listaCuentas);
					request.setAttribute("page",page);
					RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
					rd.forward(request, response);
				}
				
				if(request.getParameter("Listar")!=null) {
					request.setAttribute("ListaCuentas", null);
					CuentaNegocioImpl cuenta = new CuentaNegocioImpl();
					ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) cuenta.ListarCuentas();
					request.setAttribute("ListaCuentas", listaCuentas);
					RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
					rd.forward(request, response);
				}
				
				if(request.getParameter("btnModificar")!=null) {
					CuentaNegocioImpl cuenta = new CuentaNegocioImpl();
					ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) cuenta.ListarCuentas();
					for(Cuenta c :listaCuentas) {
						if(request.getParameter("NroCuenta")!=null) {
						if(Integer.valueOf(request.getParameter("NroCuenta"))==c.getNroCuenta()) {

							request.setAttribute("CuentaModificar", c);
							}
						}else {
							JOptionPane.showMessageDialog(null, "null");
						}
					}

					RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
					rd.forward(request, response);
				}
				
				if(request.getParameter("Modificar")!=null) {
					CuentaNegocioImpl cuenta = new CuentaNegocioImpl();
					Cuenta c= new Cuenta();
					c.setNroCuenta(Integer.parseInt(request.getParameter("NroCuenta")));
					c.setTipoDeCuenta(request.getParameter("tipoCuenta"));
					c.setCbu(request.getParameter("cbu"));
					Float f;
					try {
						f = Float.valueOf(request.getParameter("saldo"));
						c.setSaldo(f);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(cuenta.ModificarCuenta(c.getNroCuenta(),c.getTipoDeCuenta(),c.getCbu(),c.getSaldo())==1) {
					RequestDispatcher rd = request.getRequestDispatcher("/ServletCuentas?Listar=1");
					rd.forward(request, response);
					}else if(cuenta.ModificarCuenta(c.getNroCuenta(),c.getTipoDeCuenta(),c.getCbu(),c.getSaldo())==0) {
						RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
						rd.forward(request, response);}
				}
				
				if(request.getParameter("Eliminar")!=null) {
					if(request.getParameter("Eliminar")!=null) {
						CuentaNegocioImpl cuenta = new CuentaNegocioImpl();
						ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) cuenta.ListarCuentas();
						for(Cuenta c :listaCuentas) {
							if(Integer.valueOf(request.getParameter("NroCuenta"))==c.getNroCuenta()) {

								request.setAttribute("CuentaEliminar", c);
							}
						}

						RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
						rd.forward(request, response);
					}
				}
				
				if(request.getParameter("Cancelar")!=null) {
					RequestDispatcher rd = request.getRequestDispatcher("/ServletCuentas?Listar=1");
					rd.forward(request, response);
				}
				
				if(request.getParameter("Buscar") != null) {
				    ArrayList<Cuenta> listaCuentas;
				    if(request.getAttribute("ListaCuentas") != null) {
				        listaCuentas = (ArrayList<Cuenta>) request.getAttribute("ListaCuentas");
				    } else {
				        CuentaNegocioImpl cuenta = new CuentaNegocioImpl();
				        listaCuentas = (ArrayList<Cuenta>) cuenta.ListarCuentas();
				    }
				    
				    if(request.getParameter("nroDeCliente") != "") {
				    	Iterator<Cuenta> iterator = listaCuentas.iterator();
				    	int i = Integer.parseInt(request.getParameter("nroDeCliente"));
				    	while (iterator.hasNext()) {
				    		Cuenta c = iterator.next();
				    		if (c.getNroDeCliente() != i) {
				    			iterator.remove();
				    		}
				    	}
				    }
				    
				    if(request.getParameter("nroDeCuenta") != "") {
				    	Iterator<Cuenta> iterator = listaCuentas.iterator();
				    	int cue = Integer.parseInt(request.getParameter("nroDeCuenta"));
				    	while (iterator.hasNext()) {
				    		Cuenta c = iterator.next();
				    		if (c.getNroCuenta() != cue) {
				    			iterator.remove();
				    		}
				    	}
				    }
				    
			    	
				    if (!"Seleccione una opcion".equals(request.getParameter("tipo"))) {
				        Iterator<Cuenta> iterator = listaCuentas.iterator();
				        String tipo = request.getParameter("tipo");
				        while (iterator.hasNext()) {
				            Cuenta c = iterator.next();
				            if (!c.getTipoDeCuenta().equals(tipo)) {
				                iterator.remove();
				            }
				        }
				    }
				    
				    if (!request.getParameter("txtFechaDesde").isEmpty() && !request.getParameter("txtFechaHasta").isEmpty()) {
				        Iterator<Cuenta> iterator = listaCuentas.iterator();
				        
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
				            Cuenta c = iterator.next();
				            Date fechaCreacion = c.getFechaCreacion();
				 
				            if (fechaCreacion.after(fechaHasta) || fechaCreacion.before(fechaDesde)) {
				                iterator.remove();
				            }
				        }
				    }
				    
				    if (!request.getParameter("minimo").isEmpty() && !request.getParameter("maximo").isEmpty()) {
				        Iterator<Cuenta> iterator = listaCuentas.iterator();

				        while (iterator.hasNext()) {
				            Cuenta c = iterator.next();
				            float saldo = c.getSaldo();
				            int minimo = Integer.parseInt(request.getParameter("minimo"));
				            int maximo = Integer.parseInt(request.getParameter("maximo"));
				 
				            if (saldo<minimo || saldo>maximo) {
				                iterator.remove();
				            }
				        }
				    }
				    
				    request.setAttribute("ListaCuentas", listaCuentas);
				    RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
				    rd.forward(request, response);
				    
				    }
		
				if(request.getParameter("Limpiar")!=null) {
					request.setAttribute("ListaCuentas", null);
					CuentaNegocioImpl cuenta = new CuentaNegocioImpl();
					ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) cuenta.ListarCuentas();
					request.setAttribute("ListaCuentas", listaCuentas);
					RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
					rd.forward(request, response);
				}
		
	  }else{
		  RequestDispatcher rd = request.getRequestDispatcher("/Inicio.jsp");
		  rd.forward(request, response);
	  }}else {
		  RequestDispatcher rd = request.getRequestDispatcher("/Inicio.jsp");
		  rd.forward(request, response);
	  }
		
		int filas=0;
		if(request.getParameter("btnAceptar")!=null)
		{
			Cuenta cuenta  =  new Cuenta();
			int nroDeCliente = Integer.parseInt(request.getParameter("txtNroCliente"));	    
			cuenta.setNroDeCliente(nroDeCliente);
		    
			cuenta.setTipoDeCuenta(request.getParameter("txtTipo"));
		
			String fechaString = request.getParameter("txtFecha");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date sqlFecha = null;
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
			
			ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) c.ListarCuentas();
    		request.setAttribute("ListaCuentas", listaCuentas);
    		
			request.setAttribute("cantFilas", filas);
			RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");   
	        rd.forward(request, response);  
			
		}
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		if(request.getParameter("btnCrearCuenta")!=null) {
			ClienteNegocioImpl cliente = new ClienteNegocioImpl();
			ArrayList<Cliente> ListarClientes = (ArrayList<Cliente>)cliente.ListarClientes();
			request.setAttribute("ListarClientes", ListarClientes);
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarCuenta.jsp");
			rd.forward(request, response);
			}
		
		HttpSession sesion = request.getSession();
		
		if(request.getParameter("btnEliminar")!=null)
		{
			int nro = Integer.parseInt(sesion.getAttribute("NroCuenta").toString()) ;
			CuentaNegocioImpl cuenta = new CuentaNegocioImpl();
			cuenta.borrar(nro);
			
			ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) cuenta.ListarCuentas();
    		request.setAttribute("ListaCuentas", listaCuentas);
    		
    		RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
    		rd.forward(request, response);
			
		}
	}
}

