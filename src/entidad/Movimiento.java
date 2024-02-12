package entidad;

import java.sql.Date;

public class Movimiento {
	

	private int IdMovimiento;
	private int NdeCuenta;
	private String TipoDeMovimiento;
	private Date Fecha;
	private String Detalle;
	private float Importe;
	
	public Movimiento() {};
	
	
	public Movimiento(int IdMovimiento,int NdeCuenta, String TipoDeMovimiento, Date Fecha, String Detalle, float Importe) {
		this.IdMovimiento = IdMovimiento;
		this.NdeCuenta = NdeCuenta;
		this.TipoDeMovimiento = TipoDeMovimiento;
		this.Fecha = Fecha;
		this.Detalle = Detalle;
		this.Importe = Importe;
	};
	
	public int getIdMovimiento() {
		return IdMovimiento;
	}


	public void setIdMovimiento(int idMovimiento) {
		IdMovimiento = idMovimiento;
	}


	public int getNdeCuenta() {
		return NdeCuenta;
	}


	public void setNdeCuenta(int ndeCuenta) {
		NdeCuenta = ndeCuenta;
	}


	public String getTipoDeMovimiento() {
		return TipoDeMovimiento;
	}


	public void setTipoDeMovimiento(String tipoDeMovimiento) {
		TipoDeMovimiento = tipoDeMovimiento;
	}


	public Date getFecha() {
		return Fecha;
	}


	public void setFecha(Date fecha) {
		Fecha = fecha;
	}


	public String getDetalle() {
		return Detalle;
	}


	public void setDetalle(String detalle) {
		Detalle = detalle;
	}


	public float getImporte() {
		return Importe;
	}


	public void setImporte(float importe) {
		Importe = importe;
	}

}
