package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.Cuenta;

public interface CuentaNegocio {
	public List<Cuenta> ListarCuentas();
	public List<Cuenta> ListarCuenta(int num);
	public ArrayList<Cuenta> ListarCuentaPorUsuario(String user);
	int ModificarCuenta(int NroCuenta, String TipoCuenta, String cbu, float saldo);
	public boolean borrar(int nro);
	public int Agregar(Cuenta cuenta);
	public boolean ExisteCbu(String cbu);
	public boolean PuedeTransferir(Float monto, String nro);
}
