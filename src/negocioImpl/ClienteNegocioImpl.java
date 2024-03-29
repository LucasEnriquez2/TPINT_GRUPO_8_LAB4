package negocioImpl;

import java.util.List;

import dao.DaoCliente;
import daoImpl.DaoClienteImpl;
import daoImpl.DaoCuentaImpl;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import entidad.Solicitud;
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
	public int borrar(int id) {
		
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
	public List<Cliente> ListarCliente(String usuario) {
		DaoClienteImpl listar=new DaoClienteImpl();
		return listar.ListarCliente(usuario);
	}
	
	@Override
	public int ObtenerNdeCliente(String usuario) {
		DaoClienteImpl dao=new DaoClienteImpl();
		return dao.obtenerNdeCliente(usuario);
	}

	@Override
	public void generarSolicitud(Solicitud sol) {
		DaoClienteImpl dao= new DaoClienteImpl();
		dao.generarSolicitud(sol);
		
	}
	
	@Override
	public List<Solicitud> ListarSolicitudes(int ncliente){
		DaoClienteImpl dao= new DaoClienteImpl();
		return dao.ListarSolicitudes(ncliente);
	}
	
	@Override
	public List<Prestamo> ListarPrestamos(int ncliente){
		DaoClienteImpl dao= new DaoClienteImpl();
		return dao.ListarPrestamos(ncliente);
	}
	
	
	@Override
	public List<Solicitud> ListarTodasLasSolicitudes(){
		DaoClienteImpl dao= new DaoClienteImpl();
		return dao.ListarTodasLasSolicitudes();
	}
	
	
	
	@Override
	public void AprobarRechazarSolicitud(String estado, String nsolicitud) {
		DaoClienteImpl dao= new DaoClienteImpl();
		dao.AprobarRechazarSolicitud(estado, nsolicitud);
	}
}

