package negocioImpl;

import java.util.List;

import dao.DaoCliente;
import daoImpl.DaoClienteImpl;
import daoImpl.DaoCuentaImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Usuario;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio{
	
	private DaoCliente cli = new DaoClienteImpl();
	
	@Override
	public List<Cliente> ListarClientes() {
		DaoClienteImpl listar=new DaoClienteImpl();
		return listar.ListarClientes();
	}
	
	@Override
	public boolean borrar(int id) {
		
		return cli.borrar(id);
	}
	
	public int ModificarCliente(Cliente c) {
		DaoClienteImpl Modificar=new DaoClienteImpl();
		return Modificar.ModificarCliente(c);
	}
	
	@Override
	public int Agregar(Cliente cliente, Usuario usuario) {
		
		return cli.Agregar(cliente,usuario);
	}

	@Override
	public List<Cliente> ListarCliente(int num) {
		DaoClienteImpl listar=new DaoClienteImpl();
		return listar.ListarCliente(num);
	}
	
	@Override
	public int ObtenerNdeCliente(String usuario) {
		DaoClienteImpl dao=new DaoClienteImpl();
		return dao.obtenerNdeCliente(usuario);
	}
}

