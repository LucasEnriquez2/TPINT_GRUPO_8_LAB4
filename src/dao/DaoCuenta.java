package dao;

import java.util.List;

import entidad.Cuenta;

public interface DaoCuenta {
	public List<Cuenta> ListarCuentas();
	public List<Cuenta> ListarCuenta(int num);
	public List<Cuenta> ListarCuentasPorUsuario(String user);
	int ModificarCuenta(int NroCuenta, String TipoCuenta, String cbu, float saldo);
	public boolean borrar(int nro);
	public int Agregar(Cuenta cuenta);
	public int obtenerUltimoNroCuenta();
	public boolean ExisteCbu(String cbu);
	public boolean PuedeTransferir(Float monto, String nro);
}

