package negocio;

import java.util.List;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Usuario;


public interface ClienteNegocio {
	public List<Cliente> ListarClientes();
	public List<Cliente> ListarCliente(int num);
	public boolean borrar(int nro);
	public int Agregar(Cliente cliente, Usuario usuario);
}
