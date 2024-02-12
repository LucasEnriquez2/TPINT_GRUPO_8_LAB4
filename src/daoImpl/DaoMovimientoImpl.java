package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;

import dao.DaoMovimiento;
import entidad.Movimiento;

public class DaoMovimientoImpl implements DaoMovimiento{

	@Override
	public List<Movimiento> ListarMovimientosParametrizados(int min, int max) {
		
		double dmin = (double) min;
		double dmax = (double) max;
		PreparedStatement statement;
		ResultSet resulSet;
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM movimiento WHERE importe >= ? AND importe <= ?");
			statement.setDouble(1, dmin);
	        statement.setDouble(2, dmax);
			resulSet = statement.executeQuery();
			while(resulSet.next()) {
				Movimiento mov = new Movimiento();
				mov.setIdMovimiento(resulSet.getInt("IdMovimiento"));
				mov.setNdeCuenta(resulSet.getInt("NdeCuenta"));
				mov.setTipoDeMovimiento(resulSet.getString("TipoDeMovimiento"));
				mov.setFecha(resulSet.getDate("Fecha"));
				mov.setDetalle(resulSet.getString("Detalle"));
				mov.setImporte(resulSet.getFloat("Importe"));
				
				movimientos.add(mov);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movimientos;
	}

	@Override
	public List<Movimiento> ListarMovimientos(int nro) {
		
		PreparedStatement statement;
		ResultSet resulSet;
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("select m.IdMovimiento,m.NdeCuenta, m.TipoDeMovimiento, m.Fecha, m.Detalle, m.Importe from movimiento=m inner join cuenta=c on c.NdeCuenta = m.NdeCuenta inner join cliente=cl on cl.NdeCliente = c.NdeCliente where c.NdeCliente=?");
			statement.setInt(1, nro);
			resulSet = statement.executeQuery();
			while(resulSet.next()) {
				Movimiento mov = new Movimiento();
				mov.setIdMovimiento(resulSet.getInt("IdMovimiento"));
				mov.setNdeCuenta(resulSet.getInt("NdeCuenta"));
				mov.setTipoDeMovimiento(resulSet.getString("TipoDeMovimiento"));
				mov.setFecha(resulSet.getDate("Fecha"));
				mov.setDetalle(resulSet.getString("Detalle"));
				mov.setImporte(resulSet.getFloat("Importe"));
				
				movimientos.add(mov);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movimientos;
	}
	
	@Override
	public void CrearMovimiento(String nro, String cbu, String monto, String detalle) {
	    Conexion conexion = Conexion.getConexion();
	    java.sql.CallableStatement callableStatement = null;
	    
	    try {
	        callableStatement = conexion.getSQLConexion().prepareCall("{ call Transferencia(?, ?, ?, ?) }");

	        callableStatement.setInt(1, Integer.parseInt(nro));
	        callableStatement.setString(2, cbu);
	        callableStatement.setFloat(3, Float.parseFloat(monto));
	        callableStatement.setString(4, detalle);

	        
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
	
}	
        
        

	


