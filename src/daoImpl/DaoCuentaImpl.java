package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoCuenta;
import entidad.Cuenta;

public class DaoCuentaImpl implements DaoCuenta {
	
	private static final String traerCuentas = "SELECT * FROM cuenta WHERE Estado=1";
	private static final String ModificarCuenta="UPDATE cuenta SET TipoDeCuenta=?, Cbu=?, Saldo=? WHERE NdeCuenta=?";
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "banco_db";
	
	@Override
	public List<Cuenta> ListarCuentas() {
		PreparedStatement statement;
		ResultSet resulSet;
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(traerCuentas);
			resulSet = statement.executeQuery();
			while(resulSet.next()) {
				Cuenta cuenta=new Cuenta();
				cuenta.setNroCuenta(resulSet.getInt("NdeCuenta"));
				cuenta.setNroDeCliente(resulSet.getInt("NdeCliente"));
				cuenta.setCbu(resulSet.getString("Cbu"));
				cuenta.setTipoDeCuenta(resulSet.getString("TipoDeCuenta"));
				cuenta.setFechaCreacion(resulSet.getDate("FechaDeCreacion"));
				cuenta.setSaldo(resulSet.getFloat("Saldo"));
				cuenta.setEstado(resulSet.getInt("Estado"));
				cuentas.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuentas;
		 
	}
	@Override
	public int ModificarCuenta(int i, String TipoCuenta, String cbu, float saldo) {
		
		PreparedStatement statement;
				Connection conexion = Conexion.getConexion().getSQLConexion();
				int isUpdateExitoso = 0;
				try
				{
					statement = conexion.prepareStatement(ModificarCuenta);
					statement.setString(1, TipoCuenta);
					statement.setString(2, cbu);
					statement.setFloat(3, saldo);
					statement.setInt(4, i);

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
	
	@Override
	public boolean borrar(int nro) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = con.prepareStatement("Update Cuenta Set Estado=? where NdeCuenta=?");
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
	

	public int Agregar(Cuenta cuenta)
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
			String query = "Insert into cuenta (NdeCliente,Cbu,TipoDeCuenta,FechaDeCreacion,Saldo,Estado) values ('"+cuenta.getNroDeCliente()+"','"+cuenta.getCbu()+"','"+cuenta.getTipoDeCuenta()+"','"+cuenta.getFechaCreacion()+"','"+cuenta.getSaldo()+"','"+cuenta.getEstado()+"')";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	public int obtenerUltimoNroCuenta() {
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
            String query = "SELECT MAX(NdeCuenta) FROM cuenta";
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
	public List<Cuenta> ListarCuenta(int num) {
		PreparedStatement statement;
		ResultSet resulSet;
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM cuenta WHERE NdeCliente=?");
			statement.setInt(1, num);
			resulSet = statement.executeQuery();
			while(resulSet.next()) {
				Cuenta cuenta=new Cuenta();
				cuenta.setNroCuenta(resulSet.getInt("NdeCuenta"));
				cuenta.setNroDeCliente(resulSet.getInt("NdeCliente"));
				cuenta.setCbu(resulSet.getString("Cbu"));
				cuenta.setTipoDeCuenta(resulSet.getString("TipoDeCuenta"));
				cuenta.setFechaCreacion(resulSet.getDate("FechaDeCreacion"));
				cuenta.setSaldo(resulSet.getFloat("Saldo"));
				cuenta.setEstado(resulSet.getInt("Estado"));
				cuentas.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuentas;
	}
	
	public ArrayList<Cuenta> ListarCuentasPorUsuario(String user) {
		PreparedStatement statement;
		ResultSet resulSet;
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM usuario JOIN cliente ON usuario.Dni = cliente.Dni JOIN cuenta ON cliente.NdeCliente = cuenta.NdeCliente WHERE Usuario=?");
			statement.setString(1, user);
			resulSet = statement.executeQuery();
			while(resulSet.next()) {
				Cuenta cuenta=new Cuenta();
				cuenta.setNroCuenta(resulSet.getInt("NdeCuenta"));
				cuenta.setNroDeCliente(resulSet.getInt("NdeCliente"));
				cuenta.setCbu(resulSet.getString("Cbu"));
				cuenta.setTipoDeCuenta(resulSet.getString("TipoDeCuenta"));
				cuenta.setFechaCreacion(resulSet.getDate("FechaDeCreacion"));
				cuenta.setSaldo(resulSet.getFloat("Saldo"));
				cuenta.setEstado(resulSet.getInt("Estado"));
				cuentas.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuentas;
		 
	}
	
	public boolean ExisteCbu(String cbu) {
		PreparedStatement statement;
		ResultSet resulSet;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM cuenta WHERE Cbu=?");
			statement.setString(1, cbu);
			resulSet = statement.executeQuery();
			if(resulSet.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	@Override
	public boolean PuedeTransferir(Float monto, String nro) {
		
		if(monto<=0) {return false;};
		
		PreparedStatement statement;
		ResultSet resulSet;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM cuenta WHERE NdeCuenta=?");
			statement.setInt(1, Integer.parseInt(nro));
			resulSet = statement.executeQuery();
			if(resulSet.next()) {
				if(resulSet.getFloat("Saldo")>=monto) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
		
}
		