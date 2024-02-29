package negocioImpl;

import java.util.List;

import daoImpl.DaoMovimientoImpl;
import entidad.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {

	@Override
	public List<Movimiento> ListarMovimientoParametrizados(int min, int max) {
		DaoMovimientoImpl daomov = new DaoMovimientoImpl();
		return daomov.ListarMovimientosParametrizados(min, max);
	}

	@Override
	public List<Movimiento> ListarMovimientos(int nro) {
		DaoMovimientoImpl daomovi = new DaoMovimientoImpl();
		return daomovi.ListarMovimientos(nro);
	}

	@Override
	public void CrearMovimiento(String nro, String cbu, String monto, String detalle) {
		DaoMovimientoImpl dao = new DaoMovimientoImpl();
		dao.CrearMovimiento(nro, cbu, monto, detalle);	
		
	}

	@Override
	public void PagarPrestamo(String nro, String nroP, String monto, String detalle, String cuotas) {
		DaoMovimientoImpl dao = new DaoMovimientoImpl();
		dao.PagarPrestamo(nro, nroP, monto, detalle,cuotas);	
	}
}
