package dao;

import java.util.List;

import entidad.Movimiento;

public interface DaoMovimiento {
	public List<Movimiento> ListarMovimientosParametrizados(int min, int max);
	public List<Movimiento> ListarMovimientos(int nro);
	public void CrearMovimiento(String nro, String cbu, String monto, String detalle);
}
