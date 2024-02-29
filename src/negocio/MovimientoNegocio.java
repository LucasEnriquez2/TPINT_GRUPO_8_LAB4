package negocio;

import java.util.List;

import entidad.Movimiento;

public interface MovimientoNegocio {
	public List<Movimiento> ListarMovimientoParametrizados(int min, int max);
	public List<Movimiento> ListarMovimientos(int nro);
	public void CrearMovimiento(String nro, String cbu, String monto, String detalle);
	void PagarPrestamo(String nro, String nroP, String monto, String detalle, String cuotas);
}
