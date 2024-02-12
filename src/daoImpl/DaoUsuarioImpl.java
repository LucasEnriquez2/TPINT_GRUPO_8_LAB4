package daoImpl;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


import dao.DaoUsuario;

public class DaoUsuarioImpl implements DaoUsuario{
	
	

	public int validarUsuario(String user, String pw) {
				PreparedStatement st;
				ResultSet rs;
				Conexion cn = Conexion.getConexion();
				int existe=0;
				
				try {
				
					st = cn.getSQLConexion().prepareStatement("SELECT * FROM usuario WHERE Usuario = ? AND Contrasenia = ?");
					st.setString(1, user);
			        st.setString(2, pw);
			        
			        rs = st.executeQuery();
			        if(rs.next()) {
			        	existe = 1;
			        	if(rs.getBoolean(4)==true) {
			        		existe = 2;
			        	};
			        }
			        
			    
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return existe;
		        
	}
	
	

}