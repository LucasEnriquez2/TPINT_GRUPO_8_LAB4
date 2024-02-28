package dao;

import java.util.List;


import entidad.Cliente;
import entidad.Solicitud;
import entidad.Usuario;


public interface DaoCliente {
	public List<Cliente> ListarClientes();
	public List<Cliente> ListarCliente(String usuario);
	public boolean borrar(int nro);
	public int Agregar(Cliente cliente, Usuario usuario);
	public int obtenerUltimoNroCliente();
	public int obtenerNdeCliente(String usuario);
	public void generarSolicitud(Solicitud sol);
	public List<Solicitud> ListarSolicitudes(int ncliente);
	
}
