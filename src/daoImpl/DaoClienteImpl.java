package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoCliente;
import entidad.Cliente;
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
	public boolean borrar(int nro) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Cliente Set Estado=? where NdeCliente=?");
			statement.setBoolean(1, false);
			statement.setInt(2, nro);
			
			if(statement.executeUpdate() > 0) {
				con.commit();
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return false;
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
	public List<Cliente> ListarCliente(int num) {
		PreparedStatement statement;
		ResultSet resulSet;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM cliente=c inner join persona=p on p.Dni = c.Dni inner join usuario=u on p.Dni = u.Dni where c.NdeCliente=?");
			statement.setInt(1, num);
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
	
	};	
}

