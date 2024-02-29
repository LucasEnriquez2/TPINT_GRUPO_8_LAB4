package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoCliente;
import entidad.Cliente;
import entidad.Prestamo;
import entidad.Solicitud;
import entidad.Usuario;

public class DaoClienteImpl implements DaoCliente{
	private static final String traerClientes = "SELECT * FROM cliente=c inner join persona=p on p.Dni = c.Dni inner join usuario=u on p.Dni = u.Dni where c.estado=true";
	private static final String ModificarPersona="UPDATE persona SET Cuil=?, Nombre=?, Apellido=?, Sexo=?, Nacionalidad=?, FechaDeNacimiento=?, Direccion=?, Localidad=?, Provincia=?, Email=?, Telefono=? WHERE Dni=?";
	private static final String ModificarUsuario="UPDATE usuario SET Usuario=?, Contrasenia=? where Dni=?";
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "banco_db";
	
	@Override
	public List<Cliente> ListarClientes() {
		
		PreparedStatement statement;
		ResultSet resulSet;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(traerClientes);
			resulSet = statement.executeQuery();
			while(resulSet.next()) {
				Cliente cliente=new Cliente();
				cliente.setNdeCliente(resulSet.getInt("NdeCliente"));
				cliente.setDNI(resulSet.getString("p.Dni"));
				cliente.setCUIL(resulSet.getString("Cuil"));
				cliente.setApellido(resulSet.getString("Apellido"));
				cliente.setFechaDeNacimiento(resulSet.getDate("FechaDeNacimiento"));
				cliente.setNombre(resulSet.getString("Nombre"));
				cliente.setNacionalidad(resulSet.getString("Nacionalidad"));
				cliente.setDireccion(resulSet.getString("Direccion"));
				cliente.setLocalidad(resulSet.getString("Localidad"));
				cliente.setProvincia(resulSet.getString("Provincia"));
				cliente.setMail(resulSet.getString("Email"));
				cliente.setUsuario(resulSet.getString("Usuario"));
				cliente.setContrasenia(resulSet.getString("Contrasenia"));
				cliente.setTelefono(resulSet.getString("Telefono"));
				cliente.setSexo(resulSet.getString("Sexo"));
				cliente.setEstado(resulSet.getString("Estado"));
				clientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
		 
	}
	
	@Override
	public int borrar(int nro) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Cliente Set Estado=? where NdeCliente=?");
			statement.setBoolean(1, false);
			statement.setInt(2, nro);
			
			if(statement.executeUpdate() > 0) {
				con.commit();
				return 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return 0;
	}

	public int ModificarCliente(Cliente c) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int isUpdateExitoso = 0;
		try
		{
			statement = conexion.prepareStatement(ModificarPersona);
			statement.setString(1, c.getCUIL());
			statement.setString(2, c.getNombre());
			statement.setString(3, c.getApellido());
			statement.setString(4, c.getSexo());
			statement.setString(5, c.getNacionalidad());
			statement.setDate(6, c.getFechaDeNacimiento());
			statement.setString(7, c.getDireccion());
			statement.setString(8, c.getLocalidad());
			statement.setString(9, c.getProvincia());
			statement.setString(10, c.getMail());
			statement.setString(11, c.getTelefono());
			statement.setString(12, c.getDNI());

			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = 1;
			}
			statement = conexion.prepareStatement(ModificarUsuario);
			statement.setString(1, c.getUsuario());
			statement.setString(2, c.getContrasenia());
			statement.setString(3, c.getDNI());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = 1;
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isUpdateExitoso;
	}
	
	public int Agregar(Cliente cliente, Usuario usuario)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int filas=0;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			String query = "Insert into Persona (Dni,Cuil,Nombre,Apellido,Sexo,Nacionalidad,FechaDeNacimiento,Direccion,Localidad,Provincia,Email,Telefono) values"
					+ " ('"+cliente.getDNI()+"','"+cliente.getCUIL()+"','"+cliente.getNombre()+"','"+cliente.getApellido()+"','"+cliente.getSexo()+"','"+cliente.getNacionalidad()+"','"+cliente.getFechaDeNacimiento()+"','"+cliente.getDireccion()+"','"+cliente.getLocalidad()+"','"+cliente.getProvincia()+"','"+cliente.getMail()+"','"+cliente.getTelefono()+"')";
			String query1= "Insert into cliente (Dni,Estado) values ('"+cliente.getDNI()+"','"+cliente.getEstado()+"')";
			String query2 = "Insert into usuario (Usuario,Dni,Contrasenia,EsAdmin) values ('"+usuario.getUsuario()+"','"+usuario.getDni()+"','"+usuario.getContrasenia()+"','0')";
			
			filas=st.executeUpdate(query);
			filas=st.executeUpdate(query1);
			filas=st.executeUpdate(query2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	public int obtenerUltimoNroCliente() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int ultimoNro = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(host + dbName, user, pass);
            String query = "SELECT MAX(NdeCliente) FROM cliente";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            if (rs.next()) {
            	ultimoNro = rs.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ultimoNro;
    }

	@Override
	public List<Cliente> ListarCliente(String usuario) {
		PreparedStatement statement;
		ResultSet resulSet;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM cliente=c inner join persona=p on p.Dni = c.Dni inner join usuario=u on p.Dni = u.Dni where u.Usuario=?");
			statement.setString(1, usuario);
			resulSet = statement.executeQuery();
			while(resulSet.next()) {
				Cliente cliente=new Cliente();
				cliente.setNdeCliente(resulSet.getInt("NdeCliente"));
				cliente.setDNI(resulSet.getString("p.Dni"));
				cliente.setCUIL(resulSet.getString("Cuil"));
				cliente.setApellido(resulSet.getString("Apellido"));
				cliente.setFechaDeNacimiento(resulSet.getDate("FechaDeNacimiento"));
				cliente.setNombre(resulSet.getString("Nombre"));
				cliente.setNacionalidad(resulSet.getString("Nacionalidad"));
				cliente.setDireccion(resulSet.getString("Direccion"));
				cliente.setLocalidad(resulSet.getString("Localidad"));
				cliente.setProvincia(resulSet.getString("Provincia"));
				cliente.setMail(resulSet.getString("Email"));
				cliente.setUsuario(resulSet.getString("Usuario"));
				cliente.setContrasenia(resulSet.getString("Contrasenia"));
				cliente.setTelefono(resulSet.getString("Telefono"));
				cliente.setSexo(resulSet.getString("Sexo"));
				cliente.setEstado(resulSet.getString("Estado"));
				clientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	@Override
	public int obtenerNdeCliente(String usuario) {
		
		PreparedStatement statement;
		ResultSet resulSet;
		int numcliente = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM cliente=c inner join usuario=u on u.usuario =?  where u.dni = c.dni;");
			statement.setString(1, usuario);
			resulSet = statement.executeQuery();
			while(resulSet.next()) {
				 numcliente = resulSet.getInt("NdeCliente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numcliente;
	
	}

	@Override
	public void generarSolicitud(Solicitud sol) {
			
		Conexion conexion = Conexion.getConexion();
	    java.sql.CallableStatement callableStatement = null;
	    
	    try {
	        callableStatement = conexion.getSQLConexion().prepareCall("{ call Solicitud(?, ?, ?, ?, ?, ?, ?) }");

	        callableStatement.setInt(1, sol.getNroDeCliente());
	        callableStatement.setInt(2, sol.getNroCuenta());
	        
	        callableStatement.setFloat(3, sol.getImporteSolicitado());
	        callableStatement.setFloat(4, sol.getImporteAPagar());
	        
	        callableStatement.setInt(5, sol.getPlazo());

	        callableStatement.setFloat(6, sol.getMonto());
	        callableStatement.setString(7, sol.getEstado());

	        
	        callableStatement.execute();
	         

	    } catch (SQLException e) {
	        e.printStackTrace();  
	    } finally {
	       
	        if (callableStatement != null) {
	            try {
	                callableStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace(); 
	            }
	        }

	       
	        if (conexion != null) {
	            conexion.cerrarConexion();
	        }
	    }
	    
	    }
	
	@Override
	public List<Solicitud> ListarSolicitudes(int ncliente) {
		PreparedStatement statement;
		ResultSet rs;
		ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM solicitud where NdeCliente=?");
			statement.setInt(1, ncliente);
			rs = statement.executeQuery();
			while(rs.next()) {
				Solicitud sol=new Solicitud();
				sol.setNdeSolicitud(rs.getInt("NdeSolicitud"));
				sol.setNroDeCliente(rs.getInt("NdeCliente"));
				sol.setNroCuenta(rs.getInt("NdeCuenta"));
				sol.setFecha(rs.getDate("Fecha"));
				sol.setImporteSolicitado(rs.getFloat("importeSolicitado"));
				sol.setImporteAPagar(rs.getFloat("ImporteAPagar"));
				sol.setPlazo(rs.getInt("Plazo"));
				sol.setMonto(rs.getFloat("Monto"));
				sol.setEstado(rs.getString("Estado"));
				solicitudes.add(sol);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return solicitudes;
	}
	
	@Override
	public List<Prestamo> ListarPrestamos(int ncliente) {
		PreparedStatement statement;
		ResultSet rs;
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM prestamo where NdeCliente=?");
			statement.setInt(1, ncliente);
			rs = statement.executeQuery();
			while(rs.next()) {
				Prestamo pre = new Prestamo();
				pre.setNdeSolicitud(rs.getInt("NdePrestamo"));
				pre.setNroDeCliente(rs.getInt("NdeCliente"));
				pre.setNroCuenta(rs.getInt("NdeCuenta"));
				pre.setFecha(rs.getDate("Fecha"));
				pre.setImporteSolicitado(rs.getFloat("importeSolicitado"));
				pre.setImporteAPagar(rs.getFloat("ImporteAPagar"));
				pre.setPlazo(rs.getInt("Plazo"));
				pre.setMonto(rs.getFloat("Monto"));
				pre.setEstado(rs.getString("Estado"));
				pre.setCuotasPagas(rs.getInt("CuotasPagas"));
				prestamos.add(pre);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prestamos;
	}
	
	
		
	
	
	
	@Override
	public void AprobarRechazarSolicitud(String estado, String nsolicitud) {
		PreparedStatement statement;
		
		int nsolicitudINT = Integer.parseInt(nsolicitud);
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try {
			statement = conexion.prepareStatement("UPDATE solicitud SET Estado=? WHERE NdeSolicitud=?");
			statement.setString(1, estado);
			statement.setInt(2, nsolicitudINT);
			
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
		
		
	} 
	
		

	    
	
	@Override
	public List<Solicitud> ListarTodasLasSolicitudes() {
		PreparedStatement statement;
		ResultSet rs;
		ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM solicitud");
			rs = statement.executeQuery();
			while(rs.next()) {
				Solicitud sol=new Solicitud();
				sol.setNdeSolicitud(rs.getInt("NdeSolicitud"));
				sol.setNroDeCliente(rs.getInt("NdeCliente"));
				sol.setNroCuenta(rs.getInt("NdeCuenta"));
				sol.setFecha(rs.getDate("Fecha"));
				sol.setImporteSolicitado(rs.getFloat("importeSolicitado"));
				sol.setImporteAPagar(rs.getFloat("ImporteAPagar"));
				sol.setPlazo(rs.getInt("Plazo"));
				sol.setMonto(rs.getFloat("Monto"));
				sol.setEstado(rs.getString("Estado"));
				solicitudes.add(sol);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return solicitudes;
	}
			 
		};



