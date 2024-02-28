package negocio;

import java.util.List;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Solicitud;
import entidad.Usuario;


public interface ClienteNegocio {
	public List<Cliente> ListarClientes();
	public List<Cliente> ListarCliente(String usuario);
	public int borrar(int nro);
	public int Agregar(Cliente cliente, Usuario usuario);
	public int ObtenerNdeCliente(String usuario);
	public void generarSolicitud(Solicitud sol);
	public List<Solicitud> ListarSolicitudes(int ncliente);
}
