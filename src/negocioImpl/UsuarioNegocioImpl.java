package negocioImpl;


import daoImpl.DaoUsuarioImpl;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {

	@Override
	public int validarUsuario(String us, String pw) {
		DaoUsuarioImpl dao= new DaoUsuarioImpl();
		return dao.validarUsuario(us, pw);
	}

}
