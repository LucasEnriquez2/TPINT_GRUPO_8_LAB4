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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.DaoClienteImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;


@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ServletClientes() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("pagina")!=null) {
			String pagina = request.getParameter("pagina");
			request.setAttribute("ListaClientes", null);
			ClienteNegocioImpl cliente = new ClienteNegocioImpl();
			ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) cliente.ListarClientes();
			request.setAttribute("ListaClientes", listaClientes);
			request.setAttribute("pagina",pagina);
			RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("Pagina") != null) {
    		DaoClienteImpl cliDao = new DaoClienteImpl();
	        int UltimoNro = cliDao.obtenerUltimoNroCliente() + 1;
	        request.setAttribute("UltimoNro", UltimoNro);
	        
	        ClienteNegocioImpl cliente = new ClienteNegocioImpl();
    		ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) cliente.ListarClientes();
    		request.setAttribute("ListaClientes", listaClientes);
	      
    		RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");
	        rd.forward(request, response);
	        }
		
		if(request.getParameter("Listar")!=null) {
    		ClienteNegocioImpl cliente = new ClienteNegocioImpl();
    		ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) cliente.ListarClientes();
    		request.setAttribute("ListaClientes", listaClientes);
    		RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");
    		rd.forward(request, response);
		}
		if(request.getParameter("Fila")!=null) {
			ClienteNegocioImpl cliente = new ClienteNegocioImpl();
			ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) cliente.ListarClientes();
			for(Cliente c :listaClientes) {
				if(Integer.valueOf(request.getParameter("Fila"))==c.getNdeCliente()) {

					request.setAttribute("ClienteModificar", c);
				}
			}

			RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("FilaE")!=null) {
			ClienteNegocioImpl cliente = new ClienteNegocioImpl();
			ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) cliente.ListarClientes();
			for(Cliente c :listaClientes) {
				if(Integer.valueOf(request.getParameter("FilaE"))==c.getNdeCliente()) {

					request.setAttribute("ClienteEliminar", c);
				}
			}

			RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("Eliminar")!=null)
		{
			ClienteNegocioImpl cliente = new ClienteNegocioImpl();
			Cliente c= new Cliente();
			
			int x=cliente.borrar(Integer.valueOf(request.getParameter("NroCliente")));
			if(x==1) {
				RequestDispatcher rd = request.getRequestDispatcher("/ServletClientes?Listar=1");
				rd.forward(request, response);
			}
			
		}
		
		if(request.getParameter("Modificar")!=null) {
			int modif = 0;
			
			ClienteNegocioImpl cliente = new ClienteNegocioImpl();
			Cliente c= new Cliente();
			c.setNdeCliente(Integer.valueOf(request.getParameter("NroCliente")));
			c.setDNI(request.getParameter("DNI"));
			c.setCUIL(request.getParameter("CUIL"));
			c.setNombre(request.getParameter("Nombre"));
			c.setApellido(request.getParameter("Apellido"));
			c.setSexo(request.getParameter("Sexo"));
			c.setNacionalidad(request.getParameter("Nacionalidad"));
			c.setFechaDeNacimiento(Date.valueOf(request.getParameter("FechaDeNacimiento")));
			c.setDireccion(request.getParameter("Direccion"));
			c.setLocalidad(request.getParameter("Localidad"));
			c.setProvincia(request.getParameter("Provincia"));
			c.setMail(request.getParameter("Mail"));
			c.setTelefono(request.getParameter("Telefono"));
			c.setUsuario(request.getParameter("Usuario"));
			c.setContrasenia(request.getParameter("Contrasenia"));
			
			if (request.getParameter("CUIL").equals("")) modif = 1;
			if (request.getParameter("Nombre").equals("")) modif = 1;
			if (request.getParameter("Apellido").equals("")) modif = 1;
			if (request.getParameter("Nacionalidad").equals("")) modif = 1;
			if (request.getParameter("Direccion").equals("")) modif = 1;
			if (request.getParameter("Localidad").equals("")) modif = 1;
			if (request.getParameter("Provincia").equals("")) modif = 1;
			if (request.getParameter("Mail").equals("")) modif = 1;
			if (request.getParameter("Telefono").equals("")) modif = 1;
			if (request.getParameter("Contrasenia").equals("")) modif = 1;
			
			
			if (modif == 1) {
				ClienteNegocioImpl lista = new ClienteNegocioImpl();
	    		ArrayList<Cliente> listaClientes2 = (ArrayList<Cliente>) lista.ListarClientes();
	    		request.setAttribute("ListaClientes", listaClientes2);
				request.setAttribute("modif", modif);
				RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");   
				
		        rd.forward(request, response);
		        
		        return;
			}
			
			int x=cliente.ModificarCliente(c);
			if(x==1) {
			RequestDispatcher rd = request.getRequestDispatcher("/ServletClientes?Listar=1");
			rd.forward(request, response);
			}else if(cliente.ModificarCliente(c)==0) {
				RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");
				rd.forward(request, response);}
		}
		if(request.getParameter("Cancelar")!=null) {
			RequestDispatcher rd = request.getRequestDispatcher("/ServletClientes?Listar=1");
			rd.forward(request, response);
		}
		
		/*int filas=0;
		if(request.getParameter("btnAceptar")!=null)
		{
			Cliente cliente  =  new Cliente();
			Usuario usuario  =  new Usuario();
			ClienteNegocioImpl c = new ClienteNegocioImpl();
			
			if (request.getParameter("txtContrasenia") != request.getParameter("txtContrasenia2")) {
				//request.setAttribute("txtContrasenia", "");
				//request.setAttribute("txtContrasenia2", "");
				ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) c.ListarClientes();
	    		request.setAttribute("ListaClientes", listaClientes);
	    		filas = 2;
				request.setAttribute("cantFilas", filas);
				RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");   
				
		        rd.forward(request, response);
				
			}
			
		    
			cliente.setDNI(request.getParameter("txtDNI"));
			usuario.setDni(request.getParameter("txtDNI"));
			cliente.setCUIL(request.getParameter("txtCUIL"));
			cliente.setNombre(request.getParameter("txtNombre"));
			cliente.setApellido(request.getParameter("txtApellido"));
			cliente.setSexo(request.getParameter("Sexo"));
			cliente.setNacionalidad(request.getParameter("txtNacionalidad"));
		
			String fechaString = request.getParameter("txtFecha");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date sqlFecha = null;
			try {
			    java.util.Date fecha = sdf.parse(fechaString);
			    sqlFecha = new java.sql.Date(fecha.getTime());
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			cliente.setFechaDeNacimiento(sqlFecha);
			
			cliente.setDireccion(request.getParameter("txtDireccion"));
			cliente.setLocalidad(request.getParameter("txtLocalidad"));
			cliente.setProvincia(request.getParameter("txtProvincia"));
			cliente.setMail(request.getParameter("txtEmail"));
			cliente.setTelefono(request.getParameter("txtTelefono"));
			usuario.setUsuario(request.getParameter("txtUsuario"));
			usuario.setContrasenia(request.getParameter("txtContrasenia"));

			int e=1;
			String estado = Integer.toString(e);
			cliente.setEstado(estado);
			
			
			filas=c.Agregar(cliente, usuario);
			DaoClienteImpl c1 = new DaoClienteImpl();
	        int UltimoNro = c1.obtenerUltimoNroCliente() + 1;
	        request.setAttribute("UltimoNro", UltimoNro);
			
	        ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) c.ListarClientes();
    		request.setAttribute("ListaClientes", listaClientes);
    		
			request.setAttribute("cantFilas", filas);
			RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");   
			
	        rd.forward(request, response);  
	        
		}*/
		
		if(request.getParameter("Buscar") != null) {
		    ArrayList<Cliente> listaClientes;
		    if(request.getAttribute("ListaClientes") != null) {
		        listaClientes = (ArrayList<Cliente>) request.getAttribute("ListaClientes");
		    } else {
		        ClienteNegocioImpl cliente = new ClienteNegocioImpl();
		        listaClientes = (ArrayList<Cliente>) cliente.ListarClientes();
		    }
		    
		    if(request.getParameter("NdeCliente") != "") {
		    	Iterator<Cliente> iterator = listaClientes.iterator();
		    	int i = Integer.parseInt(request.getParameter("NdeCliente"));
		    	while (iterator.hasNext()) {
		        	Cliente c = iterator.next();
		        	if (c.getNdeCliente() != i) {
		            	iterator.remove();
		        	}
		    	}
		    }
		    
		    if (!request.getParameter("nombre").isEmpty()) {
		        String nombre = request.getParameter("nombre").toLowerCase();;
		        Iterator<Cliente> iterator = listaClientes.iterator();
		        while (iterator.hasNext()) {
		            Cliente cliente = iterator.next();
		            String nombreCliente = cliente.getNombre().toLowerCase();
		            if (!nombreCliente.contains(nombre)) {
		                iterator.remove();
		            }
		            
		        }
		    }
		    
		    if (!request.getParameter("apellido").isEmpty()) {
		        String nombre = request.getParameter("apellido").toLowerCase();;
		        Iterator<Cliente> iterator = listaClientes.iterator();
		        while (iterator.hasNext()) {
		            Cliente cliente = iterator.next();
		            String apellidoCliente = cliente.getApellido().toLowerCase();
		            if (!apellidoCliente.contains(nombre)) {
		                iterator.remove();
		            }
		            
		        }
		    }
		    
		    if (!"Seleccione una opcion".equals(request.getParameter("sexo"))) {
		        Iterator<Cliente> iterator = listaClientes.iterator();
		        String sexo = request.getParameter("sexo");
		        while (iterator.hasNext()) {
		        	Cliente c = iterator.next();
		            if (!c.getSexo().equals(sexo)) {
		                iterator.remove();
		            }
		        }
		    }
		    
		    if (!"Seleccione una opcion".equals(request.getParameter("nac"))) {
		        Iterator<Cliente> iterator = listaClientes.iterator();
		        String nac = request.getParameter("nac");
		        while (iterator.hasNext()) {
		        	Cliente c = iterator.next();
		            if (!c.getNacionalidad().equals(nac)) {
		                iterator.remove();
		            }
		        }
		    }
		    
		    if (!request.getParameter("txtFechaDesde").isEmpty() && !request.getParameter("txtFechaHasta").isEmpty()) {
		        Iterator<Cliente> iterator = listaClientes.iterator();
		        
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
		            Cliente c = iterator.next();
		            Date fechaNac = c.getFechaDeNacimiento();
		 
		            if (fechaNac.after(fechaHasta) || fechaNac.before(fechaDesde)) {
		                iterator.remove();
		            }
		        }
		    }
		    
		    if (!"Seleccione una opcion".equals(request.getParameter("loc"))) {
		        Iterator<Cliente> iterator = listaClientes.iterator();
		        String localidad = request.getParameter("loc");
		        while (iterator.hasNext()) {
		        	Cliente c = iterator.next();
		            if (!c.getLocalidad().equals(localidad)) {
		                iterator.remove();
		            }
		        }
		    }
		    
		    if (!"Seleccione una opcion".equals(request.getParameter("prov"))) {
		        Iterator<Cliente> iterator = listaClientes.iterator();
		        String provincia = request.getParameter("prov");
		        while (iterator.hasNext()) {
		        	Cliente c = iterator.next();
		            if (!c.getProvincia().equals(provincia)) {
		                iterator.remove();
		            }
		        }
		    }
		    
		    request.setAttribute("ListaClientes", listaClientes);
		    RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");
		    rd.forward(request, response);
		}

		if(request.getParameter("Limpiar")!=null) {
			request.setAttribute("ListaClientes", null);
			ClienteNegocioImpl cliente = new ClienteNegocioImpl();
			ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) cliente.ListarClientes();
			request.setAttribute("ListaClientes", listaClientes);
			RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");
			rd.forward(request, response);
		}
	}
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPost(request, response);
		
		int filas=0;
		if(request.getParameter("btnAceptar")!=null)
		{
			Cliente cliente  =  new Cliente();
			Usuario usuario  =  new Usuario();
			ClienteNegocioImpl c = new ClienteNegocioImpl();
			
			
			
			/*int nroDeCliente = Integer.parseInt(request.getParameter("txtNroCliente"));	    
			cliente.setNdeCliente(nroDeCliente);*/
		    
			cliente.setDNI(request.getParameter("txtDNI"));
			usuario.setDni(request.getParameter("txtDNI"));
			cliente.setCUIL(request.getParameter("txtCUIL"));
			cliente.setNombre(request.getParameter("txtNombre"));
			cliente.setApellido(request.getParameter("txtApellido"));
			cliente.setSexo(request.getParameter("Sexo"));
			cliente.setNacionalidad(request.getParameter("txtNacionalidad"));
		
			String fechaString = request.getParameter("txtFecha");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date sqlFecha = null;
			try {
			    java.util.Date fecha = sdf.parse(fechaString);
			    sqlFecha = new java.sql.Date(fecha.getTime());
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			cliente.setFechaDeNacimiento(sqlFecha);
			
			cliente.setDireccion(request.getParameter("txtDireccion"));
			cliente.setLocalidad(request.getParameter("txtLocalidad"));
			cliente.setProvincia(request.getParameter("txtProvincia"));
			cliente.setMail(request.getParameter("txtEmail"));
			cliente.setTelefono(request.getParameter("txtTelefono"));
			cliente.setUsuario(request.getParameter("txtUsuario"));
			usuario.setUsuario(request.getParameter("txtUsuario"));
			usuario.setContrasenia(request.getParameter("txtContrasenia"));

			
			ClienteNegocioImpl lista = new ClienteNegocioImpl();
    		ArrayList<Cliente> listaClientes2 = (ArrayList<Cliente>) lista.ListarClientes();
    		
    		for (Cliente z: listaClientes2) {
    			if (cliente.getDNI().equals(z.getDNI())) {
    				filas = 2;
    				
    			}
    			if (cliente.getUsuario().equals(z.getUsuario())) {
    				filas = 3;
    			}
    		}
			
			
			if (filas > 1) {
	    		request.setAttribute("ListaClientes", listaClientes2);
				request.setAttribute("cantFilas", filas);
				RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");   
				
		        rd.forward(request, response);
		        
		        return;
			}
			
			int e=1;
			String estado = Integer.toString(e);
			cliente.setEstado(estado);
			
			
			filas=c.Agregar(cliente, usuario);
			DaoClienteImpl c1 = new DaoClienteImpl();
	        int UltimoNro = c1.obtenerUltimoNroCliente() + 1;
	        request.setAttribute("UltimoNro", UltimoNro);
			
	        ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) c.ListarClientes();
    		request.setAttribute("ListaClientes", listaClientes);
    		
			request.setAttribute("cantFilas", filas);
			RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");   
			
	        rd.forward(request, response);  
	        return;
	        
		}
		
		/*if(request.getParameter("btnEliminar")!=null)
		{
			int nro = Integer.parseInt(request.getParameter("NroCliente").toString()) ;
			ClienteNegocioImpl cliente = new ClienteNegocioImpl();
			cliente.borrar(nro);
			
			ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) cliente.ListarClientes();
    		request.setAttribute("ListaClientes", listaClientes);
    		
    		RequestDispatcher rd = request.getRequestDispatcher("/Clientes.jsp");
    		rd.forward(request, response);
			
		}*/
		
		
	}

}

