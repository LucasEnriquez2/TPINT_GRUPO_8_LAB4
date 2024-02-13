package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import dao.DaoCliente;
import dao.DaoCuenta;
import daoImpl.DaoClienteImpl;
import daoImpl.DaoCuentaImpl;
import entidad.Cuenta;
import negocio.CuentaNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {
	
	private DaoCuenta cuenta = new DaoCuentaImpl();
	
	@Override
	public List<Cuenta> ListarCuentas() {
		DaoCuentaImpl listar=new DaoCuentaImpl();
		return listar.ListarCuentas();
	}
	@Override
	public int ModificarCuenta(int NroCuenta, String TipoCuenta, String cbu, float saldo) {
		DaoCuentaImpl Modificar=new DaoCuentaImpl();
		return Modificar.ModificarCuenta(NroCuenta, TipoCuenta, cbu, saldo);
	}
	
	@Override
	public boolean borrar(int nro) {
		
		return cuenta.borrar(nro);
	}
	
	@Override
	public int Agregar(Cuenta cuenta_1) {
		
		return cuenta.Agregar(cuenta_1);
	}
	@Override
	public List<Cuenta> ListarCuenta(int num) {
		DaoCuentaImpl listar=new DaoCuentaImpl();
		return listar.ListarCuenta(num);
	}
	
	@Override
	public ArrayList<Cuenta> ListarCuentaPorUsuario(String user){
		DaoCuentaImpl dao=new DaoCuentaImpl();
		return dao.ListarCuentasPorUsuario(user);
	}
	@Override
	public boolean ExisteCbu(String cbu) {
		DaoCuentaImpl dao=new DaoCuentaImpl();
		return dao.ExisteCbu(cbu);
	}
	@Override
	public boolean PuedeTransferir(Float monto, String nro) {
		DaoCuentaImpl dao=new DaoCuentaImpl();
		return dao.PuedeTransferir(monto, nro);
		
	}
	
	
}
