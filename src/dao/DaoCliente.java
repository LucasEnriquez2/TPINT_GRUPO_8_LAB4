package dao;

import java.util.List;


import entidad.Cliente;
import entidad.Usuario;


public interface DaoCliente {
	public List<Cliente> ListarClientes();
	public List<Cliente> ListarCliente(int num);
	public boolean borrar(int nro);
	public int Agregar(Cliente cliente, Usuario usuario);
	public int obtenerUltimoNroCliente();
}
